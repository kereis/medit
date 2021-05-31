package com.github.kereis.medit.ui.editor

import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.github.kereis.medit.R
import com.github.kereis.medit.application.format.TextActionCommandExecutor
import com.github.kereis.medit.databinding.FragmentEditorBinding
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.editor.TextEditor
import com.github.kereis.medit.domain.explorer.files.FileLoader
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import com.github.kereis.medit.domain.format.markdown.MarkdownTextActionCommands
import com.github.kereis.medit.ui.BaseFragment
import com.github.kereis.medit.ui.components.SelectableEditText
import com.github.kereis.medit.ui.components.ToastService
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileNotFoundException
import java.net.URI
import java.time.OffsetDateTime
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@AndroidEntryPoint
class EditorFragment :
    BaseFragment<FragmentEditorBinding>(R.layout.fragment_editor),
    TextEditor,
    SelectableEditText.OnSelectionChangedListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentEditorBinding
        get() = FragmentEditorBinding::inflate

    @Inject
    lateinit var fileLoader: FileLoader

    @Inject
    lateinit var textActionCommandExecutor: TextActionCommandExecutor

    @Inject
    lateinit var toastService: ToastService

    @Inject
    lateinit var recentFileRepository: RecentFileRepository

    @Inject
    lateinit var ioDispatcher: CoroutineDispatcher

    private lateinit var headerLevelSelectionDialog: AlertDialog

    private val viewModel by activityViewModels<EditorViewModel>()

    override fun initView() {
        toolbarTitle = "Edit file"
        readBundle()
        setup()
        super.initView()

        var selection = 1
        headerLevelSelectionDialog = AlertDialog.Builder(requireContext())
            .setTitle("Select header level")
            .setPositiveButton("Select") { _, _ ->
                textActionCommandExecutor.executeCommand(
                    MarkdownTextActionCommands.HeaderCommand(this, selection),
                    text.split("\n")
                )
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setSingleChoiceItems(arrayOf("1", "2", "3", "4", "5"), 0) { _, item ->
                selection = item + 1
            }
            .create()
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

    override fun saveAs(fileReference: FileReference) {
        TODO("Not yet implemented")
    }

    private fun readBundle() {
        Timber.d("Reading bundle")

        arguments?.let {
            it.getString("FILE_PATH")?.let { rawUri ->

                Timber.d("Got FILE_PATH from bundle: $rawUri")

                runBlocking {
                    try {
                        fileLoader.load(URI(rawUri))?.let { document ->
                            Timber.d("Document $rawUri loaded -> $document")
                            viewModel.setActiveDocument(document)
                            binding.contentInput.setText(document.content)
                        }
                    } catch (fnfex: FileNotFoundException) {
                        Timber.e("could not file while starting activity: $fnfex")
                        toastService.showLong(
                            "Could not find file with path $rawUri. Does the file exist?"
                        )
                        requireActivity().finish()
                        return@runBlocking
                    }
                }
            }

            if (it.getBoolean("INTRO")) {
                Timber.d("Running INTRO mode")

                lifecycleScope.launch(Dispatchers.Main) {
                    try {
                        requireContext().assets.open("INTRO.md").use { stream ->
                            stream.bufferedReader().use { reader ->
                                val content = reader.readLines().joinToString("\n")
                                viewModel.onContentChanged(
                                    content
                                )
                                binding.contentInput.setText(content)
                            }
                        }
                    } catch (e: Exception) {
                        throw IllegalStateException(
                            "trying to run INTRO mode but no INTRO.md found; $e"
                        )
                    }
                }
            }
        }
    }

    private val fileSaverIntent =
        registerForActivityResult(ActivityResultContracts.CreateDocument()) { uri ->
            if (uri == null) return@registerForActivityResult

            Timber.d("fileSaverIntent = $uri, ${uri.path}")

            var newDocument: Document? = null
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
                    newDocument = Document(

                        title = viewModel.activeDocument.value?.title ?: "",
                        content = viewModel.activeDocument.value?.content ?: "",
                        fileReference = FileReference(
                            id = null,
                            fileName = fileName,
                            rawFilePath = uri.toString(),
                            lastAccess = OffsetDateTime.now()
                        )
                    )
                    viewModel.setActiveDocument(newDocument!!)
                    close()
                }

            newDocument?.let {
                runBlocking {
                    try {
                        val saveSuccessful = fileLoader.save(it)

                        if (saveSuccessful) {
                            toastService.showShort("Saved!")
                        } else {
                            toastService.showShort("Saving failed!")
                        }
                    } catch (e: Exception) {
                        Timber.e(e)
                        toastService.showLong("Failed! ${e.localizedMessage} - $uri")
                    }
                }
            } ?: run {
                toastService.showLong("Could not save file.")
                Timber.w("Could not save $newDocument?")
            }
        }

    private fun setup() {
        binding.actionBarBold.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.BoldCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarItalics.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.ItalicCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarInlineCode.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.InlineCodeCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarBlockCode.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.CodeBlockCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarQuote.setOnClickListener {
            // MarkdownTextActions.INLINE_CODE.textAction.apply(this)
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.QuoteBlockCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarLink.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.LinkCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarLatex.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.LatexCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarImage.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.ImageCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarStrikeThrough.setOnClickListener {
            textActionCommandExecutor.executeCommand(
                MarkdownTextActionCommands.StrikeThroughCommand(this),
                text.split("\n")
            )
        }
        binding.actionBarHeader.setOnClickListener {
            headerLevelSelectionDialog.show()
        }

        binding.fabRenderMd.setOnClickListener {
            val view = MarkdownRenderFragment(binding.contentInput.text.toString())

            activity?.supportFragmentManager?.commit {
                replace(R.id.fragment_container, view)
                addToBackStack(null)
            }
        }

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
