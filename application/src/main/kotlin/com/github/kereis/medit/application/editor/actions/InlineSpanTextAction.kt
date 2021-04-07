package com.github.kereis.medit.application.editor.actions

import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.domain.editor.actions.TextAction

/**
 * # InlineSpanTextAction
 *
 * Applies bold text formatting at current selection.
 * If no text in `editTextView` is selected, a template will be inserted
 * and the selection cursor will be set to the center of the formatting.
 *
 * @version 1.0.0
 */

class InlineSpanTextAction(private val token: String) : TextAction() {

    override fun apply(textEditor: TextEditor) {
        val start = textEditor.selectionStart
        val end = textEditor.selectionEnd

        if (textEditor.hasSelection) {
            textEditor.insert(start, token)
            textEditor.insert(end + token.length, token)
        } else {
            textEditor.insert(start, token + token)
            textEditor.setCursor(start + token.length)
        }
    }
}
