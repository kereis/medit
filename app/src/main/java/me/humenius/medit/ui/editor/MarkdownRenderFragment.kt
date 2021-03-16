package me.humenius.medit.ui.editor

import android.os.Bundle
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import me.humenius.medit.R
import me.humenius.medit.databinding.FragmentPreviewBinding
import me.humenius.medit.ui.BaseFragment

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