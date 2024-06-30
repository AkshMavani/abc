package com.example.delete.api

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.example.delete.R


class Adapter(private val mList: List<Model_abc>,val context:Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_api, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.img.setImageResource(ItemsViewModel.img)
        holder.itemView.setOnClickListener {
            val intent=Intent(context,MainActivity_detail::class.java)
            intent.putExtra("IMG",ItemsViewModel.img)
            intent.putExtra("NME",ItemsViewModel.name)
            context.startActivity(intent)
        }
        val db=DBHandler(context)
        var read=db.readCourses()
        if (read != null) {
            for (i in read){
                if (i.name==mList.get(position).name){
                    if (i.img==1){
                        holder.img_heart.visibility=View.VISIBLE
                        holder.img_heart.setImageResource(R.drawable.baseline_favorite_24)
                    }else{
                        holder.img_heart.visibility=View.GONE
                    }

                }
            }
        }
    }
    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val img: ImageView = itemView.findViewById(R.id.imageView_apidesign)
        val img_heart: ImageView = itemView.findViewById(R.id.imageView7)


    }
}