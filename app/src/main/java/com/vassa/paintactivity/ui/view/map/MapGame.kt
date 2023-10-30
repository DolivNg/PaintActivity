package com.vassa.paintactivity.ui.view.map



import android.graphics.PointF
import com.vassa.paintactivity.gsdk.map.mapdata.GeneratedMap
import com.vassa.paintactivity.gsdk.`object`.baseobject.BaseObject
import com.vassa.paintactivity.gsdk.`object`.baseobject.ObParameter
import com.vassa.paintactivity.gsdk.`object`.baseobject.Size
import com.vassa.paintactivity.gsdk.`object`.gameobject.GameObject
import com.vassa.paintactivity.ui.view.constants.DataTypesObjects.Companion.PENCIL
import com.vassa.paintactivity.ui.view.objects.Pencil


class MapGame : BaseObject.ObjectBaseFunction {
    init {
    }

    val generatedMap: GeneratedMap = GeneratedMap()

    fun generation(

    ): GeneratedMap {
        var index = 0
        //Back Layer
        //Middle Layer
        generatedMap.mapMiddle[PENCIL] = ArrayList()
        generatedMap.mapMiddle[PENCIL]?.add(Pencil(Size(100,100), PointF(0F,0F),PENCIL,1))
        /*pool.MachineSound()
        generatedMap.mapMiddle[MACHINE_TYPE] = ArrayList()
        generatedMap.mapMiddle[MACHINE_TYPE]?.add(
            Machine(
                Size(displayMetrics.heightPixels/5*6, (displayMetrics.heightPixels/5*4.8).toInt()), PointF(displayMetrics.widthPixels/14F, displayMetrics.heightPixels/6F), 1, 0, sheet.getAssetSprite(),
                MachineParameters(3, 10),pool
            )
        )*/


        /*generatedMap.mapMiddle[SKY_TYPE] = ArrayList()
        generatedMap.mapMiddle[SKY_TYPE]?.add(
            Plane(Size(displayMetrics.heightPixels/5*6, (displayMetrics.heightPixels/5*4.8).toInt()),PointF(displayMetrics.widthPixels/14F, displayMetrics.heightPixels/6F), 1, 0,sheet.getSpriteSky(),
                Point(displayMetrics.widthPixels,displayMetrics.heightPixels)
            )
        )*/
        /*var posX = -1
        var posY = 0
        for (i in mapDesignMap.middleLayer) {
            when (i) {
                '\n' -> {
                    posX = -1
                    posY += 1
                }
            }
            posX += 1
        }*/
        return generatedMap
    }

    override fun createObject(type: Int, obParameter: ObParameter) {
        TODO("Not yet implemented")
    }

    override fun destroy(type: Int, gameObject: GameObject) {
        TODO("Not yet implemented")
    }


}


