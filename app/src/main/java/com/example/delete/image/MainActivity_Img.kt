package com.example.delete.image

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder


class MainActivity_Img : AppCompatActivity() {
    val arr:ArrayList<Model_String> = ArrayList()
    private var currentBackgroundColor =  0xffffffff
    private val root: View? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_img)
        var brn:Button=findViewById(R.id.btn_main_img)
        brn.setOnClickListener {
            ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(currentBackgroundColor.toInt())
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(object : OnColorSelectedListener {
                    override fun onColorSelected(selectedColor: Int) {
                        Log.e("TAG", "onColorSelected: $selectedColor", )
                        // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor))
                    }
                })
                .setPositiveButton("ok", object : ColorPickerClickListener {
                    override fun onClick(dialog: DialogInterface?, selectedColor: Int, allColors: Array<Int?>?) {
                        changeBackgroundColor(selectedColor)
                    }
                })
                .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> })
                .build()
                .show()
        }

        val rc:RecyclerView=findViewById(R.id.recycleView_delete)
        arr.add(Model_String("1hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("2hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("3hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("4hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("5hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("6hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("7hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("8hlo world i m looking fo a job in android developer"))
        arr.add(Model_String("9hlo world i m looking fo a job in android developer"))
        val adapter=MyAdapter(arr,this)
        rc.layoutManager=LinearLayoutManager(this)
        rc.adapter=adapter
    }
    private fun changeBackgroundColor(selectedColor: Int) {
        currentBackgroundColor = selectedColor.toLong()
        root?.setBackgroundColor(selectedColor)
    }
}