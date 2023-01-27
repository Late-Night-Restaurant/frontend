package com.example.simya.server

import com.example.simya.server.account.AccountResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitService {
    // 로그인 요청
    @Headers("Content-Type: application/json")
    @GET("/simya/form-login")
    fun onLoginSubmit(
        @Query("email") email: String?,
        @Query("password") password: String?
    ): Call<AccountResponse>






}