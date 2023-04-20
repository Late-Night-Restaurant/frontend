//package com.example.simya.src.data.repository.login
//
//import android.util.Log
//import com.example.simya.config.ApplicationClass
//import com.example.simya.config.BaseResponse
//import com.example.simya.src.data.network.api.login.LoginApi
//import com.example.simya.src.data.network.model.login.AccountDTO
//import com.example.simya.src.data.network.model.login.AccountResponse
//import com.example.simya.util.Constants
//import com.example.simya.util.Constants.OK
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class LoginService(val loginInterface: LoginDataSource) {
//    private val loginRetrofitInterface: LoginApi = ApplicationClass.sRetrofit.create(
//        LoginApi::class.java)
//
//    fun tryLoginSubmit(accountDTO: AccountDTO){
//        loginRetrofitInterface.onLoginSubmit(accountDTO).enqueue(object: Callback<AccountResponse>{
//            override fun onResponse(
//                call: Call<AccountResponse>,
//                response: Response<AccountResponse>
//            ) {
//                if(response.body()!!.code==OK){
//                    loginInterface.onPostLoginSubmitSuccess(response.body() as AccountResponse)
//                }else{
//                    Log.d("Login","Fail")
//                    loginInterface.onPostLoginSubmitFailure(response.body() as BaseResponse)
//                }
//            }
//            override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
////                loginInterface.onPostLoginSubmitFailure(t.message ?:"통신 오류")
//                loginInterface.onPostLoginSubmitDisconnect("서버가 원활하지 않습니다.")
//                // 통신 에러
//            }
//        })
//    }
//    fun setAccessTokenSharedPreferences(accessToken: String){
//        ApplicationClass.sSharedPreferences.edit()
//            .putString(Constants.ACCESS_TOKEN, "Access $accessToken").apply()
//    }
//    fun setRefreshTokenSharedPreferences(refreshToken: String){
//        ApplicationClass.sSharedPreferences.edit()
//            .putString(Constants.REFRESH_TOKEN, "Refresh $refreshToken").apply()
//    }
//}