package com.github.kereis.medit.ui.explorer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.FragmentFileExplorerBinding
import com.github.kereis.medit.ui.BaseFragment
import com.github.kereis.medit.ui.explorer.recent.FileExplorerRecentListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class FileExplorerFragment :
    BaseFragment<FragmentFileExplorerBinding>(R.layout.fragment_file_explorer) {

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
                        Timber.i(
                            "Committing navigation change to FileExplorerStorageListFragment"
                        )
                        replace(R.id.nav_host_fragment_container, FileExplorerStorageListFragment())
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
}
