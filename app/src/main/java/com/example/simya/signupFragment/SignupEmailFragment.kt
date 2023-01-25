package com.example.simya.signupFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupEmailBinding
import java.util.regex.Pattern

class SignupEmailFragment: Fragment() {
    private lateinit var binding: FragmentSignupEmailBinding
    private val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupEmailBinding.inflate(layoutInflater)

        return binding.root

    }



    // 다시 상속받는
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignupNext.setOnClickListener {
            if (emailCheck()) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fm_signup, SignupPwFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
    }

    private fun emailCheck() : Boolean {
        var email = binding.tietEmailSigninInputEmail.text.toString().trim()
        val pattern = Pattern.matches(emailValidation, email)

        return if (pattern){
            binding.tilEmailSigninInputEmail.error = null
            true
        } else {
            binding.tilEmailSigninInputEmail.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }



}


