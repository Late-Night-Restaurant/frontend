package com.example.simya.src.model.story.load

import com.google.gson.annotations.SerializedName

data class LoadMyStoryResult(
    @SerializedName("houseId")
    var houseId: Long,
    @SerializedName("category")
    var category: String,
    @SerializedName("signboardImageUrl")
    var signboardImageUrl: String,
    @SerializedName("houseName")
    var houseName: String,
    @SerializedName("comment")
    var comment: String
)
