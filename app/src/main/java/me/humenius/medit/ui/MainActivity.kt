package me.humenius.medit.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import me.humenius.medit.R
import me.humenius.medit.databinding.ActivityMainBinding
import me.humenius.medit.ui.editor.EditorFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.commit {
            replace(R.id.fragment_container, EditorFragment())
        }
    }
}
