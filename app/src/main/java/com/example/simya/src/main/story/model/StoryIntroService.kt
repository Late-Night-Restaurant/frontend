package com.example.simya.src.main.story.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class StoryIntroService(val storyIntroInterface:StoryIntroInterface) {
    private val storyRetrofitInterface: StoryIntroRetrofitInterface = ApplicationClass.sRetrofit.create(StoryIntroRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )
    fun tryGetStoryDetail(houseId: Long){
        storyRetrofitInterface.getStoryDetail(accessToken!!,refreshToken!!,houseId).enqueue(object:
            Callback<InquiryStoryDetailResponse>{
            override fun onResponse(
                call: Call<InquiryStoryDetailResponse>,
                response: Response<InquiryStoryDetailResponse>
            ) {
                if(response.code() == OK){
                    storyIntroInterface.onGetStoryDetailSuccess(response.body() as InquiryStoryDetailResponse)
                }else{
                    storyIntroInterface.onGetStoryDetailFailure(response.body() as InquiryStoryDetailResponse)
                }
            }

            override fun onFailure(call: Call<InquiryStoryDetailResponse>, t: Throwable) {
                storyIntroInterface.onGetStoryDetailDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }
}