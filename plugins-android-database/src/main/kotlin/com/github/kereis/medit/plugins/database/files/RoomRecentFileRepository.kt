package com.github.kereis.medit.plugins.database.files

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.kereis.medit.adapters.explorer.room.files.FileEntity

@Dao
interface RoomRecentFileRepository {
    @Query("SELECT * FROM recent_files")
    fun getAll(): List<FileEntity>

    @Query(
        "SELECT * FROM recent_files " +
            "WHERE lastAccess > (" +
            "SELECT DATETIME('now', '-' + :period + ' second')" +
            ")"
    )
    fun getLastRecentlyUsedFiles(period: Int): List<FileEntity>

    @Update
    fun update(vararg files: FileEntity)

    @Insert
    fun insert(vararg newFiles: FileEntity)

    @Delete
    fun delete(file: FileEntity)
}
