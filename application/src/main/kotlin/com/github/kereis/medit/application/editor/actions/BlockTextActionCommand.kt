package com.github.kereis.medit.application.editor.actions

import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.domain.editor.actions.Command

open class BlockTextActionCommand(
    private val textEditor: TextEditor,
    character: String
) : Command(textEditor) {

    protected open val startBlock = "\n$character"
    protected open val endBlock = "\n\n$character\n"

    override fun execute() {
        val endIndex = textEditor.selectionEnd

        textEditor.insert(endIndex, "$startBlock$endBlock")
        textEditor.setCursor(endIndex + startBlock.length)
    }
}