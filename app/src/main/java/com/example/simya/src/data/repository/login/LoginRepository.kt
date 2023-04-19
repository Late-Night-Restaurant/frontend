package com.example.simya.src.data.repository.login

import com.example.simya.config.BaseResponse
import com.example.simya.src.data.network.model.login.AccountResponse

interface LoginRepository {
    fun onPostLoginSubmitSuccess(response: AccountResponse)
    fun onPostLoginSubmitFailure(response: BaseResponse)
    fun onPostLoginSubmitDisconnect(message: String)
}