package com.example.simya.src.main.myPage.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.databinding.FragmentMyPageLikeRecyclerBinding
import com.example.simya.src.main.myPage.adapter.myPageLike.MyPageLikeRVAdapter
import com.example.simya.src.testData.TestDataBorder

class MyPageLikeRecyclerFragment: Fragment() {
    private lateinit var binding: FragmentMyPageLikeRecyclerBinding
    private lateinit var dataList: ArrayList<TestDataBorder>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageLikeRecyclerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        dataList = arrayListOf()
        dataList.apply {
            add(TestDataBorder(3,"마라탕","안드로이드","매워"))
            add(TestDataBorder(1,"닭갈비","서버","달달해"))
            add(TestDataBorder(2,"삼겹살","안드로이드","최고"))
            add(TestDataBorder(4,"곱창","서버","고소해"))
        }
        val dataRVAdpater = MyPageLikeRVAdapter(dataList)
        binding.rvMyPageLikeRecycler.adapter = dataRVAdpater
        binding.rvMyPageLikeRecycler.layoutManager = LinearLayoutManager(this.context)


    }
}
