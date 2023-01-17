package com.example.simya

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.simya.databinding.DialogDefaultBinding

class MyCustomDialog(context: Context, customDia: CustomDialogInterface): Dialog(context){
    private lateinit var mBinding: DialogDefaultBinding
    private val  binding get() = mBinding
    private lateinit var customDia: CustomDialogInterface
    init{
        this.customDia = customDia
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        mBinding = DialogDefaultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnYes.setOnClickListener {
            this.customDia.testClickYes()
        }
        binding.btnNo.setOnClickListener {
            this.customDia.testClickNo()
        }
    }
}