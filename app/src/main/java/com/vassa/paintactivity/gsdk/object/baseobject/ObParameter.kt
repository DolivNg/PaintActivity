package com.vassa.paintactivity.gsdk.`object`.baseobject

import android.graphics.PointF

data class ObParameter(
    var position: PointF,
    var impulse: Double,
    var angle: Int,
    var mass: Int,
    var arrayList: ArrayList<Int>? = null
)
