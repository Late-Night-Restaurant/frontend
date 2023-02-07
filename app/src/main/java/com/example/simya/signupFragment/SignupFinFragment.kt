package com.example.simya.signupFragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.simya.activity.LoginActivity
import com.example.simya.activity.SignupActivity
import com.example.simya.databinding.FragmentSignupFinBinding
import com.example.simya.server.account.ProfileDTO

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
        signupActivity!!.binding.pbSignup.progress = 100

        binding.btnSignupNext.setOnClickListener {
            var intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        signupActivity!!.nextFragmentSignUp(4)
    }


}