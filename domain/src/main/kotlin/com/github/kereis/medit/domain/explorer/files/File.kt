package com.github.kereis.medit.domain.explorer.files

import java.nio.file.Path
import java.time.OffsetDateTime

data class File(
    val id: Int?,
    val fileName: String,
    val fileExtension: String?,
    val filePath: Path,
    var lastAccess: OffsetDateTime,
) {

    companion object {
        @JvmStatic
        fun updateAccessTime(file: File): File {
            return File(
                file.id,
                file.fileName,
                file.fileExtension,
                file.filePath,
                OffsetDateTime.now()
            )
        }
    }
}
