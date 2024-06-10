package com.example.delete.sqlite

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.delete.R


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    val CAMERA_REQUEST=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
//        val myIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
//        startActivity(myIntent)

        val intent=Intent(this,MyForegroundService::class.java)
        startService(intent)

    }



}