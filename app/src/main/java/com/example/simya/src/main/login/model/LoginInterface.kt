package com.example.simya.src.main.login.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.account.AccountResponse

interface LoginInterface {
    fun onPostLoginSubmitSuccess(response: AccountResponse)
    fun onPostLoginSubmitFailure(response: BaseResponse)
    fun onPostLoginSubmitDisconnect(message: String)
}