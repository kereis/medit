package com.github.kereis.medit.adapters.explorer.room.di

import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import com.github.kereis.medit.adapters.explorer.room.files.FileEntityToFileReferenceMapper
import com.github.kereis.medit.adapters.explorer.room.time.DateTimeTypeConverter
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.mapping.DataMapper
import dagger.Module
import dagger.Provides
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
object AndroidDatabaseAdapterModule {

    @Provides
    @Singleton
    fun provideFileEntityToFileReferenceMapper(): DataMapper<FileEntity, FileReference> =
        FileEntityToFileReferenceMapper()

    @Provides
    fun provideDateTimeTypeConverter(): DataMapper<String, OffsetDateTime> =
        DateTimeTypeConverter(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
}
