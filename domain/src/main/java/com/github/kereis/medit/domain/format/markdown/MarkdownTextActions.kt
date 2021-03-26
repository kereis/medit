package com.github.kereis.medit.domain.format.markdown

import com.github.kereis.medit.domain.editor.actions.BlockTextAction
import com.github.kereis.medit.domain.editor.actions.InlineSpanTextAction
import com.github.kereis.medit.domain.editor.actions.TextAction

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
