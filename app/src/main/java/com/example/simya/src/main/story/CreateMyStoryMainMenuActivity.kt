package com.example.simya.src.main.story

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.simya.R
import com.example.simya.src.main.story.adapter.createStory.CreateMyStoryMainMenuAdapter
import com.example.simya.util.data.MainMenuData
import com.example.simya.databinding.ActivityStoryMainMenuBinding

class CreateMyStoryMainMenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityStoryMainMenuBinding
    private val mainMenuData: ArrayList<MainMenuData> = arrayListOf()
    private lateinit var dataGVAdapter: CreateMyStoryMainMenuAdapter
    private lateinit var selectMainMenu: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        // title init
        binding.included.tvDefaultLayoutTitle.text = "이야기집 생성"
        // 메인메뉴 UI 업데이트
        initMainMenu()
        // info 버튼 클릭시 메인메뉴가 무엇인지에 대한 설명 글 보이기/안보이기
        binding.ibMyStoryCreateMainMenuInfo.setOnClickListener {
            binding.tvMyStoryCreateMainInfo.isInvisible = !binding.tvMyStoryCreateMainInfo.isInvisible
        }
        binding.btnMainMenuNext.setOnClickListener {
//            moveToSetBorder()
        }
        clickMainMenu()
    }

//    private fun moveToSetBorder() {
//        val profileId = intent.getStringExtra(PROFILE_ID)
//        if (binding.btnMainMenuNext.isEnabled) {
//            Log.d("hold btn text",holdBtn!!.text.toString())
//            val intent = Intent(this, CreateMyStoryBorderActivity::class.java)
//            intent.putExtra(PROFILE_ID,profileId)
//            intent.putExtra(BORDER_MAIN_MENU, holdBtn!!.text.toString())
//            startActivity(intent)
//        }
//    }
    private fun initMainMenu() {
        mainMenuData.apply {
            add(MainMenuData("사랑", R.drawable.img_menu_love))
            add(MainMenuData("가족", R.drawable.img_menu_family))
            add(MainMenuData("인간관계", R.drawable.img_menu_relationship))
            add(MainMenuData("스트레스", R.drawable.img_menu_stress))
            add(MainMenuData("취미", R.drawable.img_menu_hobby))
            add(MainMenuData("문화생활", R.drawable.img_menu_culture))
        }
        dataGVAdapter = CreateMyStoryMainMenuAdapter(this, mainMenuData)
        binding.gvMyStoryCreateMain.adapter = dataGVAdapter
    }
    private fun clickMainMenu(){
        dataGVAdapter.setOnItemClickListener(object : CreateMyStoryMainMenuAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: MainMenuData, position: Int) {
                selectMainMenu = data.menuName
                binding.btnMainMenuNext.isEnabled = true
                Log.d("selectMainMenu", selectMainMenu)
            }
        })
    }
}
