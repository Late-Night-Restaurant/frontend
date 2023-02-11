package com.example.simya.src.main.myPage.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.config.BaseResponse
import com.example.simya.databinding.FragmentHomeMyPageBinding
import com.example.simya.src.main.login.signIn.EmailLoginActivity
import com.example.simya.src.main.myPage.adapter.myPage.MultiProfileAdapter
import com.example.simya.util.data.UserData
import com.example.simya.src.main.myPage.*
import com.example.simya.src.main.myPage.model.MyPageProfileInterface
import com.example.simya.src.main.myPage.model.MyPageProfileService
import com.example.simya.src.model.profile.ProfileDTO
import com.example.simya.src.model.profile.ProfileResponse
import com.example.simya.util.Constants.NO
import com.example.simya.util.Constants.YES
import com.example.simya.util.dialog.BasicDialog

class MyPageFragment : BaseFragment<FragmentHomeMyPageBinding>(
    FragmentHomeMyPageBinding::bind,
    R.layout.fragment_home_my_page
), MyPageProfileInterface {

    private var dataList: ArrayList<ProfileDTO> = arrayListOf()
    private lateinit var dataRVAdapter: MultiProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        binding.btnMyPageProfile.setOnClickListener {
            val intent = Intent(activity, ProfileModifyActivity::class.java)
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
                        MyPageProfileService(this@MyPageFragment).tryOnLogout()
                    } else if (resultCode == NO) {
                        dismissBasicDialog()
                        Log.d("로그아웃", "NO")
                    }
                }
            })
        }
        clickMultiProfile()
        MyPageProfileService(this).tryGetUserProfile()
    }

    private fun initMainProfile() {
        Glide.with(this).load(UserData.getProfileImage()).placeholder(R.drawable.ic_base_profile)
            .into(binding.civMyPageProfile)
        binding.tvMyPageMainNick.text = UserData.getProfileName()
        binding.tvMyPageMainComment.text = UserData.getProfileComment()
    }

    // 추가하기 or 멀티프로필 클릭시
    private fun clickMultiProfile() {
        dataRVAdapter.setOnItemClickListener(object : MultiProfileAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: ProfileDTO, position: Int) {
                Log.d("Click", "multiProfile")
                if (position == 0) {
                    moveToAdd()
                } else {
                    showLoadingDialog(this@MyPageFragment.requireContext())
                    MyPageProfileService(this@MyPageFragment).trySetMyRepresentProfile(data)
                }
            }
        })
    }

    private fun moveToAdd() {
        val intent = Intent(activity, ProfileAddActivity::class.java)
        startActivity(intent)
    }

    private fun tryChangeMyProfile(data: ProfileDTO) {
        binding.tvMyPageMainNick.text = data.nickname
        binding.tvMyPageMainComment.text = data.comment
        Glide.with(this@MyPageFragment).load(data.picture).placeholder(R.drawable.ic_base_profile)
            .into(binding.civMyPageProfile)
        UserData.setProfileId(data.profileId)

    }

    override fun onGetUserProfileSuccess(response: ProfileResponse) {
        requireActivity().runOnUiThread {
            dataList.apply {
                for (i: Int in 0 until response.result.size) {
                    add(response.result[i])
                    dataRVAdapter.notifyItemInserted(i + 1)
                }
            }
            // 어댑터 재연결하기? 리스트를 추가한걸 봐야함
        }
    }

    override fun onGetUserProfileFailure(response: ProfileResponse) {
        Log.d("@@@@@ CHECK @@@@@@", "멀티 프로필 가져오기 실패")
    }

    override fun onSetMyRepresentProfileSuccess(response: BaseResponse,data: ProfileDTO) {
        dismissLoadingDialog()
        tryChangeMyProfile(data)
    }

    override fun onSetMyRepresentProfileFailure(response: BaseResponse) {
    }

    override fun onLogoutSuccess(response: BaseResponse) {
        val intent = Intent(
            this@MyPageFragment.requireContext(),
            EmailLoginActivity::class.java
        )
        startActivity(intent)
    }

    override fun onLogoutFailure(response: BaseResponse) {
        Log.d("@@@@@ CHECK @@@@@@", "로그아웃 실패")
    }


}
