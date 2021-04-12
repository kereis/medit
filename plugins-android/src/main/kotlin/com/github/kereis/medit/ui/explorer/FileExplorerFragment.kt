package com.github.kereis.medit.ui.explorer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentFileExplorerBinding
import com.github.kereis.medit.ui.BaseFragment
import com.github.kereis.medit.ui.EditorActivity
import com.github.kereis.medit.ui.explorer.recent.FileExplorerRecentListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class FileExplorerFragment :
    BaseFragment<FragmentFileExplorerBinding>(R.layout.fragment_file_explorer) {

    private lateinit var getFilePath: ActivityResultLauncher<String>

    override val bindingInflater:
            (LayoutInflater, ViewGroup?, Boolean) -> FragmentFileExplorerBinding
        get() = FragmentFileExplorerBinding::inflate

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            parentFragmentManager.commit {
                when (item.itemId) {
                    R.id.navigation_fileExplorerRecentListFragment -> {
                        Timber.i(
                            "Committing navigation change to FileExplorerRecentListFragment"
                        )
                        replace(R.id.nav_host_fragment_container, FileExplorerRecentListFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_fileExplorerStorageListFragment -> {
                        // Timber.i(
                        //     "Committing navigation change to FileExplorerStorageListFragment"
                        // )
                        // replace(R.id.nav_host_fragment_container, FileExplorerStorageListFragment())
                        Timber.i(
                            "Opening file opening intent in FileExplorerStorageListFragment"
                        )
                        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                            type = "text/markdown"
                            addCategory(Intent.CATEGORY_OPENABLE)
                        }

                        getFilePath =
                            registerForActivityResult(ActivityResultContracts.GetContent()) {
                                openEditorActivity(it)
                            }
                        return@OnNavigationItemSelectedListener true
                    }
                }
            }

            false
        }

    override fun initView() {
        toolbarTitle = "File Explorer"

        // val navHostFragment = binding.fileExplorerNavHostFragment
        //
        // val navController = navHostFragment?.findNavController()
        //
        // if (navController != null) {
        //     binding.fileExplorerNavView.setupWithNavController(navController)
        // } else {
        //     Log.println(Log.WARN, javaClass.name, "Cannot instantiate navigation")
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
}
