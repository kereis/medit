package com.github.kereis.medit.application.format.markdown

import com.github.kereis.medit.application.editor.actions.BlockTextActionCommand
import com.github.kereis.medit.application.editor.actions.InlineTextActionCommand
import com.github.kereis.medit.domain.editor.TextEditor

// enum class MarkdownTextActionCommands(val command: Command) {
// BOLD(InlineTextActionCommand("**")),
// ITALIC(InlineTextActionCommand("*")),
// INLINE_CODE(InlineTextActionCommand("`")),
// LINK(InlineTextActionCommand("")),
//
// CODE_BLOCK(BlockTextActionCommand("```")),
// QUOTE(BlockTextActionCommand(">")),
// PICTURE
// Headers
// }

sealed class MarkdownTextActionCommands {

    class BoldCommand(textEditor: TextEditor) : InlineTextActionCommand(textEditor, "**")

    class ItalicCommand(textEditor: TextEditor) : InlineTextActionCommand(textEditor, "*")

    class InlineCodeCommand(textEditor: TextEditor) : InlineTextActionCommand(textEditor, "`")

    class CodeBlockCommand(textEditor: TextEditor) : BlockTextActionCommand(textEditor, "```")

    class QuoteBlockCommand(textEditor: TextEditor) : BlockTextActionCommand(textEditor, ">")

    class HeaderCommand(
        textEditor: TextEditor,
        level: Int
    ) : BlockTextActionCommand(textEditor, "#") {

        override val startBlock: String
        override val endBlock: String

        init {
            if (level > 6 || level <= 0) {
                throw Exception("level must be between 1 and 6")
            }

            startBlock = "#".repeat(level) + " "
            endBlock = "" // we don't need a whole block
        }
    }

    class LinkCommand(textEditor: TextEditor) : BlockTextActionCommand(textEditor, "") {

        override val startBlock = "["
        override val endBlock = "](link_here)"
    }

    class ImageCommand(textEditor: TextEditor) : BlockTextActionCommand(textEditor, "") {

        override val startBlock = "![alt_text"
        override val endBlock = "](link_here)"
    }

    class LatexCommand(textEditor: TextEditor) : BlockTextActionCommand(textEditor, "$$")
}