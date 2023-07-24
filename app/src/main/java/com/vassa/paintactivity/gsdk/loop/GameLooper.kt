package com.vassa.paintactivity.gsdk.loop

import android.graphics.Canvas
import android.util.Log
import android.view.SurfaceHolder


class GameLooper(
    private var game: UpdateCallBack,
    private val surfaceHolder: SurfaceHolder
    ) : Thread() {

    private val MAX_UPS = 30.0
    private val UPS_PERIOD = 1E+3 / MAX_UPS

    private var isRunning = false
    private var averageUPS = 0.0
    private var averageFPS = 0.0

    fun getAverageUPS() = averageUPS

    fun getAverageFPS()= averageFPS

    fun startLoop() {
        isRunning = true
        start()
    }

    override fun run() {
        Log.d("GameLoop.java", "run()")
        super.run()

        // Declare time and cycle count variables
        var updateCount = 0
        var frameCount = 0
        var startTime: Long
        var elapsedTime: Long
        var sleepTime: Long

        // Game loop
        var canvas: Canvas? = null
        startTime = System.currentTimeMillis()
        while (isRunning) {

            // Try to update and render game
            try {
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    game.update()
                    updateCount++
                    game.drawCall(canvas)

                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                        frameCount++
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            // Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime

            sleepTime = (updateCount * UPS_PERIOD - elapsedTime).toLong()

            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // Skip frames to keep up with target UPS
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                game.update()
                updateCount++

                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime = (updateCount * UPS_PERIOD - elapsedTime).toLong()
            }

            // Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime)
                averageFPS = frameCount / (1E-3 * elapsedTime)
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
            }
        }

    }

    fun stopLoop() {
        Log.d("GameLoop.java", "stopLoop()")
        isRunning = false
        try {
            join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    interface UpdateCallBack{
        fun update()
        fun drawCall(canvas : Canvas)
    }
}