package com.example.simya.src.main.myPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivityProfileEditBinding
import com.example.simya.databinding.ActivityProfileModifyBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.util.Constants
import java.util.regex.Pattern

class ProfileModifyActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileModifyBinding
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileModifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "나의 프로필"
        binding.btnModifyProfileEdit.setOnClickListener {
            // 수정 창으로
        }
        binding.btnModifyProfileDelete.setOnClickListener {
            // 삭제창으로
        }

        // 사진 , 닉네임, 한줄소개 init 만들기
    }

}