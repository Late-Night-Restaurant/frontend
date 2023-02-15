package com.example.simya.src.main.story.model

import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.story.load.LoadMyStoryResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import com.example.simya.util.SampleSnackBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyStoryService(val myStoryInterface: MyStoryInterface) {
    private val myStoryRetrofitInterface: MyStoryRetrofitInterface = ApplicationClass.sRetrofit.create(MyStoryRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )
    fun tryGetMyStory(){
        myStoryRetrofitInterface.getMyStory(accessToken!!,refreshToken!!).enqueue(object:
            Callback<LoadMyStoryResponse>{
            override fun onResponse(
                call: Call<LoadMyStoryResponse>,
                response: Response<LoadMyStoryResponse>
            ) {
                if(response.code() == OK){
                    myStoryInterface.onGetMyStorySuccess(response.body() as LoadMyStoryResponse)
                }else{
                    myStoryInterface.onGetMyStoryFailure(response.body() as LoadMyStoryResponse)
                }
            }

            override fun onFailure(call: Call<LoadMyStoryResponse>, t: Throwable) {
                myStoryInterface.onGetMyStoryDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }
}