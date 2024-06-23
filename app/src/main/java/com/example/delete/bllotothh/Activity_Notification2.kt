package com.example.delete.bllotothh

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.delete.R

class Activity_Notification2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification3)
        findViewById<Button>(R.id.playButton).setOnClickListener {
            startService(Intent(this, MusicService::class.java).apply {
                action = MusicService.ACTION_PLAY
            })
        }

        findViewById<Button>(R.id.pauseButton).setOnClickListener {
            startService(Intent(this, MusicService::class.java).apply {
                action = MusicService.ACTION_PAUSE
            })
        }

        findViewById<Button>(R.id.stopButton).setOnClickListener {
            startService(Intent(this, MusicService::class.java).apply {
                action = MusicService.ACTION_STOP
            })
        }
    }
}