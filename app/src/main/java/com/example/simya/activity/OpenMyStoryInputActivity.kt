package com.example.simya.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.R
import com.example.simya.databinding.ActivityMainBinding
import com.example.simya.databinding.ActivityMyStoryOpenInputBinding

class OpenMyStoryInputActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyStoryOpenInputBinding
    private lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyStoryOpenInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTextWatcher()
        init()
    }
    private fun init(){
        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집 오픈 준비하기"
        binding.btnMyStoryOpen.setOnClickListener {
            openMyStory()
        }
        binding.etMyStoryOpenInputMenuIntro.addTextChangedListener(textWatcher)
        binding.etMyStoryOpenInputPerson.addTextChangedListener(textWatcher)
        binding.etMyStoryOpenInputMenu.addTextChangedListener(textWatcher)
    }
    private fun openMyStory(){
        if(binding.btnMyStoryOpen.isEnabled){
            val intent = Intent(this,ChatActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val personInput = binding.etMyStoryOpenInputPerson!!.text.toString()
                val menuInput = binding.etMyStoryOpenInputMenu!!.text.toString()
                val menuIntroInput = binding.etMyStoryOpenInputMenuIntro!!.text.toString()
                // 공백이 없을시 버튼 활성화
                if (personInput.isNotEmpty() && menuInput.isNotEmpty() && menuIntroInput.isNotEmpty()) {
                    Log.d("공백없음 확인","true")
                    binding.btnMyStoryOpen.isEnabled = true
                    binding.btnMyStoryOpen.isClickable = true
                    binding.btnMyStoryOpen.setBackgroundResource(R.drawable.low_radius_button_on)
                    binding.btnMyStoryOpen.setTextColor(application.resources.getColor(R.color.Gray_03))
                }
                // 공백이 있을시 버튼 비활성화
                if (personInput.isEmpty() || menuInput.isEmpty() || menuIntroInput.isEmpty()) {
                    Log.d("공백있음 확인","false")
                    binding.btnMyStoryOpen.isEnabled = false
                    binding.btnMyStoryOpen.isClickable = false
                    binding.btnMyStoryOpen.setBackgroundResource(R.drawable.low_radius_button_off)
                    binding.btnMyStoryOpen.setTextColor(application.resources.getColor(R.color.Gray_10))
                }
            }
            override fun afterTextChanged(s: Editable) {}
        }
    }
}