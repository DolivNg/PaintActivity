package com.vassa.paintactivity.gsdk.music

class SoundCounter(private var speed: Int) {
    var active = false
    var count = 0
    var ready = false

    fun sound() {
        if (active)
            if (!ready) {
                count++
                if (speed < count)
                    ready = true

            }
    }

    fun restart() {
        count = 0
        ready = false
    }

    fun activeOn() {
        active = true
    }

    fun deActive() {
        active = false
        count = 0
        ready = true
    }


}