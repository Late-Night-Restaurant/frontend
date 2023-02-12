package com.example.simya.src.main.story

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
import com.example.simya.config.BaseActivity
import com.example.simya.config.BaseResponse
import com.example.simya.src.main.home.HomeActivity
import com.example.simya.util.data.UserData
import com.example.simya.databinding.ActivityStoryCreateBorderBinding
import com.example.simya.src.main.story.model.CreateMyHouseInterface
import com.example.simya.src.main.story.model.CreateMyHouseService
import com.example.simya.src.model.HouseDTO
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.account.AccountResponse
import com.example.simya.src.model.story.create.CreateStoryDTO
import com.example.simya.src.model.story.create.CreateStoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateMyStoryBorderActivity : BaseActivity<ActivityStoryCreateBorderBinding>(ActivityStoryCreateBorderBinding::inflate), CreateMyHouseInterface
{
    private lateinit var textWatcher: TextWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            CreateMyHouseService(this).tryOnCreateMyHouse(setBorderData())
            // 서버에 전송 데이터 전송해서 이야기집 생성
        }
    }
    private fun moveToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    private fun setBorderData(): HouseDTO {
        var profileId = intent.getStringExtra(PROFILE_ID)!!.toLong()
        var mainMenu = intent.getStringExtra(BORDER_MAIN_MENU)
        var imageUrl = "R.drawable.test_simya"
        var houseName = binding.etMyStoryCreateBorderTitle.text.toString()
        var comment = binding.etMyStoryCreateBorderIntro.text.toString()
        Log.d("PROFILE_ID",profileId!!.toString())
        Log.d("BORDER_MAIN_MENU",mainMenu!!)
        Log.d("test background",imageUrl)
        return HouseDTO(
            profileId,
            mainMenu,
            imageUrl,
            houseName,
            comment
        )
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
                }
                // 공백이 있을시 버튼 비활성화
                if (titleInput.isEmpty() || introInput.isEmpty()) {
                    binding.btnMyStoryCreateBorderNext.isEnabled = false
                    binding.btnMyStoryCreateBorderNext.isClickable = false
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

    override fun onPostCreateMyHouseSuccess(response: CreateStoryResponse) {
        // 메인으로 돌아가기
    }

    override fun onPostCreateMyHouseFailure(response: BaseResponse) {
        Log.d("@@@@@ CHECK @@@@@@", "찜한이야기집 가져오기 실패")
    }

}