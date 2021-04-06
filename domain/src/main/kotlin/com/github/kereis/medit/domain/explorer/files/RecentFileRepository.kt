package com.github.kereis.medit.domain.explorer.files

interface RecentFileRepository {

    fun getAll(): List<File>

    fun getLastRecentlyUsedFiles(period: Int): List<File>

    suspend fun update(vararg files: File)

    suspend fun insert(vararg newFiles: File)

    suspend fun delete(file: File)
}
