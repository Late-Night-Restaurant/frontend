package com.example.simya.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.adpter.createMyStoryAdapter.CreateMyStoryRVAdapter
import com.example.simya.databinding.ActivityMyStoryCreateBinding
import com.example.simya.testData.TestDataMultiProfile

class CreateMyStoryActivity : AppCompatActivity() {
    private val binding: ActivityMyStoryCreateBinding by lazy{
      ActivityMyStoryCreateBinding.inflate(layoutInflater)
    }
    private var dataList: ArrayList<TestDataMultiProfile> = arrayListOf()
    private val dataRVAdapter = CreateMyStoryRVAdapter(this, dataList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.includedTitle.tvDefaultLayoutTitle.text = "이야기집 생성"

        binding.btnMyStoryCreateNext.setOnClickListener {
            moveToSetMenu()
        }
        // test load
        Glide.with(this).load(R.drawable.test_simya)
            .into(binding.civMyStoryCreateSelectProfileImage)
        // test init
        initData()
        // Adapter init
        initAdapter()
        // recyclerview click listener
        clickItem()
    }
    private fun moveToSetMenu(){
        val intent = Intent(this,CreateMyStoryMainMenuActivity::class.java)
        startActivity(intent)
    }
    private fun initAdapter() {
        binding.rvMyStoryCreateRecycler.adapter = dataRVAdapter
        binding.rvMyStoryCreateRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun clickItem() {
        dataRVAdapter.setOnItemClickListener(object : CreateMyStoryRVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: TestDataMultiProfile, position: Int) {
                Glide.with(this@CreateMyStoryActivity).load(data.imageSource).centerCrop()
                    .into(binding.civMyStoryCreateSelectProfileImage)
                binding.tvMyStoryCreateNick.text = data.nickname
                binding.tvMyStoryCreateIntro.text = data.intro
            }

            override fun onLongClick(v: View, data: TestDataMultiProfile, position: Int) {
                Glide.with(this@CreateMyStoryActivity).load(data.imageSource).centerCrop()
                    .into(binding.civMyStoryCreateSelectProfileImage)
            }
        })
    }
    private fun singleSelected(){

    }
    private fun initData() {
        dataList.apply {
            R.drawable.testimg
            add(TestDataMultiProfile(R.drawable.test_wak, "왁", "기획을 맡은 왁입니다."))
            add(TestDataMultiProfile(R.drawable.test_chani, "채니", "백앤드 파트입니다."))
            add(TestDataMultiProfile(R.drawable.test_choi, "초이", "프론트 파트입니다."))
            add(TestDataMultiProfile(R.drawable.test_hatban, "햇반", "디자이너 입니다."))
            add(TestDataMultiProfile(R.drawable.test_poo, "푸", "프론트 파트입니다."))
            add(TestDataMultiProfile(R.drawable.test_jooni, "쭈니", "백앤드 파트입니다."))
        }
    }
}