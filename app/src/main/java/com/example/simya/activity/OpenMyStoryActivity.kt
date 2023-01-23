package com.example.simya.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.ActivityMainBinding
import com.example.simya.databinding.ActivityMyStoryOpenBinding

class OpenMyStoryActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivityMyStoryOpenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMyStoryOpenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}