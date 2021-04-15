package com.github.kereis.medit.adapter.android

import androidx.documentfile.provider.DocumentFile
import com.github.kereis.medit.domain.mapping.DataMapper
import java.io.File

/**
 * # DocumentFileToJavaFileMapper
 *
 * Bidrectional mapper for Android's [DocumentFile] and Java's IO [File]
 *
 * @version 1.0.0
 * @author kereis
 */
class DocumentFileToJavaFileMapper : DataMapper<DocumentFile, File> {

    override fun to(source: DocumentFile): File {
        source.uri.path?.let { sourcePath ->
            return File(sourcePath)
        }

        throw IllegalArgumentException("source element's path is null")
    }

    override fun from(target: File): DocumentFile {
        return DocumentFile.fromFile(target)
    }
}