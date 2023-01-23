package com.example.simya.signupFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.databinding.ActivitySignupBinding
import com.example.simya.databinding.FragmentSignupEmailBinding

class SignupEmailFragment: Fragment() {
    private lateinit var binding: FragmentSignupEmailBinding
    private lateinit var bindingSign: ActivitySignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupEmailBinding.inflate(layoutInflater)
        return binding.root

        binding.btnSignupNext.setOnClickListener {
            childFragmentManager
                .beginTransaction()
                .replace(bindingSign.fmSignup.id, SignupPwFragment())
                .commitAllowingStateLoss()
            bindingSign.pbSignup.progress = 50
        }
    }



    // 다시 상속받는
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}


