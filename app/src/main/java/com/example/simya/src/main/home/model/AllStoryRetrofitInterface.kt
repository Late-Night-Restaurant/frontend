package com.example.simya.src.main.home.model

import com.example.simya.src.model.story.load.LoadAllStoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface AllStoryRetrofitInterface {
    @Headers("Content-Type: application/json")
    @GET("/simya/house")
    fun getAllStory(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String
    ): Call<LoadAllStoryResponse>
}