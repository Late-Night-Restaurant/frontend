package com.example.simya.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simya.databinding.FragmentHomeMainBinding
import com.example.simya.databinding.FragmentHomeMyStoryBinding

class MyStoryFragment: Fragment() {
    private lateinit var binding: FragmentHomeMyStoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMyStoryBinding.inflate(layoutInflater)
        return binding.root
    }
}