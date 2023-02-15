package com.example.simya.src.main.myPage.model

import android.util.Log
import com.example.simya.config.ApplicationClass
import com.example.simya.config.BaseResponse
import com.example.simya.src.model.UserDTO
import com.example.simya.util.Constants
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class ProfileModifyService(val profileModifyInterface: ProfileModifyInterface) {
    private val profileModifyRetrofitInterface: ProfileModifyRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileModifyRetrofitInterface::class.java)
    private val accessToken = ApplicationClass.sSharedPreferences.getString(
        Constants.ACCESS_TOKEN,
        Constants.DEFAULT
    )
    private val refreshToken = ApplicationClass.sSharedPreferences.getString(
        Constants.REFRESH_TOKEN,
        Constants.DEFAULT
    )

    fun tryModifyMyProfile(userDTO: UserDTO){
        profileModifyRetrofitInterface.modifyMyProfile(accessToken!!,refreshToken!!,userDTO.profileId,userDTO).enqueue(object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.code()==OK){
                    profileModifyInterface.onModifyMyProfileSuccess(response.body() as BaseResponse)
                }else{
                    Log.d("Profile Modify","Fail")
                    profileModifyInterface.onModifyMyProfileFailure(response.body() as BaseResponse)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                profileModifyInterface.onModifyMyProfileDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }
    fun tryDeleteMyProfile(profileId: Long){
        profileModifyRetrofitInterface.deleteMyProfile(accessToken!!,refreshToken!!,profileId).enqueue(object: Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if(response.code()==OK){
                    profileModifyInterface.onDeleteMyProfileSuccess(response.body() as BaseResponse)
                }else{
                    profileModifyInterface.onDeleteMyProfileFailure(response.body() as BaseResponse)
                    Log.d("Profile Delete","Fail")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                profileModifyInterface.onDeleteMyProfileDisconnect("서버가 원활하지 않습니다.")
            }

        })
    }
}