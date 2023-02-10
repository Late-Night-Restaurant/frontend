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
import com.example.simya.config.BaseResponse
import com.example.simya.src.main.myPage.MyPageLikeActivity
import com.example.simya.src.main.myPage.MyPageReviewActivity
import com.example.simya.src.main.myPage.ProfileEditActivity
import com.example.simya.databinding.FragmentHomeMyPageBinding
import com.example.simya.src.main.myPage.adapter.myPage.MultiProfileAdapter
import com.example.simya.src.data.UserTokenData
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
    private lateinit var dataList: ArrayList<ProfileDTO>
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
        binding.ibMyPageMenu1.setOnClickListener {
            val intent = Intent(activity, MyPageLikeActivity::class.java)
            startActivity(intent)
        }

        // 내가 쓴 리뷰로
        binding.ibMyPageMenu2.setOnClickListener {
            val intent = Intent(activity, MyPageReviewActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        dataList = arrayListOf()
        dataList.apply {
            add(dataList.size, ProfileDTO(0, "추가하기", "추가하기", "R.drawable.ic_baseline_add_24"))
        }
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
            showBasicDialog(this@MyPageFragment.requireContext(),"로그아웃 하시겠습니까?")
            mBasicDialog.setOnItemClickListener(object: BasicDialog.DefaultDialogClickedListener{
                override fun onClick(resultCode: Int) {
                    if(resultCode == YES){
                        dismissBasicDialog()
                        Log.d("로그아웃","YES")
                        onLogoutService()
                    }else if(resultCode==NO){
                        dismissBasicDialog()
                        Log.d("로그아웃","NO")
                    }
                }

            })
        }
        getAllMyProfileService()
    }

    private fun getAllMyProfileService() {
        simyaApi.getMyAllProfile(UserTokenData.accessToken, UserTokenData.refreshToken)
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
                                    dataRVAdapter.notifyItemInserted(i+1)
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MyProfileResponse>, t: Throwable) {
                    Log.d("Response",t.toString())
                }
            })
    }
    private fun onLogoutService(){
        simyaApi.onLogout(UserTokenData.accessToken,UserTokenData.refreshToken).enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val code = response.body()!!.code
                Log.d("status code",code.toString())
                if(code == 200){

                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("Response",t.toString())
            }

        })
    }
    //다이얼로그 메소드
    private fun showBasicDialog(context: Context,title: String){
        mBasicDialog = BasicDialog(context, title)
        mBasicDialog.show()
    }
    private fun dismissBasicDialog(){
        if (mBasicDialog.isShowing) {
            mBasicDialog.dismiss()
        }
    }
    private fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }
    private fun dismissLoadingDialog(){
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

}