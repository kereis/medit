package com.github.kereis.medit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.databinding.ActivityEditorBinding
import com.github.kereis.medit.ui.editor.EditorFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EditorActivity : AppCompatActivity(R.layout.activity_editor) {
    private lateinit var binding: ActivityEditorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editorFragment = EditorFragment()

        intent.extras?.let { bundle ->
            Timber.d("Got file path in EditorActivity bundle: ${bundle.getString("FILE_PATH")}")
            editorFragment.arguments = bundle
        }

        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, editorFragment)
        }
    }
}
