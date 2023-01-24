package com.example.simya.mypageFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simya.databinding.FragmentHomeMyPageBinding

class MyPageFragment: Fragment() {
    private lateinit var binding: FragmentHomeMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMyPageBinding.inflate(layoutInflater)
        return binding.root
    }
}