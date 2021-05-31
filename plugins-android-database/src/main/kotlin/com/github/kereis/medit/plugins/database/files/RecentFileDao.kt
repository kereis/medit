package com.github.kereis.medit.plugins.database.files

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import java.net.URI

@Dao
interface RecentFileDao {
    @Query("SELECT * FROM recent_files")
    fun getAll(): List<FileEntity>

    @Query("SELECT * FROM recent_files WHERE filePath = :uri")
    fun getByURI(uri: URI): FileEntity?

    @Query(
        "SELECT * FROM recent_files " +
            "WHERE lastAccess > (" +
            "SELECT DATETIME('now', '-' + :period + ' second')" +
            ")"
    )
    fun getLastRecentlyUsedFiles(period: Int): List<FileEntity>

    @Update
    suspend fun update(vararg files: FileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg newFiles: FileEntity): List<Long>

    @Delete
    suspend fun delete(file: FileEntity)
}
