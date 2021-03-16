package me.humenius.medit.parser

import android.content.Context
import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import io.noties.markwon.Markwon
import me.humenius.medit.R
import me.humenius.medit.databinding.FragmentEditorBinding
import me.humenius.medit.databinding.FragmentPreviewBinding
import me.humenius.medit.ui.editor.MarkdownRenderFragment

sealed class MarkdownParser {
    companion object {
        /**
         * This method returns a `android.view.View` with parsed Markdown text.
         * It can be used to exchange fragments when render is requested.
         *
         * @return `androidx.fragment.app.Fragment` containing the Markdown text
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
