package com.example.simya.src.model.account

data class AccountResult(
    var profileId: Long,
    var nickname: String,
    var comment: String,
    var accessToken: String,
    var refreshToken: String,
)
