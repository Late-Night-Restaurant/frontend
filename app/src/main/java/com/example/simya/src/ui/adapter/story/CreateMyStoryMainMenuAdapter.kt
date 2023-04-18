//package com.example.simya.src.ui.adapter.story
//
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.simya.util.data.MainMenuData
//import com.example.simya.databinding.ItemMainMenuGvBinding
//
//class CreateMyStoryMainMenuAdapter(
//    private val context: Context,
//    private val dataList: ArrayList<MainMenuData>
//) : RecyclerView.Adapter<CreateMyStoryMainMenuAdapter.DataViewHolder>() {
//    private var listener: OnItemClickListener? = null
//    private  var selectLayout: ConstraintLayout? = null
//    inner class DataViewHolder(private val binding: ItemMainMenuGvBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: MainMenuData) {
//            Glide.with(context).load(data.menuImage).into(binding.ivGvMainMenu)
//            binding.tvGvMainMenu.text = data.menuName
//            Log.d("bind",binding.tvGvMainMenu.text.toString())
//        }
//        fun onSelectLayout(){
//            selectLayout = binding.rlItemMainMenuGv
//            binding.rlItemMainMenuGv.isSelected = true
//        }
//    }
//
//    //ViewHolder 만들어 질때 실행할 동작
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding =
//            ItemMainMenuGvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DataViewHolder(binding)
//    }
//
//    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수 , 데이터를 표시할때마다 호출
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(dataList[position])
//        if (position != RecyclerView.NO_POSITION) {
//            holder.itemView.setOnClickListener {
//                listener?.onItemClick(holder.itemView, dataList[position], position)
//                if(selectLayout ==  null){
//                    holder.onSelectLayout()
//                }else{
//                    selectLayout!!.isSelected = false
//                    holder.onSelectLayout()
//                }
//            }
//        }
//    }
//
//    // 표현할 Item의 총 개수
//    override fun getItemCount(): Int = dataList.size
//    override fun getItemViewType(position: Int): Int {
//        return position
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(v: View, data: MainMenuData, position: Int)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener) {
//        this.listener = listener
//    }
//}