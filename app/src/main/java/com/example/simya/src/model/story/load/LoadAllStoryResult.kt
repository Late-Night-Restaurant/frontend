package com.example.simya.src.model.story.load

import com.google.gson.annotations.SerializedName

data class LoadAllStoryResult(
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
