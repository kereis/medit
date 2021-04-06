package com.github.kereis.medit.domain.explorer.files

interface RecentFileRepository {

    suspend fun getAll(): List<File>

    suspend fun getLastRecentlyUsedFiles(period: Int): List<File>

    suspend fun update(vararg files: File)

    suspend fun insert(vararg newFiles: File)

    suspend fun delete(file: File)
}
