package com.github.kereis.medit.editor

import android.widget.EditText

/**
 * **TextAction**
 *
 * Defines a text action that applies text formatting
 *
 * @version 1.0.0
 */
abstract class TextAction {
    abstract fun apply(editTextView: EditText)
}
