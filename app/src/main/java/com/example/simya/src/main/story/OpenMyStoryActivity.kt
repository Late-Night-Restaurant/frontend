package com.example.simya.src.main.story

import android.os.Bundle
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityStoryCreateBinding
import com.example.simya.databinding.ActivityStoryOpenBinding
import com.example.simya.src.main.story.fragment.CreateMyStoryBorderFragment
import com.example.simya.src.main.story.fragment.CreateMyStoryFragment
import com.example.simya.src.main.story.fragment.CreateMyStoryMainMenuFragment
import com.example.simya.src.main.story.fragment.OpenMyStoryFragment
import com.example.simya.util.Constants
import com.example.simya.util.Constants.BORDER_MAIN_MENU
import com.example.simya.util.Constants.BORDER_TITLE
import com.example.simya.util.Constants.HOUSE_ID

class OpenMyStoryActivity:  BaseActivity<ActivityStoryOpenBinding>(ActivityStoryOpenBinding::inflate){
    lateinit var category: String
    lateinit var houseName: String
    var houseId = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = intent.getStringExtra(BORDER_MAIN_MENU)!!
        houseName= intent.getStringExtra(BORDER_TITLE)!!
        houseId = intent.getLongExtra(HOUSE_ID,0L)
        init()
    }
    private fun init(){
        nextFragmentSignUp(1)
    }
    fun nextFragmentSignUp(int: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> transaction.replace(binding.fmOpenStory.id, OpenMyStoryFragment())
            2 -> transaction.replace(binding.fmOpenStory.id, CreateMyStoryMainMenuFragment())
        }
        transaction.commit()
    }
//    fun resultFinish(){
//        setResult(Constants.REQUEST_CODE_FOR_INTENT)
//        finish()
//    }
}