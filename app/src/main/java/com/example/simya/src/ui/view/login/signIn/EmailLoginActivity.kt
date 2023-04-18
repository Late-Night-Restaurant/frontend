package com.example.simya.src.ui.view.login.signIn

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
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
    BaseActivity<ActivitySigninEmailBinding>(R.layout.activity_signin_email), LoginInterface,PrepareDialogInterface {
    private lateinit  var viewModel: EmailLoginViewModel
    private lateinit var textWatcher: TextWatcher
    private lateinit var email: String
    private lateinit var password: String

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//    }
    override fun init() {
        viewModel = ViewModelProvider(this)[EmailLoginViewModel::class.java]
        initTextWatcher()
        prePareServiceFindIdAndPassword()
    }

    private fun init2() {
        binding.btnEmailSigninLogin.isEnabled = false

        //EditText 입력확인
        binding.edtEmailSignInInputEmail.addTextChangedListener(textWatcher)
        binding.edtEmailSignInInputPassword.addTextChangedListener(textWatcher)

        //로그인 이벤트
        binding.btnEmailSigninLogin.onThrottleClick {
            if (checkEmail() && checkPassword()) {
                showLoadingDialog(this)
                email = binding.edtEmailSignInInputEmail.text.toString()
                password = binding.edtEmailSignInInputPassword.text.toString()
                LoginService(this).tryLoginSubmit(
                    AccountDTO(
                        email,
                        password
                    )
                )
            }
        }

        binding.btnSigninEmailSignup.onThrottleClick {
//            val intent = Intent(this, SignupActivity::class.java)
//            startActivity(intent)
        }
    }
    // 아직 준비중인 서비스
    private fun prePareServiceFindIdAndPassword(){
        binding.btnSignInEmailFindEmail.onThrottleClick {
            PrepareDialog(this,this).show()
        }
        binding.btnSignInEmailFindPassword.onThrottleClick {
            PrepareDialog(this,this).show()
        }
    }

    private fun moveToHome() {
//        val intent = Intent(this, HomeActivity::class.java)
        // 메인,로그인 액티비티 스택 제거
//        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        finish()
        startActivity(intent)
    }

    private fun checkEmail(): Boolean {
        var email = binding.edtEmailSignInInputEmail.text.toString().trim() // 공백제거
        val pattern = Pattern.matches(EMAIL_VALIDATION, email) // 패턴확인
        return if (pattern) {
            binding.tilEmailSignInInputEmail.error = null
            true
        } else {
            binding.tilEmailSignInInputEmail.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }

    private fun checkPassword(): Boolean {
        var password = binding.edtEmailSignInInputPassword.text!!.length
        return if (password in 8..12) {
            binding.tilEmailSignInInputPassword.error = null
            true
        } else {
            binding.tilEmailSignInInputPassword.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
            false
        }
    }

    private fun initTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val emailInput = binding.edtEmailSignInInputEmail!!.text.toString()
                val passwordInput = binding.edtEmailSignInInputPassword!!.text.toString()
                if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                    binding.btnEmailSigninLogin.isEnabled = true
                    binding.btnEmailSigninLogin.isClickable = true
                }
                if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                    binding.btnEmailSigninLogin.isEnabled = false
                    binding.btnEmailSigninLogin.isClickable = false
                }
            }
            override fun afterTextChanged(s: Editable) {
            }
        }
    }

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
        SampleSnackBar.make(binding.root,message).show()
        dismissLoadingDialog()
    }

    override fun onOKClicked() {

    }

    override fun onBackPressed() {
        backApplicationExit(this)
    }


}