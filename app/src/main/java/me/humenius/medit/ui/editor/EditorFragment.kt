package me.humenius.medit.ui.editor

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import me.humenius.medit.R
import me.humenius.medit.databinding.FragmentEditorBinding
import me.humenius.medit.format.markdown.MarkdownTextActions
import me.humenius.medit.parser.MarkdownParser
import me.humenius.medit.ui.BaseFragment

class EditorFragment : BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditorBinding
        get() = FragmentEditorBinding::inflate

    override fun initView() {
        toolbarTitle = "Edit file"
        super.initView()
        setup()
    }

    private fun setup() {
        binding.actionBarBold.setOnClickListener {
            MarkdownTextActions.BOLD.textAction.apply(binding.contentInput)
        }
        binding.actionBarItalics.setOnClickListener {
            MarkdownTextActions.ITALIC.textAction.apply(binding.contentInput)
        }
        binding.actionBarInlineCode.setOnClickListener {
            MarkdownTextActions.INLINE_CODE.textAction.apply(binding.contentInput)
        }

        binding.fabRenderMd.setOnClickListener {
            val view = MarkdownParser.render(requireContext(), binding.contentInput.text.toString())
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_container, view)
                addToBackStack(null)
            }
        }
    }
}
