package com.github.kereis.medit.ui.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.application.format.markdown.MarkdownTextActions
import com.github.kereis.medit.databinding.FragmentEditorBinding
import com.github.kereis.medit.parser.MarkdownParser
import com.github.kereis.medit.ui.BaseFragment

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
