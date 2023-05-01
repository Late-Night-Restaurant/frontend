package com.example.simya.src.ui.view.login.signIn

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivitySigninEmailBinding
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.src.ui.viewmodel.login.signin.EmailLoginViewModel
import com.example.simya.util.dialog.PrepareDialogInterface
import com.example.simya.util.onThrottleClick
import com.example.simya.util.textwatcher.CommonTextWatcher


class EmailLoginActivity :
    BaseActivity<ActivitySigninEmailBinding>(R.layout.activity_signin_email){
    private lateinit var signInViewModel: EmailLoginViewModel

    override fun init() {
        signInViewModel = ViewModelProvider(this)[EmailLoginViewModel::class.java]
        binding.signinViewmodel = signInViewModel


        signInViewModel.inputEmail.observe(this, Observer {
            checkInputFrom()
        })
        signInViewModel.inputPassword.observe(this, Observer {
            checkInputFrom()
        })
        // 로그인 버튼 클릭시
        binding.btnEmailSigninLogin.onThrottleClick {
            if (checkValidationEmail() && checkValidationPassword()) {
                showLoadingDialog(this)
                // 로그인 서비스
            }
        }
        // 회원가입 서비스로 이동
        binding.btnSigninEmailSignup.onThrottleClick {
            moveToSignup()
        }
    }
    private fun checkInputFrom(){
        binding.btnEmailSigninLogin.isEnabled = signInViewModel.checkEmailPwCheck()
        binding.btnEmailSigninLogin.isClickable = signInViewModel.checkEmailPwCheck()
    }

    private fun moveToSignup() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    // 아이디 폼에 따른 Error 출력
    private fun checkValidationEmail(): Boolean {
        return if (signInViewModel.checkEmail()) {
            binding.tilEmailSignInInputEmail.error = null
            true
        } else {
            binding.tilEmailSignInInputEmail.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }

    // 비밀번호 폼에 따른 Error 출력
    private fun checkValidationPassword(): Boolean {
        return if (signInViewModel.checkPassword()) {
            binding.tilEmailSignInInputPassword.error = null
            true
        } else {
            binding.tilEmailSignInInputPassword.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
            false
        }
    }
}