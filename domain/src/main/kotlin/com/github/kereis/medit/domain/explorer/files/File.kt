package com.github.kereis.medit.domain.explorer.files

import java.io.Serializable
import java.net.URI
import java.time.OffsetDateTime

data class File(
    val id: Int?,
    val fileName: String,
    val filePath: URI,
    var lastAccess: OffsetDateTime,
) : Serializable {

    override fun toString() =
        "File(id=$id, fileName='$fileName', filePath=$filePath, lastAccess=$lastAccess)"

    companion object {

        @JvmStatic
        fun updateAccessTime(file: File): File {
            return File(
                file.id,
                file.fileName,
                file.filePath,
                OffsetDateTime.now()
            )
        }
    }
}
