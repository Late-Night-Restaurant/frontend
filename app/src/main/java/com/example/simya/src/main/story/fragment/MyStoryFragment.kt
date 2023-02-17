package com.example.simya.src.main.story.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.databinding.FragmentHomeMyStoryMainBinding
import com.example.simya.src.main.story.CreateMyStoryActivity
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.util.Constants
import com.example.simya.util.Constants.REQUEST_CODE_FOR_INTENT
import com.example.simya.util.SampleSnackBar
import java.net.URI


class MyStoryFragment: Fragment() {
    private lateinit var binding: FragmentHomeMyStoryMainBinding
    private var defaultViewType = R.drawable.ic_box_4
    private lateinit var getResult: ActivityResultLauncher<Intent>
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
        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == REQUEST_CODE_FOR_INTENT) {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.fl_my_story_main, MyStoryGridFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                } else {
                    SampleSnackBar.make(binding.root, "이야기 집 생성 실패").show()
                }
            }
        binding.ibMyStoryAdd.setOnClickListener{
            moveToCreateStory()
        }
        //초기 프래그먼트 설정
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_my_story_main, MyStoryGridFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        // View Type 설정
//        binding.ibHomeMyStoryMainViewType.run{
//            setOnClickListener{
//                viewTypeChange()
//                binding.ibHomeMyStoryMainViewType.setImageResource(defaultViewType)
//                when (defaultViewType){
//                    R.drawable.ic_box_4 ->{
//                        childFragmentManager.beginTransaction()
//                            .replace(R.id.fl_my_story_main, MyStoryGridFragment())
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                    }
//                    R.drawable.ic_box_2 ->{
//                        childFragmentManager.beginTransaction()
//                            .replace(R.id.fl_my_story_main, MyStoryRecyclerFragment())
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                    }
//                }
//                true
//            }
//
//        }
    }
    private fun moveToCreateStory(){
        val intent = Intent(this.context, CreateMyStoryActivity::class.java)
        getResult.launch(intent)
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