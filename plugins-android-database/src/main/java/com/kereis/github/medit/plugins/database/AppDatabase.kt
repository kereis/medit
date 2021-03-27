package com.kereis.github.medit.plugins.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.kereis.medit.domain.explorer.files.File

@Database(entities = [File::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recentFilesDao(): Any

    companion object {
        private val lockObj = Any()

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance
                ?: synchronized(lockObj) {
                    instance
                        ?: buildDatabase(
                            context
                        )
                            .also { instance = it }
                }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "medit.db"
            ).build()
        }
    }
}
