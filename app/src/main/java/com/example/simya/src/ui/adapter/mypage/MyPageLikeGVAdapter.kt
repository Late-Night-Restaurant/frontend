//package com.example.simya.src.ui.adapter.mypage
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.simya.databinding.ItemBorderGv156156Binding
//import com.example.simya.util.data.BorderData
//
//class MyPageLikeGVAdapter(private val dataList: ArrayList<BorderData>) :
//    RecyclerView.Adapter<MyPageLikeGVAdapter.DataViewHolder>() {
//    inner class DataViewHolder(private val binding: ItemBorderGv156156Binding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: BorderData) {
////            binding.tvGvTodayMenu.text = ""
//            binding.tvGvMainMenu.text = data.category
//            binding.tvGvTitle.text = data.houseName
//        }
//    }
//
//    // ViewHolder 만들어질때 실행할 동작
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding =
//            ItemBorderGv156156Binding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DataViewHolder(binding)
//    }
//
//    // 실제로 데이터를 표시해야할 때 호출되는 함수
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(dataList[position])
//    }
//
//    override fun getItemCount(): Int = dataList.size
//
//}