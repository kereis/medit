package com.github.kereis.medit.ui.explorer.storage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.documentfile.provider.DocumentFile
import androidx.recyclerview.widget.RecyclerView
import com.github.kereis.medit.databinding.FragmentFileExplorerRecentListElementBinding
import com.github.kereis.medit.ui.AbstractViewHolder
import com.github.kereis.medit.ui.explorer.FileExplorerFileElementViewHolder

class FileExplorerStorageListAdapter(
    private val context: Context
) : RecyclerView.Adapter<AbstractViewHolder<*>>(),
    FileExplorerFileElementViewHolder.OnClickListener {

    private val directoryEntries = mutableListOf<DocumentFile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
        val itemBinding = FragmentFileExplorerRecentListElementBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return FileExplorerFileElementViewHolder(this, itemBinding)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
        when (holder) {
            is FileExplorerFileElementViewHolder -> holder.bind(directoryEntries[position])
        }
    }

    override fun getItemCount() = directoryEntries.size

    override fun onFileClicked(clickedFile: DocumentFile) {
        TODO("Not yet implemented")
    }

    override fun onFileLongClicked(clickedFile: DocumentFile) {
        TODO("Not yet implemented")
    }

    @Synchronized
    fun setEntries(files: List<DocumentFile>) {
        directoryEntries.clear()
        directoryEntries.addAll(files)
        notifyDataSetChanged()
    }
}
