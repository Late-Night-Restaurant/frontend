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
        if (com.example.simya.server.RetrofitBuilder.instance == null) {
            com.example.simya.server.RetrofitBuilder.instance = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return com.example.simya.server.RetrofitBuilder.instance!!
    }
}