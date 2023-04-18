//package com.example.simya.src.ui.view.story
//
//import android.graphics.Color
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.simya.R
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityStoryReviewListBinding
//import com.example.simya.src.ui.adapter.home.StoryReviewRVAapter
//import com.example.simya.src.testData.TestDataReview
//import com.taufiqrahman.reviewratings.BarLabels
//import java.util.*
//import kotlin.collections.ArrayList
//
//class StoryReviewActivity :
//    BaseActivity<ActivityStoryReviewListBinding>(ActivityStoryReviewListBinding::inflate) {
//    private lateinit var dataList: ArrayList<TestDataReview>
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집의 평점과 리뷰"
//        val ratingReviews = binding.ratingReviews
//
//        val colors = intArrayOf(
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString()),
//            Color.parseColor("#" + Integer.toHexString(getColor(R.color.Primary_01)).toString())
//        )
//        val raters = intArrayOf(
//            Random().nextInt(100),
//            Random().nextInt(100),
//            Random().nextInt(100),
//            Random().nextInt(100),
//            Random().nextInt(100)
//        )
//        ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, raters)
//
//        dataList = arrayListOf()
//        dataList.apply {
//            add(TestDataReview("푸", "올인하고자 하는 마음...", 5))
//            add(TestDataReview("쭈니", "비약적인 성장을 위한 마음...", 5))
//            add(TestDataReview("햇반", "자식같은 마음...", 5))
//            add(TestDataReview("왁", "PM의 마음....", 5))
//            add(TestDataReview("초이", "중요한건 꺾이지 않는 마음...", 5))
//        }
//        val dataRVAdapter = StoryReviewRVAapter(dataList)
//        binding.rvStroyReviewRecycler.adapter = dataRVAdapter
//        binding.rvStroyReviewRecycler.layoutManager = LinearLayoutManager(this)
//    }
//
//}