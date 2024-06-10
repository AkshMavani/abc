package com.example.delete.sqlite

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.delete.R


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    val CAMERA_REQUEST=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        startActivity(myIntent)

//        val intent=Intent(this,MyForegroundService::class.java)
//        startService(intent)
        check()
    }
    fun check(){
        val dialog= Dialog(this,R.style.CustomAlertDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_location)
        dialog.show()
    }



}