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
        @Body login: AccountDTO
    ): Call<AccountResponse>

    // 내 모든 프로필 조회
    @Headers("Content-Type: application/json")
    @GET("/simya/users/profile")
    fun getUserProfile(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<ProfileResponse>

    // 회원가입
    @Headers("Content-Type: application/json")
    @POST("/simya/form-signup")
    fun onSignUpSubmit(
        @Body signup: SignupDTO
    ): Call<SignupResponse>

    // 이야기 집 생성
    @Headers("Content-Type: application/json")
    @POST("/simya/house")
    fun onCreateMyHouse(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Body myStory: CreateStoryDTO
    ): Call<CreateStoryResponse>

    // 내 이야기 집 조회하기
    @Headers("Content-Type: application/json")
    @GET("/simya/house/my-houses")
    fun getMyStory(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<LoadMyStoryResponse>

    @Headers("Content-Type: application/json")
    @GET("/simya/house")
    fun getAllStory(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<LoadAllStoryResponse>

    // 이야기 집 오픈
    @Headers("Content-Type: application/json")
    @PATCH("/simya/house/open")
    fun openStory(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Body houseData: OpenStoryDTO
    ): Call<OpenStoryResponse>


}