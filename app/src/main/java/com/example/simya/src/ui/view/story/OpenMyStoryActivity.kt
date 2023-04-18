//package com.example.simya.src.ui.view.story
//
//import android.os.Bundle
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityStoryOpenBinding
//import com.example.simya.util.Constants.BORDER_IMAGE_URL
//import com.example.simya.util.Constants.BORDER_MAIN_MENU
//import com.example.simya.util.Constants.BORDER_TITLE
//import com.example.simya.util.Constants.HOUSE_ID
//
//class OpenMyStoryActivity :
//    BaseActivity<ActivityStoryOpenBinding>(ActivityStoryOpenBinding::inflate) {
//    var fragmentStep: Int = 1
//    lateinit var category: String
//    lateinit var houseName: String
//    var houseId = 0L
//    var houseImageUrl: String = "default"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        category = intent.getStringExtra(BORDER_MAIN_MENU)!!
//        houseName = intent.getStringExtra(BORDER_TITLE)!!
//        houseId = intent.getLongExtra(HOUSE_ID, 0L)
//        if(intent.getStringExtra(BORDER_IMAGE_URL) == null){
//            houseImageUrl = "default"
//        }else{
//            houseImageUrl = intent.getStringExtra(BORDER_IMAGE_URL)!!
//        }
//        init()
//    }
//
//    private fun init() {
//
//        nextFragmentSignUp(1)
//    }
//
//    fun nextFragmentSignUp(int: Int) {
//        val transaction = supportFragmentManager.beginTransaction()
//        when (int) {
//            1 -> transaction.replace(binding.fmOpenStory.id, OpenMyStoryFragment())
//            2 -> transaction.replace(binding.fmOpenStory.id, OpenMyStoryInputFragment())
//        }
//        transaction.commit()
//    }
//
//    //    fun resultFinish(){
////        setResult(Constants.REQUEST_CODE_FOR_INTENT)
////        finish()
////    }
//    override fun onBackPressed() {
//        val transaction = supportFragmentManager.beginTransaction()
//        when (fragmentStep) {
//            1 -> {
//                finish()
//            }
//            2 -> {
//                transaction.replace(binding.fmOpenStory.id, OpenMyStoryInputFragment())
//                fragmentStep = 1
//            }
//        }
//        transaction.commit()
//    }
//}