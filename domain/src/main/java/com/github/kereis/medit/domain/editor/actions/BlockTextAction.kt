package com.github.kereis.medit.domain.editor.actions

class BlockTextAction(private val token: String) : TextAction() {
    override fun apply(text: String): String {
        TODO("Not yet implemented")
    }
    // private val startBlock = "\n$token"
    // private val endBlock = "\n\n$token\n"
    //
    // override fun apply(editTextView: EditText) {
    //     val end = editTextView.selectionEnd
    //
    //     val content = editTextView.text
    //
    //     if (!editTextView.hasSelection()) {
    //         content.insert(end, "$startBlock$endBlock")
    //         editTextView.setSelection(end + startBlock.length)
    //     }
    // }
}
