package com.github.kereis.medit.ui.editor

import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentPreviewBinding
import com.github.kereis.medit.ui.BaseFragment

class MarkdownRenderFragment(private val textToDisplay: Spanned)
    : BaseFragment<FragmentPreviewBinding>(R.layout.fragment_preview) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
        -> FragmentPreviewBinding = FragmentPreviewBinding::inflate

    override fun initView() {
        initToolbar()

        binding.previewTextView.text = textToDisplay
    }

    private fun initToolbar() {
        val newToolbar = requireView().findViewById<Toolbar>(R.id.toolbar)

        if (newToolbar != null) {
            newToolbar.title = "Markdown Preview"
            newToolbar.setNavigationIcon(R.drawable.ic_dashboard_black_24dp)
            newToolbar.setNavigationOnClickListener {
                if (!parentFragmentManager.popBackStackImmediate())
                    activity?.onBackPressed()
            }

            toolbar = newToolbar
        } else {
            Log.println(Log.WARN, javaClass.name, "Could not initialize toolbar.")
        }
    }
}