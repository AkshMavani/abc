package com.example.delete.image


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R


//class Custom_Adapter1(private val mList: List<Model_String>) : RecyclerView.Adapter<Custom_Adapter1.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.ping_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val ItemsViewModel = mList[position]
//        holder.textView.text=ItemsViewModel.data.toString()
//
//
//    }
//
//    override fun getItemCount(): Int {
//        return mList.size
//    }
//
//    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        val textView: TextView = itemView.findViewById(R.id.txtView)
//
//
//    }
//}
//class MyAdapter(private val items: MutableList<String>, var context: Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//
//    private var isSingleItemVisible = true
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textView: ImageView = itemView.findViewById(R.id.imageView_layout)
//        val cl: ConstraintLayout = itemView.findViewById(R.id.cl)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.ping_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    @SuppressLint("SuspiciousIndentation")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//       val itemViewModel = items[position]
//        holder.textView.setImageResource(itemViewModel)
//        holder.itemView.setOnClickListener {
//            val intent=Intent(context,MainActivity2::class.java)
//            intent.putExtra("img",itemViewModel)
//            context.startActivity(intent)
//        }
////        holder.itemView.visibility = if (isSingleItemVisible && position != 0) View.GONE else View.VISIBLE
////
////        holder.itemView.setOnClickListener {
////            if (isSingleItemVisible && position == 0) {
////                isSingleItemVisible = false
////                notifyDataSetChanged()
////            }
////        }
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//}

