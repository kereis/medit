package com.github.kereis.medit.ui.explorer.recent

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.kereis.medit.databinding.FragmentFileExplorerRecentListElementBinding
import com.github.kereis.medit.domain.explorer.files.File
import com.github.kereis.medit.ui.AbstractViewHolder

class FileExplorerRecentListAdapter(
    private val context: Context
) : RecyclerView.Adapter<AbstractViewHolder<*>>() {

    private var fileList = listOf<File>()

    fun setFileList(fileList: List<File>) {
        this.fileList = fileList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
        val itemBinding = FragmentFileExplorerRecentListElementBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return FileExplorerRecentListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
        when (holder) {
            is FileExplorerRecentListViewHolder -> holder.bind(fileList[position])
        }
    }

    override fun getItemCount(): Int = fileList.size

    private inner class FileExplorerRecentListViewHolder(
        private val binding: FragmentFileExplorerRecentListElementBinding
    ) : AbstractViewHolder<File>(binding.root) {

        override fun bind(item: File) = with(binding) {
            fileExplorerRecentFilesElementName.text = item.fileName
            fileExplorerRecentFilesElementPath.text = item.filePath
            fileExplorerRecentFilesElementLastAccess.text = item.lastAccess.toString()
        }
    }
}
