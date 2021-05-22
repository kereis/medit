package com.github.kereis.medit.application.di

// import com.github.kereis.medit.application.files.FileStorageService
import com.github.kereis.medit.application.files.FileToJavaNIOFileMapper
import com.github.kereis.medit.application.files.RecentFileRepositoryService
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.domain.mapping.DataMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
object AppCoreModule {

    @Provides
    @Singleton
    fun provideRecentFileRepositoryService(recentFileRepository: RecentFileRepository) =
        RecentFileRepositoryService(recentFileRepository)

    // @Provides
    // @Singleton
    // fun provideFileStorageService() =
    //     FileStorageService(ioDispatcher(), provideFileToJavaNIOFileMapper())

    @Provides
    fun provideFileToJavaNIOFileMapper(): DataMapper<File, java.io.File> = FileToJavaNIOFileMapper()

    @Provides
    @Singleton
    fun ioDispatcher() = Dispatchers.IO
}
