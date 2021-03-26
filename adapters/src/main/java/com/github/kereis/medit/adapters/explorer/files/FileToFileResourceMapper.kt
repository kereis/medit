package com.github.kereis.medit.adapters.explorer.files

import com.github.kereis.medit.domain.explorer.files.File
import java.util.function.Function

class FileToFileResourceMapper : Function<File, FileResource> {

    override fun apply(file: File): FileResource {
        return FileResource(
            file.uid,
            file.fileName,
            file.fileExtension,
            file.filePath,
            file.lastAccess
        )
    }
}
