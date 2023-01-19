package com.example.simya

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.simya.databinding.ActivityLoginMainBinding
import com.example.simya.databinding.ActivitySigninEmailBinding
import java.util.regex.Pattern

class EmailLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninEmailBinding
    private val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val emailInput = binding.tietEmailSigninInputEmail!!.text.toString()
            val passwordInput = binding.tietEmailSigninInputPassword!!.text.toString()
            if (emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                binding.btnEmailSigninLogin.isEnabled = true
                binding.btnEmailSigninLogin.setBackgroundResource(R.drawable.low_radius_button_on)
                binding.btnEmailSigninLogin.setTextColor(application.resources.getColor(R.color.Gray_03))
            }
        }
        override fun afterTextChanged(s: Editable) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }
    private fun init(){
        binding.btnEmailSigninLogin.isEnabled = false

        //EditText 입력확인
        binding.tietEmailSigninInputEmail.addTextChangedListener(textWatcher)
        binding.tietEmailSigninInputPassword.addTextChangedListener(textWatcher)

        //로그인 이벤트
        binding.btnEmailSigninLogin.setOnClickListener {
            checkEmail()
            checkPassword()
        }

    }
    private fun checkEmail():Boolean{
        var email = binding.tietEmailSigninInputEmail.text.toString().trim() // 공백제거
        val pattern = Pattern.matches(emailValidation, email) // 패턴확인
        return if (pattern) {
//            Toast.makeText(this,"올바른 이메일",Toast.LENGTH_SHORT).show()
            binding.tilEmailSigninInputEmail.error = null
            true
        } else {
//            Toast.makeText(this,"올바른 이메일아님",Toast.LENGTH_SHORT).show()
            binding.tilEmailSigninInputEmail.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }
    private fun checkPassword(): Boolean{
        var password = binding.tietEmailSigninInputPassword.text!!.length
        return if (password in 8..12) {
//            Toast.makeText(this,"올바른 패스워드",Toast.LENGTH_SHORT).show()
            binding.tilEmailSigninInputPassword.error = null
            true
        } else {
//            Toast.makeText(this,"올바른 패스워드아님",Toast.LENGTH_SHORT).show()
            binding.tilEmailSigninInputPassword.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
            false
        }
    }
}