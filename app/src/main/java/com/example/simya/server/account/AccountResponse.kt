package com.example.simya.server.account

data class AccountResponse(
    var isSuccess: Boolean,
    var code: Int,
    var message: String,
    var result: AccountResult?,
    )
