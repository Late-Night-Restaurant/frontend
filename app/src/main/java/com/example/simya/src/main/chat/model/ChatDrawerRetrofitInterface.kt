package com.example.simya.src.main.chat.model

import com.example.simya.src.model.ChatProfileListResponse
import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
import com.example.simya.src.model.story.load.LoadMyStoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ChatDrawerRetrofitInterface {
    // 내가 찜한 집들 모두 조회
    @Headers("Content-Type: application/json")
    @GET("/simya/house/{houseId}/guest")
    fun getHouseProfileList(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Path("houseId") houseId: Long
    ): Call<ChatProfileListResponse>

}