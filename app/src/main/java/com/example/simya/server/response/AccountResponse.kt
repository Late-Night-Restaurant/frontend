package com.example.simya.server.response

import com.example.simya.server.result.AccountReslut

data class AccountResponse(
    var isSuccess: Boolean,
    var code: Int,
    var message: String,
    var result: List<AccountReslut>?,

)
