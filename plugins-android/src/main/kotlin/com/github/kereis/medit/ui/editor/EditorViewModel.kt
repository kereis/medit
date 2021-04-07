package com.github.kereis.medit.ui.editor

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import timber.log.Timber

@HiltViewModel
class EditorViewModel
@Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _content = savedStateHandle.getLiveData("content", "")
    private val _selectionStart = savedStateHandle.getLiveData<Int>("selection_start")
    private val _selectionEnd = savedStateHandle.getLiveData<Int>("selection_end")

    val content: LiveData<String> = _content
    val selectionStart: LiveData<Int> = _selectionStart
    val selectionEnd: LiveData<Int> = _selectionEnd

    fun onContentChanged(newContent: String) {
        // Timber.d("onContentChanged: %s", newContent)
        _content.value = newContent
    }

    fun onSelectionChanged(start: Int, end: Int) {
        Timber.d("onSelectionChanged: %d, %d", start, end)
        _selectionStart.value = start
        _selectionEnd.value = end
    }
}
