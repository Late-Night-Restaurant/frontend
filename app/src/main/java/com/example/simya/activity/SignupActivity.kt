package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.signUpViewModel.SignUpViewModel
import com.example.simya.signupFragment.*

class SignupActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(SignUpViewModel::class.java)

        setContentView(binding.root)

        viewModel.pbValue.observe(this, Observer {
            binding.pbSignup.progress = it
        })




        init()

    }

    // count == 0 약관동의 fragment 표시
    private fun init() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fmSignup.id, SignupAgreeFragment())
            .commitAllowingStateLoss()
        binding.pbSignup.progress = 0
    }


}