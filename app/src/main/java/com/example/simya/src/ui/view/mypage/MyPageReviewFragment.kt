//package com.example.simya.src.ui.view.mypage
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.simya.databinding.FragmentMyPageReviewRecyclerBinding
//import com.example.simya.util.data.UserData
//import com.example.simya.src.ui.adapter.mypage.MyPageReviewAdapter
//import com.example.simya.src.model.RetrofitBuilder
//import com.example.simya.src.model.RetrofitService
//import com.example.simya.src.model.mypage.review.MyReviewDTO
//import com.example.simya.src.model.mypage.review.MyWriteReviewResponse
//import com.example.simya.util.Constants.OK
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MyPageReviewFragment: Fragment() {
//    private lateinit var binding: FragmentMyPageReviewRecyclerBinding
//    private lateinit var dataList: ArrayList<MyReviewDTO>
//    private lateinit var dataRVAdapter: MyPageReviewAdapter
//    private val retrofit by lazy {
//        RetrofitBuilder.getInstnace()
//    }
//    private val simyaApi by lazy {
//        retrofit.create(RetrofitService::class.java)
//    }
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentMyPageReviewRecyclerBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        dataList = arrayListOf()
//        dataList.apply {
//        }
//
//        dataRVAdapter = MyPageReviewAdapter(dataList)
//        binding.rvMyPageReviewRecycler.adapter = dataRVAdapter
//        binding.rvMyPageReviewRecycler.layoutManager = LinearLayoutManager(this.context)
//
//    }
//    private fun tryGetMyWriteReview(){
//        simyaApi.getMyWriteReview(UserData.accessToken, UserData.refreshToken).enqueue(object:
//            Callback<MyWriteReviewResponse> {
//            override fun onResponse(
//                call: Call<MyWriteReviewResponse>,
//                response: Response<MyWriteReviewResponse>
//            ) {
//                if(response.body()!!.code == OK){
//                    dataList.apply{
//                        for(i: Int in 0 until response.body()!!.result.size){
//                            add(MyReviewDTO(response.body()!!.result[i].myReview.rate,response.body()!!.result[i].myReview.content))
//
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<MyWriteReviewResponse>, t: Throwable) {
//                Log.d("Response",t.toString())
//            }
//
//        })
//    }
//}