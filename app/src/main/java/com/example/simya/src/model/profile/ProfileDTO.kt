package com.example.simya.src.model.profile

import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    @SerializedName("profileId") var profileId: Long,
    @SerializedName("nickname") var nickname: String,
    @SerializedName("comment") var comment: String,
    @SerializedName("pictureUrl") var pictureUrl: String
)

