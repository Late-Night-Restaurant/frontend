package com.example.simya.src.model.story.create

import com.google.gson.annotations.SerializedName

data class CreateStoryResult(
    @SerializedName("houseId")
    var houseId: Int,
    @SerializedName("category")
    var category: String,
    @SerializedName("signboardImageUrl")
    var signboardImageUrl: String,
    @SerializedName("houseName")
    var houseName: String,
    @SerializedName("comment")
    var comment: String
)
