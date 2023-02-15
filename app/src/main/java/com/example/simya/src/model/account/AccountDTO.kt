package com.example.simya.src.model.account

import com.google.gson.annotations.SerializedName

data class AccountDTO(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)
