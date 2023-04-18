//package com.example.simya.src.ui.adapter.mypage
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.simya.databinding.ItemBorderRv328156Binding
//import com.example.simya.util.data.BorderData
//
//class MyPageLikeRVAdapter(private val dataList: ArrayList<BorderData>) :
//    RecyclerView.Adapter<MyPageLikeRVAdapter.DataViewHolder>() {
//    inner class DataViewHolder(private val binding: ItemBorderRv328156Binding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: BorderData) {
////            binding.tvRvTodayMenu.text = ""
//            binding.tvRvMainMenu.text = data.category
//            binding.tvRvTitle.text = data.houseName
//        }
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): DataViewHolder {
//        val binding =
//            ItemBorderRv328156Binding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DataViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(dataList[position])
//    }
//
//    override fun getItemCount(): Int = dataList.size
//}