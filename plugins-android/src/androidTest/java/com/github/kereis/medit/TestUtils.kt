package com.github.kereis.medit

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

object TestUtils {

    fun retrieveAppContext(): Context =
        InstrumentationRegistry.getInstrumentation().targetContext
}