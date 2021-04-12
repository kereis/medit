package com.github.kereis.medit.ui.explorer

import androidx.documentfile.provider.DocumentFile
import com.github.kereis.medit.databinding.FragmentFileExplorerRecentListElementBinding
import com.github.kereis.medit.ui.AbstractViewHolder

internal class FileExplorerFileElementViewHolder(
    private var onClickListener: OnClickListener?,
    private val binding: FragmentFileExplorerRecentListElementBinding
) : AbstractViewHolder<DocumentFile>(binding.root) {

    interface OnClickListener {
        fun onFileClicked(clickedFile: DocumentFile)
        fun onFileLongClicked(clickedFile: DocumentFile)
    }

    override fun bind(item: DocumentFile) = with(binding) {
        fileExplorerRecentFilesElementName.text = item.name
        fileExplorerRecentFilesElementPath.text = item.uri.toString()
        fileExplorerRecentFilesElementLastAccess.text = item.lastModified().toString()

        root.setOnClickListener {
            onClickListener?.onFileClicked(item)
        }
        root.setOnLongClickListener {
            onClickListener?.onFileLongClicked(item)
            return@setOnLongClickListener true
        }
    }
}