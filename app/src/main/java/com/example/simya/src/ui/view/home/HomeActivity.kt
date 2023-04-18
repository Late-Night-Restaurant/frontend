//package com.example.simya.src.ui.view.home
//
//import android.os.Bundle
//import com.example.simya.R
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityHomeBinding
//import com.example.simya.src.ui.view.location.LocationFragment
//import com.example.simya.src.ui.view.mypage.MyPageFragment
//import com.example.simya.src.ui.view.story.MyStoryFragment
//import com.example.simya.util.data.UserData
//
//class HomeActivity : BaseActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate)
//{
//    private var checkStatus = true
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//
//    }
//
//    private fun init() {
//        UserData.printAllData()
////        fragment 전환
//
//        binding.bnvHomeNavi.run {
//            setOnItemSelectedListener {
//                when (it.itemId) {
//                    R.id.tab_home_main_home -> {
//                        if(checkStatus){
//                            supportFragmentManager
//                                .beginTransaction()
//                                .replace(binding.fmHome.id, HomeFragment())
//                                .commitAllowingStateLoss()
//                        }
//
//                    }
//                    R.id.tab_home_main_my_chat -> {
//                        supportFragmentManager
//                            .beginTransaction()
//                            .replace(binding.fmHome.id, MyStoryFragment())
//                            .commitAllowingStateLoss()
//                    }
//                    R.id.tab_home_main_location -> {
//                        supportFragmentManager
//                            .beginTransaction()
//                            .replace(binding.fmHome.id, LocationFragment())
//                            .commitAllowingStateLoss()
//                    }
//                    R.id.tab_home_main_my -> {
//                        supportFragmentManager
//                            .beginTransaction()
//                            .replace(binding.fmHome.id, MyPageFragment())
//                            .commitAllowingStateLoss()
//                    }
//                }
//                true
//            }
//            selectedItemId = R.id.tab_home_main_home
//        }
//    }
//    override fun onBackPressed() {
//        backApplicationExit(this)
//    }
//}
