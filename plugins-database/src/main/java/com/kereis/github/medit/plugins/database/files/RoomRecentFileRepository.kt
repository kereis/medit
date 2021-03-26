package com.kereis.github.medit.plugins.database.files

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository

@Dao
interface RoomRecentFileRepository : RecentFileRepository {
    @Query("SELECT * FROM file")
    override fun getAll(): List<File>

    @Query(
        "SELECT * FROM file " +
            "WHERE lastAccess > (" +
            "SELECT DATETIME('now', '-' + :period + ' second')" +
            ")"
    )
    override fun getLastRecentlyUsedFiles(period: Int): List<File>

    @Update
    override fun update(vararg files: File)

    @Insert
    override fun insert(vararg newFiles: File)

    @Delete
    override fun delete(file: File)
}
