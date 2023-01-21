package com.example.simya.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.R
import com.example.simya.databinding.ActivityStoryReviewListBinding
import com.example.simya.homeAdapter.MainRVAdapter
import com.example.simya.homeAdapter.StoryReviewRVAapter
import com.example.simya.testData.TestDataBorder
import com.example.simya.testData.TestDataReview
import com.taufiqrahman.reviewratings.BarLabels
import java.util.*
import kotlin.collections.ArrayList

class StoryReviewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStoryReviewListBinding
    private lateinit var dataList: ArrayList<TestDataReview>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryReviewListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        val ratingReviews = binding.ratingReviews

        val colors = intArrayOf(
            Color.parseColor("#"+Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#"+Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#"+Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#"+Integer.toHexString(getColor(R.color.Primary_01)).toString()),
            Color.parseColor("#"+Integer.toHexString(getColor(R.color.Primary_01)).toString())
        )
        val raters = intArrayOf(
            Random().nextInt(100),
            Random().nextInt(100),
            Random().nextInt(100),
            Random().nextInt(100),
            Random().nextInt(100)
        )
        ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, raters)

        dataList = arrayListOf()
        dataList.apply{
            add(TestDataReview("푸","재밌다 푸하하",3))
            add(TestDataReview("왁","초이 성장에 이르렀는가..?",4))
            add(TestDataReview("채니","렛츠고 페스티벌",1))
            add(TestDataReview("쭈니","갓생사는중",2))
            add(TestDataReview("햇반","디자인 ..완료..",3))
            add(TestDataReview("푸","재밌다 푸하하",3))
            add(TestDataReview("왁","초이 성장에 이르렀는가..?",4))
            add(TestDataReview("채니","렛츠고 페스티벌",1))
            add(TestDataReview("쭈니","갓생사는중",2))
            add(TestDataReview("햇반","디자인 ..완료..",3))
            add(TestDataReview("푸","재밌다 푸하하",3))
            add(TestDataReview("왁","초이 성장에 이르렀는가..?",4))
            add(TestDataReview("채니","렛츠고 페스티벌",1))
            add(TestDataReview("쭈니","갓생사는중",2))
            add(TestDataReview("햇반","디자인 ..완료..",3))
        }
        val dataRVAdapter = StoryReviewRVAapter(dataList)
        binding.rvStroyReviewRecycler.adapter = dataRVAdapter
        binding.rvStroyReviewRecycler.layoutManager = LinearLayoutManager(this)
    }
}