package com.example.simya.src.main.home.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.util.Constants
import com.example.simya.R
import com.example.simya.databinding.FragmentHomeMainBinding
import com.example.simya.databinding.SnackbarLayoutBinding
import com.example.simya.util.SampleSnackBar
import com.example.simya.util.dialog.SortDialog
import com.google.android.material.snackbar.Snackbar

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeMainBinding
    private var defaultViewType = R.drawable.ic_box_4

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init(){
        // 기본 View type, Sort type 정의
        childFragmentManager.beginTransaction()
            .replace(R.id.fm_home_main, HomeGridFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        // Sort type 바꾸기 -> 클린코드 필요
        binding.ibHomeMainSortType.setOnClickListener {
            val dialog = SortDialog(this.context as AppCompatActivity)
            dialog!!.showDia()
            dialog.setOnItemClickListener(object: SortDialog.SortDialogClickedListener{
                override fun onClick(resultCode: Int) {
                   when(resultCode){
                       Constants.SORT_LIKE->{
                           SampleSnackBar.make(binding.root,"최근 찜 순 정렬").show()
                       }
                       Constants.SORT_LONG->{
                           SampleSnackBar.make(binding.root,"오랫동안 들은 순 정렬").show()
                       }
                       Constants.SORT_RECENT->{
                           SampleSnackBar.make(binding.root,"즐겨 찾는 순 정렬").show()
                       }
                   }
                }


            })
        }

        // View type 바꾸기
        binding.ibHomeMainViewType.run{
            setOnClickListener{
                viewTypeChange()
                binding.ibHomeMainViewType.setImageResource(defaultViewType)
                when (defaultViewType){
                    R.drawable.ic_box_4 ->{
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fm_home_main, HomeGridFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.drawable.ic_box_2 ->{
                        childFragmentManager.beginTransaction()
                            .replace(R.id.fm_home_main, HomeRecyclerFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                }
                true
            }

        }
    }
//    보기방식 바꾸는 메소드
    private fun viewTypeChange(){
        if (defaultViewType == R.drawable.ic_box_4){
            defaultViewType = R.drawable.ic_box_2
        }else{
            defaultViewType = R.drawable.ic_box_4
        }
    }

    // SnackBar 구현
    private fun onSnackBar(view: View, message: String){
        var snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)

        val snackBarView: View = layoutInflater.inflate(R.layout.snackbar_layout, null)
        val snackBarBinding = SnackbarLayoutBinding.bind(snackBarView)
        snackBar.view.setBackgroundColor(Color.TRANSPARENT)
        snackBarBinding.snackBarMessage.text = message

        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        snackBarLayout.addView(snackBarView)

        snackBar.show()
    }

}