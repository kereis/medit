package com.kereis.github.medit.domain.editor

import com.kereis.github.medit.domain.explorer.files.File

data class Document(
    val title: String,
    val content: String,
    val file: File,
    val history: History<Cloneable>
)
