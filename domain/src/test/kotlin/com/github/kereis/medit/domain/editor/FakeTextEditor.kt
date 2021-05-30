package com.github.kereis.medit.domain.editor

import com.github.kereis.medit.domain.explorer.files.FileReference

class FakeTextEditor : TextEditor {

    private var internalSelectionStart = 0
    private var internalSelectionEnd = 0
    private var internalText = ""
    private var currentDocument: Document? = null

    override val selectionStart: Int
        get() = internalSelectionStart
    override val selectionEnd: Int
        get() = internalSelectionEnd
    override val text: String
        get() = internalText

    override fun replace(startIndex: Int, endIndex: Int, text: String) {
        internalText.replaceRange(startIndex, endIndex, text)
    }

    override fun insert(atIndex: Int, text: String) {
        val stringBuilder = StringBuilder(internalText)
        // stringBuilder.append(internalText.substring(0, atIndex))
        // stringBuilder.append(text)
        stringBuilder.insert(atIndex, text)
        internalText = stringBuilder.toString()
    }

    override fun setCursor(atIndex: Int) {
        setSelection(atIndex, atIndex)
    }

    fun setSelection(start: Int, end: Int) {
        internalSelectionStart = start
        internalSelectionEnd = end
    }

    override fun load(document: Document) {
        currentDocument = document
    }

    override fun save() {
        // nothing to do here?
    }

    override fun saveAs(fileReference: FileReference) {
        // nothing to do here?
    }
}