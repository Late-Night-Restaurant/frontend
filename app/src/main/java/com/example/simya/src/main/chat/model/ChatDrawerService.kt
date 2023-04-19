package com.example.simya.src.main.chat.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.config.BaseResponse
import com.example.simya.src.model.ChatProfileListResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatDrawerService(val chatDrawerInterface: ChatDrawerInterface) {
    private val chatDrawerRetrofitInterface: ChatDrawerRetrofitInterface = ApplicationClass.sRetrofit.create(
        ChatDrawerRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )

    fun tryGetChatProfileList(houseId: Long){
        chatDrawerRetrofitInterface.getHouseProfileList(accessToken!!,refreshToken!!,houseId).enqueue(object:
            Callback<ChatProfileListResponse> {
            override fun onResponse(
                call: Call<ChatProfileListResponse>,
                response: Response<ChatProfileListResponse>
            ) {
                if(response.body()!!.code == OK){
                    chatDrawerInterface.onGetChatProfileListSuccess(response.body() as ChatProfileListResponse)
                }else{
                    Log.d("Chat Profile List","Fail")
                    chatDrawerInterface.onGetChatProfileListFailure(response.body() as BaseResponse)
                }
            }

            override fun onFailure(call: Call<ChatProfileListResponse>, t: Throwable) {
                chatDrawerInterface.onGetChatProfileListDisconnect("서버가 원할하지 않습니다.")
            }

        })
    }
}