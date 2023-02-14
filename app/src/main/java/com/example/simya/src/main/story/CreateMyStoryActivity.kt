package com.example.simya.src.main.story

import android.os.Bundle
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityStoryCreateBinding
import com.example.simya.src.main.story.fragment.CreateMyStoryBorderFragment
import com.example.simya.src.main.story.fragment.CreateMyStoryFragment
import com.example.simya.src.main.story.fragment.CreateMyStoryMainMenuFragment

class CreateMyStoryActivity: BaseActivity<ActivityStoryCreateBinding>(ActivityStoryCreateBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    private fun init(){
        nextFragmentSignUp(1)
    }
    fun nextFragmentSignUp(int: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> transaction.replace(binding.fmCreateStory.id,CreateMyStoryFragment())
            2 -> transaction.replace(binding.fmCreateStory.id,CreateMyStoryMainMenuFragment())
            3 -> transaction.replace(binding.fmCreateStory.id,CreateMyStoryBorderFragment())
        }
        transaction.commit()
    }
}