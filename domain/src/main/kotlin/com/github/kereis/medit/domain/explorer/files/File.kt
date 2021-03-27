package com.kereis.github.medit.domain.explorer.files

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class File(

    @PrimaryKey
    val uid: Int,
    val fileName: String,
    val fileExtension: String?,
    val filePath: String,
    val lastAccess: Timestamp,
)
