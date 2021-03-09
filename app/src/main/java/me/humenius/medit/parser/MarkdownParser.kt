package me.humenius.medit.parser

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import io.noties.markwon.Markwon

sealed class MarkdownParser {
    companion object {
        /**
         * This method returns a `android.view.View` with parsed Markdown text.
         * It can be used to exchange fragments when render is requested.
         *
         * @return `android.widget.TextView` containing the Markdown text
         */
        fun render(input: String): View {
            val layout = RelativeLayout(null)
            val parser = makeParser(layout.context)
            val textView = TextView(layout.context)

            parser.setMarkdown(textView, input)

            return textView
        }

        private fun makeParser(context: Context): Markwon {
            return Markwon.create(context)
        }
    }
}
