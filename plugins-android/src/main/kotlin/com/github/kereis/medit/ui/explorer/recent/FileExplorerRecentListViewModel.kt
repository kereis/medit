package com.github.kereis.medit.ui.explorer.recent

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.domain.explorer.files.RecentFileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class FileExplorerRecentListViewModel
@Inject constructor(
    private val recentFileRepository: RecentFileRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val currentFileList = savedStateHandle.getLiveData<File>("recent_files", null)

    val fetchFileList = currentFileList.distinctUntilChanged().switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            val list = recentFileRepository.getAll()
            Log.e(FileExplorerRecentListViewModel::javaClass.name, list.toString())
            emit(list)
        }
    }
}
