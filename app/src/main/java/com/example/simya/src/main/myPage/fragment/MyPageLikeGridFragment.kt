package com.example.simya.src.main.myPage.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.databinding.FragmentHomeMyPageBinding
import com.example.simya.databinding.FragmentMyPageLikeGridBinding
import com.example.simya.util.data.UserData
import com.example.simya.src.main.myPage.adapter.myPageLike.MyPageLikeGVAdapter
import com.example.simya.src.main.myPage.model.MyPageLikeHouseInterface
import com.example.simya.src.main.myPage.model.MyPageLikeHouseService
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
import com.example.simya.src.testData.TestDataBorder
import com.example.simya.util.data.BorderData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageLikeGridFragment : BaseFragment<FragmentMyPageLikeGridBinding>(
    FragmentMyPageLikeGridBinding::bind,
    R.layout.fragment_my_page_like_grid
), MyPageLikeHouseInterface {
    private var dataList: ArrayList<BorderData> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        MyPageLikeHouseService(this).tryGetGetMyLikeHouse()
    }

    private fun init() {
    }

    override fun onGetMyLikeHouseSuccess(response: MyLikeStoryResponse) {
        requireActivity().runOnUiThread{
            dataList.apply {
                for (i: Int in 0 until response.result.size) {
                    add(
                        BorderData(
                            response.result[i].favoriteHouse.houseId,
                            response.result[i].favoriteHouse.category,
                            response.result[i].favoriteHouse.signboardImageUrl,
                            response.result[i].favoriteHouse.houseName,
                            response.result[i].favoriteHouse.comment,
                            response.result[i].favoriteHouse.todayTopicTitle
                        )
                    )
                }
            }
            val dataGVAdapter = MyPageLikeGVAdapter(dataList)
            val gridLayoutManager = GridLayoutManager(this.context, 2)
            binding.gvMyPageLikeGrid.adapter = dataGVAdapter
            binding.gvMyPageLikeGrid.layoutManager = gridLayoutManager
            Log.d("onGetMyLikeHouseSuccess","true")
        }
    }

    override fun onGetMyLikeHouseFailure(response: MyLikeStoryResponse) {
        Log.d("@@@@@ CHECK @@@@@@", "찜한이야기집 가져오기 실패")
    }

}