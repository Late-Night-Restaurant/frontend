package com.example.simya.signupFragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.example.simya.R
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupEmailBinding
import com.example.simya.server.account.SignupDTO
import com.example.simya.signUpViewModel.SignUpViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignupEmailFragment: Fragment() {
    private lateinit var binding: FragmentSignupEmailBinding
    private val emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private lateinit var textWatcher: TextWatcher
    private lateinit var bindingMain: ActivitySignupBinding
    private lateinit var viewModel: SignUpViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupEmailBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
            .get(SignUpViewModel::class.java)

        return binding.root

    }


    // 다시 상속받는
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FalseButton()

        initTW()

        binding.tietEmailSignupInputEmail.addTextChangedListener(textWatcher)

        binding.btnSignupNextEmail.setOnClickListener {
            if (emailCheck()) {
                // pw프래그먼트로 데이터 전달
                val email = binding.tietEmailSignupInputEmail.text.toString()
                setFragmentResult("email", bundleOf("bundleKeyEmail" to email))

                // progress bar 값 변경
                viewModel.pbValue.value = 50

                // 프래그먼트 전환
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fm_signup, SignupPwFragment())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
    }




    private fun emailCheck() : Boolean {
        var email = binding.tietEmailSignupInputEmail.text.toString().trim()
        val pattern = Pattern.matches(emailValidation, email)

        return if (pattern){
            binding.tilEmailSignupInputEmail.error = null
            true
        } else {
            binding.tilEmailSignupInputEmail.error = "올바른 이메일 형식을 입력해주세요."
            false
        }
    }


    private fun initTW() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val emailInput = binding.tietEmailSignupInputEmail!!.text.toString()

                if (emailInput.isNotEmpty()) {
                    TrueButton()
                }
                if (emailInput.isEmpty()) {
                    FalseButton()
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }

    private fun TrueButton() {
        binding.btnSignupNextEmail.isEnabled = true
        binding.btnSignupNextEmail.isClickable = true
        binding.btnSignupNextEmail.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnSignupNextEmail.setTextColor(resources.getColor(R.color.Gray_03))

    }

    private fun FalseButton() {
        binding.btnSignupNextEmail.isEnabled = false
        binding.btnSignupNextEmail.isClickable = false
        binding.btnSignupNextEmail.setBackgroundResource(R.drawable.low_radius_button_off)
        binding.btnSignupNextEmail.setTextColor(resources.getColor(R.color.Gray_10))

    }

}


