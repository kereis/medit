package com.github.kereis.medit.di

import android.content.Context
import androidx.annotation.AnyThread
import com.github.kereis.medit.adapters.explorer.room.di.AndroidDatabaseAdapterModule
import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import com.github.kereis.medit.adapters.explorer.room.files.FileEntityToFileReferenceMapper
import com.github.kereis.medit.domain.explorer.files.AbstractFileLoader
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.domain.mapping.DataMapper
import com.github.kereis.medit.explorer.AndroidFileLoader
import com.github.kereis.medit.plugins.database.files.RecentFileDao
import com.github.kereis.medit.plugins.database.files.RoomRecentFileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(
    includes = [
        AndroidDatabaseAdapterModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object FileManagementModule {

    @Provides
    @Singleton
    fun ioDispatcher() = Dispatchers.IO

    @AnyThread
    @Singleton
    @Provides
    fun provideRecentFileRepository(
        recentFilesDao: RecentFileDao,
        fileEntityToFileReferenceMapper: DataMapper<FileEntity, FileReference>
    ): RecentFileRepository =
        RoomRecentFileRepository(recentFilesDao, fileEntityToFileReferenceMapper)

    @Singleton
    @Provides
    fun provideAndroidFileLoader(
        @ApplicationContext context: Context
    ): AbstractFileLoader = AndroidFileLoader(ioDispatcher(), context)


}
