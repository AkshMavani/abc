package com.example.delete.image

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.delete.R
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity_cal : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cal)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CALENDAR), PERMISSION_REQUEST_CODE)
        } else {
            getSystemNextAlarmDetails(this)
        }
    }
    @SuppressLint("SimpleDateFormat", "ObsoleteSdkInt")
    fun getSystemNextAlarmDetails(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmClockInfo = alarmManager.nextAlarmClock

            if (alarmClockInfo != null) {
                val triggerTime = alarmClockInfo.triggerTime
                val alarmIntent = alarmClockInfo.showIntent
                Toast.makeText(context, "Next alarm set for: ${java.util.Date(triggerTime)}", Toast.LENGTH_LONG).show()
                Log.e("TAG", "getSystemNextAlarmDetails:>>>>>>>>>>${java.util.Date(triggerTime)} ")
                val calendar: Calendar = Calendar.getInstance()
                val sdf = SimpleDateFormat("dd/MM/yy/MMM")
                val currentDate: String = sdf.format(java.util.Date(triggerTime))
                Log.e("TAG", "getSystemNextAlarmDetails: $currentDate", )
            } else {
                Toast.makeText(context, "No system alarms are set.", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "This feature is not available on your device.", Toast.LENGTH_LONG).show()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getSystemNextAlarmDetails(this)
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}