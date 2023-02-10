package com.example.simya.src.model.account

import com.example.simya.config.BaseResponse

data class AccountResponse(
    var result: AccountResult?,
    ):BaseResponse(){
    fun getProfileId(): Long{
        return result!!.profileId
    }
    fun getAccessToken(): String{
        return result!!.accessToken
    }
    fun getRefreshToken(): String{
        return result!!.refreshToken
    }
}
