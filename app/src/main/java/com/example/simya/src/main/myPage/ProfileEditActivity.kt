package com.example.simya.src.main.myPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.src.main.home.HomeActivity
import com.example.simya.databinding.ActivityProfileEditBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.util.Constants
import java.util.regex.Pattern

class ProfileEditActivity : AppCompatActivity() {
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
        binding.included.tvDefaultLayoutTitle.text = "프로필 수정"
        binding.btnEditProfileEdit.setOnClickListener {

        }
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