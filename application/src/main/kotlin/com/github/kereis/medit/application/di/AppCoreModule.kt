package com.github.kereis.medit.application.di

// import com.github.kereis.medit.application.files.FileStorageService
// import com.github.kereis.medit.application.files.FileToJavaNIOFileMapper
import com.github.kereis.medit.application.files.RecentFileRepositoryService
import com.github.kereis.medit.application.format.TextActionCommandExecutor
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
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

    @Provides
    @Singleton
    fun ioDispatcher() = Dispatchers.IO

    @Provides
    fun provideTextActionCommandExecutor() = TextActionCommandExecutor()
}
