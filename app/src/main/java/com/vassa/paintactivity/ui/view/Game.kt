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
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.vassa.paintactivity.domain.entity.socket.PointData
import com.vassa.paintactivity.ui.view.render.PhysicRender
import com.vassa.paintactivity.gsdk.loop.GameLooper
import com.vassa.paintactivity.ui.view.constants.DataTypesObjects.Companion.PENCIL
import com.vassa.paintactivity.ui.view.map.MapGame
import com.vassa.paintactivity.ui.view.objects.Pencil


@SuppressLint("ViewConstructor", "ClickableViewAccessibility")
class Game(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs),
    SurfaceHolder.Callback, GameLooper.UpdateCallBack, Pencil.CallPencil {
    private val scaleDetector = ScaleGestureDetector(context, ScaleListener())
    private var scaleFactor = 1f

    var callGameBack: GameCallBack? = null

    private val inverseMatrix = Matrix()

    //var paint = Paint()
    private var gameLoop: GameLooper
    private var hhandler: Handler

    private var array: Array<Int> = arrayOf()
    private var mapGame: MapGame = MapGame()
    private var physic: PhysicRender = PhysicRender(mapGame.generatedMap)

    private var turn = false
    private var mayDraw = false

    init {
        //temp
        //paint.color = Color.WHITE
        //paint.strokeWidth = 17F
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

        mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
            when (it) {
                is Pencil -> {
                    it.pencilCall = this@Game
                }
            }
        }

        hhandler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {

            }
        }

        ////////////////////////////
        setOnTouchListener { v, event ->
            val list = ArrayList<PointF>(10)
            scaleDetector.onTouchEvent(event)
            val transformedEvent = MotionEvent.obtain(event)
            transformedEvent.transform(inverseMatrix)
            // событие
            val actionMask = event.actionMasked

            val pointerIndex = event.actionIndex
            // число касаний
            val pointerCount = event.pointerCount
            var worldX = 0F
            var worldY = 0F
            if (matrix.invert(inverseMatrix)) {
                val touchPoint = floatArrayOf(event.x, event.y)  // координати дотику
                inverseMatrix.mapPoints(touchPoint)  // перетворюємо координати дотику

                worldX = touchPoint[0]
                worldY = touchPoint[1]

                // тепер ви можете використовувати worldX і worldY для малювання
            }
            if (turn)
                if (mayDraw)
                    when (actionMask) {

                        MotionEvent.ACTION_DOWN -> {
                            if (pointerCount == 1)
                                startTouch(PointF(worldX, worldY))
                        }

                        MotionEvent.ACTION_POINTER_DOWN -> {
                        }

                        MotionEvent.ACTION_MOVE -> {
                            if (pointerCount == 1) {
                                moveTouch(PointF(worldX, worldY), MotionEvent.ACTION_MOVE)
                            } else
                                if (!scaleDetector.isInProgress) {
                                    /* val dx = transformedEvent.x - lastTouchX
                                     val dy = transformedEvent.y - lastTouchY

                                     offsetX += dx
                                     offsetY += dy

                                     drawOnSurface(lastTouchX, lastTouchY, transformedEvent.x, transformedEvent.y)
                                     lastTouchX = transformedEvent.x
                                     lastTouchY = transformedEvent.y*/
                                }

                        }

                        MotionEvent.ACTION_UP -> {
                            if (pointerCount == 1)
                                endTouch(PointF(worldX, worldY))
                        }
                    }

            transformedEvent.recycle()
            true;
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
        //canvas.concat(matrix)
        canvas.drawColor(Color.WHITE)
        mapGame.generatedMap.mapMiddle.mapKeys { it ->
            it.value.forEach {
                it.draw(canvas)
            }
        }
    }

    fun initRules() {

    }

    private fun startTouch(point: PointF) {
        mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
            when (it) {
                is Pencil -> {
                    it.touchStart(PointData(point.x.toInt(), point.y.toInt()))
                }
            }
        }
    }

    private fun moveTouch(point: PointF, action: Int) {
        mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
            when (it) {
                is Pencil -> {
                    it.touch(PointData(point.x.toInt(), point.y.toInt()))
                }
            }
        }
    }

    private fun endTouch(point: PointF) {
        mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
            when (it) {
                is Pencil -> {
                    it.touchEnd(PointData(point.x.toInt(), point.y.toInt()))
                }
            }
        }
    }


    fun chooseEraser() {
        mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
            when (it) {
                is Pencil -> {
                    it.selectEraser()
                }
            }
        }
    }

    fun choosePencil() {
        mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
            when (it) {
                is Pencil -> {
                    it.selectPencil()
                }
            }
        }
    }

    fun changeColor(id: Int) {

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

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = scaleFactor.coerceAtMost(5.0f).coerceAtLeast(0.1f)

            Log.d("vasa", "$scaleFactor l")
            mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
                when (it) {
                    is Pencil -> {
                        it.scale = scaleFactor
                        it.matrixScale.setScale(
                            scaleFactor,
                            scaleFactor,
                            detector.focusX,
                            detector.focusY
                        )
                    }
                }
            }
            return true
        }
    }

    fun newTurn() {
        turn = true
        mayDraw = true
        mapGame.generatedMap.mapMiddle[PENCIL]?.forEach {
            when (it) {
                is Pencil -> {
                    it.distance = 0.0
                }
            }
        }

    }

    fun endTurn() {
        turn = false
    }

    fun inkLose() {
        mayDraw = false
    }

    interface GameCallBack {
        fun timerUsed(sec: Int)
        fun inkUsed(count: Int)

    }

    override fun sendPoint(point: PointData) {

    }

    override fun sendDistance(distance: Int) {
        if (callGameBack != null)
            callGameBack!!.inkUsed(distance)
        Log.d("ink","sended $distance")
    }

}