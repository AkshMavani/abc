package com.example.delete.image

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.delete.R


class MainActivity_Img : AppCompatActivity() {
    val arr:ArrayList<Int> = ArrayList()
    private var currentBackgroundColor =  0xffffffff
    private val root: View? = null
    private val REQUEST_CODE_GALLERY = 100
    private val REQUEST_CODE_PERMISSION = 200
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_img)
        var brn:Button=findViewById(R.id.btn_main_img)
        var brn3:Button=findViewById(R.id.button3)
        var id:ConstraintLayout=findViewById(R.id.abc)
            brn3.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION)
                    } else {
                        openGallery()
                    }
                } else {
                    openGallery()
                }
            }
//        brn.setOnClickListener {
//            ColorPickerDialogBuilder
//                .with(this)
//                .setTitle("Choose color")
//                .initialColor(currentBackgroundColor.toInt())
//                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
//                .density(12)
//                .setOnColorSelectedListener(object : OnColorSelectedListener {
//                    override fun onColorSelected(selectedColor: Int) {
//                        Log.e("TAG", "onColorSelected: $selectedColor", )
//                        // toast("onColorSelected: 0x" + Integer.toHexString(selectedColor))
//                    }
//                })
//                .setPositiveButton("ok", object : ColorPickerClickListener {
//                    override fun onClick(dialog: DialogInterface?, selectedColor: Int, allColors: Array<Int?>?) {
//                        changeBackgroundColor(selectedColor)
//                    }
//                })
//                .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, which -> })
//                .build()
//                .show()
//        }

        val rc:RecyclerView=findViewById(R.id.recycleView_delete)
        val aniSlide: Animation = AnimationUtils.loadAnimation(this, R.anim.up)
        rc.startAnimation(aniSlide)
        arr.add(R.drawable.def)
        arr.add(R.drawable.ghi)
        arr.add(R.drawable.jk)
        arr.add(R.drawable.kl)
        arr.add(R.drawable.mn)
        arr.add(R.drawable.op)
        arr.add(R.drawable.qr)
        arr.add(R.drawable.rs)
        val adapter=MyAdapter(arr,this)
        val layoutManager = GridLayoutManager(this, 3)
        rc.layoutManager=layoutManager
        rc.adapter=adapter
        brn.setOnClickListener {


        }
    }
    private fun changeBackgroundColor(selectedColor: Int) {
        currentBackgroundColor = selectedColor.toLong()
        root?.setBackgroundColor(selectedColor)
    }
    private fun buildAlertMessageNoGps() {
        val dialog = Dialog(this, R.style.CustomAlertDialog)
        dialog.setContentView(R.layout.dialog_location)
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        val width = (resources.displayMetrics.widthPixels * 0.93).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.20 ).toInt()
        dialog.window?.setLayout(width, height)
        dialog.show()
    }
    fun bottom(){
        val bottomSheet = BottomSheetDialog()
        bottomSheet.show(supportFragmentManager, "ModalBottomSheet")
    }
    override fun onResume() {
        super.onResume()
       // buildAlertMessageNoGps()
       // bottom()
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                // Permission denied
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            Log.e("TAG", "onActivityResult:$selectedImageUri ")
            val intent=Intent(this,MainActivity2::class.java)
            intent.putExtra("image",selectedImageUri.toString())
            startActivity(intent)
        }
    }
}