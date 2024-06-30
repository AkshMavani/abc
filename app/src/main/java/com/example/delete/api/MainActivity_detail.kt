package com.example.delete.api

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.delete.R

class MainActivity_detail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    val adapter:Adapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)
        val img=intent.getIntExtra("IMG",0)
        val nme=intent.getStringExtra("NME")
        val imgview=findViewById<ImageView>(R.id.imageView6)
        val imgview_heart=findViewById<ImageView>(R.id.imageView_heart)
        imgview.setImageResource(img)
        Log.e("TAG", "nme:$nme ", )
        var isHeartFilled = false
        val db=DBHandler(this)
        val a=  db.readCourses()
        if (a != null) {
            for (i in a){
                Log.e("TAG", "onCreate:$i ", )
                if (i.name==nme){
                    if (i.img==1){
                        isHeartFilled = !isHeartFilled
                        updateHeartIcon(isHeartFilled,imgview_heart)
                        imgview_heart.setImageResource(R.drawable.baseline_favorite_24)
                        break
                    }
                }
            }
        }
        imgview_heart.setOnClickListener {

            isHeartFilled = !isHeartFilled
            updateHeartIcon(isHeartFilled,imgview_heart)
//
//
           val a= db.heartStateExists(nme.toString())
            Log.e("TAG", "onCreate:>>>>>>>>>>$a ", )
            if (a){
                db.updateHeartState(isHeartFilled,nme.toString())
            }else{
                db.addNewCourse(isHeartFilled,nme.toString())
            }
        }

    }
    private fun updateHeartIcon(isFilled: Boolean,img:ImageView) {
        if (isFilled) {
            img.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            img.setImageResource(R.drawable.baseline_favorite_border_24)
        }

    }
}