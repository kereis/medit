package me.humenius.medit.format.markdown

import android.widget.EditText
import me.humenius.medit.editor.TextAction

/**
 * **InlineSpanTextAction**
 *
 * An inline text format action
 *
 * @author humenius
 * @version 1.0.0
 */
abstract class InlineSpanTextAction(private val tag: String) : TextAction() {
    override fun apply(editTextView: EditText) {
        val start = editTextView.selectionStart
        val end = editTextView.selectionEnd

        val content = editTextView.text

        if (editTextView.hasSelection()) {
            content.insert(start, tag)
            content.insert(end + 1, tag)
        } else {
            content.insert(start, tag + tag)
            editTextView.setSelection(start + 2)
        }
    }
}