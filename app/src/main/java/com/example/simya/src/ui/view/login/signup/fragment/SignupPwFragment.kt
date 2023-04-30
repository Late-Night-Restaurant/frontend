package com.example.simya.src.ui.view.login.signup.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.Navigation
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.FragmentSignupPwBinding
import com.example.simya.util.Constants.PW_VALIDATION
import java.util.regex.Pattern

class SignupPwFragment: BaseFragment<FragmentSignupPwBinding>(R.layout.fragment_signup_pw){

    var signupActivity: SignupActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signupActivity = context as SignupActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FalseButton()
        binding.btnSignupNextPw.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signupPwFragment_to_signupProfileFragment)
        }
    }


    private fun pwCheck(): Boolean {
        var pwLength = binding.tietPwSignupInput.text!!.length
        var pw = binding.tietPwSignupInput.text.toString().trim()
        val pattern = Pattern.matches(PW_VALIDATION, pw)

        return if (pattern && pwLength in 8..12) {
            binding.btnSignupNextPw.error = null
            true
        } else {
            binding.tilPwSignupInput.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
            false
        }
    }

    private fun rePwCheck(): Boolean {
        val rePw = binding.etRePwSignupInput.text.toString().trim()
        val pw = binding.tietPwSignupInput.text.toString().trim()

        return if (rePw == pw) {
            binding.etRePwSignupInput.error = null
            true
        } else {
            binding.etRePwSignupInput.error = "입력하신 비밀번호와 일치하지 않습니다."
            false
        }

    }

    private fun TrueButton() {
        binding.btnSignupNextPw.isEnabled = true
        binding.btnSignupNextPw.isClickable = true
        binding.btnSignupNextPw.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnSignupNextPw.setTextColor(resources.getColor(R.color.Gray_03))

    }

    private fun FalseButton() {
        binding.btnSignupNextPw.isEnabled = false
        binding.btnSignupNextPw.isClickable = false
        binding.btnSignupNextPw.setBackgroundResource(R.drawable.low_radius_button_off)
        binding.btnSignupNextPw.setTextColor(resources.getColor(R.color.Gray_10))

    }



}

