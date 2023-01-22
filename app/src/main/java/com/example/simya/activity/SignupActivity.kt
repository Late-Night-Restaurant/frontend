package com.example.simya.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.signupFragment.*

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    var count = 0   // 다음으로 버튼 count를 셀 변수 선언
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()  // 첫 번째 Fragment 초기화

        // 다음으로 버튼 눌렀을 때
        binding.btnSignupNext.setOnClickListener {
            count++ // count값 증가
            when (count) {
                // count == 1이면 Email 페이지 이동
                1 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.fmSignup.id, SignupEmailFragment())
                        .commitAllowingStateLoss()
                    binding.pbSignup.progress = 25
                }
                // count == 2면 Pw 페이지
                2 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.fmSignup.id, SignupPwFragment())
                        .commitAllowingStateLoss()
                    binding.pbSignup.progress = 50
                }
                // count == 3 프로필 페이지
                3 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.fmSignup.id, SignupProfileFragment())
                        .commitAllowingStateLoss()
                    binding.pbSignup.progress = 75
                }
                // count == 4 끝 페이지
                4 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.fmSignup.id, SignupFinFragment())
                        .commitAllowingStateLoss()
                    binding.pbSignup.progress = 100
                }
                // count == 5 로그인 화면으로 이동
                5 -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    // count == 0 약관동의 fragment 표시
    private fun init() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fmSignup.id, SignupFragment())
            .commitAllowingStateLoss()
    }




}