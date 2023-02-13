package com.example.simya.src.main.story.model

import com.example.simya.src.model.story.HouseDTO
import com.example.simya.src.model.story.create.CreateStoryDTO
import com.example.simya.src.model.story.create.CreateStoryResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CreateMyHouseRetrofitInterface {
    @Headers("Content-Type: application/json")
    @POST("/simya/house")
    fun onCreateMyHouse(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Body myStory: CreateStoryDTO
    ): Call<CreateStoryResponse>
}