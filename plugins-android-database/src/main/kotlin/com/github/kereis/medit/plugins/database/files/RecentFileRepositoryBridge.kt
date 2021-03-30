package com.github.kereis.medit.plugins.database.files

import androidx.annotation.AnyThread
import com.github.kereis.medit.adapters.explorer.room.files.asDomainFileList
import com.github.kereis.medit.adapters.explorer.room.files.asFileEntity
import com.github.kereis.medit.adapters.explorer.room.files.asFileEntityArray
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@AnyThread
class RecentFileRepositoryBridge
@Inject constructor(private val recentFileRepository: RoomRecentFileRepository) :
    RecentFileRepository {

    override fun getAll(): List<File> {
        return recentFileRepository.getAll().asDomainFileList()
    }

    override fun getLastRecentlyUsedFiles(period: Int): List<File> {
        return recentFileRepository.getLastRecentlyUsedFiles(period).asDomainFileList()
    }

    override suspend fun update(vararg files: File) {
        recentFileRepository.update(*files.asFileEntityArray())
    }

    override suspend fun insert(vararg newFiles: File) {
        recentFileRepository.insert(*newFiles.asFileEntityArray())
    }

    override suspend fun delete(file: File) {
        recentFileRepository.delete(file.asFileEntity())
    }
}
