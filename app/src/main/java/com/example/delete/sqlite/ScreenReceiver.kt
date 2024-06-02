package com.example.delete.sqlite

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startForegroundService


class ScreenReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_ON) {
            Log.e("TAG", "onReceive:On ", )
            val serviceIntent = Intent(context, MyForegroundService::class.java)
            serviceIntent.putExtra("a",true)
            serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startService(serviceIntent)

        } else if (intent.action == Intent.ACTION_SCREEN_OFF) {
            Log.e("TAG", "onReceive:off ", )
        }
    }
}