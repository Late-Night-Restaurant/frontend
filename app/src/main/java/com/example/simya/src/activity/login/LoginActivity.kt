package com.example.simya.src.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simya.src.activity.home.HomeActivity
import com.example.simya.src.activity.login.signIn.EmailLoginActivity
import com.example.simya.databinding.ActivityLoginMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        // 카카오 로그인(현재 테스트용으로 홈화면으로 이동으로 바꿈)
        binding.ibLoginMainKakao.setOnClickListener {
            //잠시테스트
//            moveToKaKao()
        }

        // 이메일 로그인
        binding.ibLoginMainEmail.setOnClickListener {
            moveToEmail()
        }
    }

    private fun moveToKaKao() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun moveToEmail() {
        val intent = Intent(this, EmailLoginActivity::class.java)
        startActivity(intent)
    }
}