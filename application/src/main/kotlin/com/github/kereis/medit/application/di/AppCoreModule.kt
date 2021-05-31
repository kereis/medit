package com.github.kereis.medit.application.di

import com.github.kereis.medit.application.files.FileReferenceAccessDateUpdater
import com.github.kereis.medit.application.format.TextActionCommandExecutor
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import dagger.Module
import dagger.Provides
import java.time.Clock
import javax.inject.Singleton

@Module
object AppCoreModule {

    @Provides
    fun provideTextActionCommandExecutor() = TextActionCommandExecutor()

    @Provides
    fun provideFileReferenceAccessDateUpdater(clock: Clock) = FileReferenceAccessDateUpdater(clock)

    @Provides
    @Singleton
    fun provideClock() = Clock.systemUTC()
}
