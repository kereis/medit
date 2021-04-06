package com.github.kereis.medit.di

import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.plugins.database.files.RecentFileRepositoryBridge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    abstract fun recentFileRepository(recentFileRepositoryBridge: RecentFileRepositoryBridge):
        RecentFileRepository
}
