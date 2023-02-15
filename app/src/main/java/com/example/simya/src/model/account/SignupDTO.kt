package com.example.simya.src.model.account

import com.google.gson.annotations.SerializedName

data class SignupDTO(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("profile")
    var profile: SignUpProfileDTO
)


