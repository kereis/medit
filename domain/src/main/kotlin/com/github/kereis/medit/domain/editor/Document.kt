package com.github.kereis.medit.domain.editor

import com.github.kereis.medit.domain.explorer.files.File

data class Document(
    val title: String,
    val content: String,
    val file: File,
    val history: History<Cloneable>
)
