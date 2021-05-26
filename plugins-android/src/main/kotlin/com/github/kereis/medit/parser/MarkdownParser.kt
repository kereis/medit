package com.github.kereis.medit.parser

import android.content.Context
import androidx.fragment.app.Fragment
import com.github.kereis.medit.ui.editor.MarkdownRenderFragment
import io.noties.markwon.Markwon

sealed class MarkdownParser {
    companion object {
        /**
         * This method returns a [android.view.View] with parsed Markdown text.
         * It can be used to exchange fragments when render is requested.
         *
         * @return [androidx.fragment.app.Fragment] containing the Markdown text
         */
        fun render(context: Context, input: String): Fragment {
            val parser = makeParser(context)
            val parsedInput = parser.parse(input)
            val renderedSpanned = parser.render(parsedInput)
            return MarkdownRenderFragment(renderedSpanned)
        }

        private fun makeParser(context: Context): Markwon {
            return Markwon.create(context)
        }
    }
}
