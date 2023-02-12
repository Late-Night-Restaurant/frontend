package com.example.simya.src.main.story

import android.content.Intent
import com.example.simya.R
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.config.BaseActivity
import com.example.simya.util.Constants.HOUSE_ID
import com.example.simya.util.Constants.OK
import com.example.simya.util.Constants.PROFILE_ID
import com.example.simya.src.main.chat.ChatActivity
import com.example.simya.util.data.UserData
import com.example.simya.databinding.ActivityStoryIntroBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse
import com.taufiqrahman.reviewratings.BarLabels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoryIntroActivity :
    BaseActivity<ActivityStoryIntroBinding>(ActivityStoryIntroBinding::inflate) {
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        val ratingReviews = binding.ratingReviews
        inquiryStoryDetail()
        // 별점 test
        val colors = intArrayOf(
            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString())
        )
        val raters = intArrayOf(
            0,
            0,
            0,
            0,
            0
        )
        ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, raters)
    }

    fun moveToChat(houseId: Long, profileId: Long) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(HOUSE_ID, houseId)
        intent.putExtra(PROFILE_ID, profileId)
        startActivity(intent)
    }

    fun inquiryStoryDetail() {
        simyaApi.getStoryDetail(
            UserData.accessToken,
            UserData.refreshToken, intent.getLongExtra(HOUSE_ID, 0)
        )
            .enqueue(object : Callback<InquiryStoryDetailResponse> {
                override fun onResponse(
                    call: Call<InquiryStoryDetailResponse>,
                    response: Response<InquiryStoryDetailResponse>
                ) {
                    if (response.body()!!.code == OK) {
                        runOnUiThread {
                            // 프로필 주인정보
                            binding.tvStoryProfileNick.text =
                                response.body()!!.result!!.masterProfile.nickname
                            binding.tvStoryProfileIntro.text =
                                response.body()!!.result!!.masterProfile.comment
                            //Glide binding.civStoryProfileImage 프로필 이미지 추가
                            // 이야기집 정보
                            binding.tvIntroMainMenu.text =
                                response.body()!!.result!!.houseInfo.category
                            binding.tvIntroTitle.text =
                                response.body()!!.result!!.houseInfo.houseName
                            binding.tvStoryProfileStoryIntro.text =
                                response.body()!!.result!!.houseInfo.comment
                            binding.btnStoryIntroEnterChat.setOnClickListener {
                                moveToChat(
                                    response.body()!!.result!!.houseInfo.houseId,
                                    UserData.getProfileId()
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<InquiryStoryDetailResponse>, t: Throwable) {
                    Log.d("Response", t.toString())
                }

            })
    }
}