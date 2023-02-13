package com.example.simya.src.main.story.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.story.HouseDTO
import com.example.simya.src.model.story.create.CreateStoryDTO
import com.example.simya.src.model.story.create.CreateStoryResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    fun tryOnCreateMyHouse(createStoryDTO: CreateStoryDTO){
        createMyHouseRetrofitInterface.onCreateMyHouse(accessToken!!,refreshToken!!,createStoryDTO).enqueue(object:
            Callback<CreateStoryResponse> {
            override fun onResponse(
                call: Call<CreateStoryResponse>,
                response: Response<CreateStoryResponse>
            ) {
                if(response.code() == OK){
                    createMyHouseInterface.onPostCreateMyHouseSuccess(response.body() as CreateStoryResponse)
                }else{
                    Log.d("responseCode!!!!!!!!!!!!",response.code().toString())
                    // 에러 생김
//                    createMyHouseInterface.onPostCreateMyHouseFailure(response.body() as CreateStoryResponse)
                }
            }
            override fun onFailure(call: Call<CreateStoryResponse>, t: Throwable) {
                Log.d("Retrofit2",t.toString())
            }

        })
    }
}