//package com.example.simya.util.dialog
//
//import android.app.Dialog
//import android.content.Context
//import android.graphics.drawable.ColorDrawable
//import android.os.Bundle
//import com.example.simya.databinding.DialogAgreeBinding
//import com.example.simya.src.ui.view.login.singup.fragment.SignupAgreeFragment
//
//class AgreeDialog(message: String, context: Context, myCustomDialogInterface: SignupAgreeFragment) : Dialog(context) {
//
//    private var mBinding: DialogAgreeBinding? = null
//    private val binding get() = mBinding!!
//
//    private var myCustomDialogInterface: AgreeDialogInterface? = null
//
//    private var message: String? = null
//
//    init {
//        this.message = message
//        this.myCustomDialogInterface = myCustomDialogInterface
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        mBinding = DialogAgreeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        window!!.setBackgroundDrawable(ColorDrawable())
//        window!!.setDimAmount(0.2f)
//
//        binding.tvQuestion.text = message
//
//        // requestWindowFeature(Window.FEATURE_NO_TITLE)
////      // setCanceledOnTouchOutside(false)
////      // setCancelable(false)
//
//        binding.btnClose.setOnClickListener {
//            this.myCustomDialogInterface?.onCloseButtonClicked()
//            this.dismiss()
//        }
//    }
//}