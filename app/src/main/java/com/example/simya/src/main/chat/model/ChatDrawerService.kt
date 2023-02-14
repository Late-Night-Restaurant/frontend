package com.example.simya.src.main.chat.model

import com.example.simya.config.ApplicationClass
import com.example.simya.src.main.login.model.LoginRetrofitInterface
import com.example.simya.util.Constants

class ChatDrawerService(val chatDrawerInterface: ChatDrawerInterface) {
    private val chatDrawerRetrofitInterface: ChatDrawerRetrofitInterface = ApplicationClass.sRetrofit.create(
        ChatDrawerRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )
}