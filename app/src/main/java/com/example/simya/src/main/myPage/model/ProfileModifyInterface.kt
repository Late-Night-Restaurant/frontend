package com.example.simya.src.main.myPage.model

import com.example.simya.config.BaseResponse

interface ProfileModifyInterface {
    fun onModifyMyProfileSuccess(response: BaseResponse)
    fun onModifyMyProfileFailure(response: BaseResponse)
    fun onModifyMyProfileDisconnect(message: String)

    fun onDeleteMyProfileSuccess(response: BaseResponse)
    fun onDeleteMyProfileFailure(response: BaseResponse)
    fun onDeleteMyProfileDisconnect(message: String)
}