package com.example.simya.src.main.login.model

import com.example.simya.config.BaseResponse
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.src.model.account.SignupResponse

interface SignUpInterface {
    fun onPostSignUpSubmitSuccess(response: SignupResponse)
    fun onPostSignUpSubmitFailure(response: SignupResponse)
}