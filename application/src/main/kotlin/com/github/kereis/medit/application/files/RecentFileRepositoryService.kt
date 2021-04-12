package com.github.kereis.medit.application.files

import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository

class RecentFileRepositoryService(
    private val recentFileRepository: RecentFileRepository
) {
    suspend fun getAll(): List<File> {
        return recentFileRepository.getAll()
    }

    suspend fun updateAccess(file: File) {
        recentFileRepository.update(File.updateAccessTime(file))
    }
}
