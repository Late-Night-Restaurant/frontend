package com.example.simya.src.main.login.signIn

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color
import android.os.Bundle
import android.os.Message
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.R

import com.example.simya.databinding.ActivitySigninEmailBinding
import com.example.simya.databinding.SnackbarLayoutBinding
import com.example.simya.src.main.home.HomeActivity
import com.example.simya.src.main.login.singUp.SignupActivity
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.account.AccountDTO
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.util.Constants
import com.example.simya.util.Constants.EMAIL_VALIDATION
import com.example.simya.util.Constants.OK
import com.example.simya.util.data.UserData
import com.example.simya.util.sharedpreferences.Shared
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class EmailLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninEmailBinding
    private lateinit var textWatcher: TextWatcher
    private lateinit var email: String
    private lateinit var password: String
    private val retrofit by lazy {
       RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            if(checkEmail()&&checkPassword()){

                email = binding.edtEmailSignInInputEmail.text.toString()
                password = binding.edtEmailSignInInputPassword.text.toString()

                onSignIn(
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
    private fun moveToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        // 메인,로그인 액티비티 스택 제거
//        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
    private fun onSignIn(user: AccountDTO){
        simyaApi.onLoginSubmit(user).enqueue(object: Callback<AccountResponse>{
            override fun onResponse(
                call: Call<AccountResponse>,
                response: Response<AccountResponse>
            ) {
                Log.d("access",UserData.accessToken)
                Log.d("refresh",UserData.refreshToken)
                Log.d("Response",response.toString())
                if(response.body()!!.code==OK){
                    Log.d("Response check",response.message().toString())
                    Log.d("Response check",response.code().toString())
                    Log.d("Response check",response.body().toString())
                    Shared.prefs.setString("accessToken",response.body()!!.getAccessToken())
                    Shared.prefs.setString("refreshToken",response.body()!!.getRefreshToken())
                    Shared.prefs.setLong("profileId",response.body()!!.getProfileId())
                    UserData.setProfileId(response.body()!!.result!!.profileId)
                    UserData.setProfileName(response.body()!!.result!!.nickname)
                    UserData.setProfileComment(response.body()!!.result!!.comment)
                    // 프로필 이미지 추가하기
//                    UserData.setProfileImage(response.body()!!.result!!.profileImage)
                    onShared()
                    moveToHome()

                }else if(response.body()!!.code == 400){
                    if(response.body()!!.message == "존재하지 않는 사용자입니다."){
                        onSnackBar(binding.root, "존재하지 않는 사용자입니다.")
                    }
                }else if(response.body()!!.code == 404){
                    onSnackBar(binding.root, "존재하지 않는 아이디이거나 비밀번호가 틀렸습니다.")
                }
            }
            override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
                Log.d("Reponse check",t.toString())
            }
        })
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

    // 화면터치시 키보드 내려가게하는 이벤트
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
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
    private fun onShared(){
        UserData.init(
            Shared.prefs.getString("accessToken", Constants.DEFAULT),
            Shared.prefs.getString("refreshToken", Constants.DEFAULT))
        Log.d("User AccessToken", UserData.getUserAccessToken())
        Log.d("User RefreshToken", UserData.getUserRefreshToken())
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