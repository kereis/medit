package me.humenius.medit.ui.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * **AbstractFragment**
 *
 * A base class for handling fragments
 *
 * @see Fragment
 * @author humenius
 * @version 1.0.0
 */
abstract class AbstractFragment(layoutId: Int) : Fragment(layoutId) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        initView()

        return view
    }

    /**
     * Initializes view
     */
    abstract fun initView()
}