//package com.example.simya.src.ui.adapter.story
//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.simya.databinding.ItemTodayMenuChipBinding
//import com.example.simya.src.testData.TestDataChip
//
//class TodayMenuRVAdapter(
//    private val context: Context,
//    private val dataList: ArrayList<TestDataChip>
//) : RecyclerView.Adapter<TodayMenuRVAdapter.DataViewHolder>() {
//    inner class DataViewHolder(private val binding: ItemTodayMenuChipBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: TestDataChip) {
//            binding.btnItemTodayMenuChip.text = data.title
//            binding.btnItemTodayMenuChip.setOnClickListener {
//                Log.d("클릭타이틀", data.title)
//                binding.btnItemTodayMenuChip.isSelected = binding.btnItemTodayMenuChip.isSelected != true
//            }
//        }
//    }
//
//    //ViewHolder 만들어 질때 실행할 동작
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding =
//            ItemTodayMenuChipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DataViewHolder(binding)
//    }
//
//    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수 , 데이터를 표시할때마다 호출
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(dataList[position])
//    }
//
//    // 표현할 Item의 총 개수
//    override fun getItemCount(): Int = dataList.size
//
//}
