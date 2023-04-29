package com.example.simya.src.ui.view.login.signup.fragment


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.FragmentSignupEmailBinding
import com.example.simya.src.ui.viewmodel.login.signup.SignupViewModel

class SignupEmailFragment :
    BaseFragment<FragmentSignupEmailBinding>(R.layout.fragment_signup_email),
    SignupActivity.onBackPressedListener {

    private lateinit var signupViewModel: SignupViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupViewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        binding.signupViewModel = signupViewModel

        signupViewModel.email.observe(viewLifecycleOwner, Observer {
            emailEmpty()
        })

        binding.btnSignupNextEmail.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_signupEmailFragment_to_signupPWFragment)
            signupViewModel.increaseProgress()
        }
    }
    private fun emailEmpty(){
        binding.btnSignupNextEmail.isEnabled = signupViewModel.emailEmptyCheck()
        binding.btnSignupNextEmail.isClickable = signupViewModel.emailEmptyCheck()
    }

    override fun onBackPressed() {
    }

}


