package com.example.simya.src.main.story.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.simya.R
import com.example.simya.util.Constants.HOUSE_ID
import com.example.simya.util.Constants.PROFILE_ID
import com.example.simya.config.BaseFragment
import com.example.simya.src.main.chat.ChatActivity
import com.example.simya.util.data.UserData
import com.example.simya.databinding.FragmentMyStoryOpenInputBinding
import com.example.simya.src.main.story.OpenMyStoryActivity
import com.example.simya.src.main.story.model.OpenMyHouseInterface
import com.example.simya.src.main.story.model.OpenMyHouseService
import com.example.simya.src.model.story.open.OpenStoryDTO
import com.example.simya.src.model.story.open.OpenStoryResponse
import com.example.simya.src.model.story.topic.TopicRequestDTO
import com.example.simya.util.SampleSnackBar

class OpenMyStoryInputFragment :
    BaseFragment<FragmentMyStoryOpenInputBinding>(
        FragmentMyStoryOpenInputBinding::bind,
        R.layout.fragment_drawer_my_stroy_open
    ), OpenMyHouseInterface {
    private lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTextWatcher()
        init()
    }

    private fun init() {
        binding.included.tvDefaultLayoutTitle.text = "내 이야기 집 오픈 준비하기"
        binding.tvRvMainMenu.text = (activity as OpenMyStoryActivity).category
        binding.tvRvTitle.text = (activity as OpenMyStoryActivity).houseName
        binding.etMyStoryOpenInputMenuIntro.addTextChangedListener(textWatcher)
        binding.etMyStoryOpenInputPerson.addTextChangedListener(textWatcher)
        binding.etMyStoryOpenInputMenu.addTextChangedListener(textWatcher)
        // test
        binding.btnMyStoryOpen.setOnClickListener {
            OpenMyHouseService(this).tryOpenMyHouse(
                OpenStoryDTO(
                    (activity as OpenMyStoryActivity).houseId,
                    Integer.parseInt(binding.etMyStoryOpenInputPerson.text.toString()),
                    TopicRequestDTO(
                        binding.etMyStoryOpenInputMenu.text.toString(),
                        binding.etMyStoryOpenInputMenuIntro.text.toString()
                    )
                )
            )
        }
    }

    private fun moveMyStory(houseId: Long) {
        if (binding.btnMyStoryOpen.isEnabled) {
            val intent = Intent(requireContext(), ChatActivity::class.java)
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
        moveMyStory((activity as OpenMyStoryActivity).houseId)
        (activity as OpenMyStoryActivity).finish()
    }

    override fun onPatchCreateMyHouseFailure(response: OpenStoryResponse) {
        SampleSnackBar.make(binding.root,response.message!!)
        dismissLoadingDialog()
    }

    override fun onPatchCreateMyHouseDisconnect(message: String) {
        SampleSnackBar.make(binding.root,message)
        dismissLoadingDialog()
    }
}