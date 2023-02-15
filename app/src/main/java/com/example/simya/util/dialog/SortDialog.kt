package com.example.simya.util.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.util.Constants
import com.example.simya.R
import com.example.simya.databinding.DialogOrderBinding

class SortDialog(private val context: AppCompatActivity) : View.OnClickListener {
    private val dialog = Dialog(context)
    private lateinit var binding: DialogOrderBinding
    private var listener: SortDialogClickedListener? = null
    fun showDia() {
        init()
        dialog.show()
    }
    private fun init() {
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DialogOrderBinding.inflate(context.layoutInflater)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
        binding.btnDialogOrderLike.setOnClickListener(this)
        binding.btnDialogOrderLong.setOnClickListener(this)
        binding.btnDialogOrderRecent.setOnClickListener(this)
        binding.btnDialogOrderCancle.setOnClickListener(this)

    }

    fun setOnItemClickListener(listener: SortDialogClickedListener) {
        this.listener = listener
    }

    interface SortDialogClickedListener {
        fun onClick(resultCode: Int)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnDialogOrderLike.id -> {
                orderLike()
                listener?.onClick(Constants.SORT_LIKE)
            }
            binding.btnDialogOrderLong.id -> {
                orderLong()
                listener?.onClick(Constants.SORT_LONG)
            }
            binding.btnDialogOrderRecent.id -> {
                orderRecent()
                listener?.onClick(Constants.SORT_RECENT)
            }
            binding.btnDialogOrderCancle.id -> {
                orderCancel()
            }
        }
    }

    private fun orderRecent() {
        binding.btnDialogOrderRecentCheck.setImageResource(R.drawable.ic_check_enabled)
        binding.btnDialogOrderLikeCheck.setImageResource(R.drawable.ic_check_disabled)
        binding.btnDialogOrderLongCheck.setImageResource(R.drawable.ic_check_disabled)
    }

    private fun orderLike() {
        binding.btnDialogOrderRecentCheck.setImageResource(R.drawable.ic_check_disabled)
        binding.btnDialogOrderLikeCheck.setImageResource(R.drawable.ic_check_enabled)
        binding.btnDialogOrderLongCheck.setImageResource(R.drawable.ic_check_disabled)
    }

    private fun orderLong() {
        binding.btnDialogOrderRecentCheck.setImageResource(R.drawable.ic_check_disabled)
        binding.btnDialogOrderLikeCheck.setImageResource(R.drawable.ic_check_disabled)
        binding.btnDialogOrderLongCheck.setImageResource(R.drawable.ic_check_enabled)
    }

    private fun orderCancel() {
        dialog.dismiss()

    }

}