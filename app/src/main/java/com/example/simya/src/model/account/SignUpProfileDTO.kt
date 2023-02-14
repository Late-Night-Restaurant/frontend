package com.example.simya.src.model.account

import com.google.gson.annotations.SerializedName

data class SignUpProfileDTO(
    @SerializedName("nickname") var nickname: String,
    @SerializedName("comment") var comment: String,
)

