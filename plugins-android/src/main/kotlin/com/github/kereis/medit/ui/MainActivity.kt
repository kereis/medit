package com.github.kereis.medit.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.ActivityMainBinding
import com.github.kereis.medit.ui.explorer.FileExplorerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    AppCompatActivity(R.layout.activity_main) {

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

        supportFragmentManager.commit {
            replace(binding.mainContainer.id, FileExplorerFragment())
        }
    }

    private fun openEditorActivity(uri: Uri) {
        val intent = Intent(this, EditorActivity::class.java)

        val bundle = Bundle()
        bundle.putString("FILE_PATH", uri.toString())

        intent.putExtras(bundle)

        startActivity(intent)
    }
}
