package com.example.simya.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.Constants
import com.example.simya.databinding.DialogDefaultBinding

class BasicDialog (private val context: AppCompatActivity,private val title: String) : View.OnClickListener {
    private val dialog = Dialog(context)
    private lateinit var binding: DialogDefaultBinding
    private var listener: DefaultDialogClickedListener? = null

    fun showDia() {
        init()
        dialog.show()
    }
    private fun init() {
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DialogDefaultBinding.inflate(context.layoutInflater)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
        // 제목연결
        binding.tvQuestion.text = title
        // 클릭리스너
        binding.btnYes.setOnClickListener(this)
        binding.btnNo.setOnClickListener(this)

    }

    fun setOnItemClickListener(listener: DefaultDialogClickedListener) {
        this.listener = listener
    }

    interface DefaultDialogClickedListener {
        fun onClick(resultCode: Int)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnYes.id->{
                listener?.onClick(Constants.YES)
            }
            binding.btnNo.id -> {
                orderCancel()
            }
        }
    }

    private fun orderCancel() {
        dialog.dismiss()

    }

}