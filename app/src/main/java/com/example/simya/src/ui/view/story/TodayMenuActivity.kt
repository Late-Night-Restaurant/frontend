//package com.example.simya.src.ui.view.story
//
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.simya.config.BaseActivity
//import com.example.simya.src.ui.adapter.story.TodayMenuRVAdapter
//import com.example.simya.databinding.ActivityTodayMenuBinding
//import com.example.simya.src.testData.TestDataChip
//
//class TodayMenuActivity :
//    BaseActivity<ActivityTodayMenuBinding>(ActivityTodayMenuBinding::inflate) {
//    lateinit var dataList: ArrayList<TestDataChip>
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        binding.included.tvDefaultLayoutTitle.text = "이야기 집 오늘의 메뉴"
//        dataList = arrayListOf()
//        dataList.apply {
//            add(TestDataChip("데모데이 파이팅!", "???"))
//            add(TestDataChip("심야식당을 만들었습니다.", "???"))
//            add(TestDataChip("어떻게 비슷한 사람들과 만나나요?", "???"))
//            add(TestDataChip("어떻게 솔직한 이야기를 하나요?", "???"))
//            add(TestDataChip("우리들의 이야기", "???"))
//        }
//        val dataRVAdapter = TodayMenuRVAdapter(this, dataList)
//        binding.rvTodayMenu.adapter = dataRVAdapter
//        binding.rvTodayMenu.layoutManager = LinearLayoutManager(this)
//    }
//}