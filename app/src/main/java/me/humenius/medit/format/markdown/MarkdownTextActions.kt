package me.humenius.medit.format.markdown

import me.humenius.medit.editor.BlockTextAction
import me.humenius.medit.editor.InlineSpanTextAction
import me.humenius.medit.editor.TextAction

enum class MarkdownTextActions(val textAction: TextAction) {
    BOLD(InlineSpanTextAction("**")),
    ITALIC(InlineSpanTextAction("*")),
    INLINE_CODE(InlineSpanTextAction("`")),
    LINK(InlineSpanTextAction("")),

    CODE_BLOCK(BlockTextAction("```")), QUOTE(BlockTextAction(">")),
    // PICTURE
    // Headers
}
