package com.example.simya.signUpViewModel

import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simya.activity.SignupActivity
import com.example.simya.databinding.ActivitySignupBinding

class SignUpViewModel: ViewModel() {
    var pbValue: MutableLiveData<Int> = MutableLiveData()
}