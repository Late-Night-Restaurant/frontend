package com.example.simya.activity

import com.example.simya.R
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivityStoryIntroBinding
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import com.taufiqrahman.reviewratings.BarLabels
import java.util.*


class StoryIntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoryIntroBinding
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

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
    }
    fun inquiryStoryDetail(){
        simyaApi.
    }
}