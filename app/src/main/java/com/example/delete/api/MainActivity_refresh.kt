package com.example.delete.api

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.delete.ItemsViewModel
import com.example.delete.R
import kotlin.random.Random


class MainActivity_refresh : AppCompatActivity() {
    var adapter:CustomAdapter?=null
    var text: ArrayList<ItemsViewModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_refresh)
        val rc=findViewById<RecyclerView>(R.id.recyclerView_refresh)
        val src1=findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)

        rc.layoutManager=LinearLayoutManager(this)
        text.add(ItemsViewModel("Facebook"))
        text.add(ItemsViewModel("WP"))
        text.add(ItemsViewModel("Hello"))
        text.add(ItemsViewModel("i have"))
        text.add(ItemsViewModel("0ne"))
        text.add(ItemsViewModel("tw0"))
        text.add(ItemsViewModel("three"))
        text.add(ItemsViewModel("four"))
         adapter=CustomAdapter(text)
        rc.adapter=adapter
        src1.setOnRefreshListener(OnRefreshListener {

            src1.setRefreshing(true)
            Handler().postDelayed(Runnable {

                src1.setRefreshing(false)
                shuffleRecyclerViewItems()
            },2000 )
        })

    }
    private fun shuffleRecyclerViewItems() {
        text.shuffle(Random(System.currentTimeMillis()))
        adapter?.notifyDataSetChanged()
    }
}