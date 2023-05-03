package com.example.simya.src.ui.viewmodel.login.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simya.src.data.repository.login.LoginRepository
import com.example.simya.src.data.repository.login.LoginRepositoryImpl
import com.example.simya.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class EmailLoginViewModel: ViewModel() {

    // 이메일
    val inputEmail = MutableLiveData<String>("")
    // 비밀번호
    val inputPassword = MutableLiveData<String>("")

    fun checkEmailPwCheck():Boolean{
        return (inputEmail.value.toString().isNotEmpty() && inputPassword.value.toString().isNotEmpty())
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