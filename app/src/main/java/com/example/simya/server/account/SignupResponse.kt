package com.example.simya.server.account

data class SignupResponse(
    var isSuccess: Boolean?,
    var code: Int?,
    var message: String?,
    var result: SignupResult?,
)