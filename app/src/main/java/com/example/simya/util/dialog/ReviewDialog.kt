package com.example.simya.util.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.DialogOrderBinding
import com.example.simya.databinding.DialogReviewSortBinding

class ReviewDialog(private val context: AppCompatActivity) {
    private val dialog = Dialog(context)
    private lateinit var binding: DialogReviewSortBinding

    fun showDia() {

    }

    private fun init(){
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DialogReviewSortBinding.inflate(context.layoutInflater)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
    }

    private fun orderRecent(){
    }
}