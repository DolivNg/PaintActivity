package com.vassa.paintactivity.gsdk.grafic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect


open class SpriteSheet(context: Context) {
    /**
     * Bitmap sheet
     */
    protected var bitmap: Bitmap? = null
    protected var SPRITE_WIDTH_PIXELS = 96
    protected var SPRITE_HEIGHT_PIXELS = 96

    /**
     *
     */
    protected fun getSpriteByIndex(idxRow: Int, idxCol: Int): Sprite {
        return Sprite(
            bitmap!!, Rect(
                idxCol * SPRITE_WIDTH_PIXELS,
                idxRow * SPRITE_HEIGHT_PIXELS,
                (idxCol + 1) * SPRITE_WIDTH_PIXELS,
                (idxRow + 1) * SPRITE_HEIGHT_PIXELS
            )
        )
    }

    protected fun getSpriteByIndex(idxRow: Int, idxCol: Int, id: Int): Sprite {
        return Sprite(
            bitmap!!, Rect(
                idxCol * SPRITE_WIDTH_PIXELS,
                idxRow * SPRITE_HEIGHT_PIXELS,
                (idxCol + 1) * SPRITE_WIDTH_PIXELS,
                (idxRow + 1) * SPRITE_HEIGHT_PIXELS
            ), id
        )
    }


    init {
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inScaled = false

        /*bitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.sprite_sheet,
            bitmapOptions
        )*/
    }
}