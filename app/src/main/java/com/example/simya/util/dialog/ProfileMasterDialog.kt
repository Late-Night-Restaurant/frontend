package com.example.simya.util.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.simya.databinding.DialogProfileMasterBinding
import com.example.simya.src.model.UserDTO

class ProfileMasterDialog(val data: UserDTO, context: Context, profileMasterDialogInterface: ProfileMasterDialogInterface) : Dialog(context) {

    private var mBinding : DialogProfileMasterBinding? = null
    private val binding get() = mBinding!!

    private var profileMasterDialogInterface:ProfileMasterDialogInterface? = null

    // 인터페이스 연결
    init {
        this.profileMasterDialogInterface = profileMasterDialogInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DialogProfileMasterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mBinding!!.civDialogProfileMasterNick.text = data.nickname
        mBinding!!.civDialogProfileMasterIntro.text = data.comment
        Glide.with(context).load(data.picture).into(mBinding!!.civDialogProfileMasterImage)

        mBinding!!.btnDialogProfileMasterForce.setOnClickListener {
            this.profileMasterDialogInterface?.onBenClicked()
        }
        mBinding!!.btnDialogProfileMasterBen.setOnClickListener{
            this.profileMasterDialogInterface?.onForceClicked()
        }
    }
}