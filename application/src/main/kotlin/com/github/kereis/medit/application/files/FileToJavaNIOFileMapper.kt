package com.github.kereis.medit.application.files

import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.mapping.DataMapper
import java.time.OffsetDateTime

class FileToJavaNIOFileMapper : DataMapper<File, java.io.File> {

    override fun to(source: File): java.io.File {
        return source.filePath.toFile()
    }

    override fun from(target: java.io.File): File {
        return File(
            id = null,
            fileName = target.nameWithoutExtension,
            filePath = target.toPath(),
            fileExtension = target.extension,
            lastAccess = OffsetDateTime.now(), // TODO FIX!!!
        )
    }
}