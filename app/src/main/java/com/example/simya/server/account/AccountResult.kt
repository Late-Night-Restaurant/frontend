package com.example.simya.server.account

data class AccountResult(
    var granType: String,
    var accessToken: String,
    var refreshToken: String,
    var accessTokenExpiresIn: Long
)
