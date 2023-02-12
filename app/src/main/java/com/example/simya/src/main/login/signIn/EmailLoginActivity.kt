package com.example.simya.src.main.login.signIn

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.simya.R
import com.example.simya.config.ApplicationClass
import com.example.simya.config.BaseActivity
import com.example.simya.config.BaseResponse
import com.example.simya.databinding.ActivitySigninEmailBinding
import com.example.simya.databinding.SnackbarLayoutBinding
import com.example.simya.src.main.home.HomeActivity
import com.example.simya.src.main.login.model.LoginInterface
import com.example.simya.src.main.login.model.LoginService
import com.example.simya.src.main.login.singUp.SignupActivity
import com.example.simya.src.model.account.AccountDTO
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.util.Constants.ACCESS_TOKEN
import com.example.simya.util.Constants.DEFAULT
import com.example.simya.util.Constants.EMAIL_VALIDATION
import com.example.simya.util.Constants.REFRESH_TOKEN
import com.example.simya.util.data.UserData
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern


class EmailLoginActivity :
    BaseActivity<ActivitySigninEmailBinding>(ActivitySigninEmailBinding::inflate), LoginInterface {
    private lateinit var textWatcher: TextWatcher
    private lateinit var email: String
    private lateinit var password: String
//    private val retrofit by lazy {
//       RetrofitBuilder.getInstnace()
//    }
//    private val simyaApi by lazy{
//        retrofit.create(RetrofitService::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTextWatcher()
        init()
    }

    private fun init() {
        binding.btnEmailSigninLogin.isEnabled = false

        //EditText 입력확인
        binding.edtEmailSignInInputEmail.addTextChangedListener(textWatcher)
        binding.edtEmailSignInInputPassword.addTextChangedListener(textWatcher)

        //로그인 이벤트
        binding.btnEmailSigninLogin.setOnClickListener {
            if (checkEmail() && checkPassword()) {
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
        binding.btnSigninEmailSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    private fun moveToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        // 메인,로그인 액티비티 스택 제거
//        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
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
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

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
        Log.d("response@@@@@@@@@@@@@@@",response.toString())
        Log.d("response", response.toString())
        ApplicationClass.sSharedPreferences.edit()
            .putString(ACCESS_TOKEN, "Access "+response.result!!.accessToken).commit()
        Log.d("accessToken CHECK @@@",ApplicationClass.sSharedPreferences.getString(ACCESS_TOKEN, DEFAULT)!!)
        ApplicationClass.sSharedPreferences.edit()
            .putString(REFRESH_TOKEN, "Refresh "+response.result!!.refreshToken).commit()
        Log.d("refreshToken CHECK @@@",ApplicationClass.sSharedPreferences.getString(REFRESH_TOKEN, DEFAULT)!!)
        UserData.setProfileId(response.result!!.profileId)
        UserData.setProfileName(response.result!!.nickname)
        UserData.setProfileComment(response.result!!.comment)
//                    UserData.setProfileImage(response.result!!.profileImage)
        moveToHome()
    }

    override fun onPostLoginSubmitFailure(response: BaseResponse) {
        Log.d("ERROR message", response.toString())
        showCustomToast(response.message!!)
    }

    // SnackBar 구현
    private fun onSnackBar(view: View, message: String){
        var snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)

        val snackBarView: View = layoutInflater.inflate(R.layout.snackbar_layout, null)
        val snackBarBinding = SnackbarLayoutBinding.bind(snackBarView)
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        snackBarBinding.snackBarMessage.text = message

        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.addView(snackBarView)

        snackBar.show()
    }

}