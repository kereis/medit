package com.github.kereis.medit.application.di

import com.github.kereis.medit.application.files.FileReferenceDataUpdater
import com.github.kereis.medit.application.format.TextActionCommandExecutor
import dagger.Module
import dagger.Provides
import java.time.Clock
import javax.inject.Singleton

@Module
object AppCoreModule {

    @Provides
    fun provideTextActionCommandExecutor() = TextActionCommandExecutor()

    @Provides
    fun provideFileReferenceDataUpdater(clock: Clock) = FileReferenceDataUpdater(clock)

    @Provides
    @Singleton
    fun provideClock() = Clock.systemUTC()
}
