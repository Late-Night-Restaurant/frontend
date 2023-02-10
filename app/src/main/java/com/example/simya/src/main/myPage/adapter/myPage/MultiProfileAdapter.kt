package com.example.simya.src.main.myPage.adapter.myPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.databinding.ItemMypageMultiProfileBinding
import com.example.simya.src.main.home.adapter.HomeGVAdapter
import com.example.simya.src.model.profile.ProfileDTO
import com.example.simya.src.model.story.load.LoadAllStoryResult

class MultiProfileAdapter(private val context: Fragment, private val dataList: ArrayList<ProfileDTO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listener: OnItemClickListener? = null
    inner class DataViewHolder(private val binding: ItemMypageMultiProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProfileDTO) {
            binding.tvItemMyStoryMultiProfile.text = data.nickname
            Glide.with(context).load(data.picture).placeholder(R.drawable.bg_profile).into(binding.civItemMyStoryMultiProfile)
        }
    }
    inner class AddViewHolder(private val binding: ItemMypageMultiProfileBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.tvItemMyStoryMultiProfile.text = "추가하기"
            Glide.with(context).load(R.drawable.ic_baseline_add_24).into(binding.civItemMyStoryMultiProfile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0){
            val binding = ItemMypageMultiProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AddViewHolder(binding)
        }else{
            val binding = ItemMypageMultiProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            DataViewHolder(binding)
        }
    }


    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0){
            (holder as AddViewHolder).bind()
        }else{
            (holder as DataViewHolder).bind(dataList[position])
        }
    }
    interface OnItemClickListener {
        fun onItemClick(v: View, data: ProfileDTO, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}