package com.example.simya.src.main.myPage.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.profile.ProfileResponse
import retrofit2.Call
import retrofit2.http.*

interface MyPageProfileRetrofitInterface {
    @Headers("Content-Type: application/json")
    @GET("/simya/users/profile")
    fun getUserProfile(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<ProfileResponse>

    // 로그아웃
    @Headers("Content-Type: application/json")
    @GET("/simya/logout")
    fun onLogout(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
    ): Call<BaseResponse>

    // 대표 프로필 선택
    @Headers("Content-Type: application/json")
    @PATCH("/simya/users/profile/{profileId}/main")
    fun setMyRepresentProfile(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Path("profileId") profileId: Long
    ): Call<BaseResponse>
}