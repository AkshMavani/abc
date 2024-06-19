package com.example.delete.image

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.delete.R

class Activity_Notification : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        val btn=findViewById<Button>(R.id.button4)
        btn.setOnClickListener {
           showCustomNotification(this)
        }
    }
    @SuppressLint("MissingPermission")
    fun showCustomNotification(context: Context) {
        val channelId = "custom_notification_channel"
        val channelName = "Custom Notification Channel"

        // Create the NotificationChannel (required for Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Create an Intent for the notification action
        val intent = Intent(context, Activity_Notification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Create the custom notification layout
        val remoteViews = RemoteViews(context.packageName, R.layout.abc).apply {
            setTextViewText(R.id.title, "Custom Title")
            setTextViewText(R.id.message, "This is a custom notification message.")
            setImageViewResource(R.id.image, R.drawable.ic_launcher_background)
        }

        // Build the notification
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)  // Small icon
            .setContent(remoteViews)  // Set the custom content view
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)  // Set the intent that will fire when the user taps the notification
            .setAutoCancel(true)  // Automatically remove the notification when the user taps it

        // Show the notification
        with(NotificationManagerCompat.from(context)) {
            notify(1, builder.build())
        }
    }

}