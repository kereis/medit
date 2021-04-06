package com.github.kereis.medit.domain.explorer.files

import java.time.OffsetDateTime

data class File(

    val id: Int,
    val fileName: String,
    val fileExtension: String?,
    val filePath: String,
    val lastAccess: OffsetDateTime,
)
