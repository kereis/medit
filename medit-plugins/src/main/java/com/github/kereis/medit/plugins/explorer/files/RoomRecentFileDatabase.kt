package com.github.kereis.medit.plugins.explorer.files

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kereis.medit.domain.explorer.files.File

@Database(entities = [File::class], version = 1)
abstract class RoomRecentFileDatabase : RoomDatabase() {
}