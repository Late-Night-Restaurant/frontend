package com.example.simya.src.ui.view.login.signup.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.FragmentSignupPwBinding
import com.example.simya.src.ui.viewmodel.login.signup.SignupViewModel
import com.example.simya.util.Constants.PW_VALIDATION
import java.util.regex.Pattern

class SignupPwFragment: BaseFragment<FragmentSignupPwBinding>(R.layout.fragment_signup_pw) {

    private lateinit var signupViewModel: SignupViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupViewModel = ViewModelProvider(activity as SignupActivity)[SignupViewModel::class.java]
        binding.signupViewModel = signupViewModel
        signupViewModel.setSignupProgress(50)
        signupViewModel.pw.observe(viewLifecycleOwner,Observer{
            pwEmpty()
        })
        signupViewModel.rePw.observe(viewLifecycleOwner,Observer{
            pwEmpty()
        })
        binding.btnSignupNextPw.setOnClickListener {
            if(checkEqualPassword()){
                Navigation.findNavController(view)
                    .navigate(R.id.action_signupPwFragment_to_signupProfileFragment)
            }else{
                binding.tilRePwSignupInput.error = "입력하신 비밀번호와 일치하지 않습니다."
            }
        }
    }
    private fun checkEqualPassword(): Boolean {
        return signupViewModel.matchPwCheck()
    }

    private fun pwEmpty(){
        binding.btnSignupNextPw.isEnabled = signupViewModel.pwEmptyCheck()
        binding.btnSignupNextPw.isClickable = signupViewModel.pwEmptyCheck()
    }
}
