package com.example.simya.src.model.account

data class AccountResponse(
    var isSuccess: Boolean,
    var code: Int,
    var message: String,
    var result: AccountResult?,
    ){
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
