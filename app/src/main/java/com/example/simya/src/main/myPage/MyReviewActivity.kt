package com.example.simya.src.main.myPage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivityReviewBinding
import com.example.simya.src.data.UserTokenData
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.mypage.review.MyModifyReviewResponse
import com.example.simya.util.Constants.OK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyReviewActivity: AppCompatActivity() {
    lateinit var binding: ActivityReviewBinding
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "리뷰 수정하기"
        // 버튼을 누르면 리뷰 수정하기
        binding.btnItemEditReview.setOnClickListener {
            modifyWriteReview()
        }
    }
    private fun modifyWriteReview(){
        simyaApi.modifyMyWriteReview(UserTokenData.accessToken,UserTokenData.refreshToken,1).enqueue(object :
            Callback<MyModifyReviewResponse>{
            override fun onResponse(
                call: Call<MyModifyReviewResponse>,
                response: Response<MyModifyReviewResponse>
            ) {
                val code = response.body()!!.code
                if(code == OK){
                    // 리뷰 수정
                }
            }
            override fun onFailure(call: Call<MyModifyReviewResponse>, t: Throwable) {
                Log.d("Response",t.toString())
            }

        })
    }
}