package com.example.simya.src.model.token

import com.google.gson.annotations.SerializedName

data class TokenDTO(
    @SerializedName("Access-Token")
    var accessToken: String,
    @SerializedName("Refresh-Token")
    var refreshToken: String
)
