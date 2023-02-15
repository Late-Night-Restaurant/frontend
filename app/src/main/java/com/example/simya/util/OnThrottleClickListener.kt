package com.example.simya.util

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View

class OnThrottleClickListener(
    private val clickListener: View.OnClickListener,
    private val interval: Long = 500
):View.OnClickListener {
    private var clickable = true
    override fun onClick(v: View?) {
        if (clickable) {
            clickable = false
            v?.run {
                postDelayed({
                    clickable = true
                }, interval)
                clickListener.onClick(v)
            }
        } else {
            Log.d(TAG, "waiting for a while")
        }
    }
}
fun View.onThrottleClick(action: (v: View) -> Unit) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(listener))
}

fun View.onThrottleClick(action: (v: View) -> Unit, interval: Long) {
    val listener = View.OnClickListener { action(it) }
    setOnClickListener(OnThrottleClickListener(listener, interval))
}