package com.example.simya.adpter.homeAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simya.testData.TestDataBorder
import com.example.simya.databinding.ItemBorderGv156156Binding

class MainGVAdapter (private val dataList:ArrayList<TestDataBorder>): RecyclerView.Adapter<MainGVAdapter.DataViewHolder>() {
    inner class DataViewHolder(private val binding: ItemBorderGv156156Binding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TestDataBorder) {
            binding.tvGvTodayMenu.text = data.todayMenu
            binding.tvGvMainMenu.text = data.mainMenu
            binding.tvGvTitle.text = data.title
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