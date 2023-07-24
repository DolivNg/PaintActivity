package com.vassa.paintactivity.gsdk.display

import android.graphics.Rect
import com.vassa.paintactivity.gsdk.`object`.baseobject.BaseObject

class GameDisplay(
    private val widthPixels: Int,
    private val heightPixels: Int,
    private val centerObject: BaseObject
) {

    private var displayCenterX = 0F
    private var displayCenterY = 0F
    private var gameToDisplayCoordinatesOffsetX = 0F
    private var gameToDisplayCoordinatesOffsetY = 0F
    private var gameCenterX = 0F
    private var gameCenterY = 0F

    init {
        displayCenterX = widthPixels / 2F
        displayCenterY = heightPixels / 2F
        update()
    }

    /**
     * Render postion display
     */
    fun update() {
        gameCenterX = centerObject.getCenterObjectX()
        gameCenterY = centerObject.getCenterObjectY()
        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY
    }

    fun putPositionDraw(gameObject: BaseObject) {
        gameObject.positionDraw.x = (gameObject.position.x + gameToDisplayCoordinatesOffsetX)
        gameObject.positionDraw.y = (gameObject.position.y + gameToDisplayCoordinatesOffsetY)
    }


    fun gameToDisplayCoordinatesX(x: Double): Double {
        return x + gameToDisplayCoordinatesOffsetX
    }

    fun gameToDisplayCoordinatesY(y: Double): Double {
        return y + gameToDisplayCoordinatesOffsetY
    }

    fun getGameRect(): Rect {
        return Rect(
            (gameCenterX - widthPixels / 2).toInt(),
            (gameCenterY - heightPixels / 2).toInt(),
            (gameCenterX + widthPixels / 2).toInt(),
            (gameCenterY + heightPixels / 2).toInt()
        )
    }
}