package com.example.simya.src.activity.story

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.simya.util.Constants.BORDER_MAIN_MENU
import com.example.simya.util.Constants.OK
import com.example.simya.util.Constants.PROFILE_ID
import com.example.simya.R
import com.example.simya.src.activity.home.HomeActivity
import com.example.simya.src.data.UserTokenData
import com.example.simya.databinding.ActivityStoryCreateBorderBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.story.create.CreateStoryDTO
import com.example.simya.src.model.story.create.CreateStoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateMyStoryBorderActivity : AppCompatActivity() {
    private val binding: ActivityStoryCreateBorderBinding by lazy {
        ActivityStoryCreateBorderBinding.inflate(layoutInflater)
    }
    private lateinit var textWatcher: TextWatcher
    private val retrofit by lazy {
       RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initTextWatcher()
        init()
    }

    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "이야기집 간판 생성"
        binding.ibMyStoryCreateBorderInfo.setOnClickListener {
            binding.tvMyStoryCreateMainInfo.isInvisible = false
        }
        binding.etMyStoryCreateBorderTitle.addTextChangedListener(textWatcher)
        binding.etMyStoryCreateBorderIntro.addTextChangedListener(textWatcher)
        binding.ibMyStoryCreateBorder.setOnClickListener {
            // 권한 , 카메라 , 갤러리 -> 사진가져오기
            // test 코드로 임시 사진주기
            binding.ibMyStoryCreateBorder.setImageResource(R.drawable.test_simya)
            binding.ibMyStoryCreateBorder.setBackgroundResource(R.drawable.test_simya)
        }
        binding.btnMyStoryCreateBorderNext.setOnClickListener {
            onCreateStory(setBorderData())
            // 서버에 전송 데이터 전송해서 이야기집 생성
        }
    }
    private fun moveToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    private fun setBorderData(): CreateStoryDTO {
        var profileId = intent.getStringExtra(PROFILE_ID)!!.toLong()
        var mainMenu = intent.getStringExtra(BORDER_MAIN_MENU)
        var imageUrl = "R.drawable.test_simya"
        var houseName = binding.etMyStoryCreateBorderTitle.text.toString()
        var comment = binding.etMyStoryCreateBorderIntro.text.toString()
        Log.d("PROFILE_ID",profileId!!.toString())
        Log.d("BORDER_MAIN_MENU",mainMenu!!)
        Log.d("test background",imageUrl)
        return CreateStoryDTO(
            profileId,
            mainMenu,
            imageUrl,
            houseName,
            comment
        )
    }

    private fun onCreateStory(data: CreateStoryDTO) {
        simyaApi.onCreateMyHouse(
            UserTokenData.getUserAccessToken(),
            UserTokenData.getUserRefreshToken(),data).enqueue(object : Callback<CreateStoryResponse> {
            override fun onResponse(
                call: Call<CreateStoryResponse>,
                response: Response<CreateStoryResponse>
            ) {
                if(response.body()!!.code == OK){
                    Log.d("Response",response.body().toString())
                    moveToHome()

                }
            }
            override fun onFailure(call: Call<CreateStoryResponse>, t: Throwable) {
                Log.d("Response",t.toString())
            }
        })
    }

//    private fun moveToStoryMain() {
//        if(binding.btnMyStoryCreateBorderNext.isEnabled){
//            setBorderData()
//            val intent = Intent(this, OpenMyStoryActivity::class.java)
//            intent.putExtra("borderData", setBorderData())
//            startActivity(intent)
//        }
//    }

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