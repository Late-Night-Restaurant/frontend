package com.example.simya.server


import com.example.simya.server.account.*
import retrofit2.Call
import retrofit2.http.*
import com.example.simya.server.account.AccountDTO
import com.example.simya.server.account.AccountResponse
import com.example.simya.server.profile.ProfileResponse
import com.example.simya.server.story.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitService {

    // 폼 로그인
    @Headers("Content-Type: application/json")
    @POST("/simya/form-login")
    fun onLoginSubmit(
        @Body login: com.example.simya.server.account.AccountDTO
    ): Call<com.example.simya.server.account.AccountResponse>

    // 내 모든 프로필 조회
    @Headers("Content-Type: application/json")
    @GET("/simya/users/profile")
    fun getUserProfile(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<com.example.simya.server.profile.ProfileResponse>

    // 회원가입
    @Headers("Content-Type: application/json")
    @POST("/simya/form-signup")
    fun onSignUpSubmit(
        @Body signup: com.example.simya.server.account.SignupDTO
    ): Call<com.example.simya.server.account.SignupResponse>

    // 이야기 집 생성
    @Headers("Content-Type: application/json")
    @POST("/simya/house")
    fun onCreateMyHouse(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Body myStory: com.example.simya.server.story.CreateStoryDTO
    ): Call<com.example.simya.server.story.CreateStoryResponse>

    // 내 이야기 집 조회하기
    @Headers("Content-Type: application/json")
    @GET("/simya/house/my-houses")
    fun getMyStory(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<com.example.simya.server.story.LoadMyStoryResponse>

    // 이야기 집 오픈
    @Headers("Content-Type: application/json")
    @PATCH("/simya/house/open")
    fun openStory(
        @Body houseData: com.example.simya.server.story.OpenStoryDTO
    ): Call<com.example.simya.server.story.OpenStoryResponse>


}