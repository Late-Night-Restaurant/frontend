package com.example.simya.src.main.login.model

import android.net.Uri
import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import com.example.simya.util.Constants.OK
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.File
import java.net.URI

class SignUpService(val signUpInterface: SignUpInterface) {
    private val signUpRetrofitInterface: SignUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)
    fun trySignUpSubmit(image: URI,signUpDTO: SignupDTO){
        val file = File(image)
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("images", file.name, requestBody)
        signUpRetrofitInterface.onSignUpSubmit(image,signUpDTO).enqueue(object: Callback<SignupResponse>{
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
                Log.d("Retrofit2",t.toString())
            }
        })
    }

}