package com.example.simya.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simya.databinding.ActivityMainBinding
import com.example.simya.databinding.DefaultChatBinding
import com.example.simya.databinding.DefaultLayoutBinding
import com.example.simya.databinding.DialogProfileBinding
import com.example.simya.databinding.DialogProfileMasterBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}