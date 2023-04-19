package com.example.simya.src.data.network.model.login

import com.example.simya.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class AccountResponse(
    @SerializedName("result")
    var result: AccountResult?,
    ):BaseResponse(){
}
