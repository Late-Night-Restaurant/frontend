//package com.example.simya.src.ui.view.story
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.Log
//import android.view.View
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.view.isInvisible
//import androidx.fragment.app.setFragmentResultListener
//import com.bumptech.glide.Glide
//import com.example.simya.R
//import com.example.simya.config.BaseFragment
//import com.example.simya.config.BaseResponse
//import com.example.simya.util.data.UserData
//import com.example.simya.databinding.FragmentStoryCreateBorderBinding
//import com.example.simya.src.main.story.model.CreateMyHouseInterface
//import com.example.simya.src.main.story.model.CreateMyHouseService
//import com.example.simya.src.model.story.create.CreateStoryDTO
//import com.example.simya.src.model.story.create.CreateStoryResponse
//import com.example.simya.util.Constants
//import com.example.simya.util.SampleSnackBar
//import com.example.simya.src.ui.view.gallery.GalleryActivity
//
//class CreateMyStoryBorderFragment : BaseFragment<FragmentStoryCreateBorderBinding>(
//    FragmentStoryCreateBorderBinding::bind,
//    R.layout.fragment_story_create_border
//), CreateMyHouseInterface {
//    private lateinit var textWatcher: TextWatcher
//    private lateinit var getResult: ActivityResultLauncher<Intent>
//    private var selectId = 0L
//    private lateinit var mainMenu: String
//    private var getUri: Uri? = null
//    private var getPath: String? = null
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initTextWatcher()
//        getResult =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == Constants.REQUEST_CODE_FOR_INTENT) {
//                    getUri = Uri.parse(result.data?.getStringExtra(Constants.IMAGE_URI))
//                    getPath = result.data?.getStringExtra(Constants.IMAGE_PATH)
//                    Glide.with(this).load(getUri).into(binding.ibMyStoryCreateBorder)
//                    binding.ivMyStoryCreateBorderEx.isInvisible = true
//                    Log.d("이미지크롭 성공", "Success")
//                } else {
//                    Log.d("RegisterForActivity", "이미지를 가져오는데 실패했습니다.")
//                }
//            }
//
//        setFragmentResultListener("profileId") { _, bundle ->
//            selectId = bundle.getLong("bundleKeyProfileId")
//        }
//        setFragmentResultListener("mainMenu") { _, bundle ->
//            mainMenu = bundle.getString("bundleKeyMainMenu").toString()
//        }
//        init()
//    }
////    override fun(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        initTextWatcher()
////        getResult =
////            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
////                if (result.resultCode == Constants.REQUEST_CODE_FOR_INTENT) {
////                    var getUri: Uri = Uri.parse(result.data?.getStringExtra("cropImage"))
////                    Glide.with(this).load(getUri).into(binding.ibMyStoryCreateBorder)
////                    binding.ivMyStoryCreateBorderEx.isInvisible = true
////                    Log.d("이미지크롭 성공", "Success")
////                } else {
////                    Log.d("RegisterForActivity", "이미지를 가져오는데 실패했습니다.")
////                }
////            }
////        init()
////    }
//
//    private fun init() {
//        UserData.printAllData()
//        binding.included.tvDefaultLayoutTitle.text = "이야기집 간판 생성"
//        binding.ibMyStoryCreateBorderInfo.setOnClickListener {
//            binding.tvMyStoryCreateMainInfo.isInvisible = false
//        }
//        binding.etMyStoryCreateBorderTitle.addTextChangedListener(textWatcher)
//        binding.etMyStoryCreateBorderIntro.addTextChangedListener(textWatcher)
//        binding.ibMyStoryCreateBorder.setOnClickListener {
//            val intent = Intent(requireContext(), GalleryActivity::class.java)
//            getResult.launch(intent)
//        }
//        binding.btnMyStoryCreateBorderNext.setOnClickListener {
//            CreateMyHouseService(this).tryOnCreateMyHouse(getPath,setBorderData())
//            // 서버에 전송 데이터 전송해서 이야기집 생성
//        }
//
//    }
//
//    private fun setBorderData(): CreateStoryDTO {
//        var profileId = selectId
//        var mainMenu = mainMenu
//        var imageUrl = getPath
//        var houseName = binding.etMyStoryCreateBorderTitle.text.toString()
//        var comment = binding.etMyStoryCreateBorderIntro.text.toString()
//        return CreateStoryDTO(
//            profileId,
//            mainMenu,
//            houseName,
//            comment
//        )
//    }
//
//    private fun initTextWatcher() {
//        textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                val titleInput = binding.etMyStoryCreateBorderTitle!!.text.toString()
//                val introInput = binding.etMyStoryCreateBorderIntro!!.text.toString()
//                // 공백이 없을시 버튼 활성화
//                if (titleInput.isNotEmpty() && introInput.isNotEmpty()) {
//                    binding.btnMyStoryCreateBorderNext.isEnabled = true
//                    binding.btnMyStoryCreateBorderNext.isClickable = true
//                }
//                // 공백이 있을시 버튼 비활성화
//                if (titleInput.isEmpty() || introInput.isEmpty()) {
//                    binding.btnMyStoryCreateBorderNext.isEnabled = false
//                    binding.btnMyStoryCreateBorderNext.isClickable = false
//                }
//            }
//            override fun afterTextChanged(s: Editable) {}
//        }
//    }
//
//    override fun onPostCreateMyHouseSuccess(response: CreateStoryResponse) {
//        (activity as CreateMyStoryActivity).resultFinish()
//    }
//
//    override fun onPostCreateMyHouseFailure(response: BaseResponse) {
//        SampleSnackBar.make(binding.root,response.message!!)
//    }
//
//    override fun onPostCreateMyHouseDisconnect(message: String) {
//        SampleSnackBar.make(binding.root,message)
//        dismissLoadingDialog()
//    }
//
//}