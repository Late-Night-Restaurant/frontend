package com.example.simya.sharedpreferences

import android.app.Application

class Shared : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }
    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}