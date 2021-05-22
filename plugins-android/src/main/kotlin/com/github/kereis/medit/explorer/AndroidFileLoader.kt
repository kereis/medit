package com.github.kereis.medit.explorer

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.editor.History
import com.github.kereis.medit.domain.explorer.files.AbstractFileLoader
import com.github.kereis.medit.domain.explorer.files.File
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.time.OffsetDateTime
import java.io.File as JavaIoFile

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

    override suspend fun load(file: JavaIoFile): Document? = withContext(coroutineContext) {
        val inputStream =
            context.contentResolver.openInputStream(file.toUri()) ?: return@withContext null

        val fileContent = inputStream.use { stream ->
            stream.bufferedReader().use { reader ->
                reader.lineSequence().toMutableList()
            }
        }

        val documentFile = File(
            id = null,
            fileName = file.nameWithoutExtension,
            fileExtension = file.extension,
            filePath = file.toPath(),
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
            val uri = Uri.fromFile(document.file.filePath.toFile())
            val inputStream =
                context.contentResolver.openOutputStream(uri) ?: return@withContext false

            inputStream.use { stream ->
                stream.bufferedWriter().use { writer ->
                    document.content.asSequence().forEach { writer.append(it).appendLine() }
                }
            }

            true
        }
}