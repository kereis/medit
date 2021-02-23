package me.humenius.medit.ui.editor

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import me.humenius.medit.R
import me.humenius.medit.databinding.FragmentEditorBinding

class EditorFragment : Fragment(R.layout.fragment_editor) {
    private var _binding: FragmentEditorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditorBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initView() {
        TODO("Not yet implemented")
    }
}
