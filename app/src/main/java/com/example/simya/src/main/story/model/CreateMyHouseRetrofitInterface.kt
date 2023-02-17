package com.example.simya.src.main.story.model

import com.example.simya.src.model.story.HouseDTO
import com.example.simya.src.model.story.create.CreateStoryDTO
import com.example.simya.src.model.story.create.CreateStoryResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface CreateMyHouseRetrofitInterface {
    @Multipart
    @POST("/simya/house")
    fun onCreateMyHouse(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Part image : MultipartBody.Part?,
        @Part ("houseCreateRequestDto") myStory: CreateStoryDTO
    ): Call<CreateStoryResponse>
}