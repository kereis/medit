package com.github.kereis.medit.domain.explorer.files

import java.sql.Timestamp

data class File(

    val fileName: String,
    val fileExtension: String?,
    val filePath: String,
    val lastAccess: Timestamp,
)
