package com.example.simya.src.ui.viewmodel.login.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    // 진행률
    var progressStatus = MutableLiveData<Int>()

    // 전체 약관 동의
    var agreeAll = MutableLiveData<Boolean>()

    // 개인정보 처리방침
    var agreeInfo = MutableLiveData<Boolean>()

    // 서비스 이용약관
    var agreeService = MutableLiveData<Boolean>()

    val email = MutableLiveData<String>()

    val pw = MutableLiveData<String>()

    fun emailEmptyCheck(): Boolean {
        return email.value.toString().isNotEmpty()
    }

    init{
        progressStatus.value = 0
        agreeAll.value = false
        agreeInfo.value = false
        agreeService.value = false
    }
    fun increaseProgress() {
        progressStatus.value?.plus(25)
    }

    fun decreaseProgress() {
        progressStatus.value?.minus(25)
    }

    fun isAgreeStatus(): Boolean {
        return agreeInfo.value!! && agreeService.value!!
    }

    fun checkAgreeAllStatus() = viewModelScope.launch {
        agreeService.value = agreeAll.value
        agreeInfo.value = agreeAll.value
    }

}