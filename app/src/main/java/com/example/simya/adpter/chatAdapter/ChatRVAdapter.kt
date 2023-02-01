package com.example.simya.adpter.chatAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simya.Constants.CHAT_NOTIFY
import com.example.simya.Constants.CHAT_OTHERS
import com.example.simya.Constants.CHAT_SELF
import com.example.simya.Constants.DEFAULT
import com.example.simya.Constants.UNKNOWN
import com.example.simya.data.ChatRVData
import com.example.simya.databinding.ItemChatNotifyBinding
import com.example.simya.databinding.ItemChatReceiveBinding
import com.example.simya.databinding.ItemChatSendBinding
import com.example.simya.databinding.ItemDrawerProfileBinding
import com.example.simya.testData.TestChatData

class ChatRVAdapter (private val context: Context, private val dataList:ArrayList<ChatRVData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ReceiveDataViewHolder(private val binding: ItemChatReceiveBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ChatRVData) {
            binding.tvChatReceiveContent.text = data.message
            binding.tvChatReceiveNick.text = data.sender
//            Glide.with(context).load(data.picture).centerCrop().into(binding.civChatReceiveProfile)
        }
    }
    inner class SendDataViewHolder(private val binding: ItemChatSendBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ChatRVData) {
            binding.tvChatSendContent.text = data.message
            binding.tvChatSendNick.text = data.sender
//            Glide.with(context).load(data.user.image).centerCrop().into(binding.civChatSendProfile)
        }
    }
    inner class NotifyDataViewHolder(private val binding: ItemChatNotifyBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ChatRVData) {
            binding.tvChatNotifyEnter.text = data.message
//            Glide.with(context).load(data.user.image).centerCrop().into(binding.civChatSendProfile)
        }
    }
    // test return if를 3개로 나누어서 CHAT_SELF , CHAT_OTHERS , ERROR
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (dataList[viewType].userType) {
            CHAT_SELF -> {
                return SendDataViewHolder((ItemChatSendBinding.inflate(LayoutInflater.from(parent.context),parent,false)))
            }
            CHAT_OTHERS -> {
                return ReceiveDataViewHolder(ItemChatReceiveBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            CHAT_NOTIFY -> {
                return NotifyDataViewHolder(ItemChatNotifyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
        //조치 취해야함
        return SendDataViewHolder((ItemChatSendBinding.inflate(LayoutInflater.from(parent.context),parent,false)))
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (dataList[position].userType == CHAT_SELF){
            (holder as SendDataViewHolder).bind(dataList[position])
        }else if(dataList[position].userType == CHAT_OTHERS){
            (holder as ReceiveDataViewHolder).bind(dataList[position])
        }else if(dataList[position].userType == CHAT_NOTIFY){
            (holder as NotifyDataViewHolder).bind(dataList[position])
        }
    }

    override fun getItemCount(): Int= dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
