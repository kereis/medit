package com.github.kereis.medit.domain.editor

import com.github.kereis.medit.domain.explorer.files.FileReference
import java.io.Serializable

data class Document(
    val title: String,
    var content: String,
    val fileReference: FileReference
) : Serializable {
    override fun toString() = "Document(title='$title', file=$fileReference)"
}
