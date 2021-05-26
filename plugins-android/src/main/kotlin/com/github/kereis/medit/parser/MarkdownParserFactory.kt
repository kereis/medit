package com.github.kereis.medit.parser

import android.content.Context
import io.noties.markwon.Markwon
import io.noties.markwon.ext.latex.JLatexMathPlugin
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin

sealed class MarkdownParserFactory {
    companion object {
        @JvmStatic
        fun newInstance(context: Context, textSize: Float): Markwon =
            Markwon.builder(context)
                .usePlugin(MarkwonInlineParserPlugin.create())
                .usePlugin(
                    JLatexMathPlugin.create(textSize) { builder -> builder.inlinesEnabled(true) }
                )
                .build()
    }
}