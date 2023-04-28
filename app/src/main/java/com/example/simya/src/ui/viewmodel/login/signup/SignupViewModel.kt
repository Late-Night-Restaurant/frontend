package com.example.simya.src.ui.viewmodel.login.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel() {

    val email = MutableLiveData<String>()

    val pw = MutableLiveData<String>()

    fun emailEmptyCheck():Boolean{
        return email.value.toString().isNotEmpty()
    }
}