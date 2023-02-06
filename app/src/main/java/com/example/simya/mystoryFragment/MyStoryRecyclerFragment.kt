package com.example.simya.mystoryFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.Constants
import com.example.simya.databinding.FragmentHomeMainRecyclerBinding
import com.example.simya.adpter.mystoryAdapter.MyStoryAdapter
import com.example.simya.data.UserTokenData
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import com.example.simya.server.story.LoadMyStoryResponse
import com.example.simya.server.story.LoadMyStoryResult
import com.example.simya.testData.TestDataBorder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyStoryRecyclerFragment: Fragment() {
    private lateinit var binding: FragmentHomeMainRecyclerBinding
    private lateinit var dataList: ArrayList<TestDataBorder>
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
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
        loadMyStory()
        dataList = arrayListOf()
        dataList.apply{
        }
        val dataRVAdapter = MyStoryAdapter(dataList)
        binding.rvHomeMainRecycler.adapter = dataRVAdapter
        binding.rvHomeMainRecycler.layoutManager = LinearLayoutManager(this.context)
    }
    private fun loadMyStory(){
        simyaApi.getMyStory(UserTokenData.accessToken, UserTokenData.getUserRefreshToken()).enqueue(object:
            Callback<LoadMyStoryResponse> {
            override fun onResponse(
                call: Call<LoadMyStoryResponse>,
                response: Response<LoadMyStoryResponse>
            ) {
                if(response.code() == Constants.OK){
                    Log.d("Response",response.body().toString())
                }
            }

            override fun onFailure(call: Call<LoadMyStoryResponse>, t: Throwable) {
                Log.d("ERROR",t.toString())
            }

        })
    }
    private fun toResultBorderData(receivedata: List<LoadMyStoryResult>){
        for(i: Int in 0..receivedata.size)
        {
            TestDataBorder(receivedata[i].houseId,receivedata[i].signboardImageUrl,
                receivedata[i].houseName,
                receivedata[i].category)
        }

    }
}