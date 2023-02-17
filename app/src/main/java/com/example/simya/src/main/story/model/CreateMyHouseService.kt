package com.example.simya.src.main.story.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.story.HouseDTO
import com.example.simya.src.model.story.create.CreateStoryDTO
import com.example.simya.src.model.story.create.CreateStoryResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CreateMyHouseService(val createMyHouseInterface: CreateMyHouseInterface){
    private val createMyHouseRetrofitInterface: CreateMyHouseRetrofitInterface = ApplicationClass.sRetrofit.create(CreateMyHouseRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )
    fun tryOnCreateMyHouse(image: String?,createStoryDTO: CreateStoryDTO){
        var multiPartBody: MultipartBody.Part? = null
        if(image != null){
            val file = File(image)
            val requestBody = file!!.asRequestBody("image".toMediaTypeOrNull())
            multiPartBody = MultipartBody.Part.createFormData("image", file.name, requestBody)
        }
        createMyHouseRetrofitInterface.onCreateMyHouse(accessToken!!,refreshToken!!,multiPartBody,createStoryDTO).enqueue(object:
            Callback<CreateStoryResponse> {
            override fun onResponse(
                call: Call<CreateStoryResponse>,
                response: Response<CreateStoryResponse>
            ) {
                if(response.code() == OK){
                    createMyHouseInterface.onPostCreateMyHouseSuccess(response.body() as CreateStoryResponse)
                }else{
                    createMyHouseInterface.onPostCreateMyHouseFailure(response.body() as CreateStoryResponse)
                }
            }
            override fun onFailure(call: Call<CreateStoryResponse>, t: Throwable) {
                createMyHouseInterface.onPostCreateMyHouseDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }
}