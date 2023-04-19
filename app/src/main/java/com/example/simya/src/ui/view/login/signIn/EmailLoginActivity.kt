package com.example.simya.src.ui.view.login.signIn

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.config.BaseResponse
import com.example.simya.databinding.ActivityLoginMainBinding
import com.example.simya.databinding.ActivitySigninEmailBinding
//import com.example.simya.src.ui.view.home.HomeActivity
import com.example.simya.src.main.login.model.LoginInterface
import com.example.simya.src.main.login.model.LoginService
//import com.example.simya.src.ui.view.login.singup.SignupActivity
//import com.example.simya.src.ui.view.prepare.PrepareActivity
import com.example.simya.src.model.account.AccountDTO
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.src.ui.viewmodel.login.signin.EmailLoginViewModel
import com.example.simya.util.Constants.EMAIL_VALIDATION
import com.example.simya.util.SampleSnackBar
import com.example.simya.util.data.UserData
import com.example.simya.util.dialog.PrepareDialog
import com.example.simya.util.dialog.PrepareDialogInterface
import com.example.simya.util.onThrottleClick
import java.util.regex.Pattern


class EmailLoginActivity :
    BaseActivity<ActivitySigninEmailBinding>(R.layout.activity_signin_email), LoginInterface,
    PrepareDialogInterface {
    private lateinit var viewModel: EmailLoginViewModel
//    private lateinit var textWatcher: TextWatcher

    override fun init() {

        viewModel = ViewModelProvider(this)[EmailLoginViewModel::class.java]

        binding.btnEmailSigninLogin.onThrottleClick {
            viewModel.setEmailAndPassword(binding.edtEmailSignInInputEmail.text.toString(),binding.edtEmailSignInInputPassword.text.toString())
            if (checkValidationEmail() && checkValidationPassword()) {
                showLoadingDialog(this)
                // 로그인 서비스
            }
        }

//        Text Watcher 보류
//        initTextWatcher()
//                binding.edtEmailSignInInputEmail.addTextChangedListener(textWatcher)
//        binding.edtEmailSignInInputPassword.addTextChangedListener(textWatcher)
//        준비중인 서비스
//        prePareServiceFindIdAndPassword()
    }

    private fun moveToHome() {
//        val intent = Intent(this, HomeActivity::class.java)
        // 메인,로그인 액티비티 스택 제거
//        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        finish()
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

//    private fun initTextWatcher() {
//        textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                val emailInput = binding.edtEmailSignInInputEmail!!.text.toString()
//                val passwordInput = binding.edtEmailSignInInputPassword!!.text.toString()
//                if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
//                    binding.btnEmailSigninLogin.isEnabled = true
//                    binding.btnEmailSigninLogin.isClickable = true
//                }
//                if (emailInput.isEmpty() || passwordInput.isEmpty()) {
//                    binding.btnEmailSigninLogin.isEnabled = false
//                    binding.btnEmailSigninLogin.isClickable = false
//                }
//            }
//
//            override fun afterTextChanged(s: Editable) {
//            }
//        }
//    }

    override fun onPostLoginSubmitSuccess(response: AccountResponse) {
        dismissLoadingDialog()
        LoginService(this).setAccessTokenSharedPreferences(response.result!!.accessToken)
        LoginService(this).setRefreshTokenSharedPreferences(response.result!!.refreshToken)
        UserData.setProfileId(response.result!!.profileId)
        UserData.setProfileName(response.result!!.nickname)
        UserData.setProfileComment(response.result!!.comment)
        UserData.setProfileImage(response.result!!.pictureUrl)
        UserData.setUserAccessToken(response.result!!.accessToken)
        UserData.setUserRefreshToken(response.result!!.refreshToken)
        UserData.printAllData()
        moveToHome()
    }

    override fun onPostLoginSubmitFailure(response: BaseResponse) {
        dismissLoadingDialog()
        SampleSnackBar.make(binding.root, response.message.toString()).show()
    }

    override fun onPostLoginSubmitDisconnect(message: String) {
        SampleSnackBar.make(binding.root, message).show()
        dismissLoadingDialog()
    }

    override fun onOKClicked() {

    }

    override fun onBackPressed() {
        backApplicationExit(this)
    }

    // 아직 준비중인 서비스
    private fun prePareServiceFindIdAndPassword() {
        binding.btnSignInEmailFindEmail.onThrottleClick {
            PrepareDialog(this, this).show()
        }
        binding.btnSignInEmailFindPassword.onThrottleClick {
            PrepareDialog(this, this).show()
        }
    }


}