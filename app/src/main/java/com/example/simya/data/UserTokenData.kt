package com.example.simya.data

import com.example.simya.Constants
import kotlin.properties.Delegates

// 이름 바꿔야함
object UserTokenData {
    var accessToken: String = Constants.DEFAULT
    var refreshToken: String = Constants.DEFAULT
    private var profileId: Long = 0
    private var profileName: String = Constants.DEFAULT
    fun setProfileName(profileName: String) {
        this.profileName = profileName
    }

    fun getProfileName(): String {
        return profileName
    }

    fun setProfileId(profileId: Long) {
        this.profileId = profileId
    }

    fun getProfileId(): Long {
        return profileId
    }

    fun init(accessToken: String, refreshToken: String) {
        this.accessToken = "Access $accessToken"
        this.refreshToken = "Refresh $refreshToken"
    }

    fun getUserAccessToken(): String {
        return accessToken
    }

    fun getUserRefreshToken(): String {
        return refreshToken
    }


}