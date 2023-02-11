package com.example.simya.src.main.myPage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.config.BaseResponse
import com.example.simya.databinding.ActivityProfileEditBinding
import com.example.simya.util.data.UserData
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ProfileAddActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileEditBinding
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "프로필 생성"
        binding.btnEditProfileEdit.setOnClickListener {
            if(nicknameCheck() && commentCheck()){
                onModifyUser()
            }else{
                // 수정 취소
            }
        }
    }

    private fun onModifyUser(){
        simyaApi.onLogout(UserData.accessToken, UserData.refreshToken).enqueue(object:
            Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {

            }
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Response",t.toString())
        }

        })
    }
    private fun nicknameCheck() : Boolean {
        var nickname = binding.edtEditProfileInputNickname.text.toString().trim()
        val pattern = Pattern.matches(Constants.NICKNAME_VALIDATION, nickname)

        return if (pattern) {
            binding.tilProfileInputNickname.error = null
            true
        } else {
            binding.tilProfileInputNickname.error = "올바른 닉네임 형식을 입력해주세요."
            false
        }
    }

    private fun commentCheck() : Boolean {
        var comment = binding.edtEditProfileInputComment.text.toString().trim()
        val pattern = Pattern.matches(Constants.COMMENT_VALIDATION, comment)
        var commentLength = binding.edtEditProfileInputComment.text!!.length

        return if (pattern && commentLength in 1..24) {
            binding.tilProfileInputComment.error = null
            true
        } else {
            binding.tilProfileInputComment.error = "24자 이내로 입력해주세요."
            false
        }
    }

}