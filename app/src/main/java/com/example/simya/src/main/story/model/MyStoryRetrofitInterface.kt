package com.example.simya.src.main.story.model

import com.example.simya.src.model.story.load.LoadMyStoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MyStoryRetrofitInterface {
    // 내 이야기 집 조회하기
    @Headers("Content-Type: application/json")
    @GET("/simya/house/my-houses")
    fun getMyStory(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<LoadMyStoryResponse>
}