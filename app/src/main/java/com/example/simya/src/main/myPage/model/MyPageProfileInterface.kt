package com.example.simya.src.main.myPage.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.profile.ProfileDTO
import com.example.simya.src.model.profile.ProfileResponse

interface MyPageProfileInterface {
    fun onGetUserProfileSuccess(response: ProfileResponse)
    fun onGetUserProfileFailure(response: ProfileResponse)
    fun onGetUserProfileDisconnect(message: String)

    fun onSetMyRepresentProfileSuccess(response: BaseResponse,data: ProfileDTO)
    fun onSetMyRepresentProfileFailure(response: BaseResponse)
    fun onSetMyRepresentDisconnect(message: String)

    fun onLogoutSuccess(response: BaseResponse)
    fun onLogoutFailure(response: BaseResponse)
    fun onLogoutDisconnect(message: String)
}