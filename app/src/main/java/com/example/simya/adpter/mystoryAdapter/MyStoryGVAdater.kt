package com.example.simya.adpter.mystoryAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simya.databinding.FragmentHomeMainGridBinding
import com.example.simya.databinding.ItemBorderGv156156Binding
import com.example.simya.adpter.homeAdapter.MainGVAdapter
import com.example.simya.testData.TestDataBorder

class MyStoryGVAdater(private val dataList:ArrayList<TestDataBorder>): RecyclerView.Adapter<MyStoryGVAdater.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemBorderGv156156Binding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TestDataBorder) {
            binding.tvGvTodayMenu.text = data.todayMenu
            binding.tvGvmainMenu.text = data.mainMenu
            binding.tvGvtitle.text = data.title
        }
    }
    //ViewHolder 만들어 질때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemBorderGv156156Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }
    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수 , 데이터를 표시할때마다 호출
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
    // 표현할 Item의 총 개수
    override fun getItemCount(): Int= dataList.size

}