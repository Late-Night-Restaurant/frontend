package com.example.simya.src.main.login.model

import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface SignUpRetrofitInterface {
    // 회원가입
    @Multipart
    @POST("/simya/form-signup")
    fun onSignUpSubmit(
        @Part image : MultipartBody.Part?,
        @Part("formSignupRequestDto") signup: SignupDTO
    ): Call<SignupResponse>
}