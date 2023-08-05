package com.vassa.paintactivity.ui.view.objects

import android.graphics.Canvas
import android.graphics.PointF
import com.vassa.paintactivity.gsdk.`object`.baseobject.Size
import com.vassa.paintactivity.gsdk.`object`.gameobject.GameObject

class Pencil(size: Size, position: PointF, type: Int, id: Int) :
    GameObject(size, position, type, id) {

    


    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }


}