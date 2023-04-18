package com.example.simya.src.ui.view.login

import android.content.Intent
import android.os.Bundle
import com.example.simya.config.BaseActivity
import com.example.simya.src.ui.view.home.HomeActivity
import com.example.simya.src.ui.view.login.signIn.EmailLoginActivity
import com.example.simya.databinding.ActivityLoginMainBinding
import com.example.simya.util.onThrottleClick

class LoginActivity : BaseActivity<ActivityLoginMainBinding>(ActivityLoginMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 카카오 로그인(현재 테스트용으로 홈화면으로 이동으로 바꿈)
        binding.ibLoginMainKakao.onThrottleClick {
        }
        // 이메일 로그인 -> 현재 그냥 시작하기
        binding.ibLoginMainEmail.onThrottleClick {
            moveToEmail()
            finish()
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