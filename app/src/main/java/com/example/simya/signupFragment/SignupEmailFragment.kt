package com.example.simya.signupFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupEmailBinding

class SignupEmailFragment: Fragment() {
    private lateinit var binding: FragmentSignupEmailBinding
    private lateinit var signupBinding: ActivitySignupBinding

    lateinit var sendSignupListener: SendSignupListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Fragment가 Activity에 접근
        sendSignupListener = context as SendSignupListener
    }

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

    }
}