package com.example.simya.src.ui.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.databinding.FragmentHomeMainGridBinding
import com.example.simya.src.ui.adapter.home.HomeGVAdapter
import com.example.simya.src.main.home.model.AllStoryInterface
import com.example.simya.src.main.home.model.AllStoryService
import com.example.simya.src.model.story.load.LoadAllStoryResponse
import com.example.simya.src.model.story.load.LoadAllStoryResult
import com.example.simya.util.Constants.ERROR_STRING_NULL_ALL_STORY
import com.example.simya.util.Constants.ERROR_STRING_NULL_STORY
import com.example.simya.util.SampleSnackBar

class HomeMainGridFragment : BaseFragment<FragmentHomeMainGridBinding>(
    R.layout.fragment_home_main_grid
), AllStoryInterface {
    private var dataList: ArrayList<LoadAllStoryResult> = arrayListOf()
    private lateinit var dataGVAdapter: HomeGVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        AllStoryService(this).tryGetAllStory()
    }
//    private fun clickStory() {
//        dataGVAdapter.setOnItemClickListener(object : HomeGVAdapter.OnItemClickListener {
//            override fun onItemClick(v: View, data: LoadAllStoryResult, position: Int) {
//                val intent = Intent(requireContext(), StoryIntroActivity::class.java)
//                intent.putExtra(HOUSE_ID, data.houseId)
//                intent.putExtra(BORDER_IMAGE_URL,data.signboardImageUrl)
//                startActivity(intent)
//            }
//            override fun onLongClick(v: View, data: LoadAllStoryResult, position: Int) {
//            }
//
//        })
//    }

    override fun onGetAllStorySuccess(response: LoadAllStoryResponse) {
        Log.d("response check",response.message!!)
        when (response.message) {
            ERROR_STRING_NULL_STORY -> {
                SampleSnackBar.make(binding.root,response.message!!)
            }
            ERROR_STRING_NULL_ALL_STORY -> {
                SampleSnackBar.make(binding.root,response.message!!)
            }
            else -> {
                requireActivity().runOnUiThread {
                    for(i: Int in 0 until response.result.size){
                        dataList.add(response.result[i])
                    }
                    val gridLayoutManager = GridLayoutManager(activity, 2)
                    dataGVAdapter = HomeGVAdapter(requireContext(),dataList)
                    binding.gvHomeMainGrid.adapter = dataGVAdapter
                    binding.gvHomeMainGrid.layoutManager = gridLayoutManager
//                    clickStory()
                }
            }
        }
    }

    override fun onGetAllStoryFailure(response: LoadAllStoryResponse) {
        SampleSnackBar.make(binding.root,response.message!!)
    }

    override fun onGetAllStoryDisconnect(message: String) {
        SampleSnackBar.make(binding.root,message)
        dismissLoadingDialog()
    }
}