package com.example.simya.signupFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.FragmentSignupProfileBinding
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import com.example.simya.server.account.AccountResponse
import com.example.simya.server.account.ProfileDTO
import com.example.simya.server.account.SignupDTO
import com.example.simya.server.account.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignupProfileFragment: Fragment() {
    private lateinit var binding: FragmentSignupProfileBinding
    private val nicknameValidation = "^[가-힣]{1,8}$"
    private val commentValidation = "^[가-힣a-zA-Z]{1,24}$"
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var profile: ProfileDTO

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

        binding.btnSignupNext.setOnClickListener {
            onSignUp(SignupDTO(email, password, profile))
            parentFragmentManager.beginTransaction()
                .replace(R.id.fm_signup, SignupFinFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }

    private fun nicknameCheck() : Boolean {
        var nickname = binding.tietSigninInputNickname.text.toString().trim()
        val pattern = Pattern.matches(nicknameValidation, nickname)

        return if (pattern) {
            binding.tilSigninInputNickname.error = null
            true
        } else {
            binding.tilSigninInputNickname.error = "올바른 닉네임 형식을 입력해주세요."
            false
        }
    }

    private fun commentCheck() : Boolean {
        var comment = binding.tietSigninInputComment.text.toString().trim()
        val pattern = Pattern.matches(commentValidation, comment)

        return if (pattern) {
            binding.tilSigninInputComment.error = null
            true
        } else {
            binding.tilSigninInputComment.error = "24자 이내로 입력해주세요."
            false
        }
    }


    private fun onSignUp(user: SignupDTO){
        val retrofit = RetrofitBuilder.getInstnace()
        val API = retrofit.create(RetrofitService::class.java)

        API.onSignUpSubmit(user).enqueue(object: Callback<SignupResponse>{
            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}