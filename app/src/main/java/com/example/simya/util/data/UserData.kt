package com.example.simya.util.data

import com.example.simya.util.Constants.DEFAULT

object UserData {
    var accessToken: String = DEFAULT
    var refreshToken: String = DEFAULT
    private var profileId: Long = 0
    private var profileName: String = DEFAULT
    private var profileImage: String = DEFAULT
    private var profileComment: String = ""
    fun setProfileName(profileName: String) {
        UserData.profileName = profileName
    }

    fun getProfileName(): String {
        return profileName
    }

    fun setProfileImage(profileString: String) {
        UserData.profileImage = profileImage
    }

    fun getProfileImage(): String {
        return profileImage
    }

    fun setProfileComment(profileComment: String) {
        UserData.profileComment = profileComment
    }

    fun getProfileComment(): String {
        return profileComment
    }

    fun setProfileId(profileId: Long) {
        UserData.profileId = profileId
    }

    fun getProfileId(): Long {
        return profileId
    }

    fun init(accessToken: String, refreshToken: String) {
        UserData.accessToken = "Access $accessToken"
        UserData.refreshToken = "Refresh $refreshToken"
    }

    fun getUserAccessToken(): String {
        return accessToken
    }

    fun getUserRefreshToken(): String {
        return refreshToken
    }


}