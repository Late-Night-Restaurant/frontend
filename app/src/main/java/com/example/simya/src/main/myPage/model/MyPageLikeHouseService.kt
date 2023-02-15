package com.example.simya.src.main.myPage.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageLikeHouseService(val myPageLikeHouseInterface: MyPageLikeHouseInterface) {
    private val myPageHouseLikeRetrofitInterface: MyPageLikeHouseRetrofitInterface =
        ApplicationClass.sRetrofit.create(MyPageLikeHouseRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )

    fun tryGetGetMyLikeHouse(){
        myPageHouseLikeRetrofitInterface.getMyLikeStory(accessToken!!,refreshToken!!).enqueue(object:
            Callback<MyLikeStoryResponse>{
            override fun onResponse(
                call: Call<MyLikeStoryResponse>,
                response: Response<MyLikeStoryResponse>
            ) {
                if(response.code() == OK){
                    Log.d("MyLikeHouse Load","Success")
                    myPageLikeHouseInterface.onGetMyLikeHouseSuccess(response.body() as MyLikeStoryResponse)
                }else{
                    Log.d("MyLikeHouse Load","Fail")
                    myPageLikeHouseInterface.onGetMyLikeHouseFailure(response.body() as MyLikeStoryResponse)
                }
            }

            override fun onFailure(call: Call<MyLikeStoryResponse>, t: Throwable) {
                myPageLikeHouseInterface.onGetMyLikeHouseDisconnect("서버가 원활하지 않습니다.")
            }
        })
    }
}