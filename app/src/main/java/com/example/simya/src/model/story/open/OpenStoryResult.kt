package com.example.simya.src.model.story.open

import com.google.gson.annotations.SerializedName

data class OpenStoryResult(
    @SerializedName("houseId")
    var houseId: Long,
    @SerializedName("category")
    var category: String,
    @SerializedName("signboardImageUrl")
    var signboardImageUrl: String,
    @SerializedName("houseName")
    var houseName: String,
    @SerializedName("todayTopicTitle")
    var todayTopicTitle: String
)
