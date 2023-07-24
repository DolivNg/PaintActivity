package com.vassa.paintactivity.gsdk.music

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

abstract class SoundPoolManager(var context: Context) {
    var soundPool: SoundPool
    var mapId: HashMap<Int, ArrayList<Int>> = HashMap()


    init {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(attributes)
            .build()
    }


}