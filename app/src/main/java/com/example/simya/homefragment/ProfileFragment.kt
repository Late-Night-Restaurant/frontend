package com.example.simya.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simya.databinding.FragmentHomeLocationBinding
import com.example.simya.databinding.FragmentHomeProfileBinding

class ProfileFragment: Fragment() {
    private lateinit var binding: FragmentHomeProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeProfileBinding.inflate(layoutInflater)
        return binding.root
    }
}