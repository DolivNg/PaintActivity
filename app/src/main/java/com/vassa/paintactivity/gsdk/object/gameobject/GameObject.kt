package com.vassa.paintactivity.gsdk.`object`.gameobject

import android.graphics.PointF
import com.vassa.paintactivity.gsdk.`object`.baseobject.Size
import com.vassa.paintactivity.gsdk.`object`.material.MaterialObject

abstract class GameObject(size: Size, position: PointF, type: Int, id: Int) :
    MaterialObject(size, position, type, id) {

}