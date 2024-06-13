package com.example.delete.abc

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import androidx.core.app.ActivityCompat



@SuppressLint("MissingPermission")
class LiveAmplitude(var context: Context) {
    private val audioRecord: AudioRecord
    private val sampleRate = 44100 // Sample rate in Hz
    private val channelConfig = AudioFormat.CHANNEL_IN_MONO // Mono channel
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT // 16-bit PCM encoding

    init {
        val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)


        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC,
            sampleRate,
            channelConfig,
            audioFormat,
            bufferSize
        )


    }

    fun startSampling() {
        //audioRecord.startRecording()
        Thread {
            val buffer = ByteArray(audioRecord.bufferSizeInFrames)
            while (true) {
                val bytesRead = audioRecord.read(buffer, 0, buffer.size)
                // Calculate the amplitude/level from the audio data
                val amplitude = calculateAmplitude(buffer, bytesRead)
                // Do something with the amplitude/level
                Log.d("LiveAmplitude", "Amplitude: $amplitude")
            }
        }.start()
    }

    private fun calculateAmplitude(buffer: ByteArray, bytesRead: Int): Int {
        // Calculate the amplitude/level from the audio data
        // For example, you can calculate the RMS (Root Mean Square) value
        var sum = 0
        for (i in 0 until bytesRead) {
            sum += buffer[i] * buffer[i]
        }
        return Math.sqrt((sum / bytesRead).toDouble()).toInt()
    }

    fun stopSampling() {
        audioRecord.stop()
    }
}