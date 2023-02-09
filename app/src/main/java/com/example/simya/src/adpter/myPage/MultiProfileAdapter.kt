package com.example.simya.src.adpter.myPage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.databinding.ItemMultiProfileBinding
import com.example.simya.src.fragment.mypage.MyPageProfileFragment
import com.example.simya.src.testData.TestDataMultiProfile

class MultiProfileAdapter(private val context: Fragment, private val dataList: ArrayList<TestDataMultiProfile>) : RecyclerView.Adapter<MultiProfileAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemMultiProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TestDataMultiProfile) {
            binding.tvMultiNickname.text = data.nickname
            Glide.with(context).load(data.imageSource).into(binding.civItemMultiProfile)
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