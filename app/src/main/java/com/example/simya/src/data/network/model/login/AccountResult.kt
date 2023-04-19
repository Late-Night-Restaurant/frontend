package com.example.simya.src.data.network.model.login

import com.google.gson.annotations.SerializedName

data class AccountResult(

    @SerializedName("profileId")
    var profileId: Long,
    @SerializedName("nickname")
    var nickname: String,
    @SerializedName("comment")
    var comment: String,
    @SerializedName("pictureUrl")
    var pictureUrl: String,
    @SerializedName("accessToken")
    var accessToken: String,
    @SerializedName("refreshToken")
    var refreshToken: String,
)
