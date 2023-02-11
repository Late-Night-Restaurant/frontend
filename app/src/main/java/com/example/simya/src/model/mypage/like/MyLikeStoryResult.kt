package com.example.simya.src.model.mypage.like

import com.example.simya.config.BaseResponse
import com.example.simya.util.data.BorderData
import com.google.gson.annotations.SerializedName

data class MyLikeStoryResult(
    @SerializedName("favoriteId") val favoriteId: String,
    @SerializedName("favoriteHouse") val favoriteHouse: BorderData
):BaseResponse()
