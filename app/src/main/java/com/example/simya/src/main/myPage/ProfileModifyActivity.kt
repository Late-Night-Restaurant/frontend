package com.example.simya.src.main.myPage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityProfileEditBinding
import com.example.simya.databinding.ActivityProfileModifyBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.util.Constants
import com.example.simya.util.data.UserData
import java.util.regex.Pattern

class ProfileModifyActivity : BaseActivity<ActivityProfileModifyBinding>(ActivityProfileModifyBinding::inflate)
{
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initMainProfile()
        binding.included.tvDefaultLayoutTitle.text = "나의 프로필"
        binding.btnModifyProfileEdit.setOnClickListener {
            val intent = Intent(this,ProfileEditActivity::class.java)
            startActivity(intent)
        }
        binding.btnModifyProfileDelete.setOnClickListener {
            // 삭제창으로
        }

        // 사진 , 닉네임, 한줄소개 init 만들기
    }
    private fun initMainProfile(){
        Glide.with(this).load(UserData.getProfileImage()).placeholder(R.drawable.ic_base_profile).into(binding.civModifyProfileImage)
        binding.edtModifyProfileInputNickname.text = UserData.getProfileName()
        binding.edtModifyProfileInputComment.text = UserData.getProfileComment()
    }
}