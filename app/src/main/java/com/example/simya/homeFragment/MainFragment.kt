package com.example.simya.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.FragmentHomeMainBinding

class MainFragment: Fragment() {
    private lateinit var binding: FragmentHomeMainBinding
    private var defaultViewType = R.drawable.ic_box_4

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        // 기본 View type, Sort type 정의
        childFragmentManager.beginTransaction()
            .replace(R.id.fm_home_main, MainGridFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        // View type 바꾸기
        binding.ibHomeMainViewType.run{
            setOnClickListener{
                viewTypeChange()
                binding.ibHomeMainViewType.setImageResource(defaultViewType)
                when (defaultViewType){
                    R.drawable.ic_box_4 ->{
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fm_home_main, MainGridFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.drawable.ic_box_2 ->{
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fm_home_main, MainRecyclerFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                }
                true
            }

        }
    }
//    보기방식 바꾸는 메소드
    private fun viewTypeChange(){
        if (defaultViewType == R.drawable.ic_box_4){
            defaultViewType = R.drawable.ic_box_2
        }else{
            defaultViewType = R.drawable.ic_box_4
        }
    }
}