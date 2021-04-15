package com.github.kereis.medit.adapter.android.di

import androidx.documentfile.provider.DocumentFile
import com.github.kereis.medit.adapter.android.DocumentFileToJavaFileMapper
import com.github.kereis.medit.domain.mapping.DataMapper
import dagger.Module
import dagger.Provides
import java.io.File

@Module
object AndroidFilesystemAdapterModule {

    @Provides
    fun documentFileToFileMapper(): DataMapper<DocumentFile, File> = DocumentFileToJavaFileMapper()
}
