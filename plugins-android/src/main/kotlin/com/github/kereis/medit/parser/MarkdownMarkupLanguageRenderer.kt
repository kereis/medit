package com.github.kereis.medit.parser

import android.content.Context
import android.widget.TextView
import io.noties.markwon.Markwon
import io.noties.markwon.ext.latex.JLatexMathPlugin
import io.noties.markwon.inlineparser.MarkwonInlineParserPlugin

/**
 * # MarkdownLanguageRenderer
 *
 * Implements [MarkupLanguageRenderer] for the usage of [Markwon].
 * Supports Markdown and LaTeX expressions @since 1.0.0
 *
 * @author kereis
 * @version 1.0.0
 */
class MarkdownMarkupLanguageRenderer(
    context: Context,
    textSize: Float
) : MarkupLanguageRenderer() {

    private val markwon =
        Markwon.builder(context)
            .usePlugin(MarkwonInlineParserPlugin.create())
            .usePlugin(
                JLatexMathPlugin.create(textSize) { builder -> builder.inlinesEnabled(true) }
            )
            .build()

    override fun setTextForView(textView: TextView, textToRender: String) {
        markwon.setMarkdown(textView, textToRender)
    }

    class Factory {
        fun newInstance(context: Context, textSize: Float): MarkupLanguageRenderer =
            MarkdownMarkupLanguageRenderer(context, textSize)
    }
}