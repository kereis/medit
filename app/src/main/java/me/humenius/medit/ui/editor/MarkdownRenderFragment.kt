package me.humenius.medit.ui.editor

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import me.humenius.medit.R
import me.humenius.medit.databinding.FragmentPreviewBinding

class MarkdownRenderFragment(private val textToDisplay: Spanned) : Fragment(R.layout.fragment_preview) {
    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreviewBinding.inflate(inflater, container, false)

        binding.previewTextView.text = textToDisplay

        initToolbar()

        return binding.root
    }

    private fun initToolbar() {
        val toolbar = binding.previewToolbar
        val superActivity = activity as AppCompatActivity

        superActivity.setSupportActionBar(toolbar)
        // TODO replace icon
        toolbar.setNavigationIcon(R.drawable.ic_notifications_black_24dp)
        toolbar.title = "Markdown Preview"

        toolbar.setNavigationOnClickListener {
            if (!parentFragmentManager.popBackStackImmediate()) {
                activity?.onBackPressed()
            }
        }

    }
}