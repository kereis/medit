package com.github.kereis.medit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KotlinCoroutinesModule {

    @Provides
    @Singleton
    @Named("ioDispatcher")
    fun ioDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    @Named("mainDispatcher")
    fun mainDispatcher() = Dispatchers.Main
}
