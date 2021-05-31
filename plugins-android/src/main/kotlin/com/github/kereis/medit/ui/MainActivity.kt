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
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.ui.explorer.recent.FileExplorerRecentListFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import timber.log.Timber

@AndroidEntryPoint
class MainActivity :
    AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar

    @Inject
    lateinit var recentFileReference: RecentFileRepository

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

        binding.mainIntroFragmentButtonFilePicker.setOnClickListener {
            filePickerRequest.launch(arrayOf("text/*"))
        }

        binding.mainIntroFragmentButtonExampleDoc.setOnClickListener {
            openEditorActivityWithIntro()
        }

        supportFragmentManager.commit {
            replace(binding.mainFragmentContainer.id, FileExplorerRecentListFragment())
        }
    }

    private fun openEditorActivityWithIntro() {
        val intent = Intent(this, EditorActivity::class.java)

        val bundle = Bundle()
        bundle.putBoolean("INTRO", true)

        intent.putExtras(bundle)

        startActivity(intent)
    }

    private fun openEditorActivity(uri: Uri) {
        val intent = Intent(this, EditorActivity::class.java)

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
