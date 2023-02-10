package com.example.simya.src.main.myPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.ActivityMyPageLikeBinding
import com.example.simya.util.dialog.SortDialog
import com.example.simya.src.main.myPage.fragment.MyPageLikeGridFragment
import com.example.simya.src.main.myPage.fragment.MyPageLikeRecyclerFragment

class MyPageLikeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageLikeBinding
    // 보기 방식 설정
    private var defaultViewType = R.drawable.ic_box_4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageLikeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init() {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.flMyPageLikeMain.id, MyPageLikeGridFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        binding.ibMyPageLikeViewType.setOnClickListener {
            val dialog = SortDialog(this as AppCompatActivity)
            dialog!!.showDia()
        }

        binding.ibMyPageLikeViewType.run {
            setOnClickListener {
                viewTypeChange()
                binding.ibMyPageLikeViewType.setImageResource(defaultViewType)

                when (defaultViewType) {
                    R.drawable.ic_box_4 -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.flMyPageLikeMain.id, MyPageLikeGridFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                    R.drawable.ic_box_2 -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.flMyPageLikeMain.id, MyPageLikeRecyclerFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }

                }
                true
            }
        }
    }

    // 보기방식 바꾸는 메소드
    private fun viewTypeChange(){
        if (defaultViewType == R.drawable.ic_box_4){
            defaultViewType = R.drawable.ic_box_2
        }else{
            defaultViewType = R.drawable.ic_box_4
        }
    }
}