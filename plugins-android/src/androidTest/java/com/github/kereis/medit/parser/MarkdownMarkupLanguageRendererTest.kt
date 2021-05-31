package com.github.kereis.medit.parser

import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.kereis.medit.TestUtils.retrieveAppContext
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MarkdownMarkupLanguageRendererTest {

    @Test
    fun test_setTextForView() {
        val appContext = retrieveAppContext()
        val rawText = "bold text"
        val md = "**$rawText**"

        val textView = TextView(appContext)
        val markupLanguageRenderer =
            MarkdownMarkupLanguageRenderer
                .Factory()
                .newInstance(appContext, textView.textSize)

        markupLanguageRenderer.setTextForView(
            textView,
            md
        )

        Assert.assertEquals(rawText, textView.text.toString())
        // Assert.assertTrue(textView.typeface.isBold) TODO FIX
    }
}
