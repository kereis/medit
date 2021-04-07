package com.github.kereis.medit.application.editor.actions

import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.domain.editor.actions.TextAction

class BlockTextAction(token: String) : TextAction() {

    private val startBlock = "\n$token"
    private val endBlock = "\n\n$token\n"

    override fun apply(textEditor: TextEditor) {
        val text = textEditor.text

        val endIndex = textEditor.selectionEnd

        textEditor.insert(endIndex, "$startBlock$endBlock")
        textEditor.setCursor(endIndex + startBlock.length)
    }
}
