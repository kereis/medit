package com.github.kereis.medit.adapters.explorer.room.di

import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import com.github.kereis.medit.adapters.explorer.room.files.FileEntityToFileReferenceMapper
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.mapping.DataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AndroidDatabaseAdapterModule {

    @Provides
    @Singleton
    fun provideFileEntityToFileReferenceMapper(): DataMapper<FileEntity, FileReference> =
        FileEntityToFileReferenceMapper()
}
