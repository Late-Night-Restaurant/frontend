package com.example.simya.util

import android.util.Log
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("app:progressScaled")
fun setProgress(progressBar: ProgressBar, counter: Int){
    progressBar.progress = counter
    Log.d("progressbar","progress is $counter")
}