package me.humenius.medit.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.humenius.medit.R
import me.humenius.medit.databinding.ActivityMainBinding
import me.humenius.medit.ui.explorer.FileExplorerRecentListFragment
import me.humenius.medit.ui.explorer.FileExplorerStorageListFragment

class MainActivity
    : AppCompatActivity(R.layout.activity_main),
      BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sectionPagerAdapter: FragmentStateAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager = binding.mainViewPager
        viewPager.adapter = sectionPagerAdapter
        viewPager.offscreenPageLimit = binding.bottomNavigationBar.menu.size

        binding.bottomNavigationBar.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_fileExplorerRecentListFragment -> {
                binding.mainViewPager.currentItem = 0
                toolbar.setTitle(R.string.file_explorer_nav_title_recent)
                return true
            }
            R.id.navigation_fileExplorerStorageListFragment -> {
                binding.mainViewPager.currentItem = 1
                toolbar.setTitle(R.string.file_explorer_nav_title_storage)
                return true
            }
        }

        return false
    }

    inner class SectionPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return binding.bottomNavigationBar.menu.size
        }

        override fun createFragment(position: Int): Fragment {
            when (binding.bottomNavigationBar.menu[position].itemId) {
                R.id.navigation_fileExplorerStorageListFragment -> return FileExplorerStorageListFragment()
                R.id.navigation_fileExplorerRecentListFragment -> return FileExplorerRecentListFragment()
            }

            return FileExplorerRecentListFragment()
        }
    }
}