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
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.simya.util.SampleSnackBar
import com.example.simya.util.dialog.DefaultDialog
import com.example.simya.util.dialog.DefaultDialogInterface
import com.example.simya.util.dialog.ExitDialog
import com.example.simya.util.dialog.LoadingDialog
import kotlin.system.exitProcess

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity(){
    protected lateinit var binding: B
        private set
    lateinit var mLoadingDialog: LoadingDialog

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
    fun showCustomToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
    override fun onBackPressed() {
        finish()
    }
    fun backApplicationExit(context: Context){
        ExitDialog("앱을 종료하시겠어요?",context).show()
    }

}