package com.example.simya.src.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.databinding.FragmentMyPageReviewRecyclerBinding
import com.example.simya.src.adpter.myPage.MyPageReviewAdapter
import com.example.simya.src.testData.TestDataReview

class MyPageReviewFragment: Fragment() {
    private lateinit var binding: FragmentMyPageReviewRecyclerBinding
    private lateinit var dataList: ArrayList<TestDataReview>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageReviewRecyclerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        dataList = arrayListOf()
        dataList.apply {
            add(TestDataReview("더글로리", "재밌당", 3))
            add(TestDataReview("해리포터", "굿", 5))
            add(TestDataReview("짱구는 못말려", "추억", 4))
            add(TestDataReview("냐?", "엥", 2))
        }

        val dataRVAdpater = MyPageReviewAdapter(dataList)
        binding.rvMyPageReviewRecycler.adapter = dataRVAdpater
        binding.rvMyPageReviewRecycler.layoutManager = LinearLayoutManager(this.context)

    }
}