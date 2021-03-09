package me.humenius.medit.ui.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.humenius.medit.R
import me.humenius.medit.databinding.FragmentEditorBinding
import me.humenius.medit.format.markdown.MarkdownTextActions

class EditorFragment : Fragment(R.layout.fragment_editor) {
    private var _binding: FragmentEditorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditorBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
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
            
        }
    }
}
