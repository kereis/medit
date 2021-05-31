package com.github.kereis.medit.ui.explorer

import com.github.kereis.medit.databinding.FragmentFileExplorerRecentListElementBinding
import com.github.kereis.medit.domain.explorer.files.FileReference
import com.github.kereis.medit.ui.AbstractViewHolder

class FileExplorerFileElementViewHolder(
    private var onClickListener: OnClickListener?,
    private val binding: FragmentFileExplorerRecentListElementBinding
) : AbstractViewHolder<FileReference>(binding.root) {

    interface OnClickListener {
        fun onFileClicked(clickedFile: FileReference)
        fun onFileLongClicked(clickedFile: FileReference)
    }

    override fun bind(item: FileReference) = with(binding) {
        fileExplorerRecentFilesElementName.text = item.fileName
        fileExplorerRecentFilesElementPath.text = item.rawFilePath
        fileExplorerRecentFilesElementLastAccess.text = item.lastAccess.toString()

        root.setOnClickListener {
            onClickListener?.onFileClicked(item)
        }
        root.setOnLongClickListener {
            onClickListener?.onFileLongClicked(item)
            return@setOnLongClickListener true
        }
    }
}
