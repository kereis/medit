package com.github.kereis.medit.di

import android.content.Context
import com.github.kereis.medit.adapters.explorer.room.di.AndroidDatabaseAdapterModule
import com.github.kereis.medit.domain.explorer.files.FileLoader
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.explorer.AndroidFileLoader
import com.github.kereis.medit.plugins.database.di.AndroidDatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(
    includes = [
        AndroidDatabaseAdapterModule::class,
        AndroidDatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object FileManagementModule {

    @Provides
    @Singleton
    fun ioDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideAndroidFileLoader(
        @ApplicationContext context: Context,
        recentFileRepository: RecentFileRepository
    ): FileLoader = AndroidFileLoader(ioDispatcher(), recentFileRepository, context)
}
