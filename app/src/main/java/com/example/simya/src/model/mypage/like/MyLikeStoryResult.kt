package com.example.simya.src.model.mypage.like

import com.example.simya.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class MyLikeStoryResult(
    @SerializedName("favoriteId") val favoriteId: String,
    @SerializedName("favoriteHouse") val favoriteHouse: FavoriteHouse
):BaseResponse() {
    data class FavoriteHouse(
        @SerializedName("houseId") val houseId: Long,
        @SerializedName("category") val category: String,
        @SerializedName("signboardImageUrl") val signboardImageUrl: String,
        @SerializedName("houseName") val houseName: String,
        @SerializedName("comment") val comment: String
    )
}
