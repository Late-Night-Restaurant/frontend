package com.example.simya.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.simya.databinding.FragmentHomeMainGridBinding
import com.example.simya.databinding.FragmentHomeMainRecyclerBinding

class MainRecyclerFragment: Fragment() {
    private lateinit var binding: FragmentHomeMainRecyclerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainRecyclerBinding.inflate(layoutInflater)
        return binding.root
    }
}