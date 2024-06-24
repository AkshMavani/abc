package com.example.delete.hloo

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.delete.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main15)

        val parentLayout = findViewById<LinearLayout>(R.id.parent_layout)

        val handler = Handler(Looper.getMainLooper())
        val delay = 1000L // Delay in milliseconds (10 seconds)

        for (i in 0..9) {
            handler.postDelayed({
                // Create an instance of the CircleView
                val circleView = CircleView(this)

                // Create layout parameters for the CircleView
                val layoutParams = LinearLayout.LayoutParams(
                    150,  // Width in pixels
                    150 // Height in pixels
                )

                // Optionally, set some layout parameters
                // Here, we will position each view below the previous one
                if (i > 0) {
                    layoutParams.topMargin = 10 // Margin between circles
                }

                // Assign a unique ID to each CircleView
                circleView.id = 1000 + i

                // Add the CircleView to the parent layout
                parentLayout.addView(circleView, layoutParams)
            }, i * delay)
        }
    }
}