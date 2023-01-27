package com.example.simya.homeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simya.databinding.ItemMultiProfileBinding
import com.example.simya.testData.TestDataMultiProfileMyPage

class MultiProfileAdapter(private val dataList: ArrayList<TestDataMultiProfileMyPage>) : RecyclerView.Adapter<MultiProfileAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemMultiProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TestDataMultiProfileMyPage) {
            binding.tvMultiNickname.text = data.nick
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemMultiProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

}