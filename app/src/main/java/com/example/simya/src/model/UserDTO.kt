package com.example.simya.src.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("profileId") var profileId: Long,
    @SerializedName("nickname") var nickname: String,
    @SerializedName("comment") var comment: String,
    @SerializedName("picture") var picture: String
)
