package me.humenius.medit.explorer.recentfiles

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [File::class], version = 1)
abstract class RecentFileDatabase : RoomDatabase() {
    abstract fun dao(): RecentFilesDao
}