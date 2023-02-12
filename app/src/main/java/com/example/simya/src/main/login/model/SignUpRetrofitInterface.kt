package com.example.simya.src.main.login.model

import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpRetrofitInterface {
    // 회원가입
    @Headers("Content-Type: application/json")
    @POST("/simya/form-signup")
    fun onSignUpSubmit(
        @Body signup: SignupDTO
    ): Call<SignupResponse>
}