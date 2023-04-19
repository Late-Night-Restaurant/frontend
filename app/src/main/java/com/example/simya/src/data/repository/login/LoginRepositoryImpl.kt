package com.example.simya.src.data.repository.login

import com.example.simya.config.ApplicationClass
import com.example.simya.config.BaseResponse
import com.example.simya.src.data.network.api.login.LoginRetrofitInterface
import com.example.simya.src.data.network.model.login.AccountResponse

class LoginRepositoryImpl: LoginRepository{
    private val loginRetrofitInterface: LoginRetrofitInterface = ApplicationClass.sRetrofit.create(
        LoginRetrofitInterface::class.java)

    override fun onPostLoginSubmitSuccess(response: AccountResponse) {
        TODO("Not yet implemented")
    }

    override fun onPostLoginSubmitFailure(response: BaseResponse) {
        TODO("Not yet implemented")
    }

    override fun onPostLoginSubmitDisconnect(message: String) {
        TODO("Not yet implemented")
    }
}