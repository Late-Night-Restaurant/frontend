//package com.example.simya.src.ui.view.login.singup.fragment
//
//import android.content.Context
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.os.bundleOf
//import androidx.fragment.app.*
//import com.example.simya.R
//import com.example.simya.src.ui.view.login.singup.SignupActivity
//import com.example.simya.databinding.FragmentSignupPwBinding
//import com.example.simya.util.Constants.PW_VALIDATION
//import java.util.regex.Pattern
//
//class SignupPwFragment: Fragment(), SignupActivity.onBackPressedListener {
//    private lateinit var binding: FragmentSignupPwBinding
//    private lateinit var textWatcher: TextWatcher
//    private lateinit var emailData: String
//
//    var signupActivity: SignupActivity? = null
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        signupActivity = context as SignupActivity
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSignupPwBinding.inflate(layoutInflater)
//        return binding.root
//
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        FalseButton()
//        initTW()
//        signupActivity!!.increaseProgressbar()
//
//        binding.tietPwSignupInput.addTextChangedListener(textWatcher)
//        binding.tietRepwSignupInput.addTextChangedListener(textWatcher)
//
//
//        binding.btnSignupNextPw.setOnClickListener {
//            if (pwCheck() && rePwCheck()) {
//                // profile 프래그먼트 데이터 전달
//                val pwData = binding.tietPwSignupInput.text.toString()
//
//                setFragmentResult("pw", bundleOf("bundleKeyPw" to pwData))
//
//                signupActivity!!.nextFragmentSignUp(4)
//            }
//        }
//
//    }
//
//
//    private fun pwCheck(): Boolean {
//        var pwLength = binding.tietPwSignupInput.text!!.length
//        var pw = binding.tietPwSignupInput.text.toString().trim()
//        val pattern = Pattern.matches(PW_VALIDATION, pw)
//
//        return if (pattern && pwLength in 8..12) {
//            binding.btnSignupNextPw.error = null
//            true
//        } else {
//            binding.tilPwSignupInput.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
//            false
//        }
//    }
//
//    private fun rePwCheck(): Boolean {
//        var rePw = binding.tietRepwSignupInput.text.toString().trim()
//        val pw = binding.tietPwSignupInput.text.toString().trim()
//
//        return if (rePw == pw) {
//            binding.tilRepwSignupInput.error = null
//            true
//        } else {
//            binding.tilRepwSignupInput.error = "입력하신 비밀번호와 일치하지 않습니다."
//            false
//        }
//
//    }
//
//    private fun initTW() {
//        textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                val pwInput = binding.tietPwSignupInput!!.text.toString()
//                val rePwInput = binding.tietRepwSignupInput!!.text.toString()
//
//                if (pwInput.isNotEmpty() && rePwInput.isNotEmpty()) {
//                    TrueButton()
//                }
//                if (pwInput.isEmpty() || rePwInput.isEmpty()) {
//                    FalseButton()
//                }
//            }
//
//            override fun afterTextChanged(s: Editable) {
//            }
//        }
//    }
//
//
//    private fun TrueButton() {
//        binding.btnSignupNextPw.isEnabled = true
//        binding.btnSignupNextPw.isClickable = true
//        binding.btnSignupNextPw.setBackgroundResource(R.drawable.low_radius_button_on)
//        binding.btnSignupNextPw.setTextColor(resources.getColor(R.color.Gray_03))
//
//    }
//
//    private fun FalseButton() {
//        binding.btnSignupNextPw.isEnabled = false
//        binding.btnSignupNextPw.isClickable = false
//        binding.btnSignupNextPw.setBackgroundResource(R.drawable.low_radius_button_off)
//        binding.btnSignupNextPw.setTextColor(resources.getColor(R.color.Gray_10))
//
//    }
//
//    override fun onBackPressed() {
//        signupActivity!!.nextFragmentSignUp(2)
//    }
//
//
//}
//
