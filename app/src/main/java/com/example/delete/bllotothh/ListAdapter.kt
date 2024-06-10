package com.example.delete.bllotothh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R

class ListAdapter(var devices: ArrayList<Model>,var itemClick: RecylerviewCliCK) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = devices[position].name
        holder.tvName.setOnClickListener { itemClick?.itemClick(devices[position].address) }
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView

        init {
            tvName = itemView.findViewById<TextView>(R.id.itemTextView)
        }
    }
}