package com.github.kereis.medit.domain.editor.actions

import com.github.kereis.medit.domain.editor.FakeTextEditor
import org.junit.Assert
import org.junit.Test

class InlineTextActionCommandTest {

    @Test
    fun `Test execution without selection`() {
        val textEditor = FakeTextEditor()
        val initialText = "Hello world!"
        textEditor.insert(0, initialText)
        textEditor.setCursor(initialText.length)

        val command = InlineTextActionCommand(textEditor, "++")
        command.execute()

        Assert.assertEquals("$initialText++++", textEditor.text)
    }

    @Test
    fun `Test execution with selection`() {
        val textEditor = FakeTextEditor()
        val initialText = "Hello world!"
        textEditor.insert(0, initialText)
        textEditor.setSelection(6, 12) // world!

        val command = InlineTextActionCommand(textEditor, "++")
        command.execute()

        Assert.assertEquals("Hello ++world!++", textEditor.text)
    }
}
