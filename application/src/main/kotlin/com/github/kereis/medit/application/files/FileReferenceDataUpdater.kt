package com.github.kereis.medit.application.files

import com.github.kereis.medit.domain.explorer.files.FileReference
import java.time.Clock
import java.time.OffsetDateTime

class FileReferenceDataUpdater(
    private val clock: Clock
) {

    fun updateTimestamp(fileReference: FileReference) =
        FileReference(
            fileReference.id,
            fileReference.fileName,
            fileReference.rawFilePath,
            OffsetDateTime.now(clock)
        )

    fun updateId(fileReference: FileReference, newId: Long) =
        FileReference(
            newId,
            fileReference.fileName,
            fileReference.rawFilePath,
            fileReference.lastAccess
        )
}