package com.example.delete.playlist

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlaylistAdapter
    private val playlists = ArrayList<Playlist>()
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("playlists_pref", Context.MODE_PRIVATE)
    }
    private val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activit_main13)
//        recyclerView = findViewById(R.id.recyclerView)
//        val btnAdd: Button = findViewById(R.id.btnAdd)
//        val obj=object : Click{
//            override fun data(playlist: Playlist) {
//                removePlaylist(playlist)
//                savePlaylists()
//            }
//
//        }
//        adapter = PlaylistAdapter(playlists,obj,this)
//        recyclerView.adapter = adapter
//        val layoutManager = GridLayoutManager(this, 2)
//        recyclerView.layoutManager = layoutManager
//
//        btnAdd.setOnClickListener {
//            showAddPlaylistDialog()
//        }
//
//        loadPlaylists()
//    }
//    private fun showAddPlaylistDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Add Playlist")
//
//        val input = EditText(this)
//        input.inputType = InputType.TYPE_CLASS_TEXT
//        builder.setView(input)
//
//        builder.setPositiveButton("Add") { dialog, _ ->
//            val playlistName = input.text.toString()
//            if (playlistName.isNotEmpty()) {
//                val newPlaylist = Playlist(playlistName)
//                addPlaylist(newPlaylist)
//            }
//            dialog.dismiss()
//        }
//
//        builder.setNegativeButton("Cancel") { dialog, _ ->
//            dialog.cancel()
//        }
//
//        builder.show()
//    }
//
//    private fun addPlaylist(playlist: Playlist) {
//        playlists.add(playlist)
//      //  adapter.notifyItemInserted(playlists.size - 1)
//        savePlaylists()
//    }
//    private fun removePlaylist(playlist: Playlist) {
//        playlists.remove(playlist)
//        savePlaylists()
//    }
//
//
//    private fun savePlaylists() {
//        val json = gson.toJson(playlists)
//        Log.e("TAG", "savePlaylists:$json ")
//        sharedPreferences.edit().putString("playlists_key", json).apply()
//    }
//
//    private fun loadPlaylists() {
//        val json = sharedPreferences.getString("playlists_key", null)
//        if (json != null) {
//            val type = object : TypeToken<MutableList<Playlist>>() {}.type
//            val loadedPlaylists: MutableList<Playlist> = gson.fromJson(json, type)
//            playlists.addAll(loadedPlaylists)
//        }
//    }
    }
}