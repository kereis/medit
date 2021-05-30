package com.github.kereis.medit.application.editor.actions

import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.domain.editor.actions.Command

class BlockTextActionCommand(
    private val textEditor: TextEditor,
    private val character: String
) : Command(textEditor) {

    private val startBlock = "\n$character"
    private val endBlock = "\n\n$character\n"

    override fun execute() {
        val text = textEditor.text

        val endIndex = textEditor.selectionEnd

        textEditor.insert(endIndex, "$startBlock$endBlock")
        textEditor.setCursor(endIndex + startBlock.length)
    }
}