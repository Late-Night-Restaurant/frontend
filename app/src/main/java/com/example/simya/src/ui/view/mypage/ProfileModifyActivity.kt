//package com.example.simya.src.ui.view.mypage
//
//import android.content.Intent
//import android.os.Bundle
//import com.bumptech.glide.Glide
//import com.example.simya.R
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityProfileModifyBinding
//import com.example.simya.src.ui.view.prepare.PrepareActivity
//import com.example.simya.util.data.UserData
//import com.example.simya.util.onThrottleClick
//
//class ProfileModifyActivity : BaseActivity<ActivityProfileModifyBinding>(ActivityProfileModifyBinding::inflate)
//{
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        initMainProfile()
//        binding.included.tvDefaultLayoutTitle.text = "나의 프로필"
//        binding.btnModifyProfileEdit.onThrottleClick {
////            val intent = Intent(this,ProfileEditActivity::class.java)
////            startActivity(intent)
//            // 아직 준비중
//            val intent = Intent(this, PrepareActivity::class.java)
//            startActivity(intent)
//        }
//        binding.btnModifyProfileDelete.onThrottleClick {
//            // 아직 준비중
//            val intent = Intent(this, PrepareActivity::class.java)
//            startActivity(intent)
//        }
//
//        // 사진 , 닉네임, 한줄소개 init 만들기
//    }
//    private fun initMainProfile(){
//        Glide.with(this).load(UserData.getProfileImage()).placeholder(R.drawable.ic_base_profile).into(binding.civModifyProfileImage)
//        binding.edtModifyProfileInputNickname.text = UserData.getProfileName()
//        binding.edtModifyProfileInputComment.text = UserData.getProfileComment()
//    }
//}