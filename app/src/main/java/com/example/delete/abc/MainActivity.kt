package com.example.delete.abc

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.delete.R
import com.example.delete.bllotothh.BlurTransformation


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter1
    private val dataList = mutableListOf<String>()

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)
        recyclerView = findViewById(R.id.recycler_view)
        val btn=findViewById<Button>(R.id.view_btn)
        val btn_img=findViewById<Button>(R.id.img_btn)
        var img=findViewById<ImageView>(R.id.img00)
        recyclerView.layoutManager = LinearLayoutManager(this,  LinearLayoutManager.HORIZONTAL, false)
            dataList.add("hlo")
            dataList.add("hlo1")
            dataList.add("hlo2")
            dataList.add("hlo3")
            dataList.add("hlo4")
            dataList.add("hlo5")
            dataList.add("hlo6")
            dataList.add("hlo7")
        adapter = MyAdapter1(dataList)
        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            val newItem = "New Item ${dataList.size + 1}"
            dataList.add(0, newItem) // Add new item at the top of the list
            adapter.notifyDataSetChanged()
        }
        btn.setOnClickListener {
            val intent=Intent(this,MainActivity5::class.java)
            startActivity(intent)
        }
        btn_img.setOnClickListener {
            Glide.with(this)
                .load("https://example.com/image.jpg")
                .transform(BlurTransformation())
                .into(img)
        }
    }
}
class MyAdapter1(private val dataList: MutableList<String>) : RecyclerView.Adapter<MyAdapter1.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = dataList[position]
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.text_view)
    }
}
