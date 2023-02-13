package com.example.simya.src.main.myPage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.config.BaseActivity
import com.example.simya.config.BaseResponse
import com.example.simya.src.main.home.HomeActivity
import com.example.simya.databinding.ActivityProfileEditBinding
import com.example.simya.src.main.myPage.model.ProfileModifyInterface
import com.example.simya.src.main.myPage.model.ProfileModifyService
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.UserDTO
import com.example.simya.util.Constants
import com.example.simya.util.Constants.DEFAULT
import com.example.simya.util.Constants.ERROR_STRING_DATABASE
import com.example.simya.util.Constants.ERROR_STRING_FAIL_UPDATE_PROFILE
import com.example.simya.util.Constants.OK
import com.example.simya.util.Constants.SUCCESS_STRING_MODIFY
import com.example.simya.util.data.UserData
import com.example.simya.util.dialog.BasicDialog
import com.example.simya.util.dialog.LoadingDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>(ActivityProfileEditBinding::inflate), ProfileModifyInterface
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "프로필 수정"
        binding.btnEditProfileEdit.setOnClickListener {
            if (nicknameCheck() && commentCheck()) {
                showLoadingDialog(this)
                // 사진부분 수정해야함
                ProfileModifyService(this).tryModifyMyProfile(UserDTO(
                    UserData.getProfileId(),
                    binding.edtEditProfileInputNickname.text.toString(),
                    binding.edtEditProfileInputComment.text.toString(),
                    DEFAULT
                ))
            }
        }
    }

    private fun nicknameCheck(): Boolean {
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

    private fun commentCheck(): Boolean {
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
    override fun onModifyMyProfileSuccess(response: BaseResponse) {
        dismissLoadingDialog()
        Log.d("@@@@@@ SUCCESS @@@@@@","수정완료")
    }

    override fun onModifyMyProfileFailure(response: BaseResponse) {
        // 실패했다고 올려주기
    }
    override fun onDeleteMyProfileSuccess(response: BaseResponse) {
    }
    override fun onDeleteMyProfileFailure(response: BaseResponse) {
    }

}