//package com.example.simya.src.ui.view.mypage
//
//import android.os.Bundle
//import android.util.Log
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityReviewBinding
//import com.example.simya.util.data.UserData
//import com.example.simya.src.model.RetrofitBuilder
//import com.example.simya.src.model.RetrofitService
//import com.example.simya.src.model.mypage.review.MyModifyReviewResponse
//import com.example.simya.util.Constants.OK
//import com.example.simya.util.onThrottleClick
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MyReviewActivity: BaseActivity<ActivityReviewBinding>(ActivityReviewBinding::inflate)
//{
//    private val retrofit by lazy {
//        RetrofitBuilder.getInstnace()
//    }
//    private val simyaApi by lazy {
//        retrofit.create(RetrofitService::class.java)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//    private fun init() {
//        binding.included.tvDefaultLayoutTitle.text = "리뷰 수정하기"
//        // 버튼을 누르면 리뷰 수정하기
//        binding.btnItemEditReview.onThrottleClick {
//            tryModifyMyWriteReview()
//        }
//    }
//    private fun tryModifyMyWriteReview(){
//        simyaApi.modifyMyWriteReview(UserData.accessToken, UserData.refreshToken,1).enqueue(object :
//            Callback<MyModifyReviewResponse>{
//            override fun onResponse(
//                call: Call<MyModifyReviewResponse>,
//                response: Response<MyModifyReviewResponse>
//            ) {
//                val code = response.body()!!.code
//                if(code == OK){
//                    // 리뷰 수정
//                }
//            }
//            override fun onFailure(call: Call<MyModifyReviewResponse>, t: Throwable) {
//                Log.d("Response",t.toString())
//            }
//
//        })
//    }
//}