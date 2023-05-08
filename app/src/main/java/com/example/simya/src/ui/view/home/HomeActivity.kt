package com.example.simya.src.ui.view.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityCropImageBinding
import com.example.simya.databinding.ActivityHomeBinding
import com.example.simya.util.data.UserData

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home)
{
    override fun init() {
        val bottomNavigationView = binding.bnvHomeNavi
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_home) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}
