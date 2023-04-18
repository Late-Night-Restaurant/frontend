package com.example.simya.util

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.simya.R
import com.example.simya.databinding.SnackbarLayoutBinding
import com.google.android.material.snackbar.Snackbar

class SampleSnackBar(view: View, private val message: String) {
    companion object {
        fun make(view: View, message: String) = SampleSnackBar(view, message)
    }

    private val context = view.context
    private val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    private val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout

    private val inflater = LayoutInflater.from(context)
    private val snackBarBinding = SnackbarLayoutBinding.inflate(inflater)
//    private val snackBarBinding = DataBindingUtil.setContentView(, R.layout.snackbar_layout)

    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackBarLayout) {
            removeAllViews()
            setPadding(50, 0, 50, 50)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(snackBarBinding.root, 0)
        }
    }

    private fun initData() {
        snackBarBinding.snackBarMessage.text = message
    }

    fun show() {
        snackBar.show()
    }
}