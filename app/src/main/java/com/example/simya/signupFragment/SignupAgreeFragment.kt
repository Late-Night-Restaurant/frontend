package com.example.simya.signupFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simya.R
import com.example.simya.activity.EmailLoginActivity
import com.example.simya.activity.SignupActivity
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupAgreeBinding


class SignupAgreeFragment: Fragment(), SignupActivity.onBackPressedListener {
    private lateinit var binding: FragmentSignupAgreeBinding

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
        binding = FragmentSignupAgreeBinding.inflate(layoutInflater)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupActivity!!.binding.pbSignup.progress = 0

        agreeCheck()

        binding.btnSignupNextAgree.setOnClickListener {

            if (agreeCheck()) {
                signupActivity!!.nextFragmentSignUp(2)
                initAgree()
            } else {
                Toast.makeText(this.activity, "동의하지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun agreeCheck() : Boolean {
        val agreeAll = binding.cbSignupAgreeAll
        val agreeService = binding.cbSignupAgreeService
        val agreeInfo = binding.cbSignupAgreeInfo

        if (agreeAll.isChecked) {
            agreeService.isChecked = true
            agreeInfo.isChecked = true
            TrueButton()
        } else {
            agreeService.isChecked = false
            agreeInfo.isChecked = false
            FalseButton()
        }

        agreeAll.setOnClickListener {
            if (agreeAll.isChecked) {
                agreeService.isChecked = true
                agreeInfo.isChecked = true
                TrueButton()
            } else {
                agreeService.isChecked = false
                agreeInfo.isChecked = false
                FalseButton()
            }
        }

        agreeInfo.setOnClickListener {
            if (agreeInfo.isChecked) {
                if (agreeService.isChecked) {
                    agreeAll.isChecked = true
                    TrueButton()
                } else {
                    agreeAll.isChecked = false
                    FalseButton()
                }
            }
            if (!agreeInfo.isChecked) {
                agreeAll.isChecked = false
                FalseButton()
            }
        }

        agreeService.setOnClickListener {
            if (agreeService.isChecked) {
                if (agreeInfo.isChecked) {
                    agreeAll.isChecked = true
                    TrueButton()
                } else {
                    agreeAll.isChecked = false
                    FalseButton()
                }
            }
            if (!agreeService.isChecked) {
                agreeAll.isChecked = false
                FalseButton()
            }
        }



        return agreeAll.isChecked

    }

    private fun TrueButton() {
        binding.btnSignupNextAgree.isEnabled = true
        binding.btnSignupNextAgree.isClickable = true
        binding.btnSignupNextAgree.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnSignupNextAgree.setTextColor(resources.getColor(R.color.Gray_03))

    }

    private fun FalseButton() {
        binding.btnSignupNextAgree.isEnabled = false
        binding.btnSignupNextAgree.isClickable = false
        binding.btnSignupNextAgree.setBackgroundResource(R.drawable.low_radius_button_off)
        binding.btnSignupNextAgree.setTextColor(resources.getColor(R.color.Gray_10))

    }

    private fun initAgree() {
        val agreeAll = binding.cbSignupAgreeAll
        val agreeService = binding.cbSignupAgreeService
        val agreeInfo = binding.cbSignupAgreeInfo

        agreeAll.isChecked = false
        agreeService.isChecked = false
        agreeInfo.isChecked = false
    }

    override fun onBackPressed() {
        val intent = Intent(this.context, EmailLoginActivity::class.java)
        startActivity(intent)
    }
}