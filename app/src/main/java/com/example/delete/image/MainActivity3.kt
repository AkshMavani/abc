package com.example.delete.image

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.delete.R

class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        val txt_ok=findViewById<TextView>(R.id.ans_ok)
        val card=findViewById<CardView>(R.id.cardView)
        val img=findViewById<ImageView>(R.id.ans_back)
        val one=findViewById<TextView>(R.id.ans_one)
        val two=findViewById<TextView>(R.id.ans_two)
        val three=findViewById<TextView>(R.id.ans_three)
        val four=findViewById<TextView>(R.id.ans_four)
        val five=findViewById<TextView>(R.id.ans_five)
        val six=findViewById<TextView>(R.id.ans_six)
        val seven=findViewById<TextView>(R.id.ans_seven)
        val eight=findViewById<TextView>(R.id.ans_eight)
        val nine=findViewById<TextView>(R.id.ans_none)


        val layout=findViewById<ConstraintLayout>(R.id.layout)
            val zoomOutAnimation0 = AnimationUtils.loadAnimation(this, R.anim.anim14)
        layout.startAnimation(zoomOutAnimation0)
        img.setOnClickListener {
            val zoomOutAnimation1 = AnimationUtils.loadAnimation(this, R.anim.anim13)
            card.startAnimation(zoomOutAnimation1)
        }
        txt_ok.setOnClickListener {
            val zoomOutAnimation = AnimationUtils.loadAnimation(this, R.anim.anim12)
            zoomOutAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    Handler().postDelayed(Runnable {
                        val intent=Intent(this@MainActivity3,MainActivity2::class.java)
                    startActivity(intent)
                    },1200 )
                }

                override fun onAnimationEnd(animation: Animation) {

//                    val intent=Intent(this@MainActivity3,MainActivity2::class.java)
//                    startActivity(intent)
//                    finish()


                }

                override fun onAnimationRepeat(animation: Animation) {

                }
            })

           // card.startAnimation(zoomOutAnimation)
            one.startAnimation(zoomOutAnimation)
            two.startAnimation(zoomOutAnimation)
            three.startAnimation(zoomOutAnimation)
            four.startAnimation(zoomOutAnimation)
            five.startAnimation(zoomOutAnimation)
             six.startAnimation(zoomOutAnimation)
            seven.startAnimation(zoomOutAnimation)
            eight.startAnimation(zoomOutAnimation)
            nine.startAnimation(zoomOutAnimation)

            }
        }
    }