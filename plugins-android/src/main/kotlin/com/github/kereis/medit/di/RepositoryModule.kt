package com.github.kereis.medit.di

import android.content.Context
import androidx.annotation.AnyThread
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.plugins.database.AppDatabase
import com.github.kereis.medit.plugins.database.files.RecentFileDao
import com.github.kereis.medit.plugins.database.files.RoomRecentFileRepository
import com.github.kereis.medit.ui.ToastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @AnyThread
    @Singleton
    @Provides
    fun provideRecentFileRepository(recentFilesDao: RecentFileDao): RecentFileRepository
        = RoomRecentFileRepository(recentFilesDao)
}
