//package com.example.simya.src.ui.adapter.story
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.simya.R
//import com.example.simya.databinding.ItemMultiProfileBinding
//import com.example.simya.src.model.UserDTO
//
//class CreateMyStoryMultiProfileAdapter(
//    private val context: Context,
//    private val dataList: ArrayList<UserDTO>
//) : RecyclerView.Adapter<CreateMyStoryMultiProfileAdapter.DataViewHolder>() {
//    private var listener: OnItemClickListener? = null
//
//    inner class DataViewHolder(private val binding: ItemMultiProfileBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(data: UserDTO) {
//            Glide.with(context).load(data.picture).placeholder(R.drawable.ic_base_profile).centerCrop()
//                .into(binding.civItemMultiProfile)
//            binding.tvMultiNickname.text = data.nickname
//        }
//    }
//
//    //ViewHolder 만들어 질때 실행할 동작
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding =
//            ItemMultiProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DataViewHolder(binding)
//    }
//
//    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수 , 데이터를 표시할때마다 호출
//    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
//        holder.bind(dataList[position])
//        if(position != RecyclerView.NO_POSITION){
//            holder.itemView.setOnClickListener{
//                listener?.onItemClick(holder.itemView,dataList[position],position)
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
//        fun onItemClick(v: View, data: UserDTO, position: Int)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }
//
//}
