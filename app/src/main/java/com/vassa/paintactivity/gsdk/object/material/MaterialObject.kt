package com.vassa.paintactivity.gsdk.`object`.material

import android.graphics.PointF
import android.graphics.RectF
import androidx.core.graphics.plus
import com.vassa.paintactivity.gsdk.`object`.baseobject.BaseObject
import com.vassa.paintactivity.gsdk.`object`.baseobject.Size
import com.vassa.paintactivity.gsdk.`object`.gameobject.GameObject

import kotlin.math.abs
import kotlin.math.ceil

/** Class make physical option
 * @author Vassa
 * @version 1.1
 */
abstract class MaterialObject(size: Size, position: PointF, type: Int, id: Int) :
    BaseObject(size, position, type, id) {
    /**
     * Data object with all parameters
     */
    open val physicAttr = AttrPhysic()

    /**
     * Show which sides have collision
     */
    protected val collisionDir = CollisionDir()

    /**
     * Object which listen and set gravity and wind
     */
    protected var listenerPhysics: PhysicsController? = null

    /**
     * Use when deep render move
     */
    private var stepX = 0.0
    private var stepY = 0.0
    private var rotationStep = 0.0

    /**
     *
     */
    open fun setListener(listener: PhysicsController) {
        listenerPhysics = listener
    }

    /**
     * Function to deep render position and rotation
     */
    open fun update() {
        if (physicAttr.stackObject == null) {
            if (physicAttr.speedX > 0)
                if (!collisionDir.rightFun()) {
                    position.x += stepX.toFloat()
                }

            if (physicAttr.speedX < 0)
                if (!collisionDir.leftFun()) {
                    position.x += stepX.toFloat()
                }

            if (physicAttr.speedY > 0)
                if (!collisionDir.bottomFun()) {
                    position.y += stepY.toFloat()
                }

            if (physicAttr.speedY < 0)
                if (!collisionDir.topFun()) {
                    position.y += stepY.toFloat()
                }

            if (physicAttr.rotation)
                physicAttr.angle += rotationStep
        } else
            if (physicAttr.stuck)
                if (physicAttr.stackObject != null)
                    stuckMove()
    }

    /**
     * If object have option stuck then use function. Must override this method.
     */
    open fun stuckMove() {}

    /**
     * get Max speed
     * @return maxSpeed
     */
    open fun getMaxValue(): Double {
        return if (abs(physicAttr.speedX) > abs(physicAttr.speedY))
            abs(physicAttr.speedX)
        else
            abs(physicAttr.speedY)
    }

    /**
     * Make step to deep render
     * @param max
     */
    fun makeStepToUpdate(max: Double) {
        if (physicAttr.rotation)
            rotationStep = physicAttr.rotationSpeed / max
        stepX = physicAttr.speedX / ceil(abs(max))
        stepY = physicAttr.speedY / ceil(abs(max))
    }

    /**
     * Skip deep render
     */
    open fun skipUpdate() {
        position.x += physicAttr.speedX.toFloat()
        position.y += physicAttr.speedY.toFloat()
    }

    /**
     * Prerender function
     */
    open fun preUpdate() {
        gravity()
        wind()
    }

    /**
     * Get path one frame
     */
    fun getPath(): RectF {
        return getFullArea().plus(
            RectF(
                (getFullArea().left + physicAttr.speedX).toFloat(),
                (getFullArea().top + physicAttr.speedY).toFloat(),
                (getFullArea().right + physicAttr.speedX).toFloat(),
                (getFullArea().bottom + physicAttr.speedY).toFloat()
            )
        )
    }

    /**
     * Override this method to make reaction with special object
     * @param gameObject
     */
    open fun collision(gameObject: GameObject) {
    }

    /**
     * make gravity effect
     */
    fun gravity() {
        if (listenerPhysics != null)
            if (physicAttr.physical)
                if (physicAttr.gravity)
                    physicAttr.gravityY += listenerPhysics?.gravity!!
    }

    /**
     * make wind effect
     */
    fun wind() {
        if (listenerPhysics != null)
            if (physicAttr.physical)
                if (physicAttr.wind)
                    physicAttr.windX += listenerPhysics?.wind!!
    }

    /**
     * get standard mask
     * @return mask object from base
     */
    fun getMask(): RectF {
        return RectF(
            position.x,
            position.y,
            position.x + size.width,
            position.y + size.height
        )
    }

    /**
     * get all mask which added. Override fun and add all mask which object has
     * @return mask
     */
    open fun getFullArea(): RectF {
        return getMask()
    }

    fun getPolygon(): Array<Int> {
        return arrayOf()
    }

    /**
     * Data class show side has collision
     */
    data class CollisionDir(
        var bottom: Array<Boolean> = arrayOf(false, false, false),
        var top: Array<Boolean> = arrayOf(false, false, false),
        var left: Array<Boolean> = arrayOf(false, false, false),
        var right: Array<Boolean> = arrayOf(false, false, false)
    ) {
        fun topFun(): Boolean = top[0] || top[1] || top[2]
        fun bottomFun(): Boolean = bottom[0] || bottom[1] || bottom[2]
        fun rightFun(): Boolean = right[0] || right[1] || right[2]
        fun leftFun(): Boolean = left[0] || left[1] || left[2]

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CollisionDir

            if (!bottom.contentEquals(other.bottom)) return false
            if (!top.contentEquals(other.top)) return false
            if (!left.contentEquals(other.left)) return false
            if (!right.contentEquals(other.right)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = bottom.contentHashCode()
            result = 31 * result + top.contentHashCode()
            result = 31 * result + left.contentHashCode()
            result = 31 * result + right.contentHashCode()
            return result
        }
    }

    /**
     *
     */
    interface PhysicsController {
        var gravity: Double
        var wind: Double
    }
}