package com.github.kereis.medit.di

import com.github.kereis.medit.parser.MarkdownMarkupLanguageRenderer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarkupLanguageRendererModule {

    @Provides
    @Singleton
    fun provideMarkdownParserFactory() = MarkdownMarkupLanguageRenderer.Factory()
}