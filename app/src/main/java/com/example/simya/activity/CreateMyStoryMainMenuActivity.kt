package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.simya.databinding.ActivityMyStoryCreateMainMenuBinding

class CreateMyStoryMainMenuActivity: AppCompatActivity() {
    lateinit var binding: ActivityMyStoryCreateMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyStoryCreateMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.ibMyStoryCreateMainMenuInfo.setOnClickListener {
            binding.tvMyStoryCreateMainInfo.isInvisible = false
        }
    }
}