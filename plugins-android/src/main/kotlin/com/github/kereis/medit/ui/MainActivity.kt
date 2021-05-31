package com.github.kereis.medit.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.ActivityMainBinding
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.ui.explorer.FileExplorerFileElementViewHolder
import com.github.kereis.medit.ui.explorer.recent.FileExplorerRecentListAdapter
import com.github.kereis.medit.ui.explorer.recent.FileExplorerRecentListFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity :
    AppCompatActivity(R.layout.activity_main),
    FileExplorerFileElementViewHolder.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }

        val recentListFragment = FileExplorerRecentListFragment()
        recentListFragment.setAdapter(FileExplorerRecentListAdapter(this, this))

        supportFragmentManager.commit {
            replace(binding.mainFragmentContainer.id, recentListFragment)
        }
    }

    private fun openEditorActivity(uri: Uri) {
        val intent = Intent(this, EditorActivity::class.java)

        val bundle = Bundle()
        bundle.putString("FILE_PATH", uri.toString())

        intent.putExtras(bundle)

        startActivity(intent)
    }

    override fun onFileClicked(clickedFile: FileReference) {
        openEditorActivity(Uri.parse(clickedFile.rawFilePath))
    }

    override fun onFileLongClicked(clickedFile: FileReference) {
        Timber.d("Long press on $clickedFile")
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
