package com.github.kereis.medit.adapters.explorer.room.files

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class FileEntity(

    @PrimaryKey
    val id: Int,
    val fileName: String,
    val fileExtension: String?,
    val filePath: String,
    val lastAccess: Timestamp,
)
