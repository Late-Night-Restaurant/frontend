package com.example.simya.util

import android.util.Log
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
    fun setProgress(progressBar: ProgressBar, counter: Int, max: Int) {
        Log.d("progressbar", "counter : $counter , max : $max")
        progressBar.progress = (counter)
    }
}