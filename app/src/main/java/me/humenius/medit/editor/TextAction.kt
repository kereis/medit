package me.humenius.medit.editor

import android.widget.EditText

/**
 * **TextAction**
 *
 * Defines a text action that applies text formatting
 *
 * @author humenius
 * @version 1.0.0
 */
abstract class TextAction() {
    abstract fun apply(editTextView: EditText)
}
