package com.example.simya.src.activity.login.singUp

import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.src.fragment.signupFragment.*

class SignupActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private lateinit var textWatcher: TextWatcher

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
        binding = ActivitySignupBinding.inflate(layoutInflater)
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