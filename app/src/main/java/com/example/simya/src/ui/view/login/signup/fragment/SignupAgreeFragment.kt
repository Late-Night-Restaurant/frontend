package com.example.simya.src.ui.view.login.signup.fragment

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
import com.example.simya.src.ui.viewmodel.login.signup.SignupAgreeViewModel
import com.example.simya.util.dialog.AgreeDialogInterface


class SignupAgreeFragment: BaseFragment<FragmentSignupAgreeBinding>(R.layout.fragment_signup_agree),
    SignupActivity.onBackPressedListener, AgreeDialogInterface {

    private lateinit var signupAgreeViewModel: SignupAgreeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signupAgreeViewModel = ViewModelProvider(this)[SignupAgreeViewModel::class.java]
        binding.signupAgreeViewModel = signupAgreeViewModel

        binding.btnSignupAgreeNext.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signupAgreeFragment_to_signupEmailFragment)
        }

        // 동의 체크 버튼 viewModel
        signupAgreeViewModel.agreeAll.observe(viewLifecycleOwner,Observer{
            signupAgreeViewModel.checkAgreeAllStatus()
            isAgreeCheck()
        })
        signupAgreeViewModel.agreeService.observe(viewLifecycleOwner,Observer{
            binding.cbSignupAgreeService.isChecked = signupAgreeViewModel.agreeService.value!!
            isAgreeCheck()
        })
        signupAgreeViewModel.agreeInfo.observe(viewLifecycleOwner,Observer{
            binding.cbSignupAgreeInfo.isChecked = signupAgreeViewModel.agreeInfo.value!!
            isAgreeCheck()
        })
    }
    private fun isAgreeCheck(){
        binding.btnSignupAgreeNext.isEnabled = signupAgreeViewModel.isAgreeStatus()
        binding.btnSignupAgreeNext.isClickable = signupAgreeViewModel.isAgreeStatus()
    }

    override fun onBackPressed() {
        val intent = Intent(this.context, EmailLoginActivity::class.java)
        startActivity(intent)
    }

    override fun onCloseButtonClicked() {
    }
}