package com.example.simya.mystoryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.databinding.FragmentHomeMainRecyclerBinding
import com.example.simya.adpter.homeAdapter.MainRVAdapter
import com.example.simya.adpter.mystoryAdapter.MyStoryRVAdpater
import com.example.simya.testData.TestDataBorder

class MyStoryRecyclerFragment: Fragment() {
    private lateinit var binding: FragmentHomeMainRecyclerBinding
    private lateinit var dataList: ArrayList<TestDataBorder>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainRecyclerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        dataList = arrayListOf()
        dataList.apply{
            add(TestDataBorder(3,"코딩","안드로이드","코딩은 재밌어"))
            add(TestDataBorder(1,"코딩","서버","코딩은 재밌어"))
            add(TestDataBorder(2,"코딩","안드로이드","코딩은 힘들어"))
            add(TestDataBorder(4,"코딩","서버","코딩은 힘들어"))
        }
        val dataRVAdapter = MyStoryRVAdpater(dataList)
        binding.rvHomeMainRecycler.adapter = dataRVAdapter
        binding.rvHomeMainRecycler.layoutManager = LinearLayoutManager(this.context)
    }
}