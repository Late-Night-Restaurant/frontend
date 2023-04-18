package com.example.simya.src.ui.viewmodel.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simya.util.Constants
import java.util.regex.Pattern

class EmailLoginViewModel: ViewModel() {

     // email
    private val _inputEmail = MutableLiveData<String>()
    val inputEmail: LiveData<String>
        get() = _inputEmail
    // password
    private val _inputPassword = MutableLiveData<String>()
    val inputPassword: LiveData<String>
        get() = _inputPassword

    // email 검증
    private fun checkEmail(): Boolean{
        return Pattern.matches(Constants.EMAIL_VALIDATION, inputEmail.value!!.trim())
    }
    // password 검증
    private fun checkPassword(): Boolean{
        return inputPassword.value!!.length in 8..12
    }
}