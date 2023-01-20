package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivitySignupBinding
import signupFragment.SignupFragment

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    // 첫번째 fragment 표시
    private fun init() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fmSignup.id, SignupFragment())
            .commitAllowingStateLoss()
    }
}