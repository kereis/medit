package com.github.kereis.medit.format.markdown

import com.github.kereis.medit.editor.BlockTextAction
import com.github.kereis.medit.editor.InlineSpanTextAction
import com.github.kereis.medit.editor.TextAction

enum class MarkdownTextActions(val textAction: TextAction) {
    BOLD(InlineSpanTextAction("**")),
    ITALIC(InlineSpanTextAction("*")),
    INLINE_CODE(InlineSpanTextAction("`")),
    LINK(InlineSpanTextAction("")),

    CODE_BLOCK(BlockTextAction("```")), QUOTE(BlockTextAction(">")),
    // PICTURE
    // Headers
}
