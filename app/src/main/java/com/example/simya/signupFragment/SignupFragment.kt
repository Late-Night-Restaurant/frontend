package com.example.simya.signupFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.simya.activity.SignupActivity
import com.example.simya.databinding.FragmentSignupAgreeBinding

class SignupFragment: Fragment() {
    private lateinit var binding: FragmentSignupAgreeBinding
    private lateinit var bindSign: SignupActivity

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

        binding.btnSignupNext.setOnClickListener {
            val intent = Intent(activity, SignupActivity::class.java)
            startActivity(intent)
        }

    }
}