package com.github.kereis.medit.domain.explorer.files

import java.io.Serializable
import java.net.URI
import java.net.URISyntaxException
import java.time.OffsetDateTime

data class FileReference(
    val id: Long?,
    val fileName: String,
    val rawFilePath: String,
    var lastAccess: OffsetDateTime
) : Serializable {

    val filePath: URI

    // Constructor body
    // This should properly show that exceptions can occur
    init {
        try {
            filePath = URI(rawFilePath)
        } catch (se: URISyntaxException) {
            throw se
        }
    }

    override fun toString() =
        "File(id=$id, fileName='$fileName', filePath=$rawFilePath, lastAccess=$lastAccess)"
}
