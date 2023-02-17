package com.example.simya.src.main.story.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.story.open.OpenStoryDTO
import com.example.simya.src.model.story.open.OpenStoryResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class OpenMyHouseService(val openMyHouseInterface: OpenMyHouseInterface) {
    private val openMyHouseRetrofitInterface: OpenMyHouseRetrofitInterface = ApplicationClass.sRetrofit.create(OpenMyHouseRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )
    fun tryOpenMyHouse(houseDTO: OpenStoryDTO){
        openMyHouseRetrofitInterface.openStory(accessToken!!,refreshToken!!,houseDTO).enqueue(object:
            Callback<OpenStoryResponse>{
            override fun onResponse(
                call: Call<OpenStoryResponse>,
                response: Response<OpenStoryResponse>
            ) {
                if(response.code()==OK){
                    openMyHouseInterface.onPatchCreateMyHouseSuccess(response.body() as OpenStoryResponse)
                }else{
                    openMyHouseInterface.onPatchCreateMyHouseFailure(response.body() as OpenStoryResponse)
                }
            }

            override fun onFailure(call: Call<OpenStoryResponse>, t: Throwable) {
                openMyHouseInterface.onPatchCreateMyHouseDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }
}