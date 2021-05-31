package com.github.kereis.medit.ui.explorer.recent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentFileExplorerRecentListBinding
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.ui.BaseFragment
import com.github.kereis.medit.ui.EditorActivity
import com.github.kereis.medit.ui.explorer.FileExplorerFileElementViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FileExplorerRecentListFragment :
    BaseFragment<FragmentFileExplorerRecentListBinding>
    (R.layout.fragment_file_explorer_recent_list),
    FileExplorerFileElementViewHolder.OnClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentFileExplorerRecentListBinding
        get() = FragmentFileExplorerRecentListBinding::inflate

    private val viewModel by activityViewModels<FileExplorerRecentListViewModel>()
    private lateinit var adapter: FileExplorerRecentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FileExplorerRecentListAdapter(requireContext(), this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshFileList()
    }

    override fun initView() {
        super.initView()

        binding.fileExplorerRecentFilesListView.layoutManager =
            LinearLayoutManager(requireContext())
        binding.fileExplorerRecentFilesListView.adapter = adapter

        viewModel.fetchFileList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.fileExplorerRecentFilesEmptyAlert.visibility = View.VISIBLE
            } else {
                binding.fileExplorerRecentFilesEmptyAlert.visibility = View.INVISIBLE
            }

            adapter.setFileList(it)
        }
    }

    override fun onFileClicked(clickedFile: FileReference) {
        openEditorActivity(Uri.parse(clickedFile.rawFilePath))
    }

    override fun onFileLongClicked(clickedFile: FileReference) {
        Timber.d("Long press on $clickedFile")
    }

    override fun onDelete(clickedFile: FileReference) {
        viewModel.deleteFileEntry(clickedFile)
        Timber.d("deleting entry for $clickedFile")
    }

    private fun openEditorActivity(uri: Uri) {
        val intent = Intent(requireContext(), EditorActivity::class.java)

        val bundle = Bundle()
        bundle.putString("FILE_PATH", uri.toString())

        intent.putExtras(bundle)

        startActivity(intent)
    }
}
