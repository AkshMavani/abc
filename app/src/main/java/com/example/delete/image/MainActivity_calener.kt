package com.example.delete.image

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.delete.R

class MainActivity_calener : AppCompatActivity() {
    private val CALENDAR_PERMISSIONS = arrayOf(
        Manifest.permission.READ_CALENDAR,
        Manifest.permission.WRITE_CALENDAR
    )
    private val REQUEST_CALENDAR_PERMISSIONS = 100
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_calener)
        val btn=findViewById<Button>(R.id.button_calender)
        btn.setOnClickListener {
            if (areCalendarPermissionsGranted()) {
                // Permissions are already granted
                accessCalendar()
            } else {
                // Request permissions
                ActivityCompat.requestPermissions(this, CALENDAR_PERMISSIONS, REQUEST_CALENDAR_PERMISSIONS)
            }
        }

    }
    private fun areCalendarPermissionsGranted(): Boolean {
        for (permission in CALENDAR_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun accessCalendar() {
        // Your code to access the calendar
        Toast.makeText(this, "Calendar access granted", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CALENDAR_PERMISSIONS -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // Permissions granted
                    accessCalendar()
                } else {
                    // Permissions denied
                    Toast.makeText(this, "Calendar permissions denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}