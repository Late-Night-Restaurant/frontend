//package com.example.simya.src.ui.view.mypage
//
//import android.os.Bundle
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityProfileEditBinding
//import com.example.simya.util.Constants
//import com.example.simya.util.onThrottleClick
//import java.util.regex.Pattern
//
//class ProfileAddActivity : BaseActivity<ActivityProfileEditBinding>(ActivityProfileEditBinding::inflate)
//{
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//    private fun init() {
//        binding.included.tvDefaultLayoutTitle.text = "프로필 생성"
//        binding.btnEditProfileEdit.onThrottleClick {
//            if(nicknameCheck() && commentCheck()){
//                // 수정하기
//            }else{
//                // 수정 취소
//            }
//        }
//    }
//
//    private fun nicknameCheck() : Boolean {
//        var nickname = binding.edtEditProfileInputNickname.text.toString().trim()
//        val pattern = Pattern.matches(Constants.NICKNAME_VALIDATION, nickname)
//
//        return if (pattern) {
//            binding.tilProfileInputNickname.error = null
//            true
//        } else {
//            binding.tilProfileInputNickname.error = "올바른 닉네임 형식을 입력해주세요."
//            false
//        }
//    }
//
//    private fun commentCheck() : Boolean {
//        var comment = binding.edtEditProfileInputComment.text.toString().trim()
//        val pattern = Pattern.matches(Constants.COMMENT_VALIDATION, comment)
//        var commentLength = binding.edtEditProfileInputComment.text!!.length
//
//        return if (pattern && commentLength in 1..24) {
//            binding.tilProfileInputComment.error = null
//            true
//        } else {
//            binding.tilProfileInputComment.error = "24자 이내로 입력해주세요."
//            false
//        }
//    }
//
//}