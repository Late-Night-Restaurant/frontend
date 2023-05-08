//package com.example.simya.src.ui.view.mypage
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.FragmentTransaction
//import com.example.simya.R
//import com.example.simya.config.BaseActivity
//import com.example.simya.databinding.ActivityMyPageLikeBinding
//import com.example.simya.util.dialog.SortDialog
//import com.example.simya.util.onThrottleClick
//
//class MyPageLikeActivity : BaseActivity<>(ActivityMyPageLikeBinding::inflate)
//{
//    // 보기 방식 설정
//    private var defaultViewType = R.drawable.ic_box_4
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        init()
//
//    }
//
//    private fun init() {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(binding.flMyPageLikeMain.id, MyPageLikeGridFragment())
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            .commit()
//
//        binding.ibMyPageLikeViewType.onThrottleClick {
//            val dialog = SortDialog(this as AppCompatActivity)
//            dialog!!.showDia()
//        }
//
//        binding.ibMyPageLikeViewType.run {
//            onThrottleClick {
//                viewTypeChange()
//                binding.ibMyPageLikeViewType.setImageResource(defaultViewType)
//
//                when (defaultViewType) {
//                    R.drawable.ic_box_4 -> {
//                        supportFragmentManager
//                            .beginTransaction()
//                            .replace(binding.flMyPageLikeMain.id, MyPageLikeGridFragment())
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                    }
//                    R.drawable.ic_box_2 -> {
//                        supportFragmentManager
//                            .beginTransaction()
//                            .replace(binding.flMyPageLikeMain.id, MyPageLikeRecyclerFragment())
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                    }
//
//                }
//                true
//            }
//        }
//    }
//
//    // 보기방식 바꾸는 메소드
//    private fun viewTypeChange(){
//        if (defaultViewType == R.drawable.ic_box_4){
//            defaultViewType = R.drawable.ic_box_2
//        }else{
//            defaultViewType = R.drawable.ic_box_4
//        }
//    }
//}