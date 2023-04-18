//package com.example.simya.src.ui.adapter.story
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.simya.databinding.ItemMyBorderRv328156Binding
//import com.example.simya.src.testData.TestDataBorder
//
//class MyStoryAdapter(private val dataList: ArrayList<TestDataBorder>) :
//    RecyclerView.Adapter<MyStoryAdapter.DataViewHolder>() {
//    inner class DataViewHolder(private val binding: ItemMyBorderRv328156Binding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: TestDataBorder) {
//            binding.tvRvTitle.text = data.title
//            binding.tvRvMainMenu.text = data.mainMenu
//        }
//    }
//
//    //ViewHolder 만들어 질때 실행할 동작
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding =
//            ItemMyBorderRv328156Binding.inflate(LayoutInflater.from(parent.context), parent, false)
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
