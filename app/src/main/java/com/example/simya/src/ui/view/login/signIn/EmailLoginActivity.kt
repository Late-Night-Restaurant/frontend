package com.example.simya.src.ui.view.login.signIn

import android.content.Intent
import android.util.Log
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
    BaseActivity<ActivitySigninEmailBinding>(R.layout.activity_signin_email),
    PrepareDialogInterface {
    private lateinit var viewModel: EmailLoginViewModel
//    private lateinit var textWatcher: TextWatcher

    override fun init() {
        viewModel = ViewModelProvider(this)[EmailLoginViewModel::class.java]
        initTextWatcher()

        // 로그인 버튼 클릭시
        binding.btnEmailSigninLogin.onThrottleClick {
            viewModel.setEmailAndPassword(
                binding.edtEmailSignInInputEmail.text.toString(),
                binding.edtEmailSignInInputPassword.text.toString()
            )
            if (checkValidationEmail() && checkValidationPassword()) {
                showLoadingDialog(this)
                // 로그인 서비스
            }
        }
        binding.btnSigninEmailSignup.onThrottleClick {
            moveToSignup()
        }
    }

    private fun initTextWatcher() {
        var emailStatus = false
        var pwStatus = false
        binding.edtEmailSignInInputEmail.addTextChangedListener(CommonTextWatcher(
            afterChanged = { email->
                if (email!!.isEmpty()) {
                    Log.d("TextWatcher", "email is null")
                    emailStatus = false
                    checkInputStatus(emailStatus,pwStatus)
                } else {
                    Log.d("TextWatcher", "email is not null")
                    emailStatus = true
                    checkInputStatus(emailStatus,pwStatus)
                }
            }
        ))
        binding.edtEmailSignInInputPassword.addTextChangedListener(CommonTextWatcher(
            afterChanged = { pw ->
                if (pw!!.isEmpty()) {
                    Log.d("TextWatcher", "pw is null")
                    pwStatus = false
                    checkInputStatus(emailStatus,pwStatus)
                } else {
                    Log.d("TextWatcher", "pw is not null")
                    pwStatus = true
                    checkInputStatus(emailStatus,pwStatus)
                }
            }
        ))
    }
    private fun checkInputStatus(emailStatus: Boolean,pwStatus:Boolean){
        binding.btnEmailSigninLogin.isEnabled = emailStatus&&pwStatus
        binding.btnEmailSigninLogin.isClickable = emailStatus&&pwStatus
    }

    private fun moveToSignup() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    // 아이디 폼에 따른 Error 출력
    private fun checkValidationEmail(): Boolean {
        return if (viewModel.checkEmail()) {
            binding.tilEmailSignInInputEmail.error = null
            true
        } else {
            binding.tilEmailSignInInputEmail.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }

    // 비밀번호 폼에 따른 Error 출력
    private fun checkValidationPassword(): Boolean {
        return if (viewModel.checkPassword()) {
            binding.tilEmailSignInInputPassword.error = null
            true
        } else {
            binding.tilEmailSignInInputPassword.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
            false
        }
    }


    override fun onOKClicked() {

    }

    override fun onBackPressed() {
        backApplicationExit(this)
    }
}