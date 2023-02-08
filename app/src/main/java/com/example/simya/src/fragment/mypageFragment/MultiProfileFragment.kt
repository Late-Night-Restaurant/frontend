package com.example.simya.src.fragment.mypageFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simya.databinding.FragmentMyPageMultiProfileBinding
import com.example.simya.src.adpter.home.MultiProfileAdapter
import com.example.simya.src.testData.TestDataMultiProfileMyPage

class MultiProfileFragment: Fragment() {
    private lateinit var binding: FragmentMyPageMultiProfileBinding
    private lateinit var dataList: ArrayList<TestDataMultiProfileMyPage>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageMultiProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        dataList = arrayListOf()
        dataList.apply {
            add(TestDataMultiProfileMyPage(1, "김아린"))
            add(TestDataMultiProfileMyPage(2, "푸"))
            add(TestDataMultiProfileMyPage(3, "초이"))
            add(TestDataMultiProfileMyPage(4, "왁"))
        }
        val dataRVAdpater = MultiProfileAdapter(dataList)
        binding.rvMultiProfileRecycler.adapter = dataRVAdpater
        binding.rvMultiProfileRecycler.layoutManager =
            LinearLayoutManager(this.context,
            RecyclerView.HORIZONTAL,
            false)

    }

}