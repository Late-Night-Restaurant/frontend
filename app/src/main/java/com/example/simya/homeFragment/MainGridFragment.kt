package com.example.simya.homeFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simya.Constants
import com.example.simya.Constants.HOUSE_ID
import com.example.simya.Constants.OK
import com.example.simya.activity.OpenMyStoryActivity
import com.example.simya.activity.StoryIntroActivity
import com.example.simya.testData.TestDataBorder
import com.example.simya.databinding.FragmentHomeMainGridBinding
import com.example.simya.adpter.homeAdapter.MainGVAdapter
import com.example.simya.adpter.mystoryAdapter.MyStoryGVAdater
import com.example.simya.data.UserTokenData
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import com.example.simya.server.story.LoadAllStoryResponse
import com.example.simya.server.story.LoadAllStoryResult
import com.example.simya.server.story.LoadMyStoryResponse
import com.example.simya.server.story.LoadMyStoryResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainGridFragment: Fragment() {
    private lateinit var binding: FragmentHomeMainGridBinding
    private var dataList:  ArrayList<LoadAllStoryResult> = arrayListOf()
    private lateinit var dataGVAdapter: MainGVAdapter
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainGridBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        loadAllStory()
    }
    private fun loadAllStory(){
        simyaApi.getAllStory(UserTokenData.accessToken,UserTokenData.refreshToken)
            .enqueue(object: Callback<LoadAllStoryResponse>{
                override fun onResponse(
                    call: Call<LoadAllStoryResponse>,
                    response: Response<LoadAllStoryResponse>
                ) {
                    if(response.body()!!.code == OK){
                        if (response.body()!!.message == "아직 오픈한 이야기 집이 없습니다." || response.body()!!.message == "이야기 집이 없습니다.") {
                            Log.d("이야기집", "아직 오픈한 이야기 집이 없습니다.")
                        }  else {
                            activity!!.runOnUiThread {
                                Log.d("이야기집","이야기 집이 있습니다.")
                                dataList = arrayListOf()
                                dataList.apply {
                                    for (i: Int in 0 until response.body()!!.result.size) {
                                        add(response.body()!!.result[i])
                                        Log.d("이야기집",i.toString())
                                    }
                                }
                                val gridLayoutManager = GridLayoutManager(activity, 2)
                                dataGVAdapter = MainGVAdapter(dataList)
                                binding.gvHomeMainGrid.adapter = dataGVAdapter
                                binding.gvHomeMainGrid.layoutManager = gridLayoutManager
                                clickStory()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<LoadAllStoryResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
    private fun clickStory() {
        dataGVAdapter.setOnItemClickListener(object : MainGVAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: LoadAllStoryResult, position: Int) {
                //뭐보낼까
                Toast.makeText(this@MainGridFragment.context,"${data.houseId}",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainGridFragment.context,StoryIntroActivity::class.java)
                intent.putExtra(HOUSE_ID,data.houseId)
                startActivity(intent)

            }

            override fun onLongClick(v: View, data: LoadAllStoryResult, position: Int) {
                TODO("Not yet implemented")
            }

        })
    }
}