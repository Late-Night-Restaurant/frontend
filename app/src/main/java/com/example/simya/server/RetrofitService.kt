package com.example.simya.server

import com.example.simya.server.account.AccountDTO
import com.example.simya.server.account.AccountResponse
import com.example.simya.server.profile.ProfileResponse
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    // 폼 로그인
    @Headers("Content-Type: application/json")
    @POST("/simya/form-login")
    fun onLoginSubmit(
        @Body login: AccountDTO
    ): Call<AccountResponse>

    // 내 모든 프로필 조회
    @Headers("Content-Type: application/json")
    @GET("/simya/users/profile")
    fun getUserProfile(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh_Token") refreshToken: String
    ): Call<ProfileResponse>





}