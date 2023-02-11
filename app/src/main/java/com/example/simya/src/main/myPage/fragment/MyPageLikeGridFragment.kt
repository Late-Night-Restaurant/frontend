package com.example.simya.src.main.myPage.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simya.databinding.FragmentMyPageLikeGridBinding
import com.example.simya.util.data.UserData
import com.example.simya.src.main.myPage.adapter.myPageLike.MyPageLikeGVAdapter
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
import com.example.simya.src.testData.TestDataBorder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageLikeGridFragment: Fragment() {
    private lateinit var binding: FragmentMyPageLikeGridBinding
    private lateinit var dataList: ArrayList<TestDataBorder>
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageLikeGridBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        dataList = arrayListOf()
        dataList.apply {
            add(TestDataBorder(3,"마라탕","안드로이드","매워"))
            add(TestDataBorder(1,"닭갈비","서버","달달해"))
            add(TestDataBorder(2,"삼겹살","안드로이드","최고"))
            add(TestDataBorder(4,"곱창","서버","고소해"))
        }
        val dataGVAdapter = MyPageLikeGVAdapter(dataList)
        val gridLayoutManager = GridLayoutManager(this.context, 2)
        binding.gvMyPageLikeGrid.adapter = dataGVAdapter
        binding.gvMyPageLikeGrid.layoutManager = gridLayoutManager
    }
    private fun tryGetMyLikeStory(){
        simyaApi.getMyLikeStory(UserData.accessToken, UserData.refreshToken).enqueue(object:
            Callback<MyLikeStoryResponse> {
            override fun onResponse(
                call: Call<MyLikeStoryResponse>,
                response: Response<MyLikeStoryResponse>
            ) {
//                찜한 리스트 가져오기
            }

            override fun onFailure(call: Call<MyLikeStoryResponse>, t: Throwable) {
                Log.d("Response",t.toString())
            }

        })
    }
}