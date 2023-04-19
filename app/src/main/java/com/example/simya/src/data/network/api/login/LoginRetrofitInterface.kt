package com.example.simya.src.data.network.api.login

import com.example.simya.src.data.network.model.login.AccountDTO
import com.example.simya.src.data.network.model.login.AccountResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginRetrofitInterface {
    // 폼 로그인
    @Headers("Content-Type: application/json")
    @POST("/simya/form-login")
    fun onLoginSubmit(
        @Body login: AccountDTO
    ): Call<AccountResponse>
}