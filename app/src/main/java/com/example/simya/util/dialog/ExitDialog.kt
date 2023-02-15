package com.example.simya.util.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.example.simya.databinding.DialogDefaultBinding
import kotlin.system.exitProcess

class ExitDialog(val message: String, context: Context) : Dialog(context) {

    private var mBinding: DialogDefaultBinding? = null
    private val binding get() = mBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = DialogDefaultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable())
        window!!.setDimAmount(0.2f)
//        setCanceledOnTouchOutside(false)
//        setCancelable(false)
        binding.tvQuestion.text = message
        binding.btnYes.setOnClickListener {
            exitProcess(0)
        }
        binding.btnNo.setOnClickListener {
            this.dismiss()
        }
    }
}