package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.simya.databinding.ActivityStoryCreateBorderBinding

class CreateMyStoryBorderActivity: AppCompatActivity() {
    lateinit var binding: ActivityStoryCreateBorderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryCreateBorderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.included.tvDefaultLayoutTitle.text = "이야기집 간판 생성"
        binding.ibMyStoryCreateBorderInfo.setOnClickListener {
            binding.tvMyStoryCreateMainInfo.isInvisible = false
        }
    }
}