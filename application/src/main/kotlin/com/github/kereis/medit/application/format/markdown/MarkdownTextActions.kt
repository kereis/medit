package com.github.kereis.medit.application.format.markdown

import com.github.kereis.medit.application.editor.actions.BlockTextAction
import com.github.kereis.medit.application.editor.actions.InlineSpanTextAction
import com.github.kereis.medit.domain.editor.actions.TextAction

@Deprecated("Use MarkdownTextActionCommands instead")
enum class MarkdownTextActions(val textAction: TextAction) {
    BOLD(InlineSpanTextAction("**")),
    ITALIC(InlineSpanTextAction("*")),
    INLINE_CODE(InlineSpanTextAction("`")),
    LINK(InlineSpanTextAction("")),

    CODE_BLOCK(BlockTextAction("```")),
    QUOTE(BlockTextAction(">")),
    // PICTURE
    // Headers
}
