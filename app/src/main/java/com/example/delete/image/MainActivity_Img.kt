package com.example.delete.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.CustomAdapter
import com.example.delete.R

class MainActivity_Img : AppCompatActivity() {
    val arr:ArrayList<Model_String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_img)
        val rc:RecyclerView=findViewById(R.id.recycleView_delete)
        arr.add(Model_String("1hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("2hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("3hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("4hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("5hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("6hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("7hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("8hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("9hlo world i m looking fo a job in android developer"))
        val adapter=MyAdapter(arr)
        rc.layoutManager=LinearLayoutManager(this)
        rc.adapter=adapter
    }
}