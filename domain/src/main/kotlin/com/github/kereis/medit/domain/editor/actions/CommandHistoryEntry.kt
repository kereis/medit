package com.github.kereis.medit.domain.editor.actions

data class CommandHistoryEntry<E>(
    val command: Command,
    val backupState: E
)