package com.github.kereis.medit.ui.editor

// import com.github.kereis.medit.application.files.FileStorageService
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.github.kereis.medit.R
import com.github.kereis.medit.application.format.markdown.MarkdownTextActions
import com.github.kereis.medit.databinding.FragmentEditorBinding
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.domain.explorer.files.AbstractFileLoader
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.parser.MarkdownParser
import com.github.kereis.medit.ui.BaseFragment
import com.github.kereis.medit.ui.components.SelectableEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.net.URI
import java.time.OffsetDateTime
import javax.inject.Inject

@AndroidEntryPoint
class EditorFragment :
    BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor),
    TextEditor,
    SelectableEditText.OnSelectionChangedListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditorBinding
        get() = FragmentEditorBinding::inflate

    // @Inject
    // lateinit var fileStorageService: FileStorageService

    @Inject
    lateinit var fileLoader: AbstractFileLoader

    private val viewModel by activityViewModels<EditorViewModel>()

    override fun initView() {
        toolbarTitle = "Edit file"
        readBundle()
        setup()
        super.initView()
    }

    override val selectionStart: Int
        get() = viewModel.selectionStart.value!!
    override val selectionEnd: Int
        get() = viewModel.selectionEnd.value!!
    override val text: String
        get() = viewModel.activeDocument.value!!.content.toString()

    override fun replace(startIndex: Int, endIndex: Int, text: String) {
        binding.contentInput.text?.replace(startIndex, endIndex, text)
    }

    override fun insert(atIndex: Int, text: String) {
        binding.contentInput.text?.insert(atIndex, text)
    }

    override fun setCursor(atIndex: Int) {
        binding.contentInput.setSelection(atIndex)
    }

    override fun load(document: Document) {
        viewModel.setActiveDocument(document)
    }

    override fun save() {
        TODO("Not yet implemented")
    }

    override fun saveAs(file: File) {
        TODO("Not yet implemented")
    }

    private fun readBundle() {
        Timber.d("Reading bundle")

        arguments?.let {
            it.getString("FILE_PATH")?.let { rawUri ->
                Timber.d("Got FILE_PATH from bundle: $rawUri")
                runBlocking {
                    fileLoader.load(URI(rawUri))?.let { document ->
                        Timber.d("Document $rawUri loaded -> $document")
                        viewModel.setActiveDocument(document)
                    }
                }
            }
        }
    }

    private val fileSaverIntent =
        registerForActivityResult(ActivityResultContracts.CreateDocument()) { uri ->
            Timber.d("fileSaverIntent = $uri, ${uri.path}")

            requireContext().contentResolver.query(
                uri,
                null,
                null,
                null,
                null
            )
                ?.apply {
                    val nameIndex = getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    moveToFirst()
                    val fileName = getString(nameIndex)
                    val newDocument = Document(

                        title = viewModel.activeDocument.value?.title ?: "",
                        content = viewModel.activeDocument.value?.content ?: "",
                        file = File(
                            id = null,
                            fileName = fileName,
                            filePath = URI(uri.toString()),
                            lastAccess = OffsetDateTime.now()
                        )
                    )
                    viewModel.setActiveDocument(newDocument)
                    close()
                }

            viewModel.activeDocument.value?.let { document ->
                runBlocking {
                    try {
                        val saveSuccessful = fileLoader.save(document)

                        if (saveSuccessful) {
                            Toast.makeText(
                                this@EditorFragment.mContext,
                                "Saved!",
                                Toast.LENGTH_SHORT
                            ).show()

                            viewModel.setActiveDocument(document)
                        } else {
                            Toast.makeText(
                                this@EditorFragment.mContext,
                                "Failed!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: Exception) {
                        Timber.e(e)
                        Toast.makeText(
                            this@EditorFragment.mContext,
                            "Failed! ${e.localizedMessage} - $uri",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

    private fun setup() {
        binding.actionBarBold.setOnClickListener {
            MarkdownTextActions.BOLD.textAction.apply(this)
        }
        binding.actionBarItalics.setOnClickListener {
            MarkdownTextActions.ITALIC.textAction.apply(this)
        }
        binding.actionBarInlineCode.setOnClickListener {
            MarkdownTextActions.INLINE_CODE.textAction.apply(this)
        }

        binding.fabRenderMd.setOnClickListener {
            val view = MarkdownParser.render(requireContext(), binding.contentInput.text.toString())
            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_container, view)
                addToBackStack(null)
            }
        }

        binding.contentInput.setText(viewModel.content.value.toString())

        binding.contentInput.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    viewModel.onContentChanged(s.toString())
                }
            }
        )

        binding.editorDebugSave.setOnClickListener {
            fileSaverIntent.launch("FILE.md")
        }

        binding.contentInput.addOnSelectionChangedListener(this)
    }

    override fun onSelectionChanged(start: Int, end: Int) {
        viewModel.onSelectionChanged(start, end)
    }
}
