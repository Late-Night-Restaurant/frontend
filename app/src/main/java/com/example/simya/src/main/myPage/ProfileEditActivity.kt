package com.example.simya.src.main.myPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.src.main.home.HomeActivity
import com.example.simya.databinding.ActivityProfileEditBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService

class ProfileEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileEditBinding
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
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