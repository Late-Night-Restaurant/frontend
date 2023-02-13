package com.example.simya.config

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.simya.util.Constants
import com.example.simya.util.dialog.BasicDialog
import com.example.simya.util.dialog.LoadingDialog

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected lateinit var binding: B
        private set
    lateinit var mLoadingDialog: LoadingDialog
    lateinit var mBasicDialog: BasicDialog
    var clickable = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }
    fun isThrottleClick(): Boolean {
        return if (clickable) {
            clickable = false
            Handler(Looper.getMainLooper()).postDelayed(
                Runnable { clickable = true},1000)
        } else {
            Log.i("TAG", "waiting for a while")
            false
        }
    }
    fun showCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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