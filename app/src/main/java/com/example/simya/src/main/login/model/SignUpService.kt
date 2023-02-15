package com.example.simya.src.main.login.model

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import com.example.simya.util.Constants.OK
import com.example.simya.util.Constants.S3_URL
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
//            val file = image.path?.let { File("$it.jpg") }
            val file = File(image)
            Log.d("filename is",file.name)
            val requestBody = file!!.asRequestBody("image/jpg".toMediaTypeOrNull())
            multiPartBody = MultipartBody.Part.createFormData("images", S3_URL+"testImage.jpg", requestBody)
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
                Log.d("Retrofit2",t.toString())
            }
        })
    }

}