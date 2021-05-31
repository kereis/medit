package com.github.kereis.medit.adapters.explorer.room.files

import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.mapping.DataMapper

class FileEntityToFileReferenceMapper : DataMapper<FileEntity, FileReference> {

    override fun toTargetType(source: FileEntity): FileReference =
        FileReference(
            source.id,
            source.fileName,
            source.filePath,
            source.lastAccess
        )

    override fun toSourceType(target: FileReference): FileEntity =
        FileEntity(
            target.id,
            target.fileName,
            target.rawFilePath,
            target.lastAccess
        )
}
