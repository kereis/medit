package com.github.kereis.medit.parser

import android.widget.TextView

/**
 * # LanguageRenderer
 *
 * Abstraction for language parsers/renders that set the text as [String] into a [TextView].
 *
 * @author kereis
 * @version 1.0.0
 */
abstract class MarkupLanguageRenderer {

    abstract fun setTextForView(textView: TextView, textToRender: String)
}