package com.vassa.paintactivity.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.vassa.paintactivity.ui.view.render.PhysicRender
import com.vassa.paintactivity.gsdk.loop.GameLooper
import com.vassa.paintactivity.ui.view.map.MapGame


@SuppressLint("ViewConstructor")
class Game(context: Context,  attrs: AttributeSet) : SurfaceView(context,attrs), SurfaceHolder.Callback, GameLooper.UpdateCallBack {

    var paint = Paint()
    private lateinit var gameLoop: GameLooper
    private var hhandler: Handler

    private var array: Array<Int> = arrayOf()
    private var mapGame: MapGame = MapGame()
    private var physic: PhysicRender = PhysicRender(mapGame.generatedMap)

    init {
        //temp
        paint.color = Color.WHITE
        paint.strokeWidth = 17F
        //
        val displayMetrics = DisplayMetrics()
        (getContext() as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        //
        mapGame.generation()
        physic = PhysicRender(mapGame.generatedMap)
        //
        val surfaceHolder = holder
        surfaceHolder.addCallback(this)
        gameLoop = GameLooper(this, surfaceHolder)

        hhandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {

            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (gameLoop.state == Thread.State.TERMINATED) {
            val surfaceHolder = getHolder()
            surfaceHolder.addCallback(this)
            gameLoop = GameLooper(this, surfaceHolder)
        }
        gameLoop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Log.d("Game.java", "surfaceChanged()");
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        mapGame.generatedMap.mapMiddle.mapKeys { it ->
            it.value.forEach {
                it.draw(canvas)
            }
        }
    }

    fun multiTouch(arrayList: ArrayList<PointF>, action:Int){
        Log.d("vasa",arrayList.size.toString())
    }

    override fun update() {
        physic.preRender(array)
        physic.render()
        hhandler.sendEmptyMessage(0)
    }

    override fun drawCall(canvas: Canvas) {
        draw(canvas)
    }

    fun pause() {
        gameLoop.stopLoop()
    }

}