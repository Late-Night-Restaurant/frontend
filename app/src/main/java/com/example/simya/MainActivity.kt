package com.example.simya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simya.databinding.ActivityMainBinding
import com.example.simya.databinding.DialogProfileBinding
import com.example.simya.databinding.DialogProfileMasterBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: DialogProfileMasterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DialogProfileMasterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
//        viewBinding.btnTest.setOnClickListener{
//            val myCustomDialog = MyCustomDialog(this,this)
//            myCustomDialog.show()
//        }
    }

//    override fun testClickYes() {
//        Toast.makeText(this, "예 클릭", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun testClickNo() {
//        Toast.makeText(this, "아니오 클릭", Toast.LENGTH_SHORT).show()
//    }
}