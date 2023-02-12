package com.example.simya.src.main.myPage.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.UserDTO
import retrofit2.Call
import retrofit2.http.*

interface ProfileModifyRetrofitInterface {
    @Headers("Content-Type: application/json")
    @PATCH("/simya/users/profile/{profileId}")
    fun modifyMyProfile(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Path("profileId") profileId: Long,
        @Body userDTO: UserDTO
    ): Call<BaseResponse>
    // 프로필 삭제
    @Headers("Content-Type: application/json")
    @DELETE("/simya/users/profile/{profileId}")
    fun deleteMyProfile(
        @Header("Access-Token") accessToken: String,
        @Header("Refresh-Token") refreshToken: String,
        @Path("profileId") profileId: Long
    ): Call<BaseResponse>
}