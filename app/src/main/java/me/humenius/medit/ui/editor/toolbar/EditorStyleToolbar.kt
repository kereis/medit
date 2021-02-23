package me.humenius.medit.ui.editor.toolbar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//abstract class EditorStyleToolbar(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
//    LinearLayout(context, attrs, defStyleAttr) {
//
//    private var styleToolbarAdapter: Any
//    private var styleButtons: ArrayList<StyleButton>
//    private var recyclerView: RecyclerView? = RecyclerView(context)
//
//    init {
//        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//        recyclerView?.layoutManager = linearLayoutManager
//
//        styleButtons = ArrayList()
//        fillStyleButtonsList()
//
//        styleToolbarAdapter = StyleToolbarAdapter(styleButtons)
//        recyclerView?.adapter = styleToolbarAdapter
//    }
//
//    private fun fillStyleButtonsList() {
//
//    }
//}