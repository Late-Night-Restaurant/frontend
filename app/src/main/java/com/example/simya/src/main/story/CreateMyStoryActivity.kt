package com.example.simya.src.main.story

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.simya.util.Constants
import com.example.simya.util.Constants.PROFILE_ID
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.config.BaseResponse
import com.example.simya.src.main.story.adapter.createStory.CreateMyStoryMultiProfileAdapter
import com.example.simya.util.data.UserData
import com.example.simya.databinding.ActivityMyStoryCreateBinding
import com.example.simya.src.main.myPage.model.MyPageProfileInterface
import com.example.simya.src.main.myPage.model.MyPageProfileService
import com.example.simya.src.model.profile.ProfileDTO
import com.example.simya.src.model.profile.ProfileResponse
import com.example.simya.src.testData.TestDataMultiProfile
import com.example.simya.util.Constants.OK
import com.example.simya.util.data.ProfileData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateMyStoryActivity :
    BaseActivity<ActivityMyStoryCreateBinding>(ActivityMyStoryCreateBinding::inflate),
    MyPageProfileInterface {
    private var dataList: ArrayList<ProfileData> = arrayListOf()
    private val dataRVAdapter = CreateMyStoryMultiProfileAdapter(this, dataList)
    private var profileId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        MyPageProfileService(this).tryGetUserProfile()
    }

    private fun init() {
        binding.includedTitle.tvDefaultLayoutTitle.text = "이야기집 생성"
        binding.btnMyStoryCreateNext.setOnClickListener {
            moveToSetMenu()
        }
        // recyclerview click listener
        clickMultiProfile()
    }

    private fun moveToSetMenu() {
        val intent = Intent(this, CreateMyStoryMainMenuActivity::class.java)
        intent.putExtra(PROFILE_ID, profileId)
        startActivity(intent)
    }

    private fun initAdapter() {
        binding.rvMyStoryCreateRecycler.adapter = dataRVAdapter
        binding.rvMyStoryCreateRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun clickMultiProfile() {
        dataRVAdapter.setOnItemClickListener(object :
            CreateMyStoryMultiProfileAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: ProfileData, position: Int) {
                Glide.with(this@CreateMyStoryActivity).load(data.picture).centerCrop()
                    .into(binding.civMyStoryCreateSelectProfileImage)
                binding.tvMyStoryCreateNick.text = data.nickname
                binding.tvMyStoryCreateIntro.text = data.comment
            }

            override fun onLongClick(v: View, data: ProfileData, position: Int) {
                Glide.with(this@CreateMyStoryActivity).load(data.picture).centerCrop()
                    .into(binding.civMyStoryCreateSelectProfileImage)
            }
        })
    }

    override fun onGetUserProfileSuccess(response: ProfileResponse) {
        binding.tvMyStoryCreateNick.text = response.result[0].nickname
        binding.tvMyStoryCreateIntro.text = response.result[0].comment
        profileId = response.result[0].profileId
        runOnUiThread {
            for (i: Int in 1 until response.result.size) {
                dataList.apply {
                    add(
                        ProfileData(
                            response.result[i].nickname,
                            response.result[i].comment,
                            response.result[i].picture
                        )
                    )
                }
            }
            initAdapter()
        }
    }

    override fun onGetUserProfileFailure(response: ProfileResponse) {
        Log.d("@@@@@ CHECK @@@@@@", "멀티프로필 가져오기 실패")
    }

    override fun onSetMyRepresentProfileSuccess(response: BaseResponse, data: ProfileDTO) {
    }

    override fun onSetMyRepresentProfileFailure(response: BaseResponse) {
    }

    override fun onLogoutSuccess(response: BaseResponse) {
    }

    override fun onLogoutFailure(response: BaseResponse) {
    }
}