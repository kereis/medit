package com.github.kereis.medit.ui.explorer.storage

import android.content.ActivityNotFoundException
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentFileExplorerStorageListBinding
import com.github.kereis.medit.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FileExplorerStorageListFragment :
    BaseFragment<FragmentFileExplorerStorageListBinding>
        (R.layout.fragment_file_explorer_storage_list) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentFileExplorerStorageListBinding
        get() = FragmentFileExplorerStorageListBinding::inflate

    private val viewModel by activityViewModels<FileExplorerStorageListViewModel>()

    private lateinit var adapter: FileExplorerStorageListAdapter

    override fun initView() {
        super.initView()

        adapter = FileExplorerStorageListAdapter(requireContext())

        binding.fileExplorerStorageListView.adapter = adapter

        viewModel.files.observe(this, Observer { files ->
            files?.let { adapter.setEntries(files) }
        })

        viewModel.openDirectory.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { directory ->
                // (activity as MainActivity).showStorageList(directory.uri)
            }
        })

        viewModel.openFile.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let { documentFile ->
                openDocument(documentFile)
            }
        })
    }

    private fun openDocument(documentFile: DocumentFile) {
        try {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                data = documentFile.uri
            }
            startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            Timber.e(anfe)
        }
    }
}
