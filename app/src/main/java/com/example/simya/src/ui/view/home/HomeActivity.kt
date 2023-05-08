package com.example.simya.src.ui.view.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityCropImageBinding
import com.example.simya.databinding.ActivityHomeBinding
import com.example.simya.util.data.UserData

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home)
{
    override fun init() {
//        val bottomNavigationView = binding.bottomNavigationView
//        val navController = findNavController(R.id.fragmentContainerView)
//
//        bottomNavigationView.setupWithNavController(navController)

        val bottomNavigationView = binding.bnvHomeNavi
        val navController = findNavController(R.id.fcv_home)
        bottomNavigationView.setupWithNavController(navController)
    }
}
