package com.github.kereis.medit.explorer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.explorer.files.FileLoader
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.net.URI
import java.time.OffsetDateTime
import java.util.stream.Collectors

/**
 * # AndroidFileLoader
 *
 * Allows managing files in Android file system
 *
 * @author kereis
 * @version 1.0.0
 */
class AndroidFileLoader(
    dispatcher: CoroutineDispatcher,
    private val recentFileRepository: RecentFileRepository,
    private val context: Context
) : FileLoader() {

    private val coroutineContext = Job() + dispatcher

    @Suppress("BlockingMethodInNonBlockingContext") // We use Dispatchers.IO by DI
    @Throws(FileNotFoundException::class)
    override suspend fun load(uri: URI): Document? = withContext(coroutineContext) {
        val androidUri = Uri.parse(uri.toString())
        val inputStream =
            context.contentResolver.openInputStream(androidUri) ?: return@withContext null

        val fileContent = inputStream.use { stream ->
            stream.bufferedReader().use { reader ->
                reader.lines().collect(Collectors.toList())
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
            close()
        }

        val documentFile = FileReference(
            id = null,
            fileName = fileName,
            rawFilePath = uri.toString(),
            lastAccess = OffsetDateTime.now(),
        )

        return@withContext Document(
            title = "",
            content = fileContent.joinToString("\n"),
            fileReference = documentFile
        )
    }

    @Suppress("BlockingMethodInNonBlockingContext") // We use Dispatchers.IO by DI
    @Throws(FileNotFoundException::class)
    override suspend fun save(document: Document): Boolean =
        withContext(coroutineContext) {
            val androidUri: Uri
            try {
                androidUri = Uri.parse(document.fileReference.rawFilePath)
                Timber.d("AndroidFileLoader.save -> $androidUri from ${document.fileReference}")
                context.contentResolver.openFileDescriptor(
                    androidUri,
                    "w"
                )?.use { descriptor ->
                    FileOutputStream(descriptor.fileDescriptor).use { outputStream ->
                        outputStream.bufferedWriter().use { writer ->
                            document.content.lines().forEach { writer.append(it).appendLine() }
                        }
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                throw e
            }

            // We need permission by Android to access the file without using Intents
            context.contentResolver.takePersistableUriPermission(
                androidUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            true
        }
}
