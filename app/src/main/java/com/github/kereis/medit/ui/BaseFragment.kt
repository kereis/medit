package com.github.kereis.medit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.kereis.medit.R

abstract class BaseFragment<VB : ViewBinding>(private val layoutId: Int) : Fragment(layoutId) {
    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB get() = _binding!! as VB

    protected var toolbar: Toolbar? = null
    protected var toolbarTitle: String? = "MeDit"

    protected lateinit var mContext: AppCompatActivity

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        mContext = activity as AppCompatActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    open fun initView() {
        if (view == null)
            return

        val newToolbar = requireView().findViewById<Toolbar>(R.id.toolbar)

        if (newToolbar != null) {
            if (toolbarTitle != null) {
                newToolbar.title = toolbarTitle
            }

            toolbar = newToolbar
            mContext.setSupportActionBar(toolbar)
        }
    }
}
