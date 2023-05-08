package com.example.simya.src.ui.view.login.signup.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.FragmentSignupFinBinding
import com.example.simya.src.ui.viewmodel.login.signup.SignupViewModel

class SignupFinFragment: BaseFragment<FragmentSignupFinBinding>(R.layout.fragment_signup_fin){
    private lateinit var signupViewModel: SignupViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupViewModel = ViewModelProvider(activity as SignupActivity)[SignupViewModel::class.java]
        binding.signupViewModel = signupViewModel
        signupViewModel.setSignupProgress(100)
        binding.btnSignupNext.setOnClickListener {
            requireActivity().finish()
        }
    }
}