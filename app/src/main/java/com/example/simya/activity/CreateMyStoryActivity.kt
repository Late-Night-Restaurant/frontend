package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.createMyStoryAdapter.CreateMyStoryRVAdapter
import com.example.simya.databinding.ActivityMyStoryCreateBinding
import com.example.simya.testData.TestDataMultiProfile

class CreateMyStoryActivity: AppCompatActivity() {
    lateinit var binding: ActivityMyStoryCreateBinding
    private lateinit var dataList: ArrayList<TestDataMultiProfile>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyStoryCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.includedTitle.tvDefaultLayoutTitle.text = "이야기집 생성"
        dataList = arrayListOf()
        // test load
        Glide.with(this).load(R.drawable.test_simya).into(binding.civMyStoryCreateSelectProfileImage)

        dataList.apply{
            R.drawable.testimg
            add(TestDataMultiProfile(R.drawable.test_wak))
            add(TestDataMultiProfile(R.drawable.test_chani))
            add(TestDataMultiProfile(R.drawable.test_choi))
            add(TestDataMultiProfile(R.drawable.test_hatban))
            add(TestDataMultiProfile(R.drawable.test_poo))
            add(TestDataMultiProfile(R.drawable.test_jooni))
        }
        val dataRVAdapter = CreateMyStoryRVAdapter(this,dataList)
        binding.rvMyStoryCreateRecycler.adapter = dataRVAdapter
        binding.rvMyStoryCreateRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }
}