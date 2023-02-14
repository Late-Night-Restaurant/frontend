package com.example.simya.src.main.story.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.util.Constants
import com.example.simya.util.Constants.BORDER_MAIN_MENU
import com.example.simya.util.Constants.BORDER_TITLE
import com.example.simya.util.Constants.HOUSE_ID
import com.example.simya.databinding.FragmentHomeMainGridBinding
import com.example.simya.src.main.story.OpenMyStoryActivity
import com.example.simya.src.main.story.adapter.mystory.MyStoryGVAdapter
import com.example.simya.src.main.story.model.MyStoryInterface
import com.example.simya.src.main.story.model.MyStoryService
import com.example.simya.src.model.story.load.LoadMyStoryResponse
import com.example.simya.src.model.story.load.LoadMyStoryResult
import com.example.simya.util.SampleSnackBar

class MyStoryGridFragment : BaseFragment<FragmentHomeMainGridBinding>(
    FragmentHomeMainGridBinding::bind,
    R.layout.fragment_home_main_grid
), MyStoryInterface {
    private var dataList: ArrayList<LoadMyStoryResult> = arrayListOf()
    private lateinit var dataGVAdapter: MyStoryGVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        MyStoryService(this).tryGetMyStory()
    }
    private fun clickMyStory() {
        dataGVAdapter.setOnItemClickListener(object : MyStoryGVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: LoadMyStoryResult, position: Int) {
                val intent =
                    Intent(this@MyStoryGridFragment.context, OpenMyStoryActivity::class.java)
                intent.putExtra(BORDER_MAIN_MENU, data.category)
                intent.putExtra(BORDER_TITLE, data.houseName)
                intent.putExtra(HOUSE_ID, data.houseId)
                startActivity(intent)
            }

            override fun onLongClick(v: View, data: LoadMyStoryResult, position: Int) {
                Log.d("STATUS", "onLongClick NO ACTION")
            }

        })
    }

    override fun onGetMyStorySuccess(response: LoadMyStoryResponse) {
        if(response.message == Constants.ERROR_STRING_NULL_ALL_STORY){
            SampleSnackBar.make(binding.root,"생성한 이야기 집이 없습니다.")
        }else{
            requireActivity().runOnUiThread {
                for(i: Int in 0 until response.result.size){
                    dataList.add(response.result[i])
                }
                val gridLayoutManager = GridLayoutManager(activity, 2)
                dataGVAdapter = MyStoryGVAdapter(dataList)
                binding.gvHomeMainGrid.adapter = dataGVAdapter
                binding.gvHomeMainGrid.layoutManager = gridLayoutManager
                clickMyStory()
            }
        }
    }

    override fun onGetMyStoryFailure(response: LoadMyStoryResponse) {
        SampleSnackBar.make(binding.root,"")
    }
}