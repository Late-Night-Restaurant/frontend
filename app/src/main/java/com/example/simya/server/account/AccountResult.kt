package com.example.simya.server.account

data class AccountResult(
    var grantType: String,
    var accessToken: String,
    var refreshToken: String,
    var accessTokenExpiresIn: Long
)
