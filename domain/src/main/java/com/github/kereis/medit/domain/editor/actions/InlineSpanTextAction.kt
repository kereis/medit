package com.github.kereis.medit.domain.editor.actions

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
    override fun apply(text: String): String {
        TODO("Not yet implemented")
    }
    // override fun apply(editTextView: EditText) {
    //     val start = editTextView.selectionStart
    //     val end = editTextView.selectionEnd
    //
    //     val content = editTextView.text
    //
    //     if (editTextView.hasSelection()) {
    //         content.insert(start, token)
    //         content.insert(end + token.length, token)
    //     } else {
    //         content.insert(start, token + token)
    //         editTextView.setSelection(start + token.length)
    //     }
    // }
}
