package com.github.kereis.medit.ui.explorer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentFileExplorerBinding
import com.github.kereis.medit.ui.BaseFragment
import com.github.kereis.medit.ui.EditorActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FileExplorerFragment :
    BaseFragment<FragmentFileExplorerBinding>(R.layout.fragment_file_explorer) {

    override val bindingInflater:
            (LayoutInflater, ViewGroup?, Boolean) -> FragmentFileExplorerBinding
        get() = FragmentFileExplorerBinding::inflate

    override fun initView() {
        toolbarTitle = "File Explorer"

        // binding.fileExplorerNavView.setOnNavigationItemSelectedListener { menuItem ->
        //     when (menuItem.itemId) {
        //         R.id.navigation_fileExplorerStorageListFragment -> {
        //             filePickerRequest.launch(arrayOf("text/*"))
        //         }
        //     }
        //
        //     false
        // }

        // val recentListFragment = FileExplorerRecentListFragment()
        // recentListFragment.setAdapter(FileExplorerRecentListAdapter(requireContext(), this))
        //
        // requireActivity().supportFragmentManager.commit {
        //     replace(binding.fileExplorerFragmentContainer.id, recentListFragment)
        // }

        super.initView()
    }

    private fun openEditorActivity(uri: Uri) {
        val intent = Intent(requireActivity(), EditorActivity::class.java)

        val bundle = Bundle()
        bundle.putString("FILE_PATH", uri.toString())

        intent.putExtras(bundle)

        startActivity(intent)
    }

    private val filePickerRequest =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            Timber.d("filePickerRequest = $uri, starting EditorActivity")

            if (uri != null) {
                openEditorActivity(uri)
            } else {
                Timber.d("Won't start EditorActivity as filePickerRequest returned null")
            }
        }
}
