package com.example.simya.src.main.login.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import com.example.simya.util.Constants.OK
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class SignUpService(val signUpInterface: SignUpInterface) {
    private val signUpRetrofitInterface: SignUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)
    fun trySignUpSubmit(image: String?,signUpDTO: SignupDTO){
        var multiPartBody: MultipartBody.Part? = null
        if(image != null){
            val file = File(image)
            val requestBody = file!!.asRequestBody("image".toMediaTypeOrNull())
            multiPartBody = MultipartBody.Part.createFormData("image", file.name, requestBody)
        }
        signUpRetrofitInterface.onSignUpSubmit(multiPartBody,signUpDTO).enqueue(object: Callback<SignupResponse>{
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if(response.body()!!.code == OK){
                    signUpInterface.onPostSignUpSubmitSuccess(response.body() as SignupResponse)
                }else{
                    Log.d("SignUp","Fail")
                    signUpInterface.onPostSignUpSubmitFailure(response.body() as SignupResponse)
                }
            }
            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                signUpInterface.onPostSignUpSubmitDisconnect("서버가 원활하지 않습니다.")
            }
        })
    }

}