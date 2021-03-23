package me.humenius.medit.explorer.recentfiles

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecentFilesDao {
    @Query("SELECT * FROM file")
    fun getAll(): List<File>

    @Query("SELECT * FROM file WHERE last_access_date > (SELECT DATETIME('now', '-' + :period + ' second'))")
    fun getLastRecentlyUsedFiles(period: Int): List<File>

    @Insert
    fun insert(vararg newFiles: File)

    @Delete
    fun delete(file: File)
}