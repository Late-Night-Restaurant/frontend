package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simya.R
import com.example.simya.adpter.storyAdapter.StoryLikeGVAdapter
import com.example.simya.databinding.ActivityStoryLikeListBinding
import com.example.simya.adpter.homeAdapter.MainGVAdapter
import com.example.simya.testData.TestDataLike

class StoryLikeActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoryLikeListBinding
    lateinit var dataList: ArrayList<TestDataLike>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryLikeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집의 찜한 손님"
        dataList = arrayListOf()
        dataList.apply{
            add(TestDataLike("wak",R.drawable.test_wak))
            add(TestDataLike("chani",R.drawable.test_chani))
            add(TestDataLike("choi",R.drawable.test_choi))
            add(TestDataLike("hatban",R.drawable.test_hatban))
            add(TestDataLike("poo",R.drawable.test_poo))
            add(TestDataLike("jooni",R.drawable.test_jooni))
        }
        val dataGVAdapter = StoryLikeGVAdapter(this,dataList)
        val gridLayoutManager = GridLayoutManager(this,4)
        binding.rvStoryLikeList.adapter = dataGVAdapter
        binding.rvStoryLikeList.layoutManager = gridLayoutManager
    }
}