package com.example.simya.util.data

import android.util.Log
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

    fun setProfileImage(profileImage: String) {
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
    fun setUserAccessToken(accessToken: String){
        UserData.accessToken = "Access $accessToken"
    }
    fun setUserRefreshToken(refreshToken: String){
        UserData.refreshToken = "Refresh $refreshToken"
    }
    fun getUserAccessToken(): String {
        return accessToken
    }
    fun getUserRefreshToken(): String {
        return refreshToken
    }
    fun printAllData(){
        Log.d("printAllData [accessToken]", accessToken)
        Log.d("printAllData [refreshToken]", refreshToken)
        Log.d("printAllData [profileId]", profileId.toString())
        Log.d("printAllData [profileName]", profileName)
        Log.d("printAllData [profileComment]", profileComment)
        Log.d("printAllData [profileImage]", profileImage)
    }
}