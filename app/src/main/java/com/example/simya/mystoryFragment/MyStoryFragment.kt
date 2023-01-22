package com.example.simya.mystoryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.FragmentHomeMyStoryMainBinding

class MyStoryFragment: Fragment() {
    private lateinit var binding: FragmentHomeMyStoryMainBinding
    private var defaultViewType = R.drawable.ic_box_4
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
    // View Type icon switch
    private fun viewTypeChange(){
        if (defaultViewType == R.drawable.ic_box_4){
            defaultViewType = R.drawable.ic_box_2
        }else{
            defaultViewType = R.drawable.ic_box_4
        }
    }

}