package me.humenius.medit.ui.editor;

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import io.noties.markwon.AbstractMarkwonPlugin
import io.noties.markwon.Markwon

class MarkdownEditText : AppCompatEditText {

    private var markwon: Markwon

    constructor(context: Context) : super(context) {
        markwon = buildMarkwon(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        markwon = buildMarkwon(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        markwon = buildMarkwon(context)
    }

    private fun buildMarkwon(context: Context): Markwon {
        return Markwon.builder(context).build()
    }
}
