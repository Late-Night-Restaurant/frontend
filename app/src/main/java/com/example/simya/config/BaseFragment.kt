package com.example.simya.config

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.simya.src.ui.view.login.signup.fragment.SignupEmailFragment
import com.example.simya.util.dialog.ExitDialog
import com.example.simya.util.dialog.LoadingDialog

abstract class BaseFragment<B : ViewDataBinding>(
    @LayoutRes val layoutResId: Int
) : Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!

//    val callback = object: OnBackPressedCallback(true){
//        override fun handleOnBackPressed() {
//
//        }
//    }
    lateinit var mLoadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,layoutResId,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

//    fun backApplicationExit(context: Context){
//        ExitDialog("앱을 종료하시겠어요?",context).show()
//    }

}