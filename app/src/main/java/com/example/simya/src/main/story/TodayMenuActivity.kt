package com.example.simya.src.main.story

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simya.src.main.story.adapter.TodayMenuRVAdapter
import com.example.simya.databinding.ActivityTodayMenuBinding
import com.example.simya.src.testData.TestDataChip

class TodayMenuActivity: AppCompatActivity() {
    lateinit var binding: ActivityTodayMenuBinding
    lateinit var dataList: ArrayList<TestDataChip>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.included.tvDefaultLayoutTitle.text = "이야기 집 오늘의 메뉴"
        dataList = arrayListOf()
        dataList.apply{
            add(TestDataChip("심야식당이 좋다","???"))
            add(TestDataChip("심야식당이 좋을까?","???"))
            add(TestDataChip("심야식당이 이렇게 괜찮을까?","???"))
            add(TestDataChip("심야식당이 어렵다","???"))
            add(TestDataChip("심야식당이 바쁘다","???"))
            add(TestDataChip("심야식당 도망쳐","???"))
        }
        val dataRVAdapter = TodayMenuRVAdapter(this,dataList)
        binding.rvTodayMenu.adapter= dataRVAdapter
        binding.rvTodayMenu.layoutManager = LinearLayoutManager(this)
    }
}