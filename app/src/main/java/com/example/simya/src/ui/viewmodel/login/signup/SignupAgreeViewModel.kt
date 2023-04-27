package com.example.simya.src.ui.viewmodel.login.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupAgreeViewModel : ViewModel() {

    // 전체 약관 동의
    var agreeAll = MutableLiveData<Boolean>()

    // 개인정보 처리방침
    var agreeInfo = MutableLiveData<Boolean>()

    // 서비스 이용약관
    var agreeService = MutableLiveData<Boolean>()


    init{
        agreeAll.value = false
        agreeInfo.value = false
        agreeService.value = false
    }

    fun isAgreeStatus(): Boolean{
        return agreeInfo.value!! && agreeService.value!!
    }
    fun checkAgreeAllStatus() = viewModelScope.launch {
        agreeService.value = agreeAll.value
        agreeInfo.value = agreeAll.value
    }
}