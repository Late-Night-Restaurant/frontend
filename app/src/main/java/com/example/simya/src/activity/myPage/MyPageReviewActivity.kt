package com.example.simya.src.activity.myPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.simya.databinding.ActivityMyPageReviewBinding
import com.example.simya.src.dialog.SortDialog
import com.example.simya.src.fragment.mypageFragment.MyPageReviewFragment

class MyPageReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageReviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fmMyPageReview.id, MyPageReviewFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        // 정렬 타입 바꾸기
        binding.ibMyPageReviewType.setOnClickListener {
            val dialog = SortDialog(this as AppCompatActivity)
            dialog!!.showDia()
        }
    }
}