package com.github.kereis.medit.ui.explorer.recent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentFileExplorerRecentListBinding
import com.github.kereis.medit.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FileExplorerRecentListFragment :
    BaseFragment<FragmentFileExplorerRecentListBinding>
    (R.layout.fragment_file_explorer_recent_list) {

    // TODO Fix ktlint heavily enforced linebreaks -> Exclude explicitly?
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean)
    -> FragmentFileExplorerRecentListBinding
        get() = FragmentFileExplorerRecentListBinding::inflate

    private val viewModel by activityViewModels<FileExplorerRecentListViewModel>()
    private lateinit var adapter: FileExplorerRecentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = FileExplorerRecentListAdapter(requireContext())
    }

    override fun initView() {
        super.initView()

        binding.fileExplorerRecentFilesListView.layoutManager = LinearLayoutManager(
            requireContext()
        )
        binding.fileExplorerRecentFilesListView.adapter = adapter

        viewModel.fetchFileList.observe(
            viewLifecycleOwner,
            {
                //adapter.setFileList(it)
            }
        )
    }
}
