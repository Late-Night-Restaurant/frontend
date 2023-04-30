package com.example.simya.src.ui.viewmodel.login.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    // 진행률
    private val _progressStatus = MutableLiveData(0)
    val progressStatus: LiveData<Int> = _progressStatus

    // 전체 약관 동의
    val agreeAll = MutableLiveData<Boolean>()

    // 개인정보 처리방침
    val agreeInfo = MutableLiveData<Boolean>()

    // 서비스 이용약관
    val agreeService = MutableLiveData<Boolean>()

    val email = MutableLiveData<String>()

    val pw = MutableLiveData<String>()

    fun emailEmptyCheck(): Boolean {
        return email.value.toString().isNotEmpty()
    }

    init{
        agreeAll.value = false
        agreeInfo.value = false
        agreeService.value = false
    }
    fun increaseProgress() {
        _progressStatus.value = _progressStatus.value?.plus(25)
        Log.d("increaseProgress","Progressbar is + 25")
        Log.d("progress value",progressStatus.value.toString())
    }

    fun decreaseProgress() {
        _progressStatus.value = _progressStatus.value?.minus(25)
    }

    fun isAgreeStatus(): Boolean {
        return agreeInfo.value!! && agreeService.value!!
    }

    fun checkAgreeAllStatus() = viewModelScope.launch {
        agreeService.value = agreeAll.value
        agreeInfo.value = agreeAll.value
    }

}