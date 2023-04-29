package com.example.simya.src.ui.view.login.signup

import androidx.lifecycle.ViewModelProvider
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.src.ui.viewmodel.login.signup.SignupViewModel

class SignupActivity : BaseActivity<ActivitySignupBinding>(R.layout.activity_signup) {
    private lateinit var signupViewModel: SignupViewModel
    override fun init() {
        signupViewModel = ViewModelProvider(this)[SignupViewModel::class.java]
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

}