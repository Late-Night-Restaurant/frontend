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
    private lateinit var emailData: String
    private lateinit var pwData: String
    private lateinit var profile: ProfileDTO
    private lateinit var textWatcher: TextWatcher
    private lateinit var viewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // email프래그먼트에서 email 받아오기
        setFragmentResultListener("email") { _, bundle ->
            emailData = bundle.getString("bundleKeyEmail").toString()
            Toast.makeText(this.context, emailData, Toast.LENGTH_SHORT).show()
        }
        // pw프래그먼트에서 pw 받아오기
        setFragmentResultListener("pw") { _, bundle ->
            pwData = bundle.getString("bundleKeyPw").toString()
            Toast.makeText(this.context, pwData, Toast.LENGTH_SHORT).show()
        }


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

        binding.btnSignupNext.setOnClickListener {
            // onSignUp(SignupDTO(emailData, pwData, profile))
            if (nicknameCheck() && commentCheck()){
                val nicknameData = binding.tietSignupInputNickname.text.toString()
                val commentData = binding.tietSignupInputComment.text.toString()

                Toast.makeText(this.context, nicknameData, Toast.LENGTH_SHORT).show()
                Toast.makeText(this.context, commentData, Toast.LENGTH_SHORT).show()
                Toast.makeText(this.context, pwData, Toast.LENGTH_SHORT).show()

                viewModel.pbValue.value = 100

                moveToFin()

            } else {
                Toast.makeText(this.context, "올바른 형식에 맞게 작성해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init() {
        binding.btnSignupNext.isEnabled = false

        binding.tietSignupInputNickname.addTextChangedListener(textWatcher)
        binding.tietSignupInputComment.addTextChangedListener(textWatcher)


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
                if(response.code()==Constants.OK){
                    Log.d("Response check", response.toString())
                    Log.d("Response check", response.message().toString())
                    Log.d("Response check", response.code().toString())
                    Log.d("Response check", response.body().toString())

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
        textWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val nicknameInput = binding.tietSignupInputNickname!!.text.toString()
                val commentInput = binding.tietSignupInputComment!!.text.toString()
                if (nicknameInput.isNotEmpty() && commentInput.isNotEmpty()) {
                    binding.btnSignupNext.isEnabled = true
                    binding.btnSignupNext.isClickable = true
                    binding.btnSignupNext.setBackgroundResource(R.drawable.low_radius_button_on)
                } else {
                    binding.btnSignupNext.isEnabled = false
                    binding.btnSignupNext.isClickable = false
                    binding.btnSignupNext.setBackgroundResource(R.drawable.low_radius_button_off)
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }
}