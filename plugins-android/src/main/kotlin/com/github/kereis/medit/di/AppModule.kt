package com.github.kereis.medit.di

import android.content.Context
import com.github.kereis.medit.plugins.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context)

    @Provides
    fun provideRecentFilesDao(db: AppDatabase) = db.recentFilesDao()
}