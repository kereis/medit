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
        FileEntity(1, "MyTestFile.md", "/hi/my/home", OffsetDateTime.now()),
        FileEntity(2, "MyTestFile2.md", "/hi/my2/home", OffsetDateTime.now()),
        FileEntity(3, "MyTestFile3.md", "/hi/my123/home", OffsetDateTime.now()),
        FileEntity(4, "MyTestFile4.md", "/hi/my/home", OffsetDateTime.now()),
    )
}
