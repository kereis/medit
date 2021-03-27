package com.github.kereis.medit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.ActivityEditorBinding
import com.github.kereis.medit.ui.explorer.FileExplorerFragment

class EditorActivity : AppCompatActivity(R.layout.activity_editor) {
    private lateinit var binding: ActivityEditorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace(R.id.fragment_container, FileExplorerFragment())
        }
    }
}
