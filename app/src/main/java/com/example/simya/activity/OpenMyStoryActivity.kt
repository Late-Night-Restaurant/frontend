package com.example.simya.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.simya.databinding.ActivityDrawerMyStroyOpenBinding
import com.example.simya.dialog.CloseDialog
import com.example.simya.testData.BorderData

class OpenMyStoryActivity: AppCompatActivity() {
    lateinit var binding: ActivityDrawerMyStroyOpenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerMyStroyOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val borderData = intent.getSerializableExtra("borderData") as BorderData?

        init(borderData)
    }
    private fun init(borderData: BorderData?){
        val drawerLayout = binding.dlMyStoryOpen
        binding.includedMyStoryOpen.tvRvMainMenu.text = borderData!!.mainMenu
        binding.includedMyStoryOpen.tvRvTitle.text = borderData!!.title
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
        // 오픈 준비하기기
        binding.includedMyStoryOpen.btnMyStoryOpenReady.setOnClickListener {
            moveToOpen()
        }
    }
    private fun getIntentData(){

    }
    private fun moveToReviewList(){
        val intent = Intent(this,StoryReviewActivity::class.java)
        startActivity(intent)
    }
    private fun moveToLikeList(){
        val intent = Intent(this,StoryLikeActivity::class.java)
        startActivity(intent)
    }
    private fun moveToOpen(){
        val intent = Intent(this,OpenMyStoryInputActivity::class.java)
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
