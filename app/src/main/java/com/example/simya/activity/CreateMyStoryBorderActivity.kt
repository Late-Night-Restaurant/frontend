package com.example.simya.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.simya.R
import com.example.simya.databinding.ActivityStoryCreateBorderBinding
import com.example.simya.testData.BorderData

class CreateMyStoryBorderActivity : AppCompatActivity() {
    private val binding: ActivityStoryCreateBorderBinding by lazy {
        ActivityStoryCreateBorderBinding.inflate(layoutInflater)
    }
    private lateinit var menu: String
    private lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initTextWatcher()
        init()
    }

    private fun init() {
        getIntentData()
        binding.included.tvDefaultLayoutTitle.text = "이야기집 간판 생성"
        binding.ibMyStoryCreateBorderInfo.setOnClickListener {
            binding.tvMyStoryCreateMainInfo.isInvisible = false
        }
        binding.etMyStoryCreateBorderTitle.addTextChangedListener(textWatcher)
        binding.etMyStoryCreateBorderIntro.addTextChangedListener(textWatcher)
        binding.ibMyStoryCreateBorder.setOnClickListener {
            // 권한 , 카메라 , 갤러리 -> 사진가져오기

        }
        binding.btnMyStoryCreateBorderNext.setOnClickListener {
            moveToStoryMain()
        }
    }

    private fun getIntentData() {
        menu = intent.getStringExtra("menu")!!
    }

    private fun moveToStoryMain() {
        if(binding.btnMyStoryCreateBorderNext.isEnabled){
            val borderData = createBorderData()
            val intent = Intent(this, OpenMyStoryActivity::class.java)
            intent.putExtra("borderData", borderData)
            startActivity(intent)
        }
    }

    private fun createBorderData(): BorderData {
        val title = binding.etMyStoryCreateBorderTitle.text.toString()
//        val intro = binding.etMyStoryCreateBorderIntro.text.toString()

        return BorderData(title, menu)
    }

    private fun initTextWatcher() {
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val titleInput = binding.etMyStoryCreateBorderTitle!!.text.toString()
                val introInput = binding.etMyStoryCreateBorderIntro!!.text.toString()
                // 공백이 없을시 버튼 활성화
                if (titleInput.isNotEmpty() && introInput.isNotEmpty()) {
                    binding.btnMyStoryCreateBorderNext.isEnabled = true
                    binding.btnMyStoryCreateBorderNext.isClickable = true
                    binding.btnMyStoryCreateBorderNext.setBackgroundResource(R.drawable.low_radius_button_on)
                    binding.btnMyStoryCreateBorderNext.setTextColor(application.resources.getColor(R.color.Gray_03))
                }
                // 공백이 있을시 버튼 비활성화
                if (titleInput.isEmpty() || introInput.isEmpty()) {
                    binding.btnMyStoryCreateBorderNext.isEnabled = false
                    binding.btnMyStoryCreateBorderNext.isClickable = false
                    binding.btnMyStoryCreateBorderNext.setBackgroundResource(R.drawable.low_radius_button_off)
                    binding.btnMyStoryCreateBorderNext.setTextColor(application.resources.getColor(R.color.Gray_10))
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    // 화면터치시 키보드 내려감
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return true
    }

}