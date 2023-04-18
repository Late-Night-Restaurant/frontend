//package com.example.simya.src.ui.view.story
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.core.os.bundleOf
//import androidx.core.view.isInvisible
//import androidx.fragment.app.setFragmentResult
//import com.example.simya.R
//import com.example.simya.config.BaseFragment
//import com.example.simya.src.ui.adapter.story.CreateMyStoryMainMenuAdapter
//import com.example.simya.util.data.MainMenuData
//import com.example.simya.databinding.FragmentStoryMainMenuBinding
//import com.example.simya.util.data.UserData
//
//class CreateMyStoryMainMenuFragment : BaseFragment<FragmentStoryMainMenuBinding>(
//    FragmentStoryMainMenuBinding::bind,
//    R.layout.fragment_story_main_menu){
//    private val mainMenuData: ArrayList<MainMenuData> = arrayListOf()
//    private lateinit var dataGVAdapter: CreateMyStoryMainMenuAdapter
//    private lateinit var selectMainMenu: String
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        UserData.printAllData()
//        // title init
//        binding.included.tvDefaultLayoutTitle.text = "이야기집 생성"
//        // 메인메뉴 UI 업데이트
//        initMainMenu()
//        // info 버튼 클릭시 메인메뉴가 무엇인지에 대한 설명 글 보이기/안보이기
//        binding.ibMyStoryCreateMainMenuInfo.setOnClickListener {
//            binding.tvMyStoryCreateMainInfo.isInvisible = !binding.tvMyStoryCreateMainInfo.isInvisible
//        }
//        binding.btnMainMenuNext.setOnClickListener {
//            moveToSetBorder()
//        }
//        clickMainMenu()
//    }
//
//    private fun moveToSetBorder() {
//        if (binding.btnMainMenuNext.isEnabled) {
//            setFragmentResult("mainMenu", bundleOf("bundleKeyMainMenu" to selectMainMenu))
//            (activity as CreateMyStoryActivity).nextFragmentSignUp(3)
//        }
//    }
//    private fun initMainMenu() {
//        mainMenuData.apply {
//            add(MainMenuData("사랑", R.drawable.img_menu_love))
//            add(MainMenuData("가족", R.drawable.img_menu_family))
//            add(MainMenuData("인간관계", R.drawable.img_menu_relationship))
//            add(MainMenuData("스트레스", R.drawable.img_menu_stress))
//            add(MainMenuData("취미", R.drawable.img_menu_hobby))
//            add(MainMenuData("문화생활", R.drawable.img_menu_culture))
//        }
//        dataGVAdapter = CreateMyStoryMainMenuAdapter(requireContext(), mainMenuData)
//        binding.gvMyStoryCreateMain.adapter = dataGVAdapter
//    }
//    private fun clickMainMenu(){
//        dataGVAdapter.setOnItemClickListener(object : CreateMyStoryMainMenuAdapter.OnItemClickListener{
//            override fun onItemClick(v: View, data: MainMenuData, position: Int) {
//                selectMainMenu = data.menuName
//                binding.btnMainMenuNext.isEnabled = true
//                Log.d("selectMainMenu", selectMainMenu)
//            }
//        })
//    }
//}
