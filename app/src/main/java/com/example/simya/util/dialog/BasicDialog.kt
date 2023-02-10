package com.example.simya.util.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.util.Constants
import com.example.simya.databinding.DialogDefaultBinding

class BasicDialog (context: Context,private val title: String) : Dialog(context),View.OnClickListener {
    private lateinit var binding: DialogDefaultBinding
    private var listener: DefaultDialogClickedListener? = null
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DialogDefaultBinding.inflate(layoutInflater)
//        window!!.setLayout(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.WRAP_CONTENT
//        )
        setContentView(binding.root)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
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
                Log.d("onClick","YES")
            }
            binding.btnNo.id -> {
                listener?.onClick(Constants.NO)
//                orderCancel()
            }
        }
    }

    private fun orderCancel() {
        this@BasicDialog.dismiss()
    }

}