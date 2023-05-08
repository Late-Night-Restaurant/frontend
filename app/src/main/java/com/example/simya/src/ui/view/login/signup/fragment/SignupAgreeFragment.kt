package com.example.simya.src.ui.view.login.signup.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signIn.EmailLoginActivity
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.FragmentSignupAgreeBinding
import com.example.simya.src.ui.viewmodel.login.signup.SignupViewModel
import com.example.simya.util.dialog.AgreeDialogInterface


class SignupAgreeFragment :
    BaseFragment<FragmentSignupAgreeBinding>(R.layout.fragment_signup_agree) {

    private lateinit var signupViewModel: SignupViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        signupViewModel = ViewModelProvider(requireActivity())[SignupViewModel::class.java]
        signupViewModel = ViewModelProvider(activity as SignupActivity)[SignupViewModel::class.java]
        binding.signupAgreeViewModel = signupViewModel
        signupViewModel.setSignupProgress(0)
        binding.btnSignupAgreeNext.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_signupAgreeFragment_to_signupEmailFragment)
        }

        // 동의 체크 버튼 viewModel
        signupViewModel.agreeAll.observe(viewLifecycleOwner, Observer {
            signupViewModel.checkAgreeAllStatus()
            isAgreeCheck()
        })
        signupViewModel.agreeService.observe(viewLifecycleOwner, Observer {
            binding.cbSignupAgreeService.isChecked = signupViewModel.agreeService.value!!
            isAgreeCheck()
        })
        signupViewModel.agreeInfo.observe(viewLifecycleOwner, Observer {
            binding.cbSignupAgreeInfo.isChecked = signupViewModel.agreeInfo.value!!
            isAgreeCheck()
        })
    }

    private fun isAgreeCheck() {
        binding.btnSignupAgreeNext.isEnabled = signupViewModel.isAgreeStatus()
        binding.btnSignupAgreeNext.isClickable = signupViewModel.isAgreeStatus()
    }

}