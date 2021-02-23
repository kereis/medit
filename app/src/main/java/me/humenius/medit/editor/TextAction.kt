package me.humenius.medit.editor

import android.widget.EditText

/**
 * <h1>TextAction</h1>
 * <p>Defines a text action that applies text formatting</p>
 *
 * @author humenius
 * @version 1.0.0
 */
abstract class TextAction() {
    abstract fun apply(editTextView: EditText)
}