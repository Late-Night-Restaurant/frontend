//package com.example.simya.src.ui.view.story
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import androidx.core.view.GravityCompat
//import com.bumptech.glide.Glide
//import com.example.simya.R
//import com.example.simya.config.BaseFragment
//import com.example.simya.databinding.FragmentDrawerMyStroyOpenBinding
//import com.example.simya.src.ui.view.prepare.PrepareActivity
//import com.example.simya.util.Constants.S3_URL
//import com.example.simya.util.dialog.DefaultDialog
//import com.example.simya.util.dialog.DefaultDialogInterface
//
//class OpenMyStoryFragment :
//    BaseFragment<FragmentDrawerMyStroyOpenBinding>(
//        FragmentDrawerMyStroyOpenBinding::bind,
//        R.layout.fragment_drawer_my_stroy_open),DefaultDialogInterface {
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        init()
//    }
//
//    private fun init() {
//        val drawerLayout = binding.dlMyStoryOpen
//        binding.includedMyStoryOpen.tvRvMainMenu.text = (activity as OpenMyStoryActivity).category
//        binding.includedMyStoryOpen.tvRvTitle.text = (activity as OpenMyStoryActivity).houseName
//        // 드로어 오픈
//        binding.includedMyStoryOpen.ibMyStoryOpenDrawer.setOnClickListener {
//            drawerLayout.openDrawer(GravityCompat.START)
//            drawerInit()
//        }
//        Glide.with(this).load(S3_URL+ (activity as OpenMyStoryActivity).houseImageUrl).into(binding.includedMyStoryOpen.ivRvBorderImage)
//        // 찜 리스트
//        binding.includedMyStoryOpen.btnMyStoryOpenLike.setOnClickListener {
//            movePrepare()
//        }
//        // 리뷰 리스트
//        binding.includedMyStoryOpen.btnMyStoryOpenReview.setOnClickListener {
//            movePrepare()
//        }
//        // 오픈 준비하기기
//        binding.includedMyStoryOpen.btnMyStoryOpenReady.setOnClickListener {
//            var houseId = (activity as OpenMyStoryActivity).houseId
//            moveToOpen(houseId)
//        }
//    }
//    private fun movePrepare(){
//        val intent = Intent(requireContext(), PrepareActivity::class.java)
//        startActivity(intent)
//    }
//    private fun moveToReviewList() {
//        val intent = Intent(requireContext(), StoryReviewActivity::class.java)
//        startActivity(intent)
//    }
//
//    private fun moveToLikeList() {
//        val intent = Intent(requireContext(), StoryLikeActivity::class.java)
//        startActivity(intent)
//    }
//
//    private fun moveToOpen(houseId: Long) {
//        (activity as OpenMyStoryActivity).category = binding.includedMyStoryOpen.tvRvMainMenu.text.toString()
//        (activity as OpenMyStoryActivity).houseName = binding.includedMyStoryOpen.tvRvTitle.text.toString()
//        (activity as OpenMyStoryActivity).nextFragmentSignUp(2)
//    }
//
//    private fun drawerInit() {
//        binding.btnMyStoryOpenBorder.setOnClickListener {
//            // 간판수정
//        }
//        binding.btnMyStoryOpenMainMenu.setOnClickListener {
//            // 메인 메뉴 수정
//        }
//        binding.btnMyStoryClose.setOnClickListener {
//            DefaultDialog("페점하시겠습니까?",requireContext(),this).show()
//        }
//    }
//
//    override fun onYesButtonClicked() {
//        // 폐점 처리
//    }
//
//    override fun onNoButtonClicked() {
//       // 그냥 dismiss
//    }
//}
