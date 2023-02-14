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
import com.example.simya.src.main.story.model.StoryIntroService
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse
import com.example.simya.util.SampleSnackBar
import com.taufiqrahman.reviewratings.BarLabels
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoryIntroActivity :
    BaseActivity<ActivityStoryIntroBinding>(ActivityStoryIntroBinding::inflate),
    StoryIntroInterface {
    private var houseId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoadingDialog(this)
        init()
    }
    private fun init() {
        StoryIntroService(this).tryGetStoryDetail(intent.getLongExtra(HOUSE_ID,0))
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
                houseId,
                UserData.getProfileId()
            )
            Log.d("houseId check",houseId.toString())
            Log.d("profileId check",UserData.getProfileId().toString())
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
        dismissLoadingDialog()
    }

    override fun onGetStoryDetailFailure(response: InquiryStoryDetailResponse) {
        SampleSnackBar.make(binding.root,response.message!!)
        finish()
    }
}
