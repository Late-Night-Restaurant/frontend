package com.example.simya.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.simya.Constants
import com.example.simya.data.UserTokenData
import com.example.simya.databinding.ActivityMainBinding
import com.example.simya.databinding.ActivitySplashBinding
import com.example.simya.sharedpreferences.Shared

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    private val splashHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onShared()
        init()
    }

//    )
    /*  달이 밝게 비춤
    **  달이 이동
    **  달과 별이 나타남 + 도시 나타남
    **  도시에 불이 켜짐
    **  메인 타이틀
    **  인트로
    **  타이틀+ 인트로만 남김 */
    private fun init() {

    splashHandler.postDelayed(
            Runnable { sequenceOne() }, 1000
        )
    splashHandler.postDelayed(
            Runnable { sequenceTwo() }, 3000

        )
    splashHandler.postDelayed(
            Runnable { sequenceThree() }, 5000
        )
    splashHandler.postDelayed(
            Runnable { sequenceFour() }, 7000
        )
    splashHandler.postDelayed(
            Runnable { sequenceFive() }, 9000
        )
    splashHandler.postDelayed(
            Runnable { sequenceSix() }, 11000
        )
    splashHandler.postDelayed(
            Runnable { sequenceLast() }, 13000
        )
    splashHandler.postDelayed(
            Runnable { moveToHome() }, 16000
        )
    }
    // 페이드인 애니메이션
    private fun fadeIn(imageView: ImageView) {
        val fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        fadeIn.duration = 2000
        fadeIn.start()
    }
    // 페이드 아웃 애니메이션
    private fun fadeOut(imageView: ImageView) {
        val fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f)
        fadeOut.duration = 1500
        fadeOut.start()
        fadeOut.addListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                imageView.isInvisible = true
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}

        })
    }
    // 스플래쉬 시퀀스
    private fun sequenceOne() {
        binding.ivSplash2.isInvisible = false
        fadeIn(binding.ivSplash2)
    }

    private fun sequenceTwo() {
        fadeOut(binding.ivSplash1)
        fadeOut(binding.ivSplash2)
        binding.ivSplash3.isInvisible = false
        fadeIn(binding.ivSplash3)
    }

    private fun sequenceThree() {
        fadeOut(binding.ivSplash3)
        binding.ivSplash4Moon.isInvisible = false
        binding.ivSplash4Building.isInvisible = false
        fadeIn(binding.ivSplash4Moon)
        fadeIn(binding.ivSplash4Building)
    }

    private fun sequenceFour() {
        binding.ivSplash5.isInvisible = false
        fadeIn(binding.ivSplash5)
    }

    private fun sequenceFive() {
        binding.ivSplash6.isInvisible = false
        fadeIn(binding.ivSplash6)
    }

    private fun sequenceSix() {
        binding.ivSplash7.isInvisible = false
        fadeIn(binding.ivSplash7)
    }
    private fun sequenceLast() {
        fadeOut(binding.ivSplash4Moon)
        fadeOut(binding.ivSplash4Building)
        fadeOut(binding.ivSplash5)
    }
    // 홈으로 이동
    private fun moveToHome(){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    // SharedPreferences
    private fun onShared(){
        UserTokenData.init(Shared.prefs.getString("accessToken",Constants.DEFAULT),Shared.prefs.getString("refreshToken",Constants.DEFAULT))
        Log.d("User AccessToken",UserTokenData.getUserAccessToken())
        Log.d("User RefreshToken",UserTokenData.getUserRefreshToken())
    }

    // 테스트용 터치 이벤트
    override fun onTouchEvent(event: MotionEvent): Boolean {
        splashHandler.removeCallbacksAndMessages(null)
        moveToHome()
        return true
    }
}