package com.example.simya.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.Constants
import com.example.simya.Constants.OK
import com.example.simya.R
import com.example.simya.data.UserTokenData
import com.example.simya.databinding.ActivitySigninEmailBinding
import com.example.simya.server.account.AccountResponse
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import com.example.simya.server.account.AccountDTO
import com.example.simya.sharedpreferences.Shared
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class EmailLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninEmailBinding
    private val emailValidation =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
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
        binding.tietEmailSigninInputEmail.setText("a8118199@gmail.com")
        binding.tietEmailSigninInputPassword.setText("4637wlsdud!")
        //EditText 입력확인
        binding.tietEmailSigninInputEmail.addTextChangedListener(textWatcher)
        binding.tietEmailSigninInputPassword.addTextChangedListener(textWatcher)

        //로그인 이벤트
        binding.btnEmailSigninLogin.setOnClickListener {
            if(checkEmail()&&checkPassword()){
                email = binding.tietEmailSigninInputEmail.text.toString()
                password = binding.tietEmailSigninInputPassword.text.toString()
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
        val intent = Intent(this,HomeMainActivity::class.java)
        startActivity(intent)
    }
    private fun onSignIn(user: AccountDTO){
        simyaApi.onLoginSubmit(user).enqueue(object: Callback<AccountResponse>{
            override fun onResponse(
                call: Call<AccountResponse>,
                response: Response<AccountResponse>
            ) {
                if(response.code()==OK){
                    Log.d("Response check",response.message().toString())
                    Log.d("Response check",response.code().toString())
                    Log.d("Response check",response.body().toString())
                    Shared.prefs.setString("accessToken",response.body()!!.getAccessToken())
                    Shared.prefs.setString("refreshToken",response.body()!!.getRefreshToken())
                    onShared()
                    moveToHome()
                }
                Toast.makeText(this@EmailLoginActivity,response.message(),Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<AccountResponse>, t: Throwable) {
                Toast.makeText(this@EmailLoginActivity,t.toString(),Toast.LENGTH_SHORT).show()

                Log.d("Reponse check",t.toString())
            }
        })
    }
    private fun checkEmail(): Boolean {
        var email = binding.tietEmailSigninInputEmail.text.toString().trim() // 공백제거
        val pattern = Pattern.matches(emailValidation, email) // 패턴확인
        return if (pattern) {
            binding.tilEmailSigninInputEmail.error = null
            true
        } else {
            binding.tilEmailSigninInputEmail.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }

    private fun checkPassword(): Boolean {
        var password = binding.tietEmailSigninInputPassword.text!!.length
        return if (password in 8..12) {
            binding.tilEmailSigninInputPassword.error = null
            true
        } else {
            binding.tilEmailSigninInputPassword.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
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
                val emailInput = binding.tietEmailSigninInputEmail!!.text.toString()
                val passwordInput = binding.tietEmailSigninInputPassword!!.text.toString()
                if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                    binding.btnEmailSigninLogin.isEnabled = true
                    binding.btnEmailSigninLogin.isClickable = true
                    binding.btnEmailSigninLogin.setBackgroundResource(R.drawable.low_radius_button_on)
                    binding.btnEmailSigninLogin.setTextColor(application.resources.getColor(R.color.Gray_03))
                }
                if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                    binding.btnEmailSigninLogin.isEnabled = false
                    binding.btnEmailSigninLogin.isClickable = false
                    binding.btnEmailSigninLogin.setBackgroundResource(R.drawable.low_radius_button_off)
                    binding.btnEmailSigninLogin.setTextColor(application.resources.getColor(R.color.Gray_10))
                }
            }
            override fun afterTextChanged(s: Editable) {
            }
        }
    }
    private fun onShared(){
        UserTokenData.init(
            Shared.prefs.getString("accessToken", Constants.DEFAULT),
            Shared.prefs.getString("refreshToken", Constants.DEFAULT))
        Log.d("User AccessToken", UserTokenData.getUserAccessToken())
        Log.d("User RefreshToken", UserTokenData.getUserRefreshToken())
    }

}