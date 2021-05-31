package com.github.kereis.medit.ui.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kereis.medit.application.files.FileReferenceAccessDateUpdater
import com.github.kereis.medit.domain.editor.Document
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.OffsetDateTime
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class EditorViewModel
@Inject constructor(
    private val recentFileRepository: RecentFileRepository,
    private val ioDispatcher: CoroutineDispatcher,
    private val fileRefDataUpdater: FileReferenceDataUpdater,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectionStart = savedStateHandle.getLiveData<Int>("selection_start")
    private val _selectionEnd = savedStateHandle.getLiveData<Int>("selection_end")
    private val _activeDocument =
        savedStateHandle.getLiveData<Document>(
            "active_document",
            Document(
                "NewFile",
                "",
                FileReference(
                    null,
                    "",
                    "",
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
        _selectionStart.value = start
        _selectionEnd.value = end
    }

    fun setActiveDocument(document: Document) = viewModelScope.launch(ioDispatcher) {
        recentFileRepository.getByURI(document.fileReference.filePath)?.let {

            _activeDocument.postValue(
                Document(
                    document.title,
                    document.content,
                    it
                )
            )

            recentFileRepository.update(fileRefDataUpdater.updateTimestamp(it))
        } ?: run {

            val id =
                recentFileRepository.insert(
                    fileRefDataUpdater.updateTimestamp(document.fileReference)
                )[0]
            _activeDocument.postValue(
                Document(
                    document.title,
                    document.content,
                    FileReference(
                        id,
                        document.fileReference.fileName,
                        document.fileReference.rawFilePath,
                        document.fileReference.lastAccess
                    )
                )
            )
        }

        _content.postValue(document.content)
    }
}
