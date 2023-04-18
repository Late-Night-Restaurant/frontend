//package com.example.simya.src.ui.view.mypage
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.simya.R
//import com.example.simya.config.BaseFragment
//import com.example.simya.databinding.FragmentMyPageLikeGridBinding
//import com.example.simya.src.ui.adapter.mypage.MyPageLikeGVAdapter
//import com.example.simya.src.main.myPage.model.MyPageLikeHouseInterface
//import com.example.simya.src.main.myPage.model.MyPageLikeHouseService
//import com.example.simya.src.model.mypage.like.MyLikeStoryResponse
//import com.example.simya.util.SampleSnackBar
//import com.example.simya.util.data.BorderData
//
//class MyPageLikeGridFragment : BaseFragment<FragmentMyPageLikeGridBinding>(
//    FragmentMyPageLikeGridBinding::bind,
//    R.layout.fragment_my_page_like_grid
//), MyPageLikeHouseInterface {
//    private var dataList: ArrayList<BorderData> = arrayListOf()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//        MyPageLikeHouseService(this).tryGetGetMyLikeHouse()
//    }
//
//    private fun init() {
//    }
//
//    override fun onGetMyLikeHouseSuccess(response: MyLikeStoryResponse) {
//        requireActivity().runOnUiThread{
//            dataList.apply {
//                for (i: Int in 0 until response.result.size) {
//                    add(
//                        BorderData(
//                            response.result[i].favoriteHouse.houseId,
//                            response.result[i].favoriteHouse.category,
//                            response.result[i].favoriteHouse.signboardImageUrl,
//                            response.result[i].favoriteHouse.houseName,
//                            response.result[i].favoriteHouse.comment,
//                            response.result[i].favoriteHouse.todayTopicTitle
//                        )
//                    )
//                }
//            }
//            val dataGVAdapter = MyPageLikeGVAdapter(dataList)
//            val gridLayoutManager = GridLayoutManager(this.context, 2)
//            binding.gvMyPageLikeGrid.adapter = dataGVAdapter
//            binding.gvMyPageLikeGrid.layoutManager = gridLayoutManager
//            Log.d("onGetMyLikeHouseSuccess","true")
//        }
//    }
//
//    override fun onGetMyLikeHouseFailure(response: MyLikeStoryResponse) {
//        SampleSnackBar.make(binding.root,response.message!!)
//
//    }
//
//    override fun onGetMyLikeHouseDisconnect(message: String) {
//        SampleSnackBar.make(binding.root,message)
//        dismissLoadingDialog()
//    }
//
//}