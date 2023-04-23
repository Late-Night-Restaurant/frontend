package com.example.simya.src.ui.view.login.signup.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.FragmentSignupProfileBinding
import com.example.simya.src.main.login.model.SignUpInterface
import com.example.simya.src.main.login.model.SignUpService
import com.example.simya.src.model.account.*
import com.example.simya.util.Constants.COMMENT_VALIDATION
import com.example.simya.util.Constants.ERROR_STRING_DUPLICATE
import com.example.simya.util.Constants.ERROR_STRING_FAILED_SIGN_UP
import com.example.simya.util.Constants.ERROR_STRING_INPUT
import com.example.simya.util.Constants.IMAGE_PATH
import com.example.simya.util.Constants.IMAGE_URI
import com.example.simya.util.Constants.NICKNAME_VALIDATION
import com.example.simya.util.Constants.POST_FAIL_USER
import com.example.simya.util.Constants.REQUEST_CODE_FOR_INTENT
import com.example.simya.util.Constants.REQUEST_ERROR
import com.example.simya.util.SampleSnackBar
import java.util.regex.Pattern

class SignupProfileFragment : BaseFragment<FragmentSignupProfileBinding>(
    R.layout.fragment_signup_profile
), SignupActivity.onBackPressedListener, SignUpInterface {
    private lateinit var emailData: String
    private lateinit var pwData: String
    private lateinit var profile: SignUpProfileDTO
    private lateinit var textWatcher: TextWatcher
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private var getUri: Uri? = null
    private var getPath: String? = null
    var signupActivity: SignupActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signupActivity = context as SignupActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        falseButton()
        initTW()
        setFragmentResultListener("email") { _, bundle ->
            emailData = bundle.getString("bundleKeyEmail").toString()
        }
        // pw프래그먼트에서 pw 받아오기
        setFragmentResultListener("pw") { _, bundle ->
            pwData = bundle.getString("bundleKeyPw").toString()
        }
        // email프래그먼트에서 email 받아오기
        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == REQUEST_CODE_FOR_INTENT) {
                    getUri = Uri.parse(result.data?.getStringExtra(IMAGE_URI))
                    getPath = result.data?.getStringExtra(IMAGE_PATH)
                    Glide.with(this).load(getUri).into(binding.civSignupInputProfile)
                    Log.d("이미지크롭 성공", "Success")
                } else {
                    SampleSnackBar.make(binding.root, "이미지를 가져오는데 실패했습니다.").show()
                }
            }
        binding.etCommentSignupInput.addTextChangedListener(textWatcher)
        binding.etCommentSignupInput.addTextChangedListener(textWatcher)
        binding.civSignupInputProfile.setOnClickListener {
//            val intent = Intent(requireContext(), GalleryActivity::class.java)
//            getResult.launch(intent)
        }
        binding.btnSignupProfileNext.setOnClickListener {
            if (nicknameCheck() && commentCheck()) {
                val nicknameData = binding.etCommentSignupInput.text.toString()
                val commentData = binding.etCommentSignupInput.text.toString()
                profile = SignUpProfileDTO(nicknameData, commentData)
                showLoadingDialog(requireContext())
                SignUpService(this).trySignUpSubmit(getPath, SignupDTO(emailData, pwData, profile))
                // 회원가입 완료되면 네비게이션을 통해 이동
//                Navigation.findNavController(view).navigate(R.id.action_signupProfileFragment_to_signupFinFragment)
            } else {
                SampleSnackBar.make(binding.root, "올바른 형식에 맞게 작성해주세요.").show()
            }
        }
    }

    private fun nicknameCheck(): Boolean {
        var nickname = binding.etCommentSignupInput.text.toString().trim()
        val pattern = Pattern.matches(NICKNAME_VALIDATION, nickname)

        return if (pattern) {
            binding.tilNicknameSignupInput.error = null
            true
        } else {
            binding.tilNicknameSignupInput.error = "올바른 닉네임 형식을 입력해주세요."
            false
        }
    }

    private fun commentCheck(): Boolean {
        var comment = binding.etCommentSignupInput.text.toString().trim()
        val pattern = Pattern.matches(COMMENT_VALIDATION, comment)
        var commentLength = binding.etCommentSignupInput.text!!.length

        return if (pattern && commentLength in 1..24) {
            binding.tilCommentSignupInput.error = null
            true
        } else {
            binding.tilCommentSignupInput.error = "24자 이내로 입력해주세요."
            false
        }
    }

//    private fun moveToFin() {
//        signupActivity!!.nextFragmentSignUp(5)
//    }

    private fun initTW() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val nicknameInput = binding.etCommentSignupInput!!.text.toString()
                val commentInput = binding.etCommentSignupInput!!.text.toString()

                if (nicknameInput.isNotEmpty() && commentInput.isNotEmpty()) {
                    trueButton()
                }
                if (nicknameInput.isEmpty() || commentInput.isEmpty()) {
                    falseButton()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }

    private fun trueButton() {
        binding.btnSignupProfileNext.isEnabled = true
        binding.btnSignupProfileNext.isClickable = true
        binding.btnSignupProfileNext.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnSignupProfileNext.setTextColor(resources.getColor(R.color.Gray_03))
    }

    private fun falseButton() {
        binding.btnSignupProfileNext.isEnabled = false
        binding.btnSignupProfileNext.isClickable = false
        binding.btnSignupProfileNext.setBackgroundResource(R.drawable.low_radius_button_off)
        binding.btnSignupProfileNext.setTextColor(resources.getColor(R.color.Gray_10))

    }

    override fun onBackPressed() {
//        signupActivity!!.nextFragmentSignUp(3)
//        signupActivity!!.decreaseProgressbar()
    }

    override fun onPostSignUpSubmitSuccess(response: SignupResponse) {
        dismissLoadingDialog()
//        moveToFin()
    }

    override fun onPostSignUpSubmitFailure(response: SignupResponse) {
        dismissLoadingDialog()
        if (response.code == REQUEST_ERROR) {
            when (response.message) {
                ERROR_STRING_INPUT -> SampleSnackBar.make(binding.root, ERROR_STRING_INPUT).show()
                ERROR_STRING_DUPLICATE -> SampleSnackBar.make(binding.root, ERROR_STRING_DUPLICATE).show()
            }
        }
        if (response.code == POST_FAIL_USER) {
            SampleSnackBar.make(binding.root, ERROR_STRING_FAILED_SIGN_UP).show()
        }
    }

    override fun onPostSignUpSubmitDisconnect(message: String) {
        SampleSnackBar.make(binding.root,message)
        dismissLoadingDialog()
    }

}