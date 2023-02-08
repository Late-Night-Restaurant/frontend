package com.example.simya.src.activity.story

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.simya.src.Constants.BORDER_MAIN_MENU
import com.example.simya.src.Constants.BORDER_TITLE
import com.example.simya.src.Constants.HOUSE_ID
import com.example.simya.databinding.ActivityDrawerMyStroyOpenBinding
import com.example.simya.util.dialog.CloseDialog

class OpenMyStoryActivity: AppCompatActivity() {
    lateinit var binding: ActivityDrawerMyStroyOpenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerMyStroyOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        val drawerLayout = binding.dlMyStoryOpen
        binding.includedMyStoryOpen.tvRvMainMenu.text = intent.getStringExtra(BORDER_MAIN_MENU)
        binding.includedMyStoryOpen.tvRvTitle.text = intent.getStringExtra(BORDER_TITLE)
        // 드로어 오픈
        binding.includedMyStoryOpen.ibMyStoryOpenDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
            drawerInit()
        }
        // 찜 리스트
        binding.includedMyStoryOpen.btnMyStoryOpenLike.setOnClickListener {
            moveToLikeList()
        }
        // 리뷰 리스트
        binding.includedMyStoryOpen.btnMyStoryOpenReview.setOnClickListener {
            moveToReviewList()
        }
        // 오픈 준비하기기(test)
        binding.includedMyStoryOpen.btnMyStoryOpenReady.setOnClickListener {
            var test = intent.getLongExtra(HOUSE_ID,0)
            moveToOpen(test)
        }
    }

    private fun moveToReviewList(){
        val intent = Intent(this, StoryReviewActivity::class.java)
        startActivity(intent)
    }
    private fun moveToLikeList(){
        val intent = Intent(this, StoryLikeActivity::class.java)
        startActivity(intent)
    }
    private fun moveToOpen(houseId: Long){
        val intent = Intent(this, OpenMyStoryInputActivity::class.java)
        intent.putExtra(BORDER_MAIN_MENU,binding.includedMyStoryOpen.tvRvMainMenu.text)
        intent.putExtra(BORDER_TITLE, binding.includedMyStoryOpen.tvRvTitle.text)
        intent.putExtra(HOUSE_ID,houseId)
        startActivity(intent)
    }
    private fun drawerInit(){
        binding.btnMyStoryOpenBorder.setOnClickListener {
            // 간판수정
        }
        binding.btnMyStoryOpenMainMenu.setOnClickListener {
            // 메인 메뉴 수정
        }
        binding.btnMyStoryClose.setOnClickListener {
            var dialog = CloseDialog(this)
            dialog.showDia()
        }
    }
}
