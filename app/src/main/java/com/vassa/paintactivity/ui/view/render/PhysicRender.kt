package com.vassa.paintactivity.ui.view.render


import com.vassa.paintactivity.gsdk.map.mapdata.GeneratedMap
import com.vassa.paintactivity.gsdk.`object`.gameobject.GameObject
import com.vassa.paintactivity.gsdk.`object`.material.MaterialObject
import com.vassa.paintactivity.gsdk.physic.CollisionFun.Companion.intersects


class PhysicRender(
    var generatedMap: GeneratedMap,
    ) : MaterialObject.PhysicsController{

    override var gravity: Double = 0.9
    override var wind: Double = 0.0

    var max = 0.0;
    var mapRender = HashMap<GameObject, ArrayList<GameObject>>()

    /* first =(n - ign) second = obMove*n  thread = obMove  */
    fun preRender(ignoreRender: Array<Int>) {
        var objectsRender = ArrayList<GameObject>()
        generatedMap.mapMiddle.mapKeys { it ->
            if (it.key !in ignoreRender)
                it.value.forEach {
                    if (it.active) {
                        it.preUpdate()
                        if ( it.physicAttr.speedX != 0.0 || it.physicAttr.speedY != 0.0){
                            objectsRender.add(it)}
                    }
                }
        }

        val mapCollision = HashMap<GameObject, ArrayList<GameObject>>()
        for (ind1 in 0 until objectsRender.size ) {
            mapCollision[objectsRender[ind1]] = ArrayList()
            generatedMap.mapMiddle.mapKeys {
                for (ob in it.value) {
                    if (ob.physicAttr.material || ob.active && ob != objectsRender[ind1]) {
                        if (intersects(objectsRender[ind1].getPath(), ob.getPath())) {
                            //if (mapCollision[objectsRender[ind1]] == null)
                            mapCollision[objectsRender[ind1]]?.add(ob)
                        }
                    } else {
                        continue
                    }
                }
            }
        }
        max = 0.0
        mapCollision.mapKeys {
            if (it.value.size == 0)
                it.key.skipUpdate()
            else {
                if (max < it.key.getMaxValue())
                    max = it.key.getMaxValue()
                mapRender.put(it.key, it.value)
            }
        }

        mapRender.mapKeys {
            it.key.makeStepToUpdate(max)//.setStep(max)
        }

    }

    fun render() {
        var time = 0
        while (time < max) {
            mapRender.mapKeys { key ->
                key.value.forEach {
                    if (intersects(key.key.getFullArea(), it.getFullArea())) {
                        key.key.collision(it)
                    }
                }
            }
            mapRender.mapKeys { key ->
                key.key.update()
            }
            time++
        }
        mapRender.clear()
    }
}