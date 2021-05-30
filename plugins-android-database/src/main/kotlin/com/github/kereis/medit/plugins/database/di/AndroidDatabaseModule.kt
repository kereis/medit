package com.github.kereis.medit.plugins.database.di

import android.content.Context
import androidx.annotation.AnyThread
import com.github.kereis.medit.adapters.explorer.room.di.AndroidDatabaseAdapterModule
import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import com.github.kereis.medit.adapters.explorer.room.time.DateTimeTypeConverter
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.domain.mapping.DataMapper
import com.github.kereis.medit.plugins.database.AppDatabase
import com.github.kereis.medit.plugins.database.files.RecentFileDao
import com.github.kereis.medit.plugins.database.files.RoomRecentFileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.OffsetDateTime
import javax.inject.Singleton

@Module(
    includes = [
        AndroidDatabaseAdapterModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object AndroidDatabaseModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context,
        dateTimeTypeConverter: DataMapper<String, OffsetDateTime>
    ) = AppDatabase.getInstance(context, dateTimeTypeConverter)

    @Provides
    fun provideRecentFilesDao(db: AppDatabase) = db.recentFilesDao()

    @Provides
    @Singleton
    @AnyThread
    fun provideRecentFileRepository(
        recentFilesDao: RecentFileDao,
        fileEntityToFileReferenceMapper: DataMapper<FileEntity, FileReference>
    ): RecentFileRepository =
        RoomRecentFileRepository(recentFilesDao, fileEntityToFileReferenceMapper)
}