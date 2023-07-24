package com.vassa.paintactivity.gsdk.physic

import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import kotlin.math.roundToInt

/**
 *
 */
class CollisionFun {
    companion object {
        /**
         * find line intersection
         * return point
         */
        fun lineIntersection(p1: PointF, p2: PointF, p3: PointF, p4: PointF): PointF? {
            val x1 = p1.x
            val y1 = p1.y
            val x2 = p2.x
            val y2 = p2.y
            val x3 = p3.x
            val y3 = p3.y
            val x4 = p4.x
            val y4 = p4.y

            val denom = ((y4 - y3) * (x2 - x1)) - ((x4 - x3) * (y2 - y1))
            val ua = ((x4 - x3) * (y1 - y3)) - ((y4 - y3) * (x1 - x3))
            val ub = ((x2 - x1) * (y1 - y3)) - ((y2 - y1) * (x1 - x3))

            if (denom == 0.0F) {
                // The two lines are parallel
                return null
            }

            val uaT = ua / denom
            val ubT = ub / denom

            if (uaT < 0 || uaT > 1 || ubT < 0 || ubT > 1) {
                // The intersection point is outside the line segments
                return null
            }

            val intersectionX = x1 + uaT * (x2 - x1)
            val intersectionY = y1 + uaT * (y2 - y1)

            return PointF(intersectionX, intersectionY)
        }


        fun intersectsPolygon(polygon1 : Array<PointF>, polygon2 : Array<PointF>): Boolean {
            for (i in polygon1.indices) {
                val p1 = polygon1[i]
                val p2 = polygon1[(i + 1) % polygon1.size]
                val edge = PointF(p2.x - p1.x, p2.y - p1.y)
                val perpendicular = PointF(-edge.y, edge.x)

                val minThis = minProjection(polygon1, perpendicular)
                val minOther = minProjection(polygon2, perpendicular)

                if (minThis.second > minOther.first || minThis.first > minOther.second) {
                    return false
                }
            }

            for (i in polygon2.indices) {
                val p1 = polygon2[i]
                val p2 = polygon2[(i + 1) % polygon2.size]
                val edge = PointF(p2.x - p1.x, p2.y - p1.y)
                val perpendicular = PointF(-edge.y, edge.x)

                val minThis = minProjection(polygon1, perpendicular)
                val minOther = minProjection(polygon2, perpendicular)

                if (minThis.second > minOther.first || minThis.first > minOther.second) {
                    return false
                }
            }

            return true
        }

        private fun minProjection(polygon1 : Array<PointF>, axis: PointF): Pair<Float, Float> {
            var min = Float.MAX_VALUE
            var max = Float.MIN_VALUE

            for (point in polygon1) {
                val projection = (point.x * axis.x + point.y * axis.y) / (axis.x * axis.x + axis.y * axis.y)
                if (projection < min) {
                    min = projection
                }
                if (projection > max) {
                    max = projection
                }
            }

            return Pair(min, max)
        }

        fun intersects(rect1: RectF, rect2: RectF): Boolean {
            return !(rect1.right < rect2.left || rect1.bottom  < rect2.top || rect1.left > rect2.right || rect1.top > rect2.bottom)
        }

        fun collisionSide(rect1: RectF, rect2: RectF): Int {
            val dx = (rect1.left + rect1.width() / 2) - (rect2.left  + rect2.width() / 2)
            val dy = (rect1.top + rect1.height() / 2) - (rect2.top + rect2.height() / 2)

            val width = (rect1.width() + rect2.width()) / 2
            val height = (rect1.height() + rect2.height()) / 2

            val crossWidth = width * dy
            val crossHeight = height * dx

            return if (Math.abs(dx) <= width && Math.abs(dy) <= height) {
                if (crossWidth > crossHeight) {
                    if (crossWidth > -crossHeight) 4 else 3 //"bottom" else "right"
                } else {
                    if (crossWidth > -crossHeight) 2 else 1 // "left" else "top"
                }
            } else {
                -1
            }
        }


        fun collisionControlPoint(point: PointF, block: RectF): Boolean {
            return (point.x.roundToInt() >= block.left && point.x.roundToInt() <= block.right)
                    &&
                    (point.y.roundToInt() >= block.top && point.y.roundToInt() <= block.bottom)
        }

        private fun rectColl(rect1: Rect, rect2: Rect): Boolean {
            return (
                    (((rect1.left <= rect2.left && rect2.left <= rect1.right) && (rect1.top <= rect2.top && rect2.top <= rect1.bottom)) ||
                            ((rect1.left <= rect2.right && rect2.right <= rect1.right) && (rect1.top <= rect2.top && rect2.top <= rect1.bottom)) ||
                            ((rect1.left <= rect2.left && rect2.left <= rect1.right) && (rect1.top <= rect2.bottom && rect2.bottom <= rect1.bottom)) ||
                            ((rect1.left <= rect2.right && rect2.right <= rect1.right) && (rect1.top <= rect2.bottom && rect2.bottom <= rect1.bottom))) || (

                            ((rect2.left <= rect1.left && rect1.left <= rect2.right) && (rect2.top <= rect1.top && rect1.top <= rect2.bottom)) ||
                                    ((rect2.left <= rect1.right && rect1.right <= rect2.right) && (rect2.top <= rect1.top && rect1.top <= rect2.bottom)) ||
                                    ((rect2.left <= rect1.left && rect1.left <= rect2.right) && (rect2.top <= rect1.bottom && rect1.bottom <= rect2.bottom)) ||
                                    ((rect2.left <= rect1.right && rect1.right <= rect2.right) && (rect2.top <= rect1.bottom && rect1.bottom <= rect2.bottom))
                            ))
        }

        private fun rectColl(rect1: RectF, rect2: RectF): Boolean {
            return (
                    (((rect1.left <= rect2.left && rect2.left <= rect1.right) && (rect1.top <= rect2.top && rect2.top <= rect1.bottom)) ||
                            ((rect1.left <= rect2.right && rect2.right <= rect1.right) && (rect1.top <= rect2.top && rect2.top <= rect1.bottom)) ||
                            ((rect1.left <= rect2.left && rect2.left <= rect1.right) && (rect1.top <= rect2.bottom && rect2.bottom <= rect1.bottom)) ||
                            ((rect1.left <= rect2.right && rect2.right <= rect1.right) && (rect1.top <= rect2.bottom && rect2.bottom <= rect1.bottom))) || (

                            ((rect2.left <= rect1.left && rect1.left <= rect2.right) && (rect2.top <= rect1.top && rect1.top <= rect2.bottom)) ||
                                    ((rect2.left <= rect1.right && rect1.right <= rect2.right) && (rect2.top <= rect1.top && rect1.top <= rect2.bottom)) ||
                                    ((rect2.left <= rect1.left && rect1.left <= rect2.right) && (rect2.top <= rect1.bottom && rect1.bottom <= rect2.bottom)) ||
                                    ((rect2.left <= rect1.right && rect1.right <= rect2.right) && (rect2.top <= rect1.bottom && rect1.bottom <= rect2.bottom))
                            ))
        }
    }
}