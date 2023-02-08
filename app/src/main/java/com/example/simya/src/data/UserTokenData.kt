package com.example.simya.src.data

import com.example.simya.src.Constants

// 이름 바꿔야함
object UserTokenData {
    var accessToken: String = Constants.DEFAULT
    var refreshToken: String = Constants.DEFAULT
    private var profileId: Long = 0
    private var profileName: String = Constants.DEFAULT
    fun setProfileName(profileName: String) {
        UserTokenData.profileName = profileName
    }

    fun getProfileName(): String {
        return profileName
    }

    fun setProfileId(profileId: Long) {
        UserTokenData.profileId = profileId
    }

    fun getProfileId(): Long {
        return profileId
    }

    fun init(accessToken: String, refreshToken: String) {
        UserTokenData.accessToken = "Access $accessToken"
        UserTokenData.refreshToken = "Refresh $refreshToken"
    }

    fun getUserAccessToken(): String {
        return accessToken
    }

    fun getUserRefreshToken(): String {
        return refreshToken
    }


}