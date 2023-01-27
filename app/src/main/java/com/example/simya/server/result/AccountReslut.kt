package com.example.simya.server.result

data class AccountReslut(
    var granType: String,
    var accessToken: String,
    var refreshToken: String,
    var accessTokenExpiresIn: Long
)
