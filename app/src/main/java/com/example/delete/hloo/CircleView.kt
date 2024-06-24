package com.example.delete.hloo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random


class CircleView : View {
    private var paint: Paint? = null
    private var textPaint: Paint? = null
    private var randomNumber: Int = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        randomNumber = Random.nextInt(100) // Generate a random number between 0 and 99

        paint = Paint().apply {
            color = getColorBasedOnNumber(randomNumber) // Set color based on the random number
            isAntiAlias = true
        }

        textPaint = Paint().apply {
            color = Color.WHITE
            textSize = 50f
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
        }
    }

    private fun getColorBasedOnNumber(number: Int): Int {
        return when {
            number < 25 -> Color.RED
            number < 50 -> Color.GREEN
            number < 75 -> Color.BLUE
            else -> Color.YELLOW
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val height = height
        val radius = Math.min(width, height) / 2

        // Draw the circle
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius.toFloat(), paint!!)

        // Draw the random number in the center of the circle
        canvas.drawText(randomNumber.toString(), (width / 2).toFloat(), (height / 2).toFloat() + 15f, textPaint!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val desiredSize = 150
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width: Int
        val height: Int

        // Measure Width
        width = if (widthMode == MeasureSpec.EXACTLY) {
            widthSize
        } else if (widthMode == MeasureSpec.AT_MOST) {
            Math.min(desiredSize, widthSize)
        } else {
            desiredSize
        }

        // Measure Height
        height = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else if (heightMode == MeasureSpec.AT_MOST) {
            Math.min(desiredSize, heightSize)
        } else {
            desiredSize
        }

        // Set the dimensions
        setMeasuredDimension(width, height)
    }
}