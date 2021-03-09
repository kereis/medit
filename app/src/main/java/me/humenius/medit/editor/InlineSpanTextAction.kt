package me.humenius.medit.editor

import android.widget.EditText

/**
 * **InlineSpanTextAction**
 *
 * Applies bold text formatting at current selection.
 * If no text in `editTextView` is selected, a template will be inserted
 * and the selection cursor will be set to the center of the formatting.
 *
 * @author humenius
 * @version 1.0.0
 */

class InlineSpanTextAction(private val token: String) : TextAction() {
    override fun apply(editTextView: EditText) {
        val start = editTextView.selectionStart
        val end = editTextView.selectionEnd

        val content = editTextView.text

        if (editTextView.hasSelection()) {
            content.insert(start, token)
            content.insert(end + token.length, token)
        } else {
            content.insert(start, token + token)
            editTextView.setSelection(start + token.length)
        }
    }
}
