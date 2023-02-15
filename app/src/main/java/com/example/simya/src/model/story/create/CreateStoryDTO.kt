package com.example.simya.src.model.story.create

import com.google.gson.annotations.SerializedName

data class CreateStoryDTO(
    @SerializedName("profileId")
    var profileId: Long,
    @SerializedName("category")
    var category: String,
    @SerializedName("signboardImageUrl")
    var signboardImageUrl: String, // 빠질예정?
    @SerializedName("houseName")
    var houseName: String,
    @SerializedName("comment")
    var comment: String
)
