package com.github.kereis.medit.domain.editor.actions

import com.github.kereis.medit.domain.editor.History
import com.github.kereis.medit.domain.editor.TextEditor

abstract class Command(
    private val textEditor: TextEditor
) {

    abstract fun execute()
}