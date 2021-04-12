package com.github.kereis.medit.domain.editor

import com.github.kereis.medit.domain.explorer.files.File

interface TextEditor {

    val selectionStart: Int

    val selectionEnd: Int

    /**
     * @return Content of the text editor
     */
    val text: String

    val hasSelection: Boolean
        get() = selectionStart != selectionEnd

    fun replace(startIndex: Int, endIndex: Int, text: String)

    fun insert(atIndex: Int, text: String)

    fun setCursor(atIndex: Int)

    fun load(document: Document)

    fun save()

    fun saveAs(file: File)
}
