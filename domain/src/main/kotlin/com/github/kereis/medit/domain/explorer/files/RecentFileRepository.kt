package com.github.kereis.medit.domain.explorer.files

import java.net.URI

interface RecentFileRepository {

    suspend fun getAll(): List<FileReference>

    suspend fun getByURI(uri: URI): FileReference?

    suspend fun getLastRecentlyUsedFiles(period: Int): List<FileReference>

    suspend fun update(vararg fileReferences: FileReference)

    suspend fun insert(vararg newFileReferences: FileReference): List<Long>

    suspend fun delete(fileReference: FileReference)
}
