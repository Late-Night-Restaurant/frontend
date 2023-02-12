package com.example.simya.src.main.login.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.config.BaseResponse
import com.example.simya.src.model.account.AccountDTO
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginInterface: LoginInterface) {
    private val loginRetrofitInterface: LoginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)

    fun tryLoginSubmit(accountDTO: AccountDTO){
        loginRetrofitInterface.onLoginSubmit(accountDTO).enqueue(object: Callback<AccountResponse>{
            override fun onResponse(
                call: Call<AccountResponse>,
                response: Response<AccountResponse>
            ) {
                if(response.code()==OK){
                    loginInterface.onPostLoginSubmitSuccess(response.body() as AccountResponse)
                }else{
                    Log.d("Login","Fail")
                    loginInterface.onPostLoginSubmitFailure(response.body() as BaseResponse)
                }
            }
            override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
//                loginInterface.onPostLoginSubmitFailure(t.message ?:"통신 오류")
                Log.d("Retrofit2",t.toString())
                // 통신 에러
            }


        })
    }
}