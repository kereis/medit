package com.github.kereis.medit.domain.editor.actions

import com.github.kereis.medit.domain.editor.TextEditor

/**
 * # TextAction
 *
 * Defines a text action that applies text formatting
 *
 * @version 1.0.0
 */
abstract class TextAction {

    @Deprecated("Will be removed after implemented FormatCommands")
    abstract fun apply(textEditor: TextEditor)
}
