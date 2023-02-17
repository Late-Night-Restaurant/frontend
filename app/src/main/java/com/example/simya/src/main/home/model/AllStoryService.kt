package com.example.simya.src.main.home.model

import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.story.load.LoadAllStoryResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllStoryService(val allStoryInterface: AllStoryInterface) {
    private val allStoryRetrofitInterface: AllStoryRetrofitInterface = ApplicationClass.sRetrofit.create(AllStoryRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )
    fun tryGetAllStory(){
        allStoryRetrofitInterface.getAllStory(accessToken!!,refreshToken!!).enqueue(object:
            Callback<LoadAllStoryResponse>{
            override fun onResponse(
                call: Call<LoadAllStoryResponse>,
                response: Response<LoadAllStoryResponse>
            ) {
                if(response.code() == OK){
                    allStoryInterface.onGetAllStorySuccess(response.body() as LoadAllStoryResponse)
                }else{
                    allStoryInterface.onGetAllStoryFailure(response.body() as LoadAllStoryResponse)
                }
            }
            override fun onFailure(call: Call<LoadAllStoryResponse>, t: Throwable) {
                allStoryInterface.onGetAllStoryDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }

}