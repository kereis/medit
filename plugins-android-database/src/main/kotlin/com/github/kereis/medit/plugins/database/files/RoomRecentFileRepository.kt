package com.github.kereis.medit.plugins.database.files

import com.github.kereis.medit.adapters.explorer.room.files.FileEntity
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.domain.mapping.DataMapper
import java.net.URI

class RoomRecentFileRepository(
    private val recentFileDao: RecentFileDao,
    private val fileEntityToFileReferenceMapper: DataMapper<FileEntity, FileReference>
) : RecentFileRepository {
    override suspend fun getAll(): List<FileReference> {
        return recentFileDao.getAll().map(fileEntityToFileReferenceMapper::toTargetType)
    }

    override suspend fun getByURI(uri: URI): FileReference? {
        return recentFileDao.getByURI(uri)?.let(fileEntityToFileReferenceMapper::toTargetType)
    }

    override suspend fun getLastRecentlyUsedFiles(period: Int): List<FileReference> {
        return recentFileDao.getLastRecentlyUsedFiles(period).map {
            fileEntityToFileReferenceMapper.toTargetType(it)
        }
    }

    override suspend fun update(vararg fileReferences: FileReference) {
        recentFileDao.update(
            *fileReferences
                .map(fileEntityToFileReferenceMapper::toSourceType)
                .toTypedArray()
        )
    }

    override suspend fun insert(vararg newFileReferences: FileReference): List<Long> {
        return recentFileDao.insert(
            *newFileReferences
                .map(fileEntityToFileReferenceMapper::toSourceType)
                .toTypedArray()
        )
    }

    override suspend fun delete(fileReference: FileReference) {
        recentFileDao.delete(
            fileEntityToFileReferenceMapper.toSourceType(fileReference)
        )
    }
}
