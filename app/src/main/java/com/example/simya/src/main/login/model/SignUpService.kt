package com.example.simya.src.main.login.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService(val signUpInterface: SignUpInterface) {
    private val signUpRetrofitInterface: SignUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)
    fun trySignUpSubmit(signUpDTO: SignupDTO){
        signUpRetrofitInterface.onSignUpSubmit(signUpDTO).enqueue(object: Callback<SignupResponse>{
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if(response.code() == OK){
                    signUpInterface.onPostSignUpSubmitSuccess(response.body() as SignupResponse)
                }else{
                    Log.d("SignUp","Fail")
                    signUpInterface.onPostSignUpSubmitFailure(response.body() as SignupResponse)
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Log.d("Retrofit2",t.toString())
            }

        })
    }

}