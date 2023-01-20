package com.example.simya.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simya.databinding.ActivityLoginMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        // 카카오 로그인
        //binding.ibLoginMainKakao

        // 이메일 로그인
        binding.ibLoginMainEmail.setOnClickListener{
            val intent = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
        }
    }
}