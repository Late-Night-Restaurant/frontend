package com.example.simya.src.main.myPage.adapter.myPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simya.databinding.ItemReviewBinding
import com.example.simya.src.testData.TestDataReview

class MyPageReviewAdapter(private val dataList: ArrayList<TestDataReview>) : RecyclerView.Adapter<MyPageReviewAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: TestDataReview) {
            binding.tvItemReviewNick.text = data.reviewNick
            binding.tvItemReviewLevel.text = data.reviewLevel.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}