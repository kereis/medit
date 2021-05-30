package com.github.kereis.medit.domain.editor.actions

import com.github.kereis.medit.domain.editor.History

class CommandHistory {

    private val history =
        History<CommandHistoryEntry<List<String>>>()

    fun push(
        command: Command,
        backupState: List<String>
    ): Boolean =
        history.add(
            CommandHistoryEntry(command, backupState)
        )

    fun pop(): CommandHistoryEntry<List<String>> =
        history.jumpToPreviousState()
}