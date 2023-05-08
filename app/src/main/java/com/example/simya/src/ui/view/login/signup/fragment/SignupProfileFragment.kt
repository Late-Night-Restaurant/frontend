package com.example.simya.src.ui.view.login.signup.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.src.ui.view.login.signup.SignupActivity
import com.example.simya.databinding.FragmentSignupProfileBinding
import com.example.simya.src.main.login.model.SignUpInterface
import com.example.simya.src.main.login.model.SignUpService
import com.example.simya.src.model.account.*
import com.example.simya.src.ui.view.gallery.GalleryActivity
import com.example.simya.src.ui.viewmodel.login.signup.SignupViewModel
import com.example.simya.util.Constants.COMMENT_VALIDATION
import com.example.simya.util.Constants.ERROR_STRING_DUPLICATE
import com.example.simya.util.Constants.ERROR_STRING_FAILED_SIGN_UP
import com.example.simya.util.Constants.ERROR_STRING_INPUT
import com.example.simya.util.Constants.IMAGE_PATH
import com.example.simya.util.Constants.IMAGE_URI
import com.example.simya.util.Constants.NICKNAME_VALIDATION
import com.example.simya.util.Constants.POST_FAIL_USER
import com.example.simya.util.Constants.REQUEST_CODE_FOR_INTENT
import com.example.simya.util.Constants.REQUEST_ERROR
import com.example.simya.util.SampleSnackBar
import java.util.regex.Pattern

class SignupProfileFragment : BaseFragment<FragmentSignupProfileBinding>(
    R.layout.fragment_signup_profile
) {
    private lateinit var signupViewModel: SignupViewModel

    private lateinit var getResult: ActivityResultLauncher<Intent>
    private var getUri: Uri? = null
    private var getPath: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signupViewModel = ViewModelProvider(activity as SignupActivity)[SignupViewModel::class.java]
        binding.signupViewModel = signupViewModel
        signupViewModel.setSignupProgress(75)
        signupViewModel.nickname.observe(viewLifecycleOwner, Observer {
            checkEmpty()
        })
        signupViewModel.comment.observe(viewLifecycleOwner, Observer {
            checkEmpty()
        })

        binding.btnSignupProfileNext.setOnClickListener{
            Navigation.findNavController(view)
                .navigate(R.id.action_signupProfileFragment_to_signupFinFragment)
        }
        binding.ibSignupProfileEdit.setOnClickListener{
            val intent = Intent(requireContext(), GalleryActivity::class.java)
            getResult.launch(intent)
        }
        getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == REQUEST_CODE_FOR_INTENT) {
                    getUri = Uri.parse(result.data?.getStringExtra(IMAGE_URI))
                    getPath = result.data?.getStringExtra(IMAGE_PATH)
                    Glide.with(this).load(getUri).into(binding.civSignupInputProfile)
                    Log.d("이미지크롭 성공", "Success")
                } else {
                    SampleSnackBar.make(binding.root, "이미지를 가져오는데 실패했습니다.").show()
                }
            }
    }
    private fun checkEmpty(){
        binding.btnSignupProfileNext.isEnabled = signupViewModel.profileEmptyCheck()
        binding.btnSignupProfileNext.isClickable = signupViewModel.profileEmptyCheck()
    }
}