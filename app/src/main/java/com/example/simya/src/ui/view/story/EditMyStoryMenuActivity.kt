package com.example.simya.src.ui.view.story//package com.example.simya.src.main.story
//
//import android.os.Bundle
//import android.widget.Button
//import androidx.core.view.isInvisible
//import com.example.simya.R
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityStoryMainMenuBinding
//
//class EditMyStoryMenuActivity :
//    BaseActivity<ActivityStoryMainMenuBinding>(ActivityStoryMainMenuBinding::inflate) {
//    var holdBtn: Button? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        // 메인 메뉴 수정 초기화
//        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집 전문 메뉴 수정하기"
//        binding.btnMainMenuNext.text = "수정하기"
//        binding.tvMyStoryCreateMainInfo.isInvisible = true
//        binding.ibMyStoryCreateMainMenuInfo.isInvisible = true
//
//        // 기존 전문 메뉴 가져와야함
//        // 기존 메뉴 Selection 하기
//        binding.ibMyStoryCreateMainMenuInfo.setOnClickListener {
//            binding.tvMyStoryCreateMainInfo.isInvisible = false
//        }
//        binding.btnMainMenuNext.setOnClickListener {
//            mainMenuModify()
//        }
////        buttonSingleSelected()
//    }
//
//    //    private fun buttonSingleSelected() {
////        binding.btnMainMenuLove.setOnClickListener(this)
////        binding.btnMainMenuFamily.setOnClickListener(this)
////        binding.btnMainMenuRelationship.setOnClickListener(this)
////        binding.btnMainMenuStress.setOnClickListener(this)
////        binding.btnMainMenuHobby.setOnClickListener(this)
////        binding.btnMainMenuCulture.setOnClickListener(this)
////    }
//    private fun mainMenuModify() {
//        binding.btnMainMenuNext.setBackgroundResource(R.drawable.low_radius_button_off)
//        binding.btnMainMenuNext.setTextColor(application.resources.getColor(R.color.Gray_10))
//        binding.btnMainMenuNext.text = "수정완료"
//    }
//
//    private fun btnHighlighted(selectBtn: Button, preBtn: Button?) {
//        selectBtn.isSelected = selectBtn?.isSelected != true
//        preBtn?.isSelected = preBtn?.isSelected != true
//        holdBtn = selectBtn
//        nextButtonEnabled()
//    }
//
//    private fun nextButtonEnabled() {
//        binding.btnMainMenuNext.setBackgroundResource(R.drawable.low_radius_button_on)
//        binding.btnMainMenuNext.setTextColor(application.resources.getColor(R.color.Gray_03))
//        binding.btnMainMenuNext.text = "수정하기"
//        binding.btnMainMenuNext.isEnabled = true
//    }
////    override fun onClick(view: View?) {
////        if(view !=null){
////            when(view.id){
////                R.id.btn_main_menu_love -> btnHighlighted(binding.btnMainMenuLove,holdBtn)
////                R.id.btn_main_menu_family -> btnHighlighted(binding.btnMainMenuFamily,holdBtn)
////                R.id.btn_main_menu_culture -> btnHighlighted(binding.btnMainMenuCulture,holdBtn)
////                R.id.btn_main_menu_hobby-> btnHighlighted(binding.btnMainMenuHobby,holdBtn)
////                R.id.btn_main_menu_stress -> btnHighlighted(binding.btnMainMenuStress,holdBtn)
////                R.id.btn_main_menu_relationship -> btnHighlighted(binding.btnMainMenuRelationship,holdBtn)
////            }
////        }
////    }
//}
