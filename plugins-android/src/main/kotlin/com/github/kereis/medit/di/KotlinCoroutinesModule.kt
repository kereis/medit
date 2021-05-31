package com.github.kereis.medit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

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
