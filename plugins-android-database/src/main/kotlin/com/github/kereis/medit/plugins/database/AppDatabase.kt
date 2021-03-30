package com.github.kereis.medit.plugins.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.kereis.medit.adapters.explorer.room.DateTimeTypeConverter
import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import com.github.kereis.medit.adapters.explorer.room.files.getSampleFileEntityData
import com.github.kereis.medit.plugins.database.files.RoomRecentFileRepository

@Database(entities = [FileEntity::class], version = 1)
@TypeConverters(DateTimeTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recentFilesDao(): RoomRecentFileRepository

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
            )
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            // TODO Remove or add logic for debug versions?
                            getInstance(context).recentFilesDao().insert(*getSampleFileEntityData())
                        }
                    }
                )
                .build()
        }
    }
}
