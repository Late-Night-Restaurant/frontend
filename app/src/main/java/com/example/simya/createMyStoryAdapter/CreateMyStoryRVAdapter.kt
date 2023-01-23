package com.example.simya.createMyStoryAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.databinding.ItemMultiProfileBinding
import com.example.simya.testData.TestDataMultiProfile

class CreateMyStoryRVAdapter (private val context: Context, private val dataList:ArrayList<TestDataMultiProfile>): RecyclerView.Adapter<CreateMyStoryRVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemMultiProfileBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TestDataMultiProfile) {
            Log.d("data test",data.imageSource.toString())
            Glide.with(context).load(data.imageSource).override(72,72).into(binding.civItemMultiProfile)
        }
    }
    //ViewHolder 만들어 질때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemMultiProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }
    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수 , 데이터를 표시할때마다 호출
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
    // 표현할 Item의 총 개수
    override fun getItemCount(): Int= dataList.size

}
