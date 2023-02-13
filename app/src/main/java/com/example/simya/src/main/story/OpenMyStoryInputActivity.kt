package com.example.simya.src.main.story

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.simya.util.Constants.BORDER_MAIN_MENU
import com.example.simya.util.Constants.BORDER_TITLE
import com.example.simya.util.Constants.HOUSE_ID
import com.example.simya.util.Constants.OK
import com.example.simya.util.Constants.PROFILE_ID
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.src.main.chat.ChatActivity
import com.example.simya.util.data.UserData
import com.example.simya.databinding.ActivityMyStoryOpenInputBinding
import com.example.simya.src.main.story.model.OpenMyHouseInterface
import com.example.simya.src.main.story.model.OpenMyHouseService
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.story.open.OpenStoryDTO
import com.example.simya.src.model.story.open.OpenStoryResponse
import com.example.simya.src.model.story.topic.TopicRequestDTO
import com.example.simya.util.sharedpreferences.Shared
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpenMyStoryInputActivity :
    BaseActivity<ActivityMyStoryOpenInputBinding>(ActivityMyStoryOpenInputBinding::inflate),OpenMyHouseInterface {
    private lateinit var textWatcher: TextWatcher
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTextWatcher()
        init()
    }

    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집 오픈 준비하기"
        binding.tvRvTitle.text = intent.getStringExtra(BORDER_TITLE)
        binding.tvRvMainMenu.text = intent.getStringExtra(BORDER_MAIN_MENU)

        binding.tvRvMainMenu.text = intent.getStringExtra(BORDER_MAIN_MENU)
        binding.tvRvTitle.text = intent.getStringExtra(BORDER_TITLE)
        binding.etMyStoryOpenInputMenuIntro.addTextChangedListener(textWatcher)
        binding.etMyStoryOpenInputPerson.addTextChangedListener(textWatcher)
        binding.etMyStoryOpenInputMenu.addTextChangedListener(textWatcher)
        // test
        binding.btnMyStoryOpen.setOnClickListener {
            OpenMyHouseService(this).tryOpenMyHouse(
                OpenStoryDTO(
                    intent.getLongExtra(HOUSE_ID, 0),
                    Integer.parseInt(binding.etMyStoryOpenInputPerson.text.toString()),
                    TopicRequestDTO(
                        intent.getStringExtra(BORDER_TITLE), intent.getStringExtra(
                            BORDER_MAIN_MENU
                        )
                    )
                )
            )
        }
    }

    private fun moveMyStory(houseId: Long) {
        if (binding.btnMyStoryOpen.isEnabled) {
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra(HOUSE_ID, houseId)
            intent.putExtra(PROFILE_ID, UserData.getProfileId())
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
                    binding.btnMyStoryOpen.isEnabled = true
                    binding.btnMyStoryOpen.isClickable = true
                }
                // 공백이 있을시 버튼 비활성화
                if (personInput.isEmpty() || menuInput.isEmpty() || menuIntroInput.isEmpty()) {
                    binding.btnMyStoryOpen.isEnabled = false
                    binding.btnMyStoryOpen.isClickable = false
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }

    override fun onPatchCreateMyHouseSuccess(response: OpenStoryResponse) {
        moveMyStory(intent.getLongExtra(HOUSE_ID, 0))
    }

    override fun onPatchCreateMyHouseFailure(response: OpenStoryResponse) {
        Log.d("@@@@@ CHECK @@@@@@", "이야기집 오픈실패")
    }
}