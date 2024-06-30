package com.example.delete.hloo


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.delete.R
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main15)
        AndroidNetworking.get("https://datausa.io/api/data?drilldowns=Nation&measures=Population")
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Log.e("TAG123", "onResponse:>>$response ", )
                }

                override fun onError(anError: ANError?) {

                }
            })
        //val parentLayout = findViewById<LinearLayout>(R.id.parent_layout)

//        val handler = Handler(Looper.getMainLooper())
//        val delay = 1000L
//
//        for (i in 0..9) {
//            handler.postDelayed({
//                val circleView = CircleView(this)
//                val layoutParams = LinearLayout.LayoutParams(
//                    150,
//                    150 )
//                if (i > 0) {
//                    layoutParams.topMargin = 10 // Margin between circles
//                }
//
//                // Assign a unique ID to each CircleView
//                circleView.id = 1000 + i
//
//                // Add the CircleView to the parent layout
//                parentLayout.addView(circleView, layoutParams)
//            }, i * delay)
//        }
    }
}
