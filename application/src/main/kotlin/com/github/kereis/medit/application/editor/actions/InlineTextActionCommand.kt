package com.github.kereis.medit.application.editor.actions

import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.domain.editor.actions.Command

class InlineTextActionCommand(
    private val textEditor: TextEditor,
    private val character: String
) : Command(textEditor) {

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