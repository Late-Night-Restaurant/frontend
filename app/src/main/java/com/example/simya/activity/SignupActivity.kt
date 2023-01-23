package com.example.simya.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.signupFragment.*

class SignupActivity : AppCompatActivity(), SendSignupListener {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val signEmailFragment: SignupEmailFragment = SignupEmailFragment()

        val manager: FragmentManager = supportFragmentManager

        val transaction: FragmentTransaction = manager.beginTransaction()

        transaction.add(binding.fmSignup.id, signEmailFragment)

        transaction.commit()

        init()


    }

    override fun sendMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }



    // count == 0 약관동의 fragment 표시
    private fun init() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fmSignup.id, SignupEmailFragment())
            .commitAllowingStateLoss()
    }




}