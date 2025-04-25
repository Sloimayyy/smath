package com.sloimay.smath.geometry.boundary

import com.sloimay.smath.vectors.IVec3
import com.sloimay.smath.vectors.ivec3


/**
 * Represents a 3D cuboid volume. Inclusive on the
 * negative axes and exclusive on the positive axes.
 */
class IntBoundary private constructor(
    /**
     * The corner in the negative XYZ direction of this boundary
     */
    val a: IVec3,

    /**
     * The corner in the positive XYZ direction of this boundary
     */
    val b: IVec3,

    /**
     * The dimensions in all 3 axes of this boundary
     */
    val dims: IVec3,
) {

    companion object {

        fun new(a: IVec3, b: IVec3): IntBoundary {
            val cornerA = a.min(b)
            val cornerB = a.max(b)
            return IntBoundary(cornerA, cornerB, cornerB - cornerA)
        }


        private fun rangeIntersect(r1Start: Int, r1End: Int, r2Start: Int, r2End: Int): Boolean {
            return r1Start < r2End && r1End > r2Start
        }

        private fun valInRange(v: Int, rStart: Int, rEnd: Int): Boolean {
            return v in rStart until rEnd
        }

    }


    fun posInside(pos: IVec3): Boolean {
        return (valInRange(pos.x, a.x, b.x)
                && valInRange(pos.y, a.y, b.y)
                && valInRange(pos.z, a.z, b.z))
    }

    fun intersects(other: IntBoundary): Boolean {
        return (rangeIntersect(a.x, b.x, other.a.x, other.b.x)
                && rangeIntersect(a.y, b.y, other.a.y, other.b.y)
                && rangeIntersect(a.z, b.z, other.a.z, other.b.z))
    }

    fun posOnBorder(pos: IVec3): Boolean {
        return posOnMinBorder(pos) || posOnMaxBorder(pos)
    }

    fun posOnMinBorder(pos: IVec3): Boolean {
        return (pos.x == a.x || pos.y == a.y || pos.z == a.z)
    }

    fun posOnMaxBorder(pos: IVec3): Boolean {
        return (pos.x == (b.x-1) || pos.y == (b.y-1) || pos.z == (b.z-1))
    }

    fun posToYzxIdx(pos: IVec3): Int {
        val localPos = pos - a
        val idx = (
                localPos.x
                + localPos.z * dims.x
                + localPos.y * dims.x * dims.z)
        return idx
    }

    fun getClampedInside(container: IntBoundary): IntBoundary {
        return new(
            a.clamp(container.a, container.b),
            b.clamp(container.a, container.b),
        )
    }

    fun getClampedInside(p: IVec3): IVec3 {
        return p.clamp(this.a, this.b - 1)
    }

    fun withMidpoint(point: IVec3): IntBoundary {
        val lowCorner = point - dims / 2
        return new(
            lowCorner,
            lowCorner + dims
        )
    }

    fun fullyInside(other: IntBoundary): Boolean {
        if (!this.intersects(other)) return false
        // If this clamped inside other didn't change, then
        // we are fully inside
        return this.getClampedInside(other).eq(this)
    }

    fun volume() = (b - a).toLVec3().eProd()
    fun shift(v: IVec3) = new(a + v, b + v)
    fun expand(dims: IVec3) = new(a - dims, b + dims)


    fun eq(other: IntBoundary) = this.a.eq(other.a) && this.b.eq(other.b)


    fun rangeX() = a.x until b.x
    fun rangeY() = a.y until b.y
    fun rangeZ() = a.z until b.z


    fun iterYzx(): Iterator<IVec3> {
        val boundary = this

        return object : Iterator<IVec3> {
            private var x = boundary.a.x
            private var y = boundary.a.y
            private var z = boundary.a.z

            override fun hasNext() = y < boundary.b.y

            override fun next(): IVec3 {
                val value = IVec3(x, y, z)
                x += 1
                if (x >= boundary.b.x) {
                    x = boundary.a.x
                    z += 1
                    if (z >= boundary.b.z) {
                        z = boundary.a.z
                        y += 1
                    }
                }
                return value
            }
        }
    }


    override fun toString(): String {
        return "IntBoundary( a=${this.a} b=${this.b} dims=${this.dims} )"
    }
}


internal infix fun IVec3.onBorderOf(boundary: IntBoundary) = boundary.posOnBorder(this)
