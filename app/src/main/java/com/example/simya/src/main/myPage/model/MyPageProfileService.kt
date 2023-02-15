package com.example.simya.src.main.myPage.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.config.BaseResponse
import com.example.simya.src.model.profile.ProfileDTO
import com.example.simya.src.model.profile.ProfileResponse
import com.example.simya.util.Constants.ACCESS_TOKEN
import com.example.simya.util.Constants.DEFAULT
import com.example.simya.util.Constants.OK
import com.example.simya.util.Constants.REFRESH_TOKEN
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageProfileService(val myPageProfileInterface: MyPageProfileInterface) {
    private val myPageProfileRetrofitInterface: MyPageProfileRetrofitInterface =
        ApplicationClass.sRetrofit.create(MyPageProfileRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(ACCESS_TOKEN, DEFAULT)
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(REFRESH_TOKEN, DEFAULT)
    fun tryGetUserProfile() {
        myPageProfileRetrofitInterface.getUserProfile(accessToken!!, refreshToken!!)
            .enqueue(object :
                Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.code() == OK) {
                        myPageProfileInterface.onGetUserProfileSuccess(response.body() as ProfileResponse)
                    } else {
                        Log.d("tryGetUserProfile", "fail")
                        myPageProfileInterface.onGetUserProfileFailure(response.body() as ProfileResponse)
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    myPageProfileInterface.onGetUserProfileDisconnect("서버가 원활하지 않습니다.")
                }

            })
    }
    fun trySetMyRepresentProfile(data: ProfileDTO){
        myPageProfileRetrofitInterface.setMyRepresentProfile(accessToken!!, refreshToken!!,data.profileId).enqueue(object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.code() == OK){
                    myPageProfileInterface.onSetMyRepresentProfileSuccess(response.body() as BaseResponse,data)
                }else{

                    myPageProfileInterface.onSetMyRepresentProfileFailure(response.body() as BaseResponse)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                myPageProfileInterface.onSetMyRepresentDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }


    fun tryOnLogout() {
        myPageProfileRetrofitInterface.onLogout(accessToken!!, refreshToken!!)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    if (response.code() == OK) {
                        myPageProfileInterface.onLogoutSuccess(response.body() as BaseResponse)
                    } else {
                        myPageProfileInterface.onLogoutFailure(response.body() as BaseResponse)
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    myPageProfileInterface.onLogoutDisconnect("서버가 원활하지 않습니다.")
                }

            })
    }

}