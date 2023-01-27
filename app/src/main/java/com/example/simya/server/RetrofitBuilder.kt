package com.example.simya.server

import android.os.Build
import com.example.simya.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    fun getInstnace(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("${BuildConfig.SIMYA_SERVER_URL}")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}