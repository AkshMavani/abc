package com.example.delete

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var colorAdapter: ColorAdapter


    private var gfgTextView: TextView? = null

    // two buttons to open color picker dialog and one to
    // set the color for GFG text
    private var mSetColorButton: Button? = null
    private var mPickColorButton: Button? = null

    // view box to preview the selected color
    private var mColorPreview: View? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val colors = listOf(Color.RED, Color.GREEN, Color.BLUE,Color.CYAN,Color.DKGRAY,Color.MAGENTA,Color.YELLOW) // Example list of colors

        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        colorAdapter = ColorAdapter(colors,
            onColorSelected = { color ->
                // Handle color selection
            },
            onPickColor = {
                showColorPicker()
            })

        recyclerView.adapter = colorAdapter
    }
    private fun showColorPicker() {

    }
}