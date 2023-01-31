package com.example.simya.signupFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.example.simya.Constants
import com.example.simya.R
import com.example.simya.databinding.FragmentSignupProfileBinding
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import com.example.simya.server.account.ProfileDTO
import com.example.simya.server.account.SignupDTO
import com.example.simya.server.account.SignupResponse
import com.example.simya.signUpViewModel.SignUpViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignupProfileFragment: Fragment() {
    private lateinit var binding: FragmentSignupProfileBinding
    private val nicknameValidation = "^[가-힣]{1,8}$"
    private val commentValidation = "^[가-힣a-zA-Z]{1,24}$"
    // 공백 추가하기
    private lateinit var emailData: String
    private lateinit var pwData: String
    private lateinit var profile: ProfileDTO
    private lateinit var textWatcher: TextWatcher
    private lateinit var viewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupProfileBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
            .get(SignUpViewModel::class.java)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        falseButton()
        initTW()
        setFragmentResultListener("email") { _, bundle ->
            emailData = bundle.getString("bundleKeyEmail").toString()
            Toast.makeText(this.context, emailData, Toast.LENGTH_SHORT).show()
        }
        // pw프래그먼트에서 pw 받아오기
        setFragmentResultListener("pw") { _, bundle ->
            pwData = bundle.getString("bundleKeyPw").toString()
            Toast.makeText(this.context, pwData, Toast.LENGTH_SHORT).show()
        }
        // email프래그먼트에서 email 받아오기


        binding.tietSignupInputNickname.addTextChangedListener(textWatcher)
        binding.tietSignupInputComment.addTextChangedListener(textWatcher)


        binding.btnSignupNextProfile.setOnClickListener {

            if (nicknameCheck() && commentCheck()){
                val nicknameData = binding.tietSignupInputNickname.text.toString()
                val commentData = binding.tietSignupInputComment.text.toString()

                viewModel.pbValue.value = 100

                profile = ProfileDTO(nicknameData,commentData,"default")
//                Log.d("Before","onSignUp")
                onSignUp(SignupDTO(emailData, pwData, profile))


            } else {
                Toast.makeText(this.context, "올바른 형식에 맞게 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun nicknameCheck() : Boolean {
        var nickname = binding.tietSignupInputNickname.text.toString().trim()
        val pattern = Pattern.matches(nicknameValidation, nickname)

        return if (pattern) {
            binding.tilSignupInputNickname.error = null
            true
        } else {
            binding.tilSignupInputNickname.error = "올바른 닉네임 형식을 입력해주세요."
            false
        }
    }

    private fun commentCheck() : Boolean {
        var comment = binding.tietSignupInputComment.text.toString().trim()
        val pattern = Pattern.matches(commentValidation, comment)
        var commentLength = binding.tietSignupInputComment.text!!.length

        return if (pattern && commentLength in 1..24) {
            binding.tilSignupInputComment.error = null
            true
        } else {
            binding.tilSignupInputComment.error = "24자 이내로 입력해주세요."
            false
        }
    }

    private fun moveToFin(){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fm_signup, SignupFinFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }



    private fun onSignUp(user: SignupDTO){
        val retrofit = RetrofitBuilder.getInstnace()
        val API = retrofit.create(RetrofitService::class.java)

        API.onSignUpSubmit(user).enqueue(object: Callback<SignupResponse>{
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                if(response.body()!!.code==Constants.OK){
                    Log.d("Response check", response.toString())
                    Log.d("Response check", response.message().toString())
                    Log.d("Response check", response.code().toString())
                    Log.d("Response check", response.body().toString())
                    moveToFin()
                }
                Toast.makeText(this@SignupProfileFragment.context, response.message(), Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Toast.makeText(this@SignupProfileFragment.context, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("Response check", t.toString())
            }
        })
    }


    private fun initTW() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val nicknameInput = binding.tietSignupInputNickname!!.text.toString()
                val commentInput = binding.tietSignupInputComment!!.text.toString()

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
}