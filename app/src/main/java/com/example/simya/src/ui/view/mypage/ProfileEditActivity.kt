//package com.example.simya.src.ui.view.mypage
//
//import android.os.Bundle
//import android.util.Log
//import com.example.simya.config.BaseActivity
//import com.example.simya.config.BaseResponse
//import com.example.simya.databinding.ActivityProfileEditBinding
//import com.example.simya.src.main.myPage.model.ProfileModifyInterface
//import com.example.simya.src.main.myPage.model.ProfileModifyService
//import com.example.simya.src.model.UserDTO
//import com.example.simya.util.Constants
//import com.example.simya.util.Constants.DEFAULT
//import com.example.simya.util.SampleSnackBar
//import com.example.simya.util.data.UserData
//import com.example.simya.util.onThrottleClick
//import java.util.regex.Pattern
//
//class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>(ActivityProfileEditBinding::inflate), ProfileModifyInterface
//{
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        binding.included.tvDefaultLayoutTitle.text = "프로필 수정"
//        binding.btnEditProfileEdit.onThrottleClick {
//            if (nicknameCheck() && commentCheck()) {
//                showLoadingDialog(this)
//                // 사진부분 수정해야함
//                ProfileModifyService(this).tryModifyMyProfile(UserDTO(
//                    UserData.getProfileId(),
//                    binding.edtEditProfileInputNickname.text.toString(),
//                    binding.edtEditProfileInputComment.text.toString(),
//                    DEFAULT
//                ))
//            }
//        }
//    }
//
//    private fun nicknameCheck(): Boolean {
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
//    private fun commentCheck(): Boolean {
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
//    override fun onModifyMyProfileSuccess(response: BaseResponse) {
//        dismissLoadingDialog()
//        Log.d("@@@@@@ SUCCESS @@@@@@","수정완료")
//    }
//
//    override fun onModifyMyProfileFailure(response: BaseResponse) {
//        // 실패했다고 올려주기
//    }
//
//    override fun onModifyMyProfileDisconnect(message: String) {
//        SampleSnackBar.make(binding.root,message)
//        dismissLoadingDialog()
//    }
//
//    override fun onDeleteMyProfileSuccess(response: BaseResponse) {
//    }
//    override fun onDeleteMyProfileFailure(response: BaseResponse) {
//    }
//
//    override fun onDeleteMyProfileDisconnect(message: String) {
//        SampleSnackBar.make(binding.root,message)
//        dismissLoadingDialog()
//    }
//
//}