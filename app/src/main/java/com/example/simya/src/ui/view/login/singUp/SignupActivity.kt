package com.example.simya.src.ui.view.login.singUp

import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.config.BaseActivity
import com.example.simya.config.BaseResponse
import com.example.simya.databinding.ActivitySigninEmailBinding
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.src.main.login.model.LoginInterface
import com.example.simya.src.main.login.model.SignUpInterface
import com.example.simya.src.main.login.singUp.fragment.*
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.src.model.account.SignupResponse
import com.example.simya.src.ui.view.login.singUp.fragment.*

class SignupActivity: BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate)
    {
    private var fragmentAgree = SignupAgreeFragment()
    private var fragmentEmail = SignupEmailFragment()
    private var fragmentPw = SignupPwFragment()
    private var fragmentProfile = SignupProfileFragment()
    private var fragmentFin = SignupFinFragment()

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)
        init()
    }

    // count == 0 약관동의 fragment 표시
    private fun init() {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.fmSignup.id, fragmentAgree)
        transaction.commit()
        binding.pbSignup.progress = 0
    }
    fun increaseProgressbar(){
        binding.pbSignup.progress += 25
    }

    fun decreaseProgressbar(){
        binding.pbSignup.progress -= 25
    }

    fun nextFragmentSignUp(int: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> transaction.replace(binding.fmSignup.id, fragmentAgree)
            2 -> transaction.replace(binding.fmSignup.id, fragmentEmail)
            3 -> transaction.replace(binding.fmSignup.id, fragmentPw)
            4 -> transaction.replace(binding.fmSignup.id, fragmentProfile)
            5 -> transaction.replace(binding.fmSignup.id, fragmentFin)
        }
        transaction.commit()
    }

}