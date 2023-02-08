package com.example.simya.src.fragment.signupFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.simya.src.Constants
import com.example.simya.R
import com.example.simya.src.activity.login.singUp.SignupActivity
import com.example.simya.databinding.FragmentSignupProfileBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.account.ProfileDTO
import com.example.simya.src.model.account.SignupDTO
import com.example.simya.src.model.account.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignupProfileFragment: Fragment(), SignupActivity.onBackPressedListener {
    private lateinit var binding: FragmentSignupProfileBinding
    private val nicknameValidation = "^[가-힣]{1,8}$"
    private val commentValidation = "^.{1,24}$" // 모든 문자 가능, 24자 이내
    private lateinit var emailData: String
    private lateinit var pwData: String
    private lateinit var profile: ProfileDTO
    private lateinit var textWatcher: TextWatcher

    var signupActivity: SignupActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signupActivity = context as SignupActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupProfileBinding.inflate(layoutInflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        falseButton()
        initTW()
        signupActivity!!.binding.pbSignup.progress = 75

        setFragmentResultListener("email") { _, bundle ->
            emailData = bundle.getString("bundleKeyEmail").toString()
        }
        // pw프래그먼트에서 pw 받아오기
        setFragmentResultListener("pw") { _, bundle ->
            pwData = bundle.getString("bundleKeyPw").toString()
        }
        // email프래그먼트에서 email 받아오기


        binding.tietNicknameSignupInput.addTextChangedListener(textWatcher)
        binding.tietCommentSignupInput.addTextChangedListener(textWatcher)


        binding.btnSignupNextProfile.setOnClickListener {

            if (nicknameCheck() && commentCheck()){
                val nicknameData = binding.tietNicknameSignupInput.text.toString()
                val commentData = binding.tietCommentSignupInput.text.toString()

                profile = ProfileDTO(nicknameData,commentData,"default")
//                Log.d("Before","onSignUp")
                onSignUp(SignupDTO(emailData, pwData, profile))


            } else {
                Toast.makeText(this.context, "올바른 형식에 맞게 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun nicknameCheck() : Boolean {
        var nickname = binding.tietNicknameSignupInput.text.toString().trim()
        val pattern = Pattern.matches(nicknameValidation, nickname)

        return if (pattern) {
            binding.tilNicknameSignupInput.error = null
            true
        } else {
            binding.tilNicknameSignupInput.error = "올바른 닉네임 형식을 입력해주세요."
            false
        }
    }

    private fun commentCheck() : Boolean {
        var comment = binding.tietCommentSignupInput.text.toString().trim()
        val pattern = Pattern.matches(commentValidation, comment)
        var commentLength = binding.tietCommentSignupInput.text!!.length

        return if (pattern && commentLength in 1..24) {
            binding.tilCommentSignupInput.error = null
            true
        } else {
            binding.tilCommentSignupInput.error = "24자 이내로 입력해주세요."
            false
        }
    }

    private fun moveToFin(){
        signupActivity!!.nextFragmentSignUp(5)
    }



    private fun onSignUp(user: SignupDTO){
        val retrofit = RetrofitBuilder.getInstnace()
        val API = retrofit.create(RetrofitService::class.java)

        API.onSignUpSubmit(user).enqueue(object: Callback<SignupResponse>{
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if(response.body()!!.code== Constants.OK){
                    Log.d("Response check", response.toString())
                    Log.d("Response check", response.message().toString())
                    Log.d("Response check", response.code().toString())
                    Log.d("Response check", response.body().toString())
                    moveToFin()
                }
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Log.d("Response check", t.toString())
            }
        })
    }


    private fun initTW() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val nicknameInput = binding.tietNicknameSignupInput!!.text.toString()
                val commentInput = binding.tietCommentSignupInput!!.text.toString()

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
        binding.btnSignupNextProfile.isEnabled = true
        binding.btnSignupNextProfile.isClickable = true
        binding.btnSignupNextProfile.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnSignupNextProfile.setTextColor(resources.getColor(R.color.Gray_03))

    }

    private fun falseButton() {
        binding.btnSignupNextProfile.isEnabled = false
        binding.btnSignupNextProfile.isClickable = false
        binding.btnSignupNextProfile.setBackgroundResource(R.drawable.low_radius_button_off)
        binding.btnSignupNextProfile.setTextColor(resources.getColor(R.color.Gray_10))

    }

    override fun onBackPressed() {
        signupActivity!!.nextFragmentSignUp(3)
    }


}