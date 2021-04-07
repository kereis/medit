package com.github.kereis.medit.ui.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

/**
 * # SelectableEditText
 *
 * This custom [AppCompatEditText] allows adding [OnSelectionChangedListener]s
 * for selection changes.
 */
class SelectableEditText : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    interface OnSelectionChangedListener {
        fun onSelectionChanged(start: Int, end: Int)
    }

    private val listeners: ArrayList<OnSelectionChangedListener> = ArrayList()

    fun addOnSelectionChangedListener(listener: OnSelectionChangedListener) {
        listeners.add(listener)
    }

    fun removeOnSelectionChangedListener(listener: OnSelectionChangedListener) {
        listeners.remove(listener)
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)

        listeners?.forEach { it.onSelectionChanged(selStart, selEnd) }
    }
}