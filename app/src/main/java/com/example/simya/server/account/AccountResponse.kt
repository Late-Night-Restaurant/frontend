package com.example.simya.server.account

data class AccountResponse(
    var isSuccess: Boolean,
    var code: Int,
    var message: String,
    var result: AccountResult?,
    ){
    fun getGrantType(): String{
        return result!!.grantType
    }
    fun getAccessToken(): String{
        return result!!.accessToken
    }
    fun getRefreshToken(): String{
        return result!!.refreshToken
    }
    fun getAccessTokenExpiresIn(): Long{
        return result!!.accessTokenExpiresIn
    }
}
