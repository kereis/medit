package com.github.kereis.medit.explorer

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.editor.History
import com.github.kereis.medit.domain.explorer.files.AbstractFileLoader
import com.github.kereis.medit.domain.explorer.files.File
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.FileOutputStream
import java.net.URI
import java.time.OffsetDateTime

/**
 * # AndroidFileLoader
 *
 * Allows managing files in Android file system
 *
 * @author kereis
 * @version 1.0.0
 */
class AndroidFileLoader(
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
) : AbstractFileLoader() {

    private val coroutineContext = Job() + dispatcher

    override suspend fun load(uri: URI): Document? = withContext(coroutineContext) {
        val androidUri = Uri.parse(uri.toString())
        val inputStream =
            context.contentResolver.openInputStream(androidUri) ?: return@withContext null

        val fileContent = inputStream.use { stream ->
            stream.bufferedReader().use { reader ->
                reader.lineSequence().toMutableList()
            }
        }

        Timber.d(fileContent.toString())

        var fileName = ""
        context.contentResolver.query(
            androidUri,
            null,
            null,
            null,
            null
        )?.apply {
            val nameIndex = getColumnIndex(OpenableColumns.DISPLAY_NAME)
            moveToFirst()
            fileName = getString(nameIndex)
        }

        val documentFile = File(
            id = null,
            fileName = fileName,
            filePath = uri,
            lastAccess = OffsetDateTime.now(),
        )

        return@withContext Document(
            title = "",
            content = fileContent,
            file = documentFile,
            History()
        )
    }

    override suspend fun save(document: Document): Boolean =
        withContext(coroutineContext) {
            try {
                context.contentResolver.openFileDescriptor(
                    Uri.parse(document.file.filePath.toString()),
                    "w"
                )?.use { descriptor ->
                    FileOutputStream(descriptor.fileDescriptor).use { outputStream ->
                        outputStream.bufferedWriter().use { writer ->
                            document.content.asSequence().forEach { writer.append(it).appendLine() }
                        }
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                throw e
            }

            true
        }
}