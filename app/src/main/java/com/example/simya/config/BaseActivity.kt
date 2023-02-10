package com.example.simya.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.simya.util.dialog.BasicDialog
import com.example.simya.util.dialog.LoadingDialog

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected lateinit var binding: B
        private set
    lateinit var mLoadingDialog: LoadingDialog
    lateinit var mBasicDialog: BasicDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }


    // 다이얼로그
    fun showBasicDialog(context: Context,title: String){
        mBasicDialog = BasicDialog(context, title)
        mBasicDialog.show()
    }
    fun dismissBasicDialog(){
        if (mBasicDialog.isShowing) {
            mBasicDialog.dismiss()
        }
    }
    fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }
    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }
}