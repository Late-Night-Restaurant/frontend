package com.example.simya.src.ui.view.story//package com.example.simya.src.main.story
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.isInvisible
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityStoryCreateBorderBinding
//
//class EditMyStoryBorderActivity : BaseActivity<ActivityStoryCreateBordzerBinding>(ActivityStoryCreateBorderBinding::inflate)
//{
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//    private fun init(){
//        //  초기화
//        binding.included.tvDefaultLayoutTitle.text ="내 이야기 집 간판"
//        binding.ibMyStoryCreateBorderInfo.isInvisible = true
//        binding.tvMyStoryCreateMainInfo.isInvisible = true
//
//        // 기존데이터 가져와서 edittext에 넣기
//
//        binding.btnMyStoryCreateBorderNext.setOnClickListener{
//            binding.btnMyStoryCreateBorderNext.isEnabled = false
//            binding.btnMyStoryCreateBorderNext.text = "수정완료"
//        }
//    }
//}