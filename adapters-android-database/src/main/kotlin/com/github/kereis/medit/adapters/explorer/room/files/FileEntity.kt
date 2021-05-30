package com.github.kereis.medit.adapters.explorer.room.files

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "recent_files")
data class FileEntity(

    @PrimaryKey
    val id: Int,
    val fileName: String,
    val filePath: String,
    val lastAccess: OffsetDateTime,
)

fun getSampleFileEntityData(): Array<FileEntity> {
    return arrayOf(
        FileEntity(1, "MyTestFile", "/hi/my/home", OffsetDateTime.now()),
        FileEntity(2, "MyTestFile2", "/hi/my2/home", OffsetDateTime.now()),
        FileEntity(3, "MyTestFile3", "/hi/my123/home", OffsetDateTime.now()),
        FileEntity(4, "MyTestFile4", "/hi/my/home", OffsetDateTime.now()),
    )
}
