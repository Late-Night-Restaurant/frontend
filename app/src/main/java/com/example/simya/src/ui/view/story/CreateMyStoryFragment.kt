//package com.example.simya.src.ui.view.story
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.core.os.bundleOf
//import androidx.fragment.app.setFragmentResult
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.bumptech.glide.Glide
//import com.example.simya.R
//import com.example.simya.config.BaseFragment
//import com.example.simya.config.BaseResponse
//import com.example.simya.src.ui.adapter.story.CreateMyStoryMultiProfileAdapter
//import com.example.simya.util.data.UserData
//import com.example.simya.databinding.FragmentMyStoryCreateBinding
//import com.example.simya.src.main.myPage.model.MyPageProfileInterface
//import com.example.simya.src.main.myPage.model.MyPageProfileService
//import com.example.simya.src.model.UserDTO
//import com.example.simya.src.model.profile.ProfileDTO
//import com.example.simya.src.model.profile.ProfileResponse
//import com.example.simya.util.Constants.S3_URL
//import com.example.simya.util.SampleSnackBar
//
//class CreateMyStoryFragment : BaseFragment<FragmentMyStoryCreateBinding>(
//    FragmentMyStoryCreateBinding::bind,
//    R.layout.fragment_my_story_create),
//    MyPageProfileInterface {
//    private var dataList: ArrayList<UserDTO> = arrayListOf()
//    private lateinit var dataRVAdapter : CreateMyStoryMultiProfileAdapter
//    private var profileId: Long = UserData.getProfileId()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//        MyPageProfileService(this).tryGetUserProfile()
//    }
//
//    private fun init() {
//
//        UserData.printAllData()
//        Log.d("UserData",UserData.toString())
//        binding.includedTitle.tvDefaultLayoutTitle.text = "이야기집 생성"
//        binding.btnMyStoryCreateNext.setOnClickListener {
//            moveToSetMenu()
//        }
//        dataRVAdapter = CreateMyStoryMultiProfileAdapter(requireContext(), dataList)
//        // recyclerview click listener
//        clickMultiProfile()
//    }
//
//    private fun moveToSetMenu() {
//        setFragmentResult("profileId", bundleOf("bundleKeyProfileId" to profileId))
//        (activity as CreateMyStoryActivity).nextFragmentSignUp(2)
//    }
//
//    private fun initAdapter() {
//        binding.rvMyStoryCreateRecycler.adapter = dataRVAdapter
//        binding.rvMyStoryCreateRecycler.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//    }
//
//    private fun clickMultiProfile() {
//        dataRVAdapter.setOnItemClickListener(object :
//            CreateMyStoryMultiProfileAdapter.OnItemClickListener {
//            override fun onItemClick(v: View, data: UserDTO, position: Int) {
//                Glide.with(this@CreateMyStoryFragment).load(S3_URL+data.picture).placeholder(R.drawable.ic_base_profile).centerCrop()
//                    .into(binding.civMyStoryCreateSelectProfileImage)
//                binding.tvMyStoryCreateNick.text = data.nickname
//                binding.tvMyStoryCreateIntro.text = data.comment
//                profileId = data.profileId
//            }
//        })
//    }
//
//    override fun onGetUserProfileSuccess(response: ProfileResponse) {
//        dataRVAdapter = CreateMyStoryMultiProfileAdapter(requireContext(), dataList)
//        binding.tvMyStoryCreateNick.text = response.result[0].nickname
//        binding.tvMyStoryCreateIntro.text = response.result[0].comment
//        profileId = response.result[0].profileId
//        requireActivity().runOnUiThread {
//            for (i: Int in 0 until response.result.size) {
//                dataList.apply {
//                    add(
//                        UserDTO(
//                            response.result[i].profileId,
//                            response.result[i].nickname,
//                            response.result[i].comment,
//                            response.result[i].pictureUrl
//                        )
//                    )
//                }
//            }
//            initAdapter()
//        }
//    }
//
//    override fun onGetUserProfileFailure(response: ProfileResponse) {
//        SampleSnackBar.make(binding.root,response.message!!)
//    }
//
//    override fun onGetUserProfileDisconnect(message: String) {
//        SampleSnackBar.make(binding.root,message)
//        dismissLoadingDialog()
//    }
//
//    override fun onSetMyRepresentProfileSuccess(response: BaseResponse, data: ProfileDTO) {
//    }
//
//    override fun onSetMyRepresentProfileFailure(response: BaseResponse) {
//    }
//
//    override fun onSetMyRepresentDisconnect(message: String) {
//    }
//
//    override fun onLogoutSuccess(response: BaseResponse) {
//    }
//
//    override fun onLogoutFailure(response: BaseResponse) {
//    }
//
//    override fun onLogoutDisconnect(message: String) {
//    }
//
//}