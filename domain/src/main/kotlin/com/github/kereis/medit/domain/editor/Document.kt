package com.github.kereis.medit.domain.editor

import com.github.kereis.medit.domain.explorer.files.File
import java.io.Serializable

data class Document(
    val title: String,
    var content: String,
    val file: File
) : Serializable {
    override fun toString() = "Document(title='$title', file=$file)"
}
