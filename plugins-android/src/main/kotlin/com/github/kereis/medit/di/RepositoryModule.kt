package com.github.kereis.medit.di

import androidx.annotation.AnyThread
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.plugins.database.files.RecentFileDao
import com.github.kereis.medit.plugins.database.files.RoomRecentFileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @AnyThread
    @Singleton
    @Provides
    fun provideRecentFileRepository(recentFilesDao: RecentFileDao): RecentFileRepository =
        RoomRecentFileRepository(recentFilesDao)
}
