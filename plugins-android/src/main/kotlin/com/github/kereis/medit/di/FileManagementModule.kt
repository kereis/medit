package com.github.kereis.medit.di

import android.content.Context
import androidx.annotation.AnyThread
import com.github.kereis.medit.domain.explorer.files.AbstractFileLoader
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
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

@Module
@InstallIn(SingletonComponent::class)
object FileManagementModule {

    @Provides
    @Singleton
    fun ioDispatcher() = Dispatchers.IO

    @AnyThread
    @Singleton
    @Provides
    fun provideRecentFileRepository(
        recentFilesDao: RecentFileDao
    ): RecentFileRepository = RoomRecentFileRepository(recentFilesDao)

    @Singleton
    @Provides
    fun provideAndroidFileLoader(
        @ApplicationContext context: Context
    ): AbstractFileLoader = AndroidFileLoader(ioDispatcher(), context)
}
