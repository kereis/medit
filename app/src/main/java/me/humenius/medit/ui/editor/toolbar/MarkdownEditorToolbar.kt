package me.humenius.medit.ui.editor.toolbar

import android.content.Context
import android.util.AttributeSet
import net.dankito.richtexteditor.android.command.*
import net.dankito.richtexteditor.android.toolbar.EditorToolbar

class MarkdownEditorToolbar : EditorToolbar {

    constructor(context: Context) : super(context) { initToolbar() }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { initToolbar() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { initToolbar() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) { initToolbar() }

    private fun initToolbar() {
        addCommand(BoldCommand())
        addCommand(ItalicCommand())
        addCommand(UnderlineCommand())
        addCommand(StrikeThroughCommand())
        addCommand(RemoveFormatCommand())

        addSpace()

        addCommand(UndoCommand())
        addCommand(RedoCommand())

        addSpace()

        addCommand(InsertBulletListCommand())
        addCommand(InsertNumberedListCommand())
        addCommand(InsertLinkCommand())
        addCommand(InsertImageCommand())
        addCommand(InsertCheckboxCommand())

        addSpace()

        addSearchView()
    }
}
