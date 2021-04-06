package com.github.kereis.medit.application.di

import com.github.kereis.medit.application.files.RecentFileRepositoryService
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppCoreModule {

    @Provides
    @Singleton
    fun provideRecentFileRepositoryService(recentFileRepository: RecentFileRepository)
        = RecentFileRepositoryService(recentFileRepository)
}