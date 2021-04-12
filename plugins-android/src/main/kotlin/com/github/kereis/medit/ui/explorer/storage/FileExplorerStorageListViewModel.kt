package com.github.kereis.medit.ui.explorer.storage

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.kereis.medit.ui.explorer.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FileExplorerStorageListViewModel
@Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _files = MutableLiveData<List<DocumentFile>>()
    val files = _files

    private val _openDirectory = MutableLiveData<Event<DocumentFile>>()
    val openDirectory = _openDirectory

    private val _openFile = MutableLiveData<Event<DocumentFile>>()
    val openFile = _openFile

    fun loadDirectory(directoryUri: Uri) {
        val tree = DocumentFile.fromTreeUri(context, directoryUri) ?: return
        val children = tree.listFiles()

        viewModelScope.launch {
            val sortedList = withContext(Dispatchers.IO) {
                children.toMutableList().apply {
                    sortBy { it.name }
                }
            }

            _files.postValue(sortedList)
        }
    }

    fun openDocument(document: DocumentFile) {
        if (document.isDirectory) {
            openDirectory.postValue(Event(document))
        } else {
            openFile.postValue(Event(document))
        }
    }
}