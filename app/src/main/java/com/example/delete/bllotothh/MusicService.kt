package com.example.delete.bllotothh

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.delete.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var notificationManager: NotificationManager
    private val channelId = "music_player_channel"
    private val notificationId = 1

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.abc) // replace with your music file
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Music Player", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> playMusic()
            ACTION_PAUSE -> pauseMusic()
            ACTION_STOP -> stopMusic()
            ACTION_PREVIOUS -> previousTrack()
            ACTION_NEXT -> nextTrack()
        }
        return START_NOT_STICKY
    }

    private fun playMusic() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            showNotification()
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            showNotification()
        }
    }

    private fun stopMusic() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
            stopForeground(true)
            stopSelf()
        }
    }

    private fun previousTrack() {
        // Implement the logic to play the previous track
        showNotification()
    }

    private fun nextTrack() {
        // Implement the logic to play the next track
        showNotification()
    }

    private fun showNotification() {
        val playPauseAction = if (mediaPlayer.isPlaying) {
            NotificationCompat.Action.Builder(R.drawable.baseline_stop_circle_24, "Pause", getPendingIntent(ACTION_PAUSE)).build()
        } else {
            NotificationCompat.Action.Builder(R.drawable.baseline_play_circle_outline_24, "Play", getPendingIntent(ACTION_PLAY)).build()
        }

        val previousAction = NotificationCompat.Action.Builder(R.drawable.baseline_arrow_left_24, "Previous", getPendingIntent(ACTION_PREVIOUS)).build()
        val nextAction = NotificationCompat.Action.Builder(R.drawable.baseline_arrow_right_24, "Next", getPendingIntent(ACTION_NEXT)).build()
      //  val stopAction = NotificationCompat.Action.Builder(R.drawable.baseline_stop_circle_24, "Stop", getPendingIntent(ACTION_STOP)).build()

        val largeIcon = BitmapFactory.decodeResource(resources, R.drawable.abc) // replace with your large image

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Music Player")
            .setContentText("Playing music")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setLargeIcon(largeIcon)
            .addAction(previousAction)
            .addAction(playPauseAction)
            .addAction(nextAction)
//            .addAction(stopAction)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1, 2))
            .build()

        startForeground(notificationId, notification)
    }

    private fun getPendingIntent(action: String): PendingIntent {
        val intent = Intent(this, MusicService::class.java).apply {
            this.action = action
        }
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    companion object {
        const val ACTION_PLAY = "action_play"
        const val ACTION_PAUSE = "action_pause"
        const val ACTION_STOP = "action_stop"
        const val ACTION_NEXT = "action_next"
        const val ACTION_PREVIOUS = "action_previous"
    }
}
