//package com.example.simya.src.ui.adapter.home
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.simya.databinding.ItemReviewBinding
//import com.example.simya.src.testData.TestDataReview
//
//class StoryReviewRVAapter (private val dataList:ArrayList<TestDataReview>): RecyclerView.Adapter<StoryReviewRVAapter.DataViewHolder>() {
//    inner class DataViewHolder(private val binding: ItemReviewBinding): RecyclerView.ViewHolder(binding.root){
//        fun bind(data: TestDataReview) {
//            binding.tvItemReviewNick.text = data.reviewNick
//            binding.tvItemReviewContent.text = data.reviewContent
//            binding.tvItemReviewLevel.text = data.reviewLevel.toString()
//        }
//    }
//    //ViewHolder 만들어 질때 실행할 동작
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return DataViewHolder(binding)
//    }
//    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수 , 데이터를 표시할때마다 호출
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(dataList[position])
//    }
//    // 표현할 Item의 총 개수
//    override fun getItemCount(): Int= dataList.size
//
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }
//
//}
