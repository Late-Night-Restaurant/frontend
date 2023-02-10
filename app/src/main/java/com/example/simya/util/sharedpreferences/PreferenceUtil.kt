package com.example.simya.util.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.simya.util.Constants.PROFILE_ID

class PreferenceUtil(context: Context) {
    private val prefs:SharedPreferences = context.getSharedPreferences("prefs_simya",Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }
    fun getLong(key: String,defValue: Long): Long{
        return prefs.getLong(PROFILE_ID,defValue)
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }
    fun setLong(key: String,num : Long){
        prefs.edit().putLong(PROFILE_ID,num).apply()
    }
}