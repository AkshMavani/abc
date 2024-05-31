package com.example.delete

import android.content.Context
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class NotificationService : NotificationListenerService() {
    var context: Context? = null
    var titleData: String? = ""
    var textData = ""
    private val notificationList = ArrayList<ItemsViewModel>()

    override fun onCreate() {
        super.onCreate()
        Log.e("TAG12", "onNotificationPosted: ", )
        context = applicationContext
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        Log.e("TAG12", "onNotificationPosted: ", )
        val packageName = sbn.packageName
        val extras = sbn.notification.extras
        titleData = if (extras.getString("android.title") != null) {
            extras.getString("android.title")
        } else {
            ""
        }
        textData = if (extras.getCharSequence("android.text") != null) {
            extras.getCharSequence("android.text").toString()
        } else {
            ""
        }
        notificationList.add(ItemsViewModel(titleData.toString()))
        Log.e("Package", packageName)
        Log.e("Title", titleData!!)
        Log.e("Text", textData)
        val msgrcv = Intent("Msg")
        msgrcv.putExtra("package", packageName)
        msgrcv.putExtra("title", titleData)
        msgrcv.putExtra("text", textData)
        LocalBroadcastManager.getInstance(context!!).sendBroadcast(msgrcv)
        getdata(notificationList)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.d("Msg", "Notification Removed")
    }

    fun position(position: Int) {
        if (position >= 0 && position < notificationList.size) {
            val notification = notificationList[position]
            Log.e("TAG", "position: $position, title: ${notification.msg}")
        } else {
            Log.e("TAG", "Invalid position: $position")
            Log.e("TAG", "Invalid position: ${notificationList.size}")
        }
    }
    fun getdata(arrayList: ArrayList<ItemsViewModel>):ArrayList<ItemsViewModel>{
        return arrayList
    }

}