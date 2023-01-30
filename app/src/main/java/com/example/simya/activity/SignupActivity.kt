package com.example.simya.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.simya.R
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupAgreeBinding
import com.example.simya.signUpViewModel.SignUpViewModel
import com.example.simya.signupFragment.*

class SignupActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel
    private lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)


        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(SignUpViewModel::class.java)


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

    private fun initTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }

}