package com.github.kereis.medit.domain.editor.actions

import com.github.kereis.medit.domain.editor.FakeTextEditor
import org.junit.Assert
import org.junit.Test

class BlockTextActionCommandTest {

    @Test
    fun `Test execution`() {
        val textEditor = FakeTextEditor()
        val initialText = "Hello world!"
        textEditor.insert(0, initialText)
        textEditor.setCursor(initialText.length)

        val command = BlockTextActionCommand(textEditor, "--")
        command.execute()

        Assert.assertEquals(
            """
            $initialText
            --
            
            --
            
            """.trimIndent(),
            textEditor.text
        )
    }
}
