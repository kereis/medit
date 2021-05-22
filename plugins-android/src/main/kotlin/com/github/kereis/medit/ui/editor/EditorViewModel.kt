package com.github.kereis.medit.ui.editor

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.editor.History
import com.github.kereis.medit.domain.explorer.files.File
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.net.URI
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class EditorViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectionStart = savedStateHandle.getLiveData<Int>("selection_start")
    private val _selectionEnd = savedStateHandle.getLiveData<Int>("selection_end")
    private val _activeDocument =
        savedStateHandle.getLiveData<Document>(
            "active_document",
            Document(
                "NewFile",
                mutableListOf("My new content"),
                File(
                    null,
                    "NewFile.md",
                    "md",
                    java.io.File("").toPath(),
                    OffsetDateTime.now()
                ),
                History()
            )
        )

    val selectionStart: LiveData<Int> = _selectionStart
    val selectionEnd: LiveData<Int> = _selectionEnd
    val activeDocument: LiveData<Document> = _activeDocument

    fun onContentChanged(newContent: String) {
        // Timber.d("onContentChanged: %s", newContent)
        _activeDocument.value?.let {
            it.content = newContent.lines().toMutableList()
        }
    }

    fun onSelectionChanged(start: Int, end: Int) {
        Timber.d("onSelectionChanged: %d, %d", start, end)
        _selectionStart.value = start
        _selectionEnd.value = end
    }

    fun setActiveDocument(document: Document) {
        _activeDocument.value = document
    }

    fun updateDocumentFilePath(uri: Uri) {
        _activeDocument.value?.let { document ->
            _activeDocument.value =
                Document(
                    title = document.title,
                    content = document.content,
                    file = File.createByURI(URI(uri.toString())),
                    history = document.history
                )
        }
    }
}
