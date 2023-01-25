package com.example.simya.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.simya.R
import com.example.simya.databinding.ActivityStoryMainMenuBinding

class CreateMyStoryMainMenuActivity: AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityStoryMainMenuBinding
    var holdBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "이야기집 생성"

        binding.ibMyStoryCreateMainMenuInfo.setOnClickListener {
            binding.tvMyStoryCreateMainInfo.isInvisible = false
        }

        buttonSingleSelected()
    }

    private fun buttonSingleSelected() {
        binding.btnMainMenuLove.setOnClickListener(this)
        binding.btnMainMenuFamily.setOnClickListener(this)
        binding.btnMainMenuRelationship.setOnClickListener(this)
        binding.btnMainMenuStress.setOnClickListener(this)
        binding.btnMainMenuHobby.setOnClickListener(this)
        binding.btnMainMenuCulture.setOnClickListener(this)
    }
    private fun btnHighlighted(selectBtn: Button,preBtn: Button?){
        selectBtn.isSelected = selectBtn?.isSelected != true
        preBtn?.isSelected = preBtn?.isSelected != true
        holdBtn = selectBtn
        nextButtonEnabled()
    }
    private fun nextButtonEnabled(){
        binding.btnMainMenuNext.setBackgroundResource(R.drawable.low_radius_button_on)
        binding.btnMainMenuNext.setTextColor(application.resources.getColor(R.color.Gray_03))
        binding.btnMainMenuNext.isEnabled= true
    }
    override fun onClick(view: View?) {
        if(view !=null){
            when(view.id){
               R.id.btn_main_menu_love -> btnHighlighted(binding.btnMainMenuLove,holdBtn)
               R.id.btn_main_menu_family -> btnHighlighted(binding.btnMainMenuFamily,holdBtn)
               R.id.btn_main_menu_culture -> btnHighlighted(binding.btnMainMenuCulture,holdBtn)
               R.id.btn_main_menu_hobby-> btnHighlighted(binding.btnMainMenuHobby,holdBtn)
               R.id.btn_main_menu_stress -> btnHighlighted(binding.btnMainMenuStress,holdBtn)
               R.id.btn_main_menu_relationship -> btnHighlighted(binding.btnMainMenuRelationship,holdBtn)
            }
        }
    }
}
