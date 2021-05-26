package com.github.kereis.medit.ui.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentPreviewBinding
import com.github.kereis.medit.parser.MarkdownMarkupLanguageRenderer
import com.github.kereis.medit.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MarkdownRenderFragment(
    private val textToDisplay: String
) : BaseFragment<FragmentPreviewBinding>(R.layout.fragment_preview) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentPreviewBinding = FragmentPreviewBinding::inflate

    @Inject
    lateinit var markdownRendererFactory: MarkdownMarkupLanguageRenderer.Factory

    override fun initView() {
        initToolbar()

        markdownRendererFactory
            .newInstance(requireContext(), binding.previewTextView.textSize)
            .setTextForView(binding.previewTextView, textToDisplay)
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
            Timber.w("Could not initialize toolbar.")
        }
    }
}
