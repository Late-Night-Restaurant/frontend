package com.example.simya.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simya.Constants
import com.example.simya.Constants.BORDER_MAIN_MENU
import com.example.simya.Constants.PROFILE_ID
import com.example.simya.R
import com.example.simya.adpter.createMyStoryAdapter.CreateMyStoryMainMenuAdapter
import com.example.simya.adpter.storyAdapter.StoryLikeGVAdapter
import com.example.simya.data.MainMenuData
import com.example.simya.databinding.ActivityStoryMainMenuBinding

class CreateMyStoryMainMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoryMainMenuBinding
    private val mainMenuData: ArrayList<MainMenuData> = arrayListOf()
    var holdBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initMainMenu()
        val dataGVAdapter = CreateMyStoryMainMenuAdapter(this,mainMenuData)
        binding.gvMyStoryCreateMain.adapter = dataGVAdapter
        binding.included.tvDefaultLayoutTitle.text = "이야기집 생성"
        binding.ibMyStoryCreateMainMenuInfo.setOnClickListener {
            binding.tvMyStoryCreateMainInfo.isInvisible = !binding.tvMyStoryCreateMainInfo.isInvisible
        }
        binding.btnMainMenuNext.setOnClickListener {
            moveToSetBorder()
        }
    }

    private fun moveToSetBorder() {
        val profileId = intent.getStringExtra(PROFILE_ID)
        if (binding.btnMainMenuNext.isEnabled) {
            Log.d("hold btn text",holdBtn!!.text.toString())
            val intent = Intent(this, CreateMyStoryBorderActivity::class.java)
            intent.putExtra(PROFILE_ID,profileId)
            intent.putExtra(BORDER_MAIN_MENU, holdBtn!!.text.toString())
            startActivity(intent)
        }
    }
    private fun initMainMenu(){
        mainMenuData.apply{
            add(MainMenuData("사랑",R.drawable.img_menu_love))
            add(MainMenuData("가족",R.drawable.img_menu_family))
            add(MainMenuData("인간관계",R.drawable.img_menu_relationship))
            add(MainMenuData("스트레스",R.drawable.img_menu_stress))
            add(MainMenuData("취미",R.drawable.img_menu_hobby))
            add(MainMenuData("문화생활",R.drawable.img_menu_culture))
        }
    }

//    private fun buttonSingleSelected() {
//        binding.btnMainMenuLove.setOnClickListener(this)
//        binding.btnMainMenuFamily.setOnClickListener(this)
//        binding.btnMainMenuRelationship.setOnClickListener(this)
//        binding.btnMainMenuStress.setOnClickListener(this)
//        binding.btnMainMenuHobby.setOnClickListener(this)
//        binding.btnMainMenuCulture.setOnClickListener(this)
//    }

//    private fun btnHighlighted(selectBtn: Button, preBtn: Button?) {
//        selectBtn.isSelected = selectBtn?.isSelected != true
//        preBtn?.isSelected = preBtn?.isSelected != true
//        holdBtn = selectBtn
//        nextButtonEnabled()
//    }

//    private fun nextButtonEnabled() {
//        Log.d("nextButtonEnabled", "호출")
//        binding.btnMainMenuNext.setBackgroundResource(R.drawable.low_radius_button_on)
//        binding.btnMainMenuNext.setTextColor(application.resources.getColor(R.color.Gray_03))
//        binding.btnMainMenuNext.isEnabled = true
//        binding.btnMainMenuNext.isClickable = true
//    }

}
