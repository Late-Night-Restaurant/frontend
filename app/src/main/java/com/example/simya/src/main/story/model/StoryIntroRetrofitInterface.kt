package com.example.simya.src.main.story.model

import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface StoryIntroRetrofitInterface {
    // 특정 이야기 집 조회
    @Headers("Content-Type: application/json")
    @GET("/simya/house/{houseId}")
    fun getStoryDetail(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Path("houseId") houseId: Long
    ): Call<InquiryStoryDetailResponse>
}