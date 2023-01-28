package com.example.simya.server


import com.example.simya.server.account.*
import retrofit2.Call
import retrofit2.http.*
import com.example.simya.server.account.AccountDTO
import com.example.simya.server.account.AccountResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitService {
    // 로그인 요청
    @Headers("Content-Type: application/json")
    @POST("/simya/form-login")
    fun onLoginSubmit(
        @Body login: AccountDTO
    ): Call<AccountResponse>

    // 회원가입
    @Headers("Content-Type: application/json")
    @POST("/simya/form-signup")
    fun onSignUpSubmit(
        @Body signup: SignupDTO
    ): Call<SignupResponse>

}