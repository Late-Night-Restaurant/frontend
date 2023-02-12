package com.example.simya.src.main.myPage.model

import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MyPageLikeHouseRetrofitInterface {
    // 내가 찜한 집들 모두 조회
    @Headers("Content-Type: application/json")
    @GET("/simya/favorite/my")
    fun getMyLikeStory(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
    ): Call<MyLikeStoryResponse>
}