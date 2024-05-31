package com.example.delete

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    var list: ListView? = null
    var arr:ArrayList<ItemsViewModel> = ArrayList()
   lateinit var recycleview:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         recycleview=findViewById<RecyclerView>(R.id.list)
        recycleview.layoutManager=LinearLayoutManager(this)
        val intent=Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        startActivity(intent)
        val n = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (n.isNotificationPolicyAccessGranted) {
            } else {
                startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
            }
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice,  IntentFilter("Msg"));


    }
    private val onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val packageName = intent.getStringExtra("package")
            val titleData = intent.getStringExtra("title")
            val textData = intent.getStringExtra("text")
            Log.e("TAG", "onReceive:$packageName ")
            Log.e("TAG", "onReceive:$titleData ")
            Log.e("TAG", "onReceive:$textData ")
            arr.add(ItemsViewModel(titleData.toString()))
            var adapter=CustomAdapter(arr)
            recycleview.adapter=adapter
//            val tr = TableRow(applicationContext)
//            tr.layoutParams = TableRow.LayoutParams(
//                TableRow.LayoutParams.MATCH_PARENT,
//                TableRow.LayoutParams.WRAP_CONTENT
//            )
//            val textview = TextView(applicationContext)
//            textview.layoutParams = TableRow.LayoutParams(
//                TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT,
//                1.0f
//            )
//            textview.textSize = 20f
//            textview.setTextColor(Color.parseColor("#0B0719"))
//            textview.text = Html.fromHtml("$packageName<br><b>$titleData : </b>$textData")
//            tr.addView(textview)
//            tab.addView(tr)
        }
    }

}