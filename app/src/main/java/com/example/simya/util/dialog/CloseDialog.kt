package com.example.simya.util.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.databinding.DialogDefaultBinding
import com.example.simya.util.SampleSnackBar

class CloseDialog(private val context: Context){
    private val dialog = Dialog(context)
    private lateinit var binding: DialogDefaultBinding
    fun showDia(){
        init()
        dialog.show()
    }

    private fun init(){
        setDialog()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.setContentView(binding.root)
        binding.btnYes.setOnClickListener {
            //폐점 메소드
            this.finish()
            SampleSnackBar.make(binding.root,"폐점 되었습니다")
        }
        binding.btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }
    private fun setDialog(){
        binding = DialogDefaultBinding.inflate(context.layoutInflater)
        binding.tvQuestion.text = "이야기 집을 정말 페점하시나요?"
        binding.btnYes.text = "폐점"
        binding.btnNo.text = "취소"
    }

}