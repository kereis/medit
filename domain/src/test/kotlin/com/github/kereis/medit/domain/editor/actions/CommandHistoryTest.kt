package com.github.kereis.medit.domain.editor.actions

import com.github.kereis.medit.domain.editor.FakeTextEditor
import com.github.kereis.medit.domain.editor.TextEditor
import org.junit.Assert
import org.junit.Test

class CommandHistoryTest {

    @Test
    fun `Push and pop entry and check backed up state`() {
        val history = CommandHistory()
        val textEditor = FakeTextEditor()
        val command = TestDollarTextActionCommand(textEditor)
        val testList1 = listOf("Hi")
        val testList2 = listOf("Hi", "there!")

        history.push(command, testList1)
        history.push(command, testList2)

        Assert.assertEquals(testList2, history.pop().backupState)
        Assert.assertEquals(testList1, history.pop().backupState)
    }

    @Test
    fun `Push and pop entries and check order`() {
        val history = CommandHistory()
        val textEditor = FakeTextEditor()
        val insertOrder = arrayOf(
            TestDollarTextActionCommand(textEditor),
            TestAmpersandTextActionCommand(textEditor)
        )
        val popOrder = insertOrder.clone().apply { reverse() }

        insertOrder.map { history.push(it, listOf()) }

        popOrder.forEach {
            Assert.assertEquals(it, history.pop().command)
        }
    }

    private inner class TestDollarTextActionCommand(
        textEditor: TextEditor
    ) : InlineTextActionCommand(textEditor, "$")

    private inner class TestAmpersandTextActionCommand(
        textEditor: TextEditor
    ) : InlineTextActionCommand(textEditor, "&")
}
