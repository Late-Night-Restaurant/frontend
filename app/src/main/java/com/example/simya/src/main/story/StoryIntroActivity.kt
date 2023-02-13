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
import com.example.simya.src.main.story.model.StoryIntroInterface
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse
import com.taufiqrahman.reviewratings.BarLabels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoryIntroActivity :
    BaseActivity<ActivityStoryIntroBinding>(ActivityStoryIntroBinding::inflate),
    StoryIntroInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    var houseId: Long = 0
    private fun init() {
        val ratingReviews = binding.ratingReviews
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
        binding.btnStoryIntroEnterChat.setOnClickListener {
            moveToChat(
                intent.getLongExtra(HOUSE_ID,0),
                UserData.getProfileId()
            )
            Log.d("@@@@@@@@@@@@house Id",houseId.toString())

            Log.d("@@@@@@@@@@@@@house Id",UserData.getProfileId().toString())
        }
    }

    private fun moveToChat(houseId: Long, profileId: Long) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra(HOUSE_ID, houseId)
        intent.putExtra(PROFILE_ID, profileId)
        startActivity(intent)
    }

    override fun onGetStoryDetailSuccess(response: InquiryStoryDetailResponse) {
        houseId = response.result!!.houseInfo.houseId
        runOnUiThread {
            // 프로필 주인정보
            binding.tvStoryProfileNick.text =
                response.result!!.masterProfile.nickname
            binding.tvStoryProfileIntro.text =
                response.result!!.masterProfile.comment
            //Glide binding.civStoryProfileImage 프로필 이미지 추가
            // 이야기집 정보
            binding.tvIntroMainMenu.text =
                response.result!!.houseInfo.category
            binding.tvIntroTitle.text =
                response.result!!.houseInfo.houseName
            binding.tvStoryProfileStoryIntro.text =
                response.result!!.houseInfo.comment
        }
    }

    override fun onGetStoryDetailFailure(response: InquiryStoryDetailResponse) {
        Log.d("@@@@@ CHECK @@@@@@", "이야기집 정보 가져오기 실패")
    }
}
