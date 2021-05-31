package com.github.kereis.medit.ui.explorer.recent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FileExplorerRecentListViewModel
@Inject constructor(
    private val recentFileRepository: RecentFileRepository,
    private val ioDispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentFileList =
        savedStateHandle.getLiveData<List<FileReference>>("recent_files", listOf())

    val fetchFileList = currentFileList.distinctUntilChanged().switchMap {
        liveData(viewModelScope.coroutineContext + ioDispatcher) {
            val list = recentFileRepository.getAll()
            Timber.d("Got list of files! %s", list.toString())
            emit(list)
        }
    }

    fun deleteFileEntry(fileReference: FileReference) = viewModelScope.launch(ioDispatcher) {
        recentFileRepository.delete(fileReference)
        currentFileList.postValue(recentFileRepository.getAll())
    }

    fun refreshFileList() = viewModelScope.launch(Dispatchers.IO) {
        currentFileList.postValue(recentFileRepository.getAll())
    }
}
