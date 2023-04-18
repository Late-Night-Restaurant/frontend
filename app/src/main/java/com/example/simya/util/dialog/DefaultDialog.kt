package com.example.simya.util.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.simya.databinding.DialogDefaultBinding

class DefaultDialog(message: String,context: Context, myCustomDialogInterface: DefaultDialogInterface) : Dialog(context) {

    private var mBinding: DialogDefaultBinding? = null
    private val binding get() = mBinding!!

    private var myCustomDialogInterface: DefaultDialogInterface? = null

    private var message: String? = null

    init {
        this.message = message
        this.myCustomDialogInterface = myCustomDialogInterface
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = DialogDefaultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable())
        window!!.setDimAmount(0.2f)

        binding.tvQuestion.text = message

        // requestWindowFeature(Window.FEATURE_NO_TITLE)
//      // setCanceledOnTouchOutside(false)
//      // setCancelable(false)
        binding.btnYes.setOnClickListener {
            this.myCustomDialogInterface?.onYesButtonClicked()
        }
        binding.btnNo.setOnClickListener {
            this.myCustomDialogInterface?.onNoButtonClicked()
            this.dismiss()
        }
    }
}