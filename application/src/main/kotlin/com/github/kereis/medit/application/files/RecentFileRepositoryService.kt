package com.github.kereis.medit.application.files

import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository

class RecentFileRepositoryService(
    private val recentFileRepository: RecentFileRepository
) {
    suspend fun getAll(): List<FileReference> {
        return recentFileRepository.getAll()
    }

    suspend fun updateAccess(fileReference: FileReference) {
        recentFileRepository.update(FileReference.updateAccessTime(fileReference))
    }
}
