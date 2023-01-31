package com.example.simya.data

import com.example.simya.Constants
import kotlin.properties.Delegates


object UserTokenData {
    var accessToken : String = Constants.DEFAULT
    var refreshToken: String = Constants.DEFAULT
    fun init(accessToken: String, refreshToken: String){
        this.accessToken = "Access $accessToken"
        this.refreshToken = "Refresh $refreshToken"
    }
    fun setUserAccessToken(accessToken: String){
        this.accessToken = accessToken
    }
    fun setUserRefreshToken(refreshToken: String){
        this.refreshToken = refreshToken
    }
    fun getUserAccessToken():String{
        return accessToken
    }
    fun getUserRefreshToken(): String{
        return refreshToken
    }


}