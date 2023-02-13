package com.example.simya.util.gallery

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityCropperBinding
import com.example.simya.databinding.ActivityGalleryBinding
import com.takusemba.cropme.OnCropListener
import java.io.ByteArrayOutputStream

class CropperActivity: BaseActivity<ActivityCropperBinding>(ActivityCropperBinding::inflate)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    private fun init(){
        val cropLayout = binding.clCropper
        cropLayout.setUri(intent.getParcelableExtra("image")!!)
        binding.btnCropper.setOnClickListener {
            cropLayout.crop()
        }
        cropLayout.addOnCropListener(object : OnCropListener {
            // 성공했을 때,
            override fun onSuccess(bitmap: Bitmap) {
//                val intent = Intent(this@CropperActivity, 이동할 곳)
//                val image = getImageUri(this@CropperActivity,bitmap)
//                intent.putExtra("crop", image2)
                Log.d("success", "성공")
//                startActivity(intent)
            }
            // 실패했을 때,
            override fun onFailure(e: Exception) {
                Log.e("Failure", "$e")
                Log.d("Failure", "failed")
            }
        })
    }
    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }
}