package com.example.simya.signupFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.FragmentSignupPwBinding

class SignupPwFragment: Fragment() {
    private lateinit var binding: FragmentSignupPwBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupPwBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignupNext.setOnClickListener {
            if (pwCheck()) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fm_signup, SignupProfileFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
    }

    private fun pwCheck(): Boolean {
        var pw = binding.tietEmailSigninInputPw.text!!.length

        return if (pw in 8..12) {
            binding.btnSignupNext.error = null
            true
        } else {
            binding.tilEmailSigninInputPw.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
            false
        }
    }
}