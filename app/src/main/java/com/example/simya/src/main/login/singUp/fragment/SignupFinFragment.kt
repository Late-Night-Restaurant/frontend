package com.example.simya.src.main.login.singUp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simya.src.main.login.LoginActivity
import com.example.simya.src.main.login.singUp.SignupActivity
import com.example.simya.databinding.FragmentSignupFinBinding

class SignupFinFragment: Fragment(), SignupActivity.onBackPressedListener {
    private lateinit var binding: FragmentSignupFinBinding

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
        binding = FragmentSignupFinBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupActivity!!.increaseProgressbar()

        binding.btnSignupNext.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onBackPressed() {
        signupActivity!!.nextFragmentSignUp(4)
        signupActivity!!.decreaseProgressbar()
    }


}