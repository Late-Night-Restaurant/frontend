package com.example.simya.util.gallery

import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.simya.config.BaseActivity
import com.example.simya.databinding.ActivityGalleryBinding
import com.example.simya.util.Constants
import kotlinx.coroutines.launch

class GalleryActivity: BaseActivity<ActivityGalleryBinding>(ActivityGalleryBinding::inflate) {
    private val imageList = arrayListOf<Uri>()
    private val adapter = GalleryAdapter()
    private val PERMISSIONS_REQUEST_CODE = 100
    private var REQUIRED_PERMISSIONS = arrayOf<String>(android.Manifest.permission.READ_MEDIA_IMAGES)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    fun init() {
        when {
            // 갤러리 접근 권한이 있는 겨우
            ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                showGallery()
                Log.d("LOG", "갤러리 접근 권한이 있는 경우")
            }
            // 갤러리 접근 권한이 없는 경우 && 교육용 팝업을 보여줘야 하는 경우
            shouldShowRequestPermissionRationale(android.Manifest.permission.READ_MEDIA_IMAGES)
            -> {
                requestPermission()
                Log.d("LOG", "갤러리 접근 권한이 없는 경우 && 교육용 팝업을 보여줘야 하는 경우")
            }
            // 권한 요청 하기
            else -> {
                requestPermission()

            }
        }

        clickImage()
    }


    private fun requestPermission() {
        var permissionCheck = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_MEDIA_IMAGES
        )
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //설명이 필요한지
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            ) {
                //설명 필요 (사용자가 요청을 거부한 적이 있음)
                Log.d("설명", "사용자가 요청을 거부 한적이 있다.")
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            } else {
                //설명 필요하지 않음
                Log.d("설명x", "사용자가 요청을 거부 한적이 있다.")
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            }
        } else {
            Log.d("허용..?", "사용자가 요청을 거부 한적이 있다.")
            //권한 허용
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 허용
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
        binding.rvGallery.adapter = adapter
        adapter.setImageList(imageList)
    }

    private fun clickImage() {
        adapter.setOnItemClickListener(object : GalleryAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: Uri, position: Int) {
                val intent = Intent(this@GalleryActivity,CropperActivity::class.java)
                intent.putExtra("image",data)
                startActivity(intent)
            }

            override fun onLongClick(v: View, data: Uri, position: Int) {
                Log.d("click", "길게 누르지마")
            }

        })
    }
}