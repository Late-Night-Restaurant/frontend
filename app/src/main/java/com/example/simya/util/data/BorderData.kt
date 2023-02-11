package com.example.simya.util.data

import com.google.gson.annotations.SerializedName

data class BorderData(
    @SerializedName("houseId") val houseId: Long,
    @SerializedName("category") val category: String,
    @SerializedName("signboardImageUrl") val signboardImageUrl: String,
    @SerializedName("houseName") val houseName: String,
    @SerializedName("comment") val comment: String?,
    @SerializedName("todayTopicTitle") val todayTopicTitle: String?
)
