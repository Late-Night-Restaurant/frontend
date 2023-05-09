package com.example.simya.src.ui.view.home

import android.os.Bundle
import android.view.View
import com.example.simya.R
import com.example.simya.config.BaseFragment
import com.example.simya.databinding.FragmentHomeMainBinding

class HomeMainFragment: BaseFragment<FragmentHomeMainBinding>(R.layout.fragment_home_main) {
    private var defaultViewType = R.drawable.ic_box_4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        init()
    }
//    private fun init(){
//        // 기본 View type, Sort type 정의
//        childFragmentManager.beginTransaction()
//            .replace(R.id.fm_home_main, HomeGridFragment())
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            .commit()
//
//        // Sort type 바꾸기 -> 클린코드 필요
//        binding.ibHomeMainSortType.setOnClickListener {
//            val dialog = SortDialog(this.context as AppCompatActivity)
//            dialog!!.showDia()
//            dialog.setOnItemClickListener(object: SortDialog.SortDialogClickedListener{
//                override fun onClick(resultCode: Int) {
//                   when(resultCode){
//                       Constants.SORT_LIKE->{
//                           SampleSnackBar.make(binding.root,"준비중입니다...").show()
//                       }
//                       Constants.SORT_LONG->{
//                           SampleSnackBar.make(binding.root,"준비중입니다...").show()
//                       }
//                       Constants.SORT_RECENT->{
//                           SampleSnackBar.make(binding.root,"준비중입니다...").show()
//                       }
//                   }
//                }
//
//
//            })
//        }

        // View type 바꾸기
//        binding.ibHomeMainViewType.run{
//            setOnClickListener{
//                viewTypeChange()
//                binding.ibHomeMainViewType.setImageResource(defaultViewType)
//                when (defaultViewType){
//                    R.drawable.ic_box_4 ->{
//                        childFragmentManager.beginTransaction()
//                            .replace(R.id.fm_home_main, HomeGridFragment())
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                    }
//                    R.drawable.ic_box_2 ->{
//                        childFragmentManager.beginTransaction()
//                            .replace(R.id.fm_home_main, HomeRecyclerFragment())
//                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                            .commit()
//                    }
//                }
//                true
//            }
//
//        }
//    }
//    보기방식 바꾸는 메소드
//    private fun viewTypeChange(){
//        if (defaultViewType == R.drawable.ic_box_4){
//            defaultViewType = R.drawable.ic_box_2
//        }else{
//            defaultViewType = R.drawable.ic_box_4
//        }
//    }
//
//    // SnackBar 구현
//    private fun onSnackBar(view: View, message: String){
//        var snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
//
//        val snackBarView: View = layoutInflater.inflate(R.layout.snackbar_layout, null)
//        val snackBarBinding = SnackbarLayoutBinding.bind(snackBarView)
//        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
//        snackBarBinding.snackBarMessage.text = message
//
//        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
//        snackBarLayout.addView(snackBarView)
//
//        snackBar.show()
//    }

}