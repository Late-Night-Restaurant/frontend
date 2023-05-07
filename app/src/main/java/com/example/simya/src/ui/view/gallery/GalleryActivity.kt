package com.example.simya.src.ui.view.gallery

import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.simya.R
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityCropImageBinding
import com.example.simya.util.Constants.IMAGE_PATH
import com.example.simya.util.Constants.IMAGE_URI
import com.example.simya.util.Constants.REQUEST_CODE_FOR_INTENT
import com.example.simya.util.Constants.REQUEST_CODE_PERMISSIONS
import com.example.simya.util.onThrottleClick
import com.takusemba.cropme.CropLayout
import com.takusemba.cropme.OnCropListener
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class GalleryActivity: BaseActivity<ActivityCropImageBinding>(R.layout.activity_crop_image) {
    private val imageList = arrayListOf<Uri>()
    private lateinit var cropLayout: CropLayout

    private var REQUIRED_PERMISSIONS =if(Build.VERSION.SDK_INT <33){
        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }else{
        arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
    }
    override fun init() {
        cropLayout = binding.clCropper
        binding.ibCropAble.onThrottleClick {
            cropLayout.crop()
        }
        cropLayout.addOnCropListener(object : OnCropListener {
            // 성공했을 때,
            override fun onSuccess(bitmap: Bitmap) {
                val image = getImageUri(this@GalleryActivity,bitmap)
                val path = absolutelyPath(image!!)
                intent.putExtra(IMAGE_URI,image.toString())
                intent.putExtra(IMAGE_PATH,path)
                setResult(REQUEST_CODE_FOR_INTENT,intent)
                finish()
            }
            // 실패 했을때,
            override fun onFailure(e: Exception) {
                Log.e("Failure", "$e")
                Log.d("Failure", "failed")
            }
        })
        when {
            // 갤러리 접근 권한이 있는 겨우
            ContextCompat.checkSelfPermission(
                this,
                REQUIRED_PERMISSIONS[0]
            ) == PackageManager.PERMISSION_GRANTED -> {
                showGallery()
                Log.d("LOG", "갤러리 접근 권한이 있는 경우")
            }
            // 갤러리 접근 권한이 없는 경우 && 교육용 팝업
            shouldShowRequestPermissionRationale(REQUIRED_PERMISSIONS[0])
            -> {
                requestPermission()
                Log.d("LOG", "갤러리 접근 권한이 없는 경우 && 교육용 팝업")
            }
            // 권한 요청 하기
            else -> {
                requestPermission()

            }
        }
//        clickImage()
    }


    private fun requestPermission() {
        var permissionCheck = ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSIONS[0]
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //설명이 필요한지
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    REQUIRED_PERMISSIONS[0]
                )
            ) {
                //설명 필요 (사용자가 요청을 거부한 적이 있음)
                Log.d("설명", "사용자가 요청을 거부 한적이 있다.")
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            } else {
                //설명 필요하지 않음
                Log.d("설명x", "사용자가 요청을 거부 한적이 있다.")
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS
                )
            }
        } else {
            //권한 허용
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showGallery()
                } else {
                    //권한 거부됨
                }
                return
            }
        }
    }

    private fun getCursor(): Cursor? {
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATE_TAKEN
        )
        val selection = null
        val selectionArgs = null
        val sortOrder = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"

        return contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

    }

    private fun showGallery() {
        lifecycleScope.launch { //비동기 처리
            Log.d("비동기 시작", "ShowGallery")
            try {
                val cursor = getCursor()
                when (cursor?.count) {
                    null -> {
                        /*
                         * 에러 대응 코드 작성. cursor 사용하지 말것!!
                         * You may want to call android.util.Log.e() to log this error.
                         *
                         */
                        Log.d("NULL", "NULL")
                    }
                    0 -> {
                    }
                    else -> {
                        while (cursor.moveToNext()) {
                            //1. 각 컬럼의 열 인덱스를 취득한다.
                            val idColNum =
                                cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                            val titleColNum =
                                cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE)
                            val dateTakenColNum =
                                cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)

                            val id = cursor.getLong(idColNum)
                            val title = cursor.getString(titleColNum)
                            val dateTaken = cursor.getLong(dateTakenColNum)
                            val imageUri =
                                ContentUris.withAppendedId(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    id
                                )

                            imageList.add(imageUri)//recylcerview에 넣기 위한 uri list

                            Log.d(
                                "LOGGING",
                                "id: ${id}, title:$title, dateTaken : $dateTaken, imageUri : $imageUri"
                            )

                        }
                        cursor.close() //사용한 cursor는 꼭 close 해줘야함

                    }

                }
            } catch (e: Exception) {
                //에러 대응 코드 작성
                Toast.makeText(this@GalleryActivity, "에러 스토리지에 접근 권한을 허가해주세요", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }

        }
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
    fun absolutelyPath(path: Uri): String {

        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = contentResolver.query(path, proj, null, null, null)
        val index = c!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c.moveToFirst()
        return c.getString(index)
    }



}