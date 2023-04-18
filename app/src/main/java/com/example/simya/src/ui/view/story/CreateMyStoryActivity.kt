//package com.example.simya.src.ui.view.story
//
//import android.os.Bundle
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityStoryCreateBinding
//import com.example.simya.util.Constants.REQUEST_CODE_FOR_INTENT
//
//class CreateMyStoryActivity: BaseActivity<ActivityStoryCreateBinding>(ActivityStoryCreateBinding::inflate){
//    var fragmentStep: Int = 1
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//    private fun init(){
//        nextFragmentSignUp(1)
//    }
//    fun nextFragmentSignUp(int: Int) {
//        val transaction = supportFragmentManager.beginTransaction()
//        when(int){
//            1 -> {
//                transaction.replace(binding.fmCreateStory.id, CreateMyStoryFragment())
//                fragmentStep = 1
//            }
//            2 -> {
//                transaction.replace(binding.fmCreateStory.id, CreateMyStoryMainMenuFragment())
//                fragmentStep = 2
//            }
//            3 -> {
//                transaction.replace(binding.fmCreateStory.id, CreateMyStoryBorderFragment())
//                fragmentStep = 3
//            }
//
//        }
//        transaction.commit()
//    }
//    fun resultFinish(){
//        setResult(REQUEST_CODE_FOR_INTENT)
//        finish()
//    }
//
//    override fun onBackPressed() {
//        val transaction = supportFragmentManager.beginTransaction()
//        when(fragmentStep){
//            1 ->{
//                finish()
//            }
//            2->{
//                transaction.replace(binding.fmCreateStory.id, CreateMyStoryFragment())
//                fragmentStep = 1
//            }
//            3->{
//                transaction.replace(binding.fmCreateStory.id, CreateMyStoryMainMenuFragment())
//                fragmentStep = 2
//            }
//        }
//        transaction.commit()
//    }
//}