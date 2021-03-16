package me.humenius.medit.ui.explorer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.humenius.medit.R
import me.humenius.medit.databinding.FragmentFileExplorerBinding
import me.humenius.medit.ui.BaseFragment

class FileExplorerFragment
    : BaseFragment<FragmentFileExplorerBinding>(R.layout.fragment_file_explorer) {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFileExplorerBinding
        get() = FragmentFileExplorerBinding::inflate

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        parentFragmentManager.commit {
            when (item.itemId) {
                R.id.navigation_fileExplorerRecentListFragment -> {
                    Log.println(Log.INFO, javaClass.name, "Committing navigation change to FileExplorerRecentListFragment")
                    replace(R.id.nav_host_fragment_container, FileExplorerRecentListFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_fileExplorerStorageListFragment -> {
                    Log.println(Log.INFO, javaClass.name, "Committing navigation change to FileExplorerStorageListFragment")
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