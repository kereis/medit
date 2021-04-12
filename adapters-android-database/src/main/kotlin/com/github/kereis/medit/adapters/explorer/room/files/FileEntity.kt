package com.github.kereis.medit.adapters.explorer.room.files

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.kereis.medit.domain.explorer.files.File
import java.nio.file.Paths
import java.time.OffsetDateTime

@Entity(tableName = "recent_files")
data class FileEntity(

    @PrimaryKey
    val id: Int,
    val fileName: String,
    val fileExtension: String?,
    val filePath: String,
    val lastAccess: OffsetDateTime,
)

fun FileEntity.asDomainFile(): File =
    File(id, fileName, fileExtension, Paths.get(filePath), lastAccess)

fun List<FileEntity>.asDomainFileList(): List<File> = map {
    File(it.id, it.fileName, it.fileExtension, Paths.get(it.filePath), it.lastAccess)
}

fun File.asFileEntity(): FileEntity =
    FileEntity(id!!, fileName, fileExtension, filePath.toString(), lastAccess)

fun List<File>.asFileEntityList(): List<FileEntity> = map {
    FileEntity(it.id!!, it.fileName, it.fileExtension, it.filePath.toString(), it.lastAccess)
}
fun Array<out File>.asFileEntityArray(): Array<out FileEntity> = map {
    FileEntity(it.id!!, it.fileName, it.fileExtension, it.filePath.toString(), it.lastAccess)
}.toTypedArray()

fun getSampleFileEntityData(): Array<FileEntity> {
    return arrayOf(
        FileEntity(1, "MyTestFile", ".md", "/hi/my/home", OffsetDateTime.now()),
        FileEntity(2, "MyTestFile2", ".md", "/hi/my2/home", OffsetDateTime.now()),
        FileEntity(3, "MyTestFile3", ".md", "/hi/my123/home", OffsetDateTime.now()),
        FileEntity(4, "MyTestFile4", ".md", "/hi/my/home", OffsetDateTime.now()),
    )
}
