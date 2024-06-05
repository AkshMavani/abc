package com.example.delete.image

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri
import com.example.delete.R

class MainActivity2 : AppCompatActivity() {
    var count=0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        val img=findViewById<ImageView>(R.id.imageView_main5)
        var save=findViewById<Button>(R.id.button_save)
        val intent2=intent.getStringExtra("image")
        var intent1=intent.getIntExtra("img",0)
        Log.e("TAG", "onCreate:$intent1 ",)
        Log.e("TAG", "onCreate:$intent2 ",)

        if (intent2?.isNotEmpty() == true){
            img.setImageURI(intent2.toUri())
        }else{
            img.setImageResource(intent1)
        }

        val db=DBHandler(this)
        save.setOnClickListener {
            if (intent2?.isNotEmpty() == true){
                count++
                intent1=count
            }
            val check=db.valueExists(intent1.toString())
            Log.e("TAG", "onCreate:>>>>>>>>>>$intent1 ", )
            Log.e("TAG", "onCreate:$check ", )

            if (check){
                db.updateCourse(intent1,intent2.toString())
            }else{
                db.addNewCourse(intent1,intent2.toString())
            }

        }
    }
}