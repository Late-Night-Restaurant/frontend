package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivityMainBinding
import com.example.simya.databinding.ActivityMyStoryOpenInputBinding

class OpenMyStoryInputActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyStoryOpenInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyStoryOpenInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집 오픈 준비하기"
    }
}