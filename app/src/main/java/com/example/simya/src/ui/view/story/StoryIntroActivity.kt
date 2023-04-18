//package com.example.simya.src.ui.view.story
//
//import android.content.Intent
//import com.example.simya.R
//import android.graphics.Color
//import android.os.Bundle
//import android.util.Log
//import com.bumptech.glide.Glide
//import com.example.simya.config.BaseActivity
//import com.example.simya.util.Constants.HOUSE_ID
//import com.example.simya.util.Constants.PROFILE_ID
//import com.example.simya.src.ui.view.chat.ChatActivity
//import com.example.simya.util.data.UserData
//import com.example.simya.databinding.ActivityStoryIntroBinding
//import com.example.simya.src.main.story.model.StoryIntroInterface
//import com.example.simya.src.main.story.model.StoryIntroService
//import com.example.simya.src.model.story.inquiry.InquiryStoryDetailResponse
//import com.example.simya.util.Constants.BORDER_IMAGE_URL
//import com.example.simya.util.Constants.BORDER_TITLE
//import com.example.simya.util.Constants.MASTER_ID
//import com.example.simya.util.Constants.S3_URL
//import com.example.simya.util.SampleSnackBar
//import com.example.simya.util.onThrottleClick
//import com.taufiqrahman.reviewratings.BarLabels
//
//
//class StoryIntroActivity :
//    BaseActivity<ActivityStoryIntroBinding>(ActivityStoryIntroBinding::inflate),
//    StoryIntroInterface {
//    private var houseId: Long = 0
//    private var masterId: Long = 0
//    private var houseName = "이야기 집"
//    private var houseBorder = "defatult"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        showLoadingDialog(this)
//        init()
//    }
//    private fun init() {
//        StoryIntroService(this).tryGetStoryDetail(intent.getLongExtra(HOUSE_ID,0))
//        val ratingReviews = binding.ratingReviews
//        // 별점 test
//        val colors = intArrayOf(
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString())
//        )
//        val raters = intArrayOf(
//            0,
//            0,
//            0,
//            0,
//            0
//        )
//        ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, raters)
//        binding.btnStoryIntroEnterChat.onThrottleClick {
//            moveToChat(
//                houseId,
//                masterId,
//                UserData.getProfileId()
//            )
//            Log.d("houseId check",houseId.toString())
//            Log.d("profileId check",UserData.getProfileId().toString())
//        }
//    }
//
//    private fun moveToChat(houseId: Long,masterId: Long, profileId: Long) {
//        val intent = Intent(this, ChatActivity::class.java)
//        intent.putExtra(HOUSE_ID, houseId)
//        intent.putExtra(PROFILE_ID, profileId)
//        intent.putExtra(MASTER_ID,masterId)
//        intent.putExtra(BORDER_TITLE,houseName)
//        intent.putExtra(BORDER_IMAGE_URL,houseBorder)
//
//        startActivity(intent)
//    }
//
//    override fun onGetStoryDetailSuccess(response: InquiryStoryDetailResponse) {
//        houseId = response.result!!.houseInfo.houseId
//        masterId = response.result!!.masterProfile.profileId
//        runOnUiThread {
//            // 프로필 주인정보
//            binding.tvStoryProfileNick.text =
//                response.result!!.masterProfile.nickname
//            binding.tvStoryProfileIntro.text =
//                response.result!!.masterProfile.comment
//            Glide.with(this).load(S3_URL+response.result!!.masterProfile.pictureUrl).into(binding.civStoryProfileImage)
//            //Glide binding.civStoryProfileImage 프로필 이미지 추가
//            // 이야기집 정보
//            binding.tvIntroMainMenu.text =
//                response.result!!.houseInfo.category
//            binding.tvIntroTitle.text =
//                response.result!!.houseInfo.houseName
//            houseName = response.result!!.houseInfo.houseName
//            binding.tvStoryProfileStoryIntro.text =
//                response.result!!.houseInfo.comment
//            Glide.with(this).load(S3_URL+response.result!!.houseInfo.signboardImageUrl).into(binding.ivRvBorderImage)
//            houseBorder = response.result!!.houseInfo.signboardImageUrl
//        }
//        dismissLoadingDialog()
//    }
//
//    override fun onGetStoryDetailFailure(response: InquiryStoryDetailResponse) {
//        SampleSnackBar.make(binding.root,response.message!!)
//        finish()
//    }
//
//    override fun onGetStoryDetailDisconnect(message: String) {
//        SampleSnackBar.make(binding.root,message)
//        dismissLoadingDialog()
//    }
//}
