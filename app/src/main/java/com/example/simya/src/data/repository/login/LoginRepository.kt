package com.example.simya.src.data.repository.login

import com.example.simya.config.ApplicationClass
import com.example.simya.src.data.network.model.login.AccountResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun onSubmitLogin(
        email: String,
        password: String
    ): Response<AccountResponse>
}