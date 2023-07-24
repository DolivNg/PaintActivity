package com.vassa.paintactivity.gsdk.`object`.baseobject

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.vassa.paintactivity.gsdk.`object`.gameobject.GameObject

/**
 * @author Vassa
 * @version 1.0
 */
abstract class BaseObject(
    var size: Size,
    var position: PointF,
    var type: Int,
    var id: Int
) {
    /**
     * CallBack
     */
    var baseFunction: ObjectBaseFunction? = null

    /**
     * Point where object must draw
     */
    var positionDraw = PointF(0F, 0F)
    val paint = Paint()

    /**
     * Show active object
     */
    var active = true

    /**
     * Center object x
     * @return center Object x in Float
     */
    fun getCenterObjectX(): Float {
        return (position.x + size.width / 2)
    }

    /**
     * Center object y
     * @return center Object y in Float
     */
    fun getCenterObjectY(): Float {
        return (position.y + size.width / 2)
    }

    /**
     * Function to draw object
     * @param canvas
     */
    open fun draw(canvas: Canvas) {
    }

    /**
     * These interface make two function
     */
    interface ObjectBaseFunction {

        /**
         * Create Object in data
         * @param type type object which must create
         * @param obParameter data object with base parameters to new object
         */
        fun createObject(type: Int, obParameter: ObParameter)

        /**
         * Delete object from List
         * @param type type object which must delete
         * @param gameObject witch Delete
         */
        fun destroy(type: Int, gameObject: GameObject)
    }
}