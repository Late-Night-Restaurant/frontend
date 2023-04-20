package com.example.simya.src.ui.view.login.signIn

import androidx.lifecycle.ViewModelProvider
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivitySigninEmailBinding
import com.example.simya.src.ui.viewmodel.login.signin.EmailLoginViewModel
import com.example.simya.util.dialog.PrepareDialog
import com.example.simya.util.dialog.PrepareDialogInterface
import com.example.simya.util.onThrottleClick


class EmailLoginActivity :
    BaseActivity<ActivitySigninEmailBinding>(R.layout.activity_signin_email),
    PrepareDialogInterface {
    private lateinit var viewModel: EmailLoginViewModel
//    private lateinit var textWatcher: TextWatcher

    override fun init() {

        viewModel = ViewModelProvider(this)[EmailLoginViewModel::class.java]

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