package com.vassa.paintactivity.ui.view.objects

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.util.Log
import com.vassa.paintactivity.domain.entity.socket.PointData
import com.vassa.paintactivity.gsdk.`object`.baseobject.Size
import com.vassa.paintactivity.gsdk.`object`.gameobject.GameObject
import org.koin.core.component.getScopeId
import java.lang.Math.sqrt

class Pencil(size: Size, position: PointF, type: Int, id: Int) :
    GameObject(size, position, type, id) {
    private var image = ArrayList<PointData>()
    private var arrayLine = ArrayList<Path>()
    private var mapLine = HashMap<Int, Path>()
    private var idLine = 0
    private var tempIdLine = 0
    private var linePath = Path()
    var pencilCall: CallPencil? = null

    var distance = 0.0

    var matrixScale = Matrix()
    var scale = 1F

    private var start = false
    private var click = false
    private var end = false

    private var tempPoint: PointData = PointData()

    fun selectEraser(){
        paint.color = Color.WHITE
        paint.strokeWidth = 30F
        paint.style = Paint.Style.STROKE
    }
    fun selectPencil(){
        paint.strokeWidth = 3F
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }
    init {
        paint.strokeWidth = 3F
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
    }

    override fun preUpdate() {
        super.preUpdate()
        if (start) {
            image.add(tempPoint)
            start = false
            click = true
        } else
            if (end) {
                distance+= distanceBetweenPoints(image.last().x,image.last().y, tempPoint.x,tempPoint.y)
                image.add(tempPoint)
                if ( pencilCall !=null)
                    pencilCall?.sendDistance((distance/4).toInt())
                click = false
                end = false
            }else
        if (click) {
            distance+= distanceBetweenPoints(image.last().x,image.last().y, tempPoint.x,tempPoint.y)
            if ( pencilCall !=null)
                pencilCall?.sendDistance((distance/4).toInt())
            image.add(tempPoint)

        }

    }
    private fun distanceBetweenPoints(x1: Int, y1: Int,x2: Int, y2: Int): Double {
        val dx = x1 - x2
        val dy = y1 - y2
        return kotlin.math.sqrt(dx.toDouble() * dx + dy * dy)
    }
    fun touchStart(point: PointData) {
        if (image.size != 0) {
            idLine = image[image.size - 1].idLine
            idLine += 1
        } else
            idLine = 0

        point.idLine = idLine
        point.size = paint.strokeWidth.toInt()
        point.color = paint.color

        pencilCall?.sendPoint(point)

        tempPoint = point
        start = true

    }


    fun touch(point: PointData) {
        point.idLine = idLine
        point.size = paint.strokeWidth.toInt()
        point.color = paint.color
        tempPoint = point
    }

    fun touchEnd(point: PointData) {
        point.idLine = idLine
        point.size = paint.strokeWidth.toInt()
        point.color = paint.color

        pencilCall?.sendPoint(point)
        end = true
        tempPoint = point
    }

    private fun createPath(canvas: Canvas) {
        //if (tempIdLine<idLine)
        var temp = -1
        var tempPath = Path()
        var pen = Paint()
        pen.style = Paint.Style.STROKE
        for (point in image) {
            if (point.idLine != temp) {
                tempPath.transform(matrixScale)
                canvas.drawPath(tempPath, pen)
                tempPath.reset()
                tempPath.moveTo(point.x.toFloat(), point.y.toFloat())
                pen.strokeWidth = point.size.toFloat() * scale
                pen.color = point.color

                temp = point.idLine
            } else {
                tempPath.lineTo(point.x.toFloat(), point.y.toFloat())
            }
        }
        tempPath.transform(matrixScale)
        canvas.drawPath(tempPath, pen)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        //canvas.setMatrix()
        createPath(canvas)
    }

    interface CallPencil {
        fun sendPoint(point: PointData)
        fun sendDistance(distance: Int)
    }
}