package com.vassa.paintactivity.gsdk.grafic

import android.graphics.*

class Sprite(bitmap: Bitmap, private val rect: Rect) {
    constructor(bitmap: Bitmap, rect: Rect, _id: Int) : this(bitmap, rect) {
        id = _id
    }
    var layer = 0.0
    var matrix: Matrix = Matrix()
    var id: Int = 0

    /**
     * cut bitmap
     */
    var bitmapNew: Bitmap = Bitmap.createBitmap(
        bitmap,
        rect.left,
        rect.top,
        rect.width(),
        rect.height()
    )


    fun scaleBitmap(nW: Int, nH: Int) {
        bitmapNew = Bitmap.createScaledBitmap(bitmapNew, nW, nH , true)
    }

    val width: Int
        get() = bitmapNew.width//rect.width()
    val height: Int
        get() = bitmapNew.height//rect.height()


    fun draw(canvas: Canvas, position: PointF) {
        canvas.drawBitmap(bitmapNew, position.x, position.y, null)
    }

    fun draw(canvas: Canvas, matrix: Matrix) {
        canvas.drawBitmap(bitmapNew, matrix, null)
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmapNew, matrix, null)
    }

    fun copy(): Sprite {
        return Sprite(this.bitmapNew, Rect(0, 0, width, height), id)
    }
}

