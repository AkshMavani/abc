package com.example.delete.playlist


import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bullhead.equalizer.DialogEqualizerFragment
import com.bullhead.equalizer.EqualizerFragment
import com.bullhead.equalizer.EqualizerModel
import com.bullhead.equalizer.Settings
import com.example.delete.R
import com.google.gson.Gson


class MainActivity2 : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main14)
        loadEqualizerSettings()
        mediaPlayer = MediaPlayer.create(this, R.raw.abc)
        val sessionId = mediaPlayer!!.audioSessionId
        mediaPlayer!!.isLooping = true
        val equalizerFragment = EqualizerFragment.newBuilder()
            .setAccentColor(Color.parseColor("#4caf50"))
            .setAudioSessionId(sessionId)
            .build()
        supportFragmentManager.beginTransaction()
            .replace(R.id.eqFrame, equalizerFragment)
            .commit()
       // showInDialog()
    }
//    private fun showInDialog() {
//        val sessionId = mediaPlayer?.audioSessionId
//        if (sessionId != null) {
//            if (sessionId > 0) {
//                val fragment: DialogEqualizerFragment = DialogEqualizerFragment.newBuilder()
//                    .setAudioSessionId(sessionId)
//                    .title(R.string.app_name)
//                    .themeColor(ContextCompat.getColor(this, R.color.black1))
//                    .textColor(ContextCompat.getColor(this, R.color.black1))
//                    .accentAlpha(ContextCompat.getColor(this, R.color.black1))
//                    .darkColor(ContextCompat.getColor(this, R.color.black1))
//                    .setAccentColor(ContextCompat.getColor(this, R.color.black1))
//                    .build()
//                fragment.show(supportFragmentManager, "eq")
//            }
//        }
//    }

    override fun onStop() {
        super.onStop()
        saveEqualizerSettings()
    }

    override fun onPause() {
        try {
            mediaPlayer!!.pause()
        } catch (ex: Exception) {
            //ignore
        }
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        try {
            Handler().postDelayed(Runnable { mediaPlayer?.start() }, 2000)
        } catch (ex: Exception) {
            //ignore
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.itemEqDialog) {
//            showInDialog()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun saveEqualizerSettings() {
        if (Settings.equalizerModel != null) {
            val settings = EqualizerSettings()
            settings.bassStrength = Settings.equalizerModel.getBassStrength()
            settings.presetPos = Settings.equalizerModel.getPresetPos()
            settings.reverbPreset = Settings.equalizerModel.getReverbPreset()
            settings.seekbarpos = Settings.equalizerModel.getSeekbarpos()
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val gson = Gson()
            preferences.edit()
                .putString(PREF_KEY, gson.toJson(settings))
                .apply()
        }
    }

    private fun loadEqualizerSettings() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val gson = Gson()
        val settings: EqualizerSettings = gson.fromJson(
            preferences.getString(PREF_KEY, "{}"),
            EqualizerSettings::class.java
        )
        val model = EqualizerModel()
        model.bassStrength = settings.bassStrength
        model.presetPos = settings.presetPos
        model.reverbPreset = settings.reverbPreset
        model.seekbarpos = settings.seekbarpos
        Settings.isEqualizerEnabled = true
        Settings.isEqualizerReloaded = true
        Settings.bassStrength = settings.bassStrength
        Settings.presetPos = settings.presetPos
        Settings.reverbPreset = settings.reverbPreset
        Settings.seekbarpos = settings.seekbarpos
        Settings.equalizerModel = model
    }

    val PREF_KEY = "equalizer"
}