package com.example.simya.util.data

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("nickname")
    var nickname: String,
    @SerializedName("comment")
    var comment: String,
    @SerializedName("picture")
    var picture: String
)
