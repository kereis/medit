package me.humenius.medit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.dankito.richtexteditor.android.RichTextEditor
import net.dankito.richtexteditor.android.toolbar.EditorToolbar
import net.dankito.utils.android.permissions.IPermissionsService
import net.dankito.utils.android.permissions.PermissionsService

class TextEditorActivity : AppCompatActivity() {

    private lateinit var editor: RichTextEditor
    private lateinit var toolbar: EditorToolbar
    private var permissionsService: IPermissionsService = PermissionsService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        setupEditor()
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.editorToolbar)
        toolbar.editor = editor
    }

    private fun setupEditor() {
        editor = findViewById(R.id.editor)
        editor.permissionsService = permissionsService

        editor.setEditorFontFamily("sans-serif")
        editor.setEditorFontSize(20)
        editor.setPadding(4 * (resources.displayMetrics.density.toInt()))
    }

    override fun onBackPressed() {
        if (!toolbar.handlesBackButtonPress()) {
            super.onBackPressed()
        }
    }
}