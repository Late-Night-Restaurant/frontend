//package com.example.simya.src.ui.adapter.chat
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.simya.databinding.ItemDrawerProfileBinding
//import com.example.simya.src.model.UserDTO
//import com.example.simya.util.Constants.S3_URL
//
//class ChatDrawerRVAdapter (private val context: Context, private val dataList:ArrayList<UserDTO>): RecyclerView.Adapter<ChatDrawerRVAdapter.DataViewHolder>() {
//    private var listener: OnItemClickListener? = null
//    inner class DataViewHolder(private val binding: ItemDrawerProfileBinding): RecyclerView.ViewHolder(binding.root){
//        fun bind(data: UserDTO) {
//            Glide.with(context).load(S3_URL+data.picture).centerCrop().into(binding.civItemDrawerProfile)
//            binding.tvItemLikeNick.text = data.nickname
//        }
//    }
//    //ViewHolder 만들어 질때 실행할 동작
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
//        val binding = ItemDrawerProfileBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
//    interface OnItemClickListener {
//        fun onItemClick(data: UserDTO, position: Int)
//    }
//
//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }
//
//}
