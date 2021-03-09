package me.humenius.medit.editor

import android.widget.EditText

class BlockTextAction(private val token: String) : TextAction() {
    private val startBlock = "\n$token"
    private val endBlock = "\n\n$token\n"

    override fun apply(editTextView: EditText) {
        val end = editTextView.selectionEnd

        val content = editTextView.text

        if (!editTextView.hasSelection()) {
            content.insert(end, "$startBlock$endBlock")
            editTextView.setSelection(end + startBlock.length)
        }
    }
}
