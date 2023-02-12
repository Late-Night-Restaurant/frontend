package com.example.simya.src.main.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simya.config.BaseActivity
import com.example.simya.src.main.home.HomeActivity
import com.example.simya.src.main.login.signIn.EmailLoginActivity
import com.example.simya.databinding.ActivityLoginMainBinding
import com.example.simya.databinding.ActivitySigninEmailBinding

class LoginActivity : BaseActivity<ActivityLoginMainBinding>(ActivityLoginMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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