package com.example.simya.src.ui.view.login.signup.fragment


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.Navigation
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupEmailBinding
import com.example.simya.util.Constants.EMAIL_VALIDATION
import java.util.regex.Pattern

class SignupEmailFragment: BaseFragment<FragmentSignupEmailBinding>(R.layout.fragment_signup_email), SignupActivity.onBackPressedListener {
    private lateinit var textWatcher: TextWatcher

    // 다시 상속받는
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        falseButton()

        initTW()

        binding.etEmailSignupInput.addTextChangedListener(textWatcher)
        binding.btnSignupNextEmail.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signupEmailFragment_to_signupPWFragment)
        }
    }




    private fun emailCheck() : Boolean {
        val email = binding.etEmailSignupInput.text.toString().trim()
        val pattern = Pattern.matches(EMAIL_VALIDATION, email)

        return if (pattern){
            binding.tilEmailSignupInput.error = null
            true
        } else {
            binding.tilEmailSignupInput.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }


    private fun initTW() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val emailInput = binding.etEmailSignupInput!!.text.toString()

                if (emailInput.isNotEmpty()) {
                    trueButton()
                }
                if (emailInput.isEmpty()) {
                    falseButton()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }

    private fun trueButton() {
        binding.btnSignupNextEmail.isEnabled = true
        binding.btnSignupNextEmail.isClickable = true
        binding.btnSignupNextEmail.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnSignupNextEmail.setTextColor(resources.getColor(R.color.Gray_03))

    }

    private fun falseButton() {
        binding.btnSignupNextEmail.isEnabled = false
        binding.btnSignupNextEmail.isClickable = false
        binding.btnSignupNextEmail.setBackgroundResource(R.drawable.low_radius_button_off)
        binding.btnSignupNextEmail.setTextColor(resources.getColor(R.color.Gray_10))

    }

    override fun onBackPressed() {
    }

}


