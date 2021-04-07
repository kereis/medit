package com.github.kereis.medit.ui.editor

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.application.format.markdown.MarkdownTextActions
import com.github.kereis.medit.databinding.FragmentEditorBinding
import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.parser.MarkdownParser
import com.github.kereis.medit.ui.BaseFragment
import com.github.kereis.medit.ui.components.SelectableEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditorFragment :
    BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor),
    TextEditor,
    SelectableEditText.OnSelectionChangedListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditorBinding
        get() = FragmentEditorBinding::inflate

    private val viewModel by activityViewModels<EditorViewModel>()

    override fun initView() {
        toolbarTitle = "Edit file"
        super.initView()
        setup()
    }

    override val selectionStart: Int
        get() = viewModel.selectionStart.value!!
    override val selectionEnd: Int
        get() = viewModel.selectionEnd.value!!
    override val text: String
        get() = viewModel.content.value!!

    override fun replace(startIndex: Int, endIndex: Int, text: String) {
        binding.contentInput.text?.replace(startIndex, endIndex, text)
    }

    override fun insert(atIndex: Int, text: String) {
        binding.contentInput.text?.insert(atIndex, text)
    }

    override fun setCursor(atIndex: Int) {
        binding.contentInput.setSelection(atIndex)
    }

    private fun setup() {
        binding.actionBarBold.setOnClickListener {
            MarkdownTextActions.BOLD.textAction.apply(this)
        }
        binding.actionBarItalics.setOnClickListener {
            MarkdownTextActions.ITALIC.textAction.apply(this)
        }
        binding.actionBarInlineCode.setOnClickListener {
            MarkdownTextActions.INLINE_CODE.textAction.apply(this)
        }

        binding.fabRenderMd.setOnClickListener {
            val view = MarkdownParser.render(requireContext(), binding.contentInput.text.toString())
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_container, view)
                addToBackStack(null)
            }
        }

        binding.contentInput.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    viewModel.onContentChanged(s.toString())
                }
            }
        )

        binding.contentInput.addOnSelectionChangedListener(this)
    }

    override fun onSelectionChanged(start: Int, end: Int) {
        viewModel.onSelectionChanged(start, end)
    }
}
