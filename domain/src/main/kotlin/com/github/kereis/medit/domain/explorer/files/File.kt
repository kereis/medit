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

        // @JvmStatic
        // @Throws(IllegalArgumentException::class)
        // @Deprecated("Remove")
        // fun createByURI(uri: URI): File {
        //     val file = java.io.File(uri)
        //
        //     return File(
        //         id = null,
        //         fileName = file.name,
        //         filePath = file.toPath(),
        //         lastAccess = OffsetDateTime.now()
        //     )
        // }
    }
}
