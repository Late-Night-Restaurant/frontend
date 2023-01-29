package com.example.simya.signupFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.example.simya.R
import com.example.simya.databinding.FragmentSignupPwBinding
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import java.util.regex.Pattern

class SignupPwFragment: Fragment() {
    private lateinit var binding: FragmentSignupPwBinding
    private val pwValidation = "^[a-zA-Z0-9]*\$"

    // 받아온 emailData 담아둘 변수
    private lateinit var emailData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // email프래그먼트에서 email 받아오기
        setFragmentResultListener("email") { _, bundle ->
            emailData = bundle.getString("bundleKeyEmail").toString()
            Toast.makeText(this.context, emailData, Toast.LENGTH_SHORT).show()
        }
    }

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
            if (pwCheck() && rePwCheck()) {
                // profile 프래그먼트 데이터 전달
                val pwData = binding.tietEmailSigninInputPw.text.toString()
                val emailData = emailData
                setFragmentResult("pw", bundleOf("bundleKeyPw" to pwData))
                setFragmentResult("email", bundleOf("bundleKeyEmail" to emailData))

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fm_signup, SignupProfileFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }

    }


    private fun pwCheck(): Boolean {
        var pwLength = binding.tietEmailSigninInputPw.text!!.length
        var pw = binding.tietEmailSigninInputPw.text.toString().trim()
        val pattern = Pattern.matches(pwValidation, pw)

        return if (pattern && pwLength in 8..12) {
            binding.btnSignupNext.error = null
            true
        } else {
            binding.tilEmailSigninInputPw.error = "영문과 숫자를 조합해서 입력해주세요.(8-12자)"
            false
        }
    }

    private fun rePwCheck(): Boolean {
        var rePw = binding.tietEmailSigninInputRepw.text.toString().trim()
        val pw = binding.tietEmailSigninInputPw.text.toString().trim()

        return if (rePw == pw) {
            binding.tilEmailSigninInputRepw.error = null
            true
        } else {
            binding.tilEmailSigninInputRepw.error = "입력하신 비밀번호와 일치하지 않습니다."
            false
        }

    }


}

