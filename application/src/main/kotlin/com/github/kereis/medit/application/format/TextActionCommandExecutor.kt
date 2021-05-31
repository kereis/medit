package com.github.kereis.medit.application.format

import com.github.kereis.medit.domain.editor.actions.Command
import com.github.kereis.medit.domain.editor.actions.CommandHistory

class TextActionCommandExecutor {

    private val history = CommandHistory()

    /**
     * Execute [Command] and add entry to [CommandHistory].
     *
     * @param command to be executed
     * @param currentContent that is going to be used as a snapshot/backup
     */
    fun executeCommand(command: Command, currentContent: List<String>) {
        history.push(command, currentContent)
        command.execute()
    }

    /**
     * Undo a [Command] and return backup state
     */
    fun undo() = history.pop()
}
