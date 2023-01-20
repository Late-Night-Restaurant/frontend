package com.example.simya.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.R
import com.example.simya.databinding.DialogOrderBinding

class SortDialog(private val context: AppCompatActivity) {
    private val dialog = Dialog(context)
    private lateinit var binding: DialogOrderBinding
    fun showDia(){
        init()
        orderRecent()
        orderLong()
        orderLike()
        orderCancel()
        dialog.show()
    }

    private fun init(){
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DialogOrderBinding.inflate(context.layoutInflater)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
    }
    private fun orderRecent(){
        binding.btnDialogOrderRecent.setOnClickListener {
            binding.btnDialogOrderRecentCheck.setImageResource(R.drawable.ic_check_on)
            binding.btnDialogOrderLikeCheck.setImageResource(R.drawable.ic_check_off)
            binding.btnDialogOrderLongCheck.setImageResource(R.drawable.ic_check_off)
        }
    }
    private fun orderLike(){
        binding.btnDialogOrderLike.setOnClickListener {
            binding.btnDialogOrderRecentCheck.setImageResource(R.drawable.ic_check_off)
            binding.btnDialogOrderLikeCheck.setImageResource(R.drawable.ic_check_on)
            binding.btnDialogOrderLongCheck.setImageResource(R.drawable.ic_check_off)
        }
    }
    private fun orderLong(){
        binding.btnDialogOrderLong.setOnClickListener {
            binding.btnDialogOrderRecentCheck.setImageResource(R.drawable.ic_check_off)
            binding.btnDialogOrderLikeCheck.setImageResource(R.drawable.ic_check_off)
            binding.btnDialogOrderLongCheck.setImageResource(R.drawable.ic_check_on)
        }
    }
    private fun orderCancel(){
        binding.btnDialogOrderCancle.setOnClickListener {
            dialog.dismiss()
        }
    }
    interface SortDialogClickedListener {
        fun onRecent(content : String)
        fun onLike(context: String)
        fun onLong(context: String)
    }

}