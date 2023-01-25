package com.example.simya.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupAgreeBinding
import com.example.simya.databinding.FragmentSignupEmailBinding
import com.example.simya.signupFragment.*

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }



    // count == 0 약관동의 fragment 표시
    private fun init() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fmSignup.id, SignupFragment())
            .commitAllowingStateLoss()
        binding.pbSignup.progress = 0
    }


}