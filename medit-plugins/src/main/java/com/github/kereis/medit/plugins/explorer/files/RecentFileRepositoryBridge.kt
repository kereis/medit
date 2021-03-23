package com.github.kereis.medit.plugins.explorer.files

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository

@Dao
interface RecentFileRepositoryBridge : RecentFileRepository {
    @Query("SELECT * FROM file")
    override fun getAll(): List<File>

    @Query("SELECT * FROM file WHERE last_access_date > (SELECT DATETIME('now', '-' + :period + ' second'))")
    override fun getLastRecentlyUsedFiles(period: Int): List<File>

    @Insert
    override fun insert(vararg newFiles: File)

    @Delete
    override fun delete(file: File)
}