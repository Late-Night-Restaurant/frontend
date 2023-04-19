package com.example.simya.src.ui.viewmodel.login.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simya.util.Constants
import kotlinx.coroutines.launch
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
    fun setEmailAndPassword(email: String,password: String) = viewModelScope.launch{
        _inputEmail.value = email
        _inputPassword.value = password
    }
    // email 검증
    fun checkEmail(): Boolean{
        return Pattern.matches(Constants.EMAIL_VALIDATION, inputEmail.value!!.trim())
    }
    // password 검증
    fun checkPassword(): Boolean{
        return inputPassword.value!!.length in 8..12
    }
}