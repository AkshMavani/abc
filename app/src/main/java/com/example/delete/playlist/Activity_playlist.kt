package com.example.delete.playlist

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Activity_playlist : AppCompatActivity() {
    val REQUEST_CODE_PERMISSION = 100
    val REQUEST_CODE_PICK_MUSIC = 200
    private var mediaPlayer: MediaPlayer? = null

    var arr:ArrayList<Music> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)
        checkPermission()
        loadPlaylists()
    }
    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("playlists_pref", Context.MODE_PRIVATE)
    }
    private val gson = Gson()
    private fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickMusicFiles()
                true
            } else {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION)
                false
            }
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickMusicFiles()
        } else {
            // Handle the case where the user denies the permission
        }
    }

    private fun pickMusicFiles() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "audio/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        startActivityForResult(intent, REQUEST_CODE_PICK_MUSIC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_MUSIC && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                if (data.clipData != null) {
                    // Handle multiple selections
                    for (i in 0 until data.clipData!!.itemCount) {
                        val uri = data.clipData!!.getItemAt(i).uri
                        Log.e("TAG", "onActivityResult: $uri")
                        retrieveMusicDetails(uri)
                    }
                } else if (data.data != null) {
                    // Handle single selection
                    val uri = data.data!!
                    Log.e("TAG0", "onActivityResult: $uri")
                    retrieveMusicDetails(uri)
                }
            }
        }
    }

    private fun retrieveMusicDetails(url: Uri) {


        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
        )

        val cursor = contentResolver.query(
            url,
            projection,
            null,
            null,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {
                val idColumn = it.getColumnIndex(MediaStore.Audio.Media._ID)
                val nameColumn = it.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)
                val durationColumn = it.getColumnIndex(MediaStore.Audio.Media.DURATION)

                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val duration = it.getInt(durationColumn)

                Log.e("TAG", "ID: $id, Name: $name, Duration: $duration")

                arr.add(Music(0,name,duration,"",url.toString()))

                savePlaylists()
                loadPlaylists()
                // do something with the id, name, and duration
            } else {
                Log.e("TAG", "Cursor is empty or doesn't contain valid data")
            }
        }
    }
    private fun savePlaylists() {
       // arr.clear()
        val json = gson.toJson(arr)
        Log.e("TAG", "savePlaylists:$json ")
        sharedPreferences.edit().putString("playlists_key1", json).apply()
    }

    private fun loadPlaylists() {
        val json = sharedPreferences.getString("playlists_key1", null)
        if (json != null) {
            val type = object : TypeToken<MutableList<Music>>() {}.type
            val loadedPlaylists: MutableList<Music> = gson.fromJson(json, type)
            arr.addAll(loadedPlaylists)
            val rc=findViewById<RecyclerView>(R.id.rc)
            rc.layoutManager=LinearLayoutManager(this)
            var data=object :click_url{
                override fun url_click(url: String) {
                   retrieveMusic(url.toUri())
                }

            }
            val adapter=CustomAdapter(arr,data)
            rc.adapter=adapter
        }
    }
    private fun retrieveMusic(url: Uri) {
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer?.reset()
            mediaPlayer?.setDataSource(this, url)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } catch (e: Exception) {
            Log.e("TAG", "Error playing music: ${e.message}")
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}
