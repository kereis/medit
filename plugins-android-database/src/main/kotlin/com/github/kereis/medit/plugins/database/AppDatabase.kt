package com.github.kereis.medit.plugins.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.kereis.medit.adapters.explorer.room.time.StaticDateTimeTypeConverter
import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import com.github.kereis.medit.adapters.explorer.room.files.getSampleFileEntityData
import com.github.kereis.medit.adapters.explorer.room.time.DateTimeTypeConverter
import com.github.kereis.medit.domain.mapping.DataMapper
import com.github.kereis.medit.plugins.database.files.RecentFileDao
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.time.OffsetDateTime

@Database(entities = [FileEntity::class], version = 2)
@TypeConverters(DateTimeTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recentFilesDao(): RecentFileDao

    companion object {
        private val lockObj = Any()

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(
            context: Context,
            dateTimeTypeConverter: DataMapper<String, OffsetDateTime>
        ): AppDatabase {
            return instance
                ?: synchronized(lockObj) {
                    instance
                        ?: buildDatabase(
                            context,
                            dateTimeTypeConverter
                        )
                            .also { instance = it }
                }
        }

        private fun buildDatabase(
            context: Context,
            dateTimeTypeConverter: DataMapper<String, OffsetDateTime>
        ): AppDatabase {
            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "medit.db"
            )
                .addTypeConverter(dateTimeTypeConverter)
                .fallbackToDestructiveMigration()
                .build()

            // TODO: Other mechanism for debug entries?
            runBlocking {
                Timber.i("Pre-populating room database!")
                db.recentFilesDao().insert(*getSampleFileEntityData())
            }

            return db
        }
    }
}
