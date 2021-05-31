package com.github.kereis.medit.di

import android.content.Context
import com.github.kereis.medit.adapters.explorer.room.di.AndroidDatabaseAdapterModule
import com.github.kereis.medit.application.di.AppCoreModule
import com.github.kereis.medit.plugins.database.di.AndroidDatabaseModule
import com.github.kereis.medit.ui.components.ToastService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        AppCoreModule::class,
        FileManagementModule::class,
        MarkupLanguageRendererModule::class,
        AndroidDatabaseAdapterModule::class,
        AndroidDatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideToastService(
        @ApplicationContext context: Context
    ): ToastService = ToastService(context)
}
