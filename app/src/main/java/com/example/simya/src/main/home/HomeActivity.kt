package com.example.simya.src.main.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityHomeBinding
import com.example.simya.src.main.loaction.LocationFragment
import com.example.simya.src.main.home.fragment.HomeFragment
import com.example.simya.src.main.myPage.fragment.MyPageFragment
import com.example.simya.src.main.story.fragment.MyStoryFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate)
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    private fun init() {
//        fragment 전환
        binding.bnvHomeNavi.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.tab_home_main_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.fmHome.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.tab_home_main_my_chat -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.fmHome.id, MyStoryFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.tab_home_main_location -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.fmHome.id, LocationFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.tab_home_main_my -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.fmHome.id, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.tab_home_main_home
        }
    }
}
