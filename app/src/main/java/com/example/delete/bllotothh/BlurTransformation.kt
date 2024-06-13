package com.example.delete.bllotothh

import android.graphics.Bitmap

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest


class BlurTransformation : BitmapTransformation() {
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {

    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int,
    ): Bitmap {
        var blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true)
        blurredBitmap = fastBlur(blurredBitmap, MAX_RADIUS, SCALE)
        return blurredBitmap
    }

    private fun fastBlur(bitmap: Bitmap, radius: Int, scale: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = pixels[y * width + x]
                var r = pixel shr 16 and 0xff
                var g = pixel shr 8 and 0xff
                var b = pixel and 0xff
                var sumR = 0
                var sumG = 0
                var sumB = 0
                for (i in -radius..radius) {
                    for (j in -radius..radius) {
                        val index = (y + j) * width + (x + i)
                        if (index >= 0 && index < pixels.size) {
                            val neighborPixel = pixels[index]
                            sumR += neighborPixel shr 16 and 0xff
                            sumG += neighborPixel shr 8 and 0xff
                            sumB += neighborPixel and 0xff
                        }
                    }
                }
                val count = (radius * 2 + 1) * (radius * 2 + 1)
                r = sumR / count
                g = sumG / count
                b = sumB / count
                pixels[y * width + x] = -0x1000000 or (r shl 16) or (g shl 8) or b
            }
        }
        val blurredBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        blurredBitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return blurredBitmap
    }

    companion object {
        private const val MAX_RADIUS = 25
        private const val SCALE = 8
    }
}