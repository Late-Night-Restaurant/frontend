package com.example.simya.src.ui.view.login.singup

import android.os.Bundle
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivitySignupBinding

class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    override fun init() {

    }

    interface onBackPressedListener {
        fun onBackPressed()
    }

    override fun onBackPressed() {
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                (fragment as onBackPressedListener).onBackPressed()
                return
            }
        }
    }
    fun increaseProgressbar() {
        binding.pbSignup.progress += 25
    }

    fun decreaseProgressbar() {
        binding.pbSignup.progress -= 25
    }

}