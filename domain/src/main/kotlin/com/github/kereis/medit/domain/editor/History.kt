package com.github.kereis.medit.domain.editor

/**
 * # History
 * Stores different states of data type [T]. Each operation is thread-safe.
 *
 * @author kereis
 */
class History<T> {
    private val historyList: ArrayList<T> = ArrayList()

    val nextStateAvailable: Boolean get() = isNextStateAvailable()
    val previousStateAvailable: Boolean get() = isPreviousStateAvailable()

    val iterator: Iterator<T> get() = historyList.iterator()

    @Synchronized
    fun add(element: T): Boolean {
        TODO("Not yet implemented")
    }

    @Synchronized
    fun remove(element: T): Boolean {
        TODO("Not yet implemented")
    }

    @Synchronized
    fun jumpToPreviousState(): T {
        TODO("Not yet implemented")
    }

    @Synchronized
    fun jumpToNextState(): T {
        TODO("Not yet implemented")
    }

    private fun isNextStateAvailable(): Boolean {
        TODO("Not yet implemented")
    }

    private fun isPreviousStateAvailable(): Boolean {
        TODO("Not yet implemented")
    }
}
