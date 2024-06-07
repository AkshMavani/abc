package com.example.delete.image


import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.delete.R
import com.example.delete.databinding.ActivityBlurBinding
import com.example.delete.image.MyBlurBuilder.applyBlur


class Activity_blur : AppCompatActivity() {
    private lateinit var binding:ActivityBlurBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBlurBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.blurButton.setOnClickListener { createBlurImage() }

    }
    private fun createBlurImage() {
        //Get seekBar progress
        val blurRadius = binding.blurSeekBar.progress

        /*
         * Note: 0 < blur radius <= 25
         * */if (blurRadius == 0) {
            Toast.makeText(this, "Please increase blurriness radius", Toast.LENGTH_SHORT).show()
            return
        }

        //Create bitmap (Note: Use Image Loading Library like Glide)
        val srcBitmap = BitmapFactory.decodeResource(resources, R.drawable.abc)

        //Blurring

        val blurredBitmap = applyBlur(this, srcBitmap, blurRadius.toFloat())
        if (blurredBitmap != null) {
            //Set Blurred bitmap to imageView
           // binding.imageView.setImageBitmap(blurredBitmap)
            Glide.with(this).load(blurredBitmap).centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView);
        }
    }
}