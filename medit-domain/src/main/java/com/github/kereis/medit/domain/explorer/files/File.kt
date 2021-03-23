package com.github.kereis.medit.domain.explorer.files

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class File(
    @PrimaryKey                             val uid: Int,
    @ColumnInfo(name = "file_name")         val fileName: String,
    @ColumnInfo(name = "file_extension")    val fileExtension: String?,
    @ColumnInfo(name = "file_path")         val filePath: String,
    @ColumnInfo(name = "last_access_date")  val lastAccess: Timestamp,
)