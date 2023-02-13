package com.example.simya.src.model.story

import com.google.gson.annotations.SerializedName

data class HouseDTO(
    @SerializedName("houseId") val houseId: Long,
    @SerializedName("category") val category: String,
    @SerializedName("signboardImageUrl") val signboardImageUrl: String,
    @SerializedName("houseName") val houseName: String,
    @SerializedName("comment") val comment: String
)