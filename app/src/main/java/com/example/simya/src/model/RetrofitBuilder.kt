package com.example.simya.src.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private var instance: Retrofit? = null

    fun getInstnace(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}