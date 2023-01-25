package com.example.simya.adpter.chatAdapter

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simya.adpter.createMyStoryAdapter.CreateMyStoryRVAdapter
import com.example.simya.databinding.ItemChatReceiveBinding
import com.example.simya.databinding.ItemChatSendBinding
import com.example.simya.databinding.ItemMultiProfileBinding
import com.example.simya.databinding.ItemReviewBinding
import com.example.simya.testData.TestChatData
import com.example.simya.testData.TestDataMultiProfile

class ChatRVAdapter (private val context: Context, private val dataList:ArrayList<TestChatData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ReceiveDataViewHolder(private val binding: ItemChatReceiveBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TestChatData) {
            binding.tvChatReceiveContent.text = data.content
            binding.tvChatReceiveNick.text = data.user.nick
            Glide.with(context).load(data.user.image).centerCrop().into(binding.civChatReceiveProfile)
        }
    }
    inner class SendDataViewHolder(private val binding: ItemChatSendBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: TestChatData) {
            binding.tvChatSendContent.text = data.content
            binding.tvChatSendNick.text = data.user.nick
            Glide.with(context).load(data.user.image).centerCrop().into(binding.civChatSendProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
           SendDataViewHolder((ItemChatSendBinding.inflate(LayoutInflater.from(parent.context),parent,false)))
        }else{
            ReceiveDataViewHolder(ItemChatReceiveBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1){
            (holder as SendDataViewHolder).bind(dataList[position])
        }else{
            (holder as ReceiveDataViewHolder).bind(dataList[position])
        }
    }


    // 표현할 Item의 총 개수
    override fun getItemCount(): Int= dataList.size

    override fun getItemViewType(position: Int): Int {
        return dataList[position].user.type
    }


}
