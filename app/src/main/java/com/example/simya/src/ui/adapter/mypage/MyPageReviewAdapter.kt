//package com.example.simya.src.ui.adapter.mypage
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.simya.databinding.ItemMyReviewBinding
//import com.example.simya.src.model.mypage.review.MyReviewDTO
//
//class MyPageReviewAdapter(private val dataList: ArrayList<MyReviewDTO>) :
//    RecyclerView.Adapter<MyPageReviewAdapter.DataViewHolder>() {
//    inner class DataViewHolder(private val binding: ItemMyReviewBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: MyReviewDTO) {
//            binding.tvItemMyReviewContent.text = data.content
//            binding.tvItemMyReviewLevel.text = data.rate.toString()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding =
//            ItemMyReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DataViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(dataList[position])
//    }
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }
//}