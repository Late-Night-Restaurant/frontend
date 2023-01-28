package com.example.simya.signupFragment

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
import com.example.simya.databinding.FragmentSignupFinBinding
import com.example.simya.server.account.ProfileDTO

class SignupFinFragment: Fragment() {
    private lateinit var binding: FragmentSignupFinBinding
    private lateinit var emailData: String
    private lateinit var pwData: String
    private lateinit var commentData: String
    private lateinit var nicknameData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // email프래그먼트에서 email 받아오기
        setFragmentResultListener("email") { _, bundle ->
            emailData = bundle.getString("bundleKeyEmail").toString()
            Toast.makeText(this.context, emailData, Toast.LENGTH_SHORT).show()
        }
        // pw프래그먼트에서 pw 받아오기
        setFragmentResultListener("pw") { _, bundle ->
            pwData = bundle.getString("bundleKeyPw").toString()
            Toast.makeText(this.context, pwData, Toast.LENGTH_SHORT).show()
        }


        setFragmentResultListener("nickname") { _, bundle ->
            nicknameData = bundle.getString("bundleKeyNickname").toString()
            Toast.makeText(this.context, nicknameData, Toast.LENGTH_SHORT).show()
        }


        setFragmentResultListener("comment") { _, bundle ->
            commentData = bundle.getString("bundleKeyComment").toString()
            Toast.makeText(this.context, commentData, Toast.LENGTH_SHORT).show()
        }

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

        binding.btnSignupNext.setOnClickListener {
            var intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}