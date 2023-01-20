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
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

    }
}