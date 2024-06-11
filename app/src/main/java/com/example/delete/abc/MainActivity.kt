package com.example.delete.abc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter1
    private val dataList = mutableListOf<String>()

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
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
