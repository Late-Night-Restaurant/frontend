package com.example.simya.src.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.testData.TestDataBorder
import com.example.simya.databinding.FragmentHomeMainRecyclerBinding
import com.example.simya.src.ui.adapter.home.HomeRVAdapter
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService

class HomeRecyclerFragment: BaseFragment<FragmentHomeMainRecyclerBinding>(R.layout.fragment_home_main_recycler) {
    private lateinit var dataList: ArrayList<TestDataBorder>
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
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
        val dataRVAdapter = HomeRVAdapter(requireContext(),dataList)
        binding.rvHomeMainRecycler.adapter = dataRVAdapter
        binding.rvHomeMainRecycler.layoutManager = LinearLayoutManager(this.context)
    }

}