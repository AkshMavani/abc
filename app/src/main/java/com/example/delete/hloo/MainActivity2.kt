package com.example.delete.hloo


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.delete.R


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main16)
        val myButton = findViewById<Button>(R.id.my_button)

        myButton.setOnLongClickListener { v -> // Show the popup window or dialog here
            showPopupWindow(v)
            true
        }
    }
    @SuppressLint("MissingInflatedId")
    private fun showPopupWindow(view: View) {
        // Inflate the popup window layout
        val popupView: View = layoutInflater.inflate(R.layout.popup_window1, null)

        // Create a popup window
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        // Set the popup window background
        popupWindow.setBackgroundDrawable(resources.getDrawable(R.drawable.popup_background1))

        // Show the popup window
        popupWindow.showAsDropDown(view, 0, 0)

        // Get the buttons in the popup window
        val selectButton = popupView.findViewById<Button>(R.id.select_button)
        val addToHomeButton = popupView.findViewById<Button>(R.id.add_to_home_button)
        val uninstallButton = popupView.findViewById<Button>(R.id.uninstall_button)

        // Set the button listeners
        selectButton.setOnClickListener {
            Toast.makeText(
                this@MainActivity2,
                "Select button clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        addToHomeButton.setOnClickListener {
            Toast.makeText(
                this@MainActivity2,
                "Add to Home button clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
        uninstallButton.setOnClickListener {
            Toast.makeText(
                this@MainActivity2,
                "Uninstall button clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}