package com.example.simya.src.main.login.model

import com.example.simya.src.model.account.SignupResponse

interface SignUpInterface {
    fun onPostSignUpSubmitSuccess(response: SignupResponse)
    fun onPostSignUpSubmitFailure(response: SignupResponse)
    fun onPostSignUpSubmitDisconnect(message: String)
}