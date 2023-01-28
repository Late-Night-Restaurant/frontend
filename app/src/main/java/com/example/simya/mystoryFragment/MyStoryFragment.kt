package com.example.simya.mystoryFragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simya.Constants.OK
import com.example.simya.R
import com.example.simya.activity.CreateMyStoryActivity
import com.example.simya.data.UserTokenData
import com.example.simya.databinding.FragmentHomeMyStoryMainBinding
import com.example.simya.server.RetrofitBuilder
import com.example.simya.server.RetrofitService
import com.example.simya.server.story.LoadMyStoryResponse
import kotlinx.coroutines.android.HandlerDispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyStoryFragment: Fragment() {
    private lateinit var binding: FragmentHomeMyStoryMainBinding
    private var defaultViewType = R.drawable.ic_box_4
    private lateinit var uiHandler: Handler
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMyStoryMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.ibMyStoryAdd.setOnClickListener{
            moveToCreateStroy()
        }
        //초기 프래그먼트 설정
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_my_story_main,MyStoryGridFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        // View Type 설정
        binding.ibHomeMyStoryMainViewType.run{
            setOnClickListener{
                viewTypeChange()
                binding.ibHomeMyStoryMainViewType.setImageResource(defaultViewType)
                when (defaultViewType){
                    R.drawable.ic_box_4 ->{
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fl_my_story_main,MyStoryGridFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.drawable.ic_box_2 ->{
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fl_my_story_main, MyStoryRecyclerFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                }
                true
            }

        }
    }

    private fun moveToCreateStroy(){
        val intent = Intent(this.context,CreateMyStoryActivity::class.java)
        startActivity(intent)
    }
    // View Type icon switch
    private fun viewTypeChange(){
        if (defaultViewType == R.drawable.ic_box_4){
            defaultViewType = R.drawable.ic_box_2
        }else{
            defaultViewType = R.drawable.ic_box_4
        }
    }

}