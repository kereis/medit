package com.github.kereis.medit.di

import android.content.Context
import com.github.kereis.medit.adapters.explorer.room.di.AndroidDatabaseAdapterModule
import com.github.kereis.medit.domain.explorer.files.FileLoader
import com.github.kereis.medit.explorer.AndroidFileLoader
import com.github.kereis.medit.plugins.database.di.AndroidDatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module(
    includes = [
        AndroidDatabaseAdapterModule::class,
        AndroidDatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object FileManagementModule {

    @Singleton
    @Provides
    fun provideAndroidFileLoader(
        ioDispatcher: CoroutineDispatcher,
        @ApplicationContext context: Context,
    ): FileLoader = AndroidFileLoader(ioDispatcher, context)
}
