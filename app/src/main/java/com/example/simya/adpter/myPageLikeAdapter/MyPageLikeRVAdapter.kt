package com.example.simya.adpter.myPageLikeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simya.databinding.ItemBorderRv328156Binding
import com.example.simya.testData.TestDataBorder

class MyPageLikeRVAdapter (private val dataList: ArrayList<TestDataBorder>): RecyclerView.Adapter<MyPageLikeRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemBorderRv328156Binding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TestDataBorder) {
            binding.tvRvTodayMenu.text = data.todayMenu
            binding.tvRvTodayMenu.text = data.mainMenu
            binding.tvRvTitle.text = data.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        val binding = ItemBorderRv328156Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int= dataList.size
}