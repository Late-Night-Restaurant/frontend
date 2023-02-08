package com.example.simya.src.activity.story

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.simya.src.Constants
import com.example.simya.src.Constants.PROFILE_ID
import com.example.simya.R
import com.example.simya.src.adpter.createStory.CreateMyStoryMultiProfileAdapter
import com.example.simya.src.data.UserTokenData
import com.example.simya.databinding.ActivityMyStoryCreateBinding
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.profile.ProfileResponse
import com.example.simya.src.testData.TestDataMultiProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateMyStoryActivity : AppCompatActivity() {
    private val binding: ActivityMyStoryCreateBinding by lazy{
      ActivityMyStoryCreateBinding.inflate(layoutInflater)
    }
    private var dataList: ArrayList<TestDataMultiProfile> = arrayListOf()
    private val dataRVAdapter = CreateMyStoryMultiProfileAdapter(this, dataList)

    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy{
        retrofit.create(RetrofitService::class.java)
    }
    private lateinit var profileId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        UserTokenData.init(
//            Shared.prefs.getString("accessToken",Constants.DEFAULT),
//            Shared.prefs.getString("refreshToken",Constants.DEFAULT))
//
        Log.d("User AccessToken", UserTokenData.getUserAccessToken())
        Log.d("User RefreshToken", UserTokenData.getUserRefreshToken())
        init()
        showMyAllProfile()
    }

    private fun init() {

        Log.d("CreateMyStoryActivity","true")
        binding.includedTitle.tvDefaultLayoutTitle.text = "이야기집 생성"

        binding.btnMyStoryCreateNext.setOnClickListener {
            moveToSetMenu()
        }
        // test load
        Glide.with(this).load(R.drawable.test_simya)
            .into(binding.civMyStoryCreateSelectProfileImage)

        // test init
        initData()
        // Adapter init
        initAdapter()
        // recyclerview click listener
        clickMultiProfile()
    }
    private fun showMyAllProfile(){
        Log.d("showMyAllProfile check","true")
        simyaApi.getUserProfile(UserTokenData.getUserAccessToken(), UserTokenData.getUserRefreshToken()).enqueue(object:
            Callback<ProfileResponse>{
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                Log.d("Response check",response.isSuccessful.toString())
                Log.d("Response check",response.code().toString())

                if(response.code() == Constants.OK){
                    binding.tvMyStoryCreateNick.text = response.body()!!.result?.get(0)!!.nickname
                    binding.tvMyStoryCreateIntro.text = response.body()!!.result?.get(0)!!.comment
                    Log.d("Response check","true")
                    Log.d("Response check",response.message())
                    Log.d("Response check",response.body().toString())
                    profileId = response.body()!!.result?.get(0)!!.profileId.toString()
                }else{
                    Log.d("Response check","failure")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.d("Response check","failure")
            }

        })
    }
    private fun moveToSetMenu(){
        val intent = Intent(this, CreateMyStoryMainMenuActivity::class.java)
        intent.putExtra(PROFILE_ID,profileId)
        startActivity(intent)
    }
    private fun initAdapter() {
        binding.rvMyStoryCreateRecycler.adapter = dataRVAdapter
        binding.rvMyStoryCreateRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun clickMultiProfile() {
        dataRVAdapter.setOnItemClickListener(object : CreateMyStoryMultiProfileAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: TestDataMultiProfile, position: Int) {
                Glide.with(this@CreateMyStoryActivity).load(data.imageSource).centerCrop()
                    .into(binding.civMyStoryCreateSelectProfileImage)
                binding.tvMyStoryCreateNick.text = data.nickname
                binding.tvMyStoryCreateIntro.text = data.intro
            }

            override fun onLongClick(v: View, data: TestDataMultiProfile, position: Int) {
                Glide.with(this@CreateMyStoryActivity).load(data.imageSource).centerCrop()
                    .into(binding.civMyStoryCreateSelectProfileImage)
            }
        })
    }
    private fun initData() {
        dataList.apply {

        }
    }
}