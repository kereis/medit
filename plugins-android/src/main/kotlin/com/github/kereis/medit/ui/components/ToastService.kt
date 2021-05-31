package com.github.kereis.medit.ui.components

import android.content.Context
import android.widget.Toast

class ToastService(
    private val context: Context
) {

    fun showLong(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    fun showShort(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}