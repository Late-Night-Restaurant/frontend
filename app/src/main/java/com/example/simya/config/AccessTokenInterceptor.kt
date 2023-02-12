package com.example.simya.config

import com.example.simya.config.ApplicationClass.Companion.sSharedPreferences
import com.example.simya.util.Constants.ACCESS_TOKEN
import com.example.simya.util.Constants.DEFAULT
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AccessTokenInterceptor: Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sSharedPreferences.getString(ACCESS_TOKEN, DEFAULT)
        if (jwtToken != null) {
            builder.addHeader(ACCESS_TOKEN, jwtToken)
        }
        return chain.proceed(builder.build())
    }
}