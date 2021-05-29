package com.github.kereis.medit.domain.explorer.files

interface RecentFileRepository {

    suspend fun getAll(): List<FileReference>

    suspend fun getLastRecentlyUsedFiles(period: Int): List<FileReference>

    suspend fun update(vararg fileReferences: FileReference)

    suspend fun insert(vararg newFileReferences: FileReference)

    suspend fun delete(fileReference: FileReference)
}
