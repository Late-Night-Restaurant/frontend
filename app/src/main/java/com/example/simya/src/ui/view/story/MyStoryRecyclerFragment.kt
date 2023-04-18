//package com.example.simya.src.ui.view.story
//
//import android.os.Bundle
//import android.view.View
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.simya.R
//import com.example.simya.config.BaseFragment
//import com.example.simya.databinding.FragmentHomeMainRecyclerBinding
//import com.example.simya.src.ui.adapter.story.MyStoryAdapter
//import com.example.simya.src.testData.TestDataBorder
//
//class MyStoryRecyclerFragment: BaseFragment<FragmentHomeMainRecyclerBinding>(
//    FragmentHomeMainRecyclerBinding::bind,
//    R.layout.fragment_home_main_recycler) {
//    private lateinit var dataList: ArrayList<TestDataBorder>
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//    }
//    private fun init(){
////        loadMyStory()
//        dataList = arrayListOf()
//        dataList.apply{
//        }
//        val dataRVAdapter = MyStoryAdapter(dataList)
//        binding.rvHomeMainRecycler.adapter = dataRVAdapter
//        binding.rvHomeMainRecycler.layoutManager = LinearLayoutManager(this.context)
//    }
////    private fun loadMyStory(){
////        simyaApi.getMyStory(UserData.accessToken, UserData.getUserRefreshToken()).enqueue(object:
////            Callback<LoadMyStoryResponse> {
////            override fun onResponse(
////                call: Call<LoadMyStoryResponse>,
////                response: Response<LoadMyStoryResponse>
////            ) {
////                if(response.code() == Constants.OK){
////                    Log.d("Response",response.body().toString())
////                }
////            }
////
////            override fun onFailure(call: Call<LoadMyStoryResponse>, t: Throwable) {
////                Log.d("ERROR",t.toString())
////            }
////
////        })
////    }
////    private fun toResultBorderData(receivedata: List<LoadMyStoryResult>){
////        for(i: Int in 0..receivedata.size)
////        {
////            TestDataBorder(receivedata[i].houseId,receivedata[i].signboardImageUrl,
////                receivedata[i].houseName,
////                receivedata[i].category)
////        }
////
////    }
//}