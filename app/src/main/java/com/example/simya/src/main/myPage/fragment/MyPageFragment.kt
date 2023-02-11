package com.example.simya.src.main.myPage.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.config.BaseResponse
import com.example.simya.src.main.myPage.MyPageLikeActivity
import com.example.simya.src.main.myPage.MyPageReviewActivity
import com.example.simya.src.main.myPage.ProfileEditActivity
import com.example.simya.databinding.FragmentHomeMyPageBinding
import com.example.simya.src.main.myPage.adapter.myPage.MultiProfileAdapter
import com.example.simya.util.data.UserData
import com.example.simya.src.main.login.signIn.EmailLoginActivity
import com.example.simya.src.main.myPage.ProfileAddActivity
import com.example.simya.src.model.RetrofitBuilder
import com.example.simya.src.model.RetrofitService
import com.example.simya.src.model.profile.MyProfileResponse
import com.example.simya.src.model.profile.ProfileDTO
import com.example.simya.util.Constants.NO
import com.example.simya.util.Constants.YES
import com.example.simya.util.dialog.BasicDialog
import com.example.simya.util.dialog.LoadingDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentHomeMyPageBinding
    private var dataList: ArrayList<ProfileDTO> = arrayListOf()
    private lateinit var dataRVAdapter: MultiProfileAdapter
    private lateinit var mLoadingDialog: LoadingDialog
    private lateinit var mBasicDialog: BasicDialog
    private val retrofit by lazy {
        RetrofitBuilder.getInstnace()
    }
    private val simyaApi by lazy {
        retrofit.create(RetrofitService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMyPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.btnMyPageProfile.setOnClickListener {
            val intent = Intent(activity, ProfileEditActivity::class.java)
            startActivity(intent)
        }

        // 찜한 이야기 집으로
        binding.ibMyPageLikeHouse.setOnClickListener {
            val intent = Intent(activity, MyPageLikeActivity::class.java)
            startActivity(intent)
        }

        // 내가 쓴 리뷰로
        binding.ibMyPageReview.setOnClickListener {
            val intent = Intent(activity, MyPageReviewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        // 메인 프로필 init
        initMainProfile()

        // 추가하기 버튼 활성화
        dataList.apply {
            add(dataList.size, ProfileDTO(0, "추가하기", "추가하기", "R.drawable.ic_baseline_add_24"))
        }
        // 어댑터 연결
        dataRVAdapter = MultiProfileAdapter(this, dataList)
        binding.rvMyPageMultiProfile.adapter = dataRVAdapter
        binding.rvMyPageMultiProfile.layoutManager =
            LinearLayoutManager(
                this.context,
                RecyclerView.HORIZONTAL,
                false
            )

        // 로그아웃
        binding.btnMyPageLogout.setOnClickListener {
            showBasicDialog(this@MyPageFragment.requireContext(), "로그아웃 하시겠습니까?")
            mBasicDialog.setOnItemClickListener(object : BasicDialog.DefaultDialogClickedListener {
                override fun onClick(resultCode: Int) {
                    if (resultCode == YES) {
                        dismissBasicDialog()
                        Log.d("로그아웃", "YES")
                        tryLogoutService()
                    } else if (resultCode == NO) {
                        dismissBasicDialog()
                        Log.d("로그아웃", "NO")
                    }
                }
            })
        }
        tryGetAllMyProfileService()
        clickMultiProfile()
    }
    // Retrofit2를 사용해서 내가 만든 멀티프로필 가져오기
    private fun tryGetAllMyProfileService() {
        simyaApi.getMyAllProfile(UserData.accessToken, UserData.refreshToken)
            .enqueue(object : Callback<MyProfileResponse> {
                override fun onResponse(
                    call: Call<MyProfileResponse>,
                    response: Response<MyProfileResponse>
                ) {
                    val code = response.body()!!.code
                    if (code == 200) {
                        activity!!.runOnUiThread {
                            dataList.apply {
                                for (i: Int in 0 until response.body()!!.result.size) {
                                    add(response.body()!!.result[i])
                                    dataRVAdapter.notifyItemInserted(i + 1)
                                }
                            }
                            // 어댑터 재연결하기? 리스트를 추가한걸 봐야함
                        }
                    }
                }

                override fun onFailure(call: Call<MyProfileResponse>, t: Throwable) {
                    Log.d("Response", t.toString())
                }
            })
    }
    private fun initMainProfile(){
        Glide.with(this).load(UserData.getProfileImage()).into(binding.civMyPageProfile)
        binding.tvMyPageMainNick.text = UserData.getProfileName()
        binding.tvMyPageMainComment.text = UserData.getProfileComment()
    }
    // Retrofit2를 사용한 로그아웃
    private fun tryLogoutService() {
        simyaApi.onLogout(UserData.accessToken, UserData.refreshToken)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>
                ) {
                    val code = response.body()!!.code
                    Log.d("status code", code.toString())
                    if (code == 200) {
                        // 로그아웃이 되었다고 다이얼로그로 알려주기
                        val intent = Intent(
                            this@MyPageFragment.requireContext(),
                            EmailLoginActivity::class.java
                        )
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("Response", t.toString())
                }
            })
    }

    // 추가하기 or 멀티프로필 클릭시
    private fun clickMultiProfile() {
        dataRVAdapter.setOnItemClickListener(object : MultiProfileAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: ProfileDTO, position: Int) {
                Log.d("Click","multiProfile")
                if (position == 0) {
                    moveTOAdd()
                } else {
                    showLoadingDialog(this@MyPageFragment.requireContext())
                    tryChangeMyProfile(data)
                }
            }
        })
    }
    private fun moveTOAdd(){
        val intent = Intent(activity, ProfileAddActivity::class.java)
        startActivity(intent)
    }
    // Retrofit2를 사용해서 메인 프로필 UI 변경 및 유저 데이터 변경
    private fun tryChangeMyProfile(data: ProfileDTO) {
        simyaApi.changeMyRepresentProfile(
            UserData.accessToken,
            UserData.refreshToken,
            UserData.getProfileId()
        ).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.body()!!.code == 200) {
                    binding.tvMyPageMainNick.text = data.nickname
                    binding.tvMyPageMainComment.text = data.comment
                    Glide.with(this@MyPageFragment).load(data.picture).placeholder(R.drawable.ic_base_profile)
                        .into(binding.civMyPageProfile)
                    UserData.setProfileId(data.profileId)
                    dismissLoadingDialog()
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Response", t.toString())
            }
        })
    }

    //다이얼로그 메소드
    private fun showBasicDialog(context: Context, title: String) {
        mBasicDialog = BasicDialog(context, title)
        mBasicDialog.show()
    }

    private fun dismissBasicDialog() {
        if (mBasicDialog.isShowing) {
            mBasicDialog.dismiss()
        }
    }

    private fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }

    private fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

}