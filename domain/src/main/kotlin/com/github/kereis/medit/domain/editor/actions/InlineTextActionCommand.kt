package com.github.kereis.medit.domain.editor.actions

import com.github.kereis.medit.domain.editor.TextEditor

open class InlineTextActionCommand(
    private val textEditor: TextEditor,
    private val character: String
) : Command() {

    override fun execute() {
        val start = textEditor.selectionStart
        val end = textEditor.selectionEnd

        if (textEditor.hasSelection) {
            textEditor.insert(start, character)
            textEditor.insert(end + character.length, character)
        } else {
            textEditor.insert(start, character + character)
            textEditor.setCursor(start + character.length)
        }
    }
}
