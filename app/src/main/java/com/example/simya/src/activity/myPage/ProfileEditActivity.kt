package com.example.simya.src.activity.myPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.src.activity.home.HomeActivity
import com.example.simya.databinding.ActivityProfileEditBinding

class ProfileEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.btnProfileNext.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}