package com.example.delete.abc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.delete.R

class MainActivity2 : AppCompatActivity() {
    private lateinit var adapter: MyAdapter1
    private val dataList = mutableListOf<String>()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)
        val btn=findViewById<Button>(R.id.btn_submit)
        dataList.add("hlo")
        dataList.add("hlo1")
        dataList.add("hlo2")
        dataList.add("hlo3")
        dataList.add("hlo4")
        dataList.add("hlo5")
        dataList.add("hlo6")
        dataList.add("hlo7")
        btn.setOnClickListener {
                val newItem = "New Item ${dataList.size + 1}"
                dataList.add(0, newItem) // Add new item at the top of the list
                adapter.notifyDataSetChanged()
        }
    }
}