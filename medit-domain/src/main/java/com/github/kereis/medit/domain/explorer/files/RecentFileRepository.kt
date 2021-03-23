package com.github.kereis.medit.domain.explorer.files

interface RecentFileRepository {

    fun getAll(): List<File>

    fun getLastRecentlyUsedFiles(period: Int): List<File>

    fun insert(vararg newFiles: File)

    fun delete(file: File)
}