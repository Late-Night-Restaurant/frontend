package com.example.simya.server.account

data class AccountResult(
    var profileId: Long,
    var nickname: String,
    var comment: String,
    var accessToken: String,
    var refreshToken: String,
)
