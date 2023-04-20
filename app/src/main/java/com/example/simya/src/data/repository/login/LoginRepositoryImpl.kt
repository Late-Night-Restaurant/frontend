package com.example.simya.src.data.repository.login

import com.example.simya.config.ApplicationClass
import com.example.simya.src.data.network.api.login.LoginApi
import com.example.simya.src.data.network.model.login.AccountDTO
import com.example.simya.src.data.network.model.login.AccountResponse
import com.example.simya.src.main.chat.model.ChatDrawerRetrofitInterface
import retrofit2.Response

class LoginRepositoryImpl: LoginRepository{
    private val loginApi: LoginApi = ApplicationClass.sRetrofit.create(
        LoginApi::class.java)

    override suspend fun onSubmitLogin(email: String, password: String): Response<AccountResponse> {
        return loginApi.onLoginSubmit(AccountDTO( email,password))
    }
}