package com.example.simya.config

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.simya.util.dialog.BasicDialog
import com.example.simya.util.dialog.LoadingDialog

abstract class BaseFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes layoutResId: Int
) : Fragment(layoutResId) {
    private var _binding: B? = null
    lateinit var mLoadingDialog: LoadingDialog
    lateinit var mBasicDialog: BasicDialog
    var clickable = true
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

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