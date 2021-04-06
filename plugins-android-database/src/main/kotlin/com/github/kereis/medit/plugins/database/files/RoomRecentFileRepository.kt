package com.github.kereis.medit.plugins.database.files

import com.github.kereis.medit.adapters.explorer.room.files.asDomainFileList
import com.github.kereis.medit.adapters.explorer.room.files.asFileEntity
import com.github.kereis.medit.adapters.explorer.room.files.asFileEntityArray
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository

class RoomRecentFileRepository(
    private val recentFileDao: RecentFileDao
) : RecentFileRepository {
    override suspend fun getAll(): List<File> {
        return recentFileDao.getAll().asDomainFileList()
    }

    override suspend fun getLastRecentlyUsedFiles(period: Int): List<File> {
        return recentFileDao.getLastRecentlyUsedFiles(period).asDomainFileList()
    }

    override suspend fun update(vararg files: File) {
        recentFileDao.update(*files.asFileEntityArray())
    }

    override suspend fun insert(vararg newFiles: File) {
        recentFileDao.insert(*newFiles.asFileEntityArray())
    }

    override suspend fun delete(file: File) {
        recentFileDao.delete(file.asFileEntity())
    }
}
