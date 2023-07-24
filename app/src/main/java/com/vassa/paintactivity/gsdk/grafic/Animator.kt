package com.vassa.paintactivity.gsdk.grafic

import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.PointF

/**
 * Class to draw animation
 * @author Vassa
 * @version 1.0
 */
class Animator(private var SpriteArray: Array<Sprite?>, var speed: Int) {

    /**
     * Repeat animation
     */
    var loop = true

    /**
     * parameter does revers
     */
    var revers = false

    /**
     * time between frame
     */
    private var step = 0

    /**
     * time
     */
    private var count = 0

    /**
     * Can draw object
     */
    private var active = false

    /**
     * Index of array Sprites
     */
    private var index = 0

    init {
        step = 30 / speed
    }

    /**
     * Active the Animation
     */
    fun activation() {
        active = true
    }

    /**
     * Set new Sprite Array
     * @param spriteArray new Sprites
     */
    fun changeSprite(spriteArray: Array<Sprite?>) {
        if (spriteArray.size - 1 < index)
            index = spriteArray.size - 1
        SpriteArray = spriteArray
    }

    /**
     * DeActive the object
     */
    fun deActivation() {
        active = false
        count = 0
        index = 0
    }

    /**
     * Draw object
     * @param canvas object where draw
     * @param drawPoint point draw
     */
    fun draw(canvas: Canvas?, drawPoint: PointF) {
        update()
        //SpriteArray[index]!!.draw(canvas!!, drawPoint.x.toInt(), drawPoint.y.toInt())
    }

    /**
     * Draw object
     * @param canvas object where draw
     * @param matrix additional parameters
     */
    fun draw(canvas: Canvas?, matrix: Matrix) {
        update()
        SpriteArray[index]!!.draw(canvas!!, matrix)
    }

    /**
     * Update Frame
     */
    fun update() {
        if (!revers) {
            if (count < step)
                count++
            else {
                if (index < SpriteArray.size - 1)
                    index++
                else
                    if (loop)
                        index = 0
                count = 0
            }
        }
        if (revers)
        {
            if (count > 0)
                count--
            else {
                if (index > 0)
                    index--
                else
                    if (loop)
                        index = SpriteArray.size - 1
                count = step
            }
        }
    }
}