package com.github.kereis.medit.ui.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.explorer.files.FileReference
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.net.URI
import java.time.OffsetDateTime
import javax.inject.Inject

@HiltViewModel
class EditorViewModel
@Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectionStart = savedStateHandle.getLiveData<Int>("selection_start")
    private val _selectionEnd = savedStateHandle.getLiveData<Int>("selection_end")
    private val _activeDocument =
        savedStateHandle.getLiveData<Document>(
            "active_document",
            Document(
                "NewFile",
                "My new content",
                FileReference(
                    null,
                    "NewFile.md",
                    URI(""),
                    OffsetDateTime.now()
                )
            )
        )
    private val _content = MutableLiveData<String>("")

    val selectionStart: LiveData<Int> = _selectionStart
    val selectionEnd: LiveData<Int> = _selectionEnd
    val activeDocument: LiveData<Document> = _activeDocument
    val content: LiveData<String> = _content

    fun onContentChanged(newContent: String) {
        _activeDocument.value?.let {
            it.content = newContent
        }
        _content.value = newContent
    }

    fun onSelectionChanged(start: Int, end: Int) {
        Timber.d("onSelectionChanged: %d, %d", start, end)
        _selectionStart.value = start
        _selectionEnd.value = end
    }

    fun setActiveDocument(document: Document) {
        _activeDocument.value = document
        _content.value = document.content
    }
}
