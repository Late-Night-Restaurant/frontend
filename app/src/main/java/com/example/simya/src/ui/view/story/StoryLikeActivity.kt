//package com.example.simya.src.ui.view.story
//
//import android.os.Bundle
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.simya.R
//import com.example.simya.config.BaseActivity
//import com.example.simya.src.ui.adapter.story.StoryLikeGVAdapter
//import com.example.simya.databinding.ActivityStoryLikeListBinding
//import com.example.simya.src.testData.TestDataLike
//
//class StoryLikeActivity : BaseActivity<ActivityStoryLikeListBinding>(ActivityStoryLikeListBinding::inflate)
//{
//    lateinit var dataList: ArrayList<TestDataLike>
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//    }
//    private fun init(){
//        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집의 찜한 손님"
//        dataList = arrayListOf()
//        dataList.apply{
//            add(TestDataLike("왁",R.drawable.test_wak))
//            add(TestDataLike("초이",R.drawable.test_choi))
//            add(TestDataLike("햇반",R.drawable.test_hatban))
//            add(TestDataLike("푸",R.drawable.test_poo))
//            add(TestDataLike("쭈니",R.drawable.test_jooni))
//        }
//        val dataGVAdapter = StoryLikeGVAdapter(this,dataList)
//        val gridLayoutManager = GridLayoutManager(this,4)
//        binding.rvStoryLikeList.adapter = dataGVAdapter
//        binding.rvStoryLikeList.layoutManager = gridLayoutManager
//    }
//}