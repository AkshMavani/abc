package com.example.delete.playlist

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R

class PlaylistAdapter(private val playlists: ArrayList<Playlist>,var click: Click,var context: Context) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPlaylistName: TextView = itemView.findViewById(R.id.tvPlaylistName)
        val delete: ImageView = itemView.findViewById(R.id.img_delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.tvPlaylistName.text = playlist.name
        Log.e("TAG", "onBindViewHolder:${playlists.size} ", )
        holder.delete.setOnClickListener {
            playlists.removeAt(position)
         //   notifyItemRemoved(position)
            click.data(playlist)
            notifyDataSetChanged()
        }
        holder.itemView.setOnClickListener {
            val intent=Intent(context,Activity_playlist::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = playlists.size


}

