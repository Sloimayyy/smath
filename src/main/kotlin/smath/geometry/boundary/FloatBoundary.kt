package com.sloimay.smath.geometry.boundary

import com.sloimay.smath.vectors.Vec3

class FloatBoundary private constructor(
    val mCorner: Vec3,
    val pCorner: Vec3,
    val dims: Vec3,
) {

    companion object {

        fun new(a: Vec3, b: Vec3): FloatBoundary {
            val boundsA = a.min(b)
            val boundsB = a.max(b)
            return FloatBoundary(boundsA, boundsB, boundsB - boundsA)
        }

        fun fromCenterDims(p: Vec3, dims: Vec3): FloatBoundary {
            val halfDims = dims / 2f
            return new(p - halfDims, p + halfDims)
        }

        fun fromMinusCornerDims(mCorner: Vec3, dims: Vec3): FloatBoundary {
            return new(mCorner, mCorner + dims)
        }

    }

    fun posInside(p: Vec3): Boolean {
        return (valInRange(p.x, mCorner.x, pCorner.x) &&
                valInRange(p.y, mCorner.y, pCorner.y) &&
                valInRange(p.z, mCorner.z, pCorner.z))
    }

    fun intersects(other: FloatBoundary): Boolean {
        return (rangeIntersect(mCorner.x, pCorner.x, other.mCorner.x, other.pCorner.x) &&
                rangeIntersect(mCorner.y, pCorner.y, other.mCorner.y, other.pCorner.y) &&
                rangeIntersect(mCorner.z, pCorner.z, other.mCorner.z, other.pCorner.z))
    }

    fun distSqrdFrom(p: Vec3): Float {
        if (posInside(p)) return 0f

        val closestPointInBoundary = p.clamp(mCorner, pCorner)
        return p.distSq(closestPointInBoundary)
    }

    fun shift(shift: Vec3): FloatBoundary {
        return new(mCorner + shift, pCorner + shift)
    }

}




private fun rangeIntersect(r1Start: Float, r1End: Float, r2Start: Float, r2End: Float): Boolean {
    return r1Start < r2End && r1End > r2Start
}

private fun valInRange(v: Float, rStart: Float, rEnd: Float): Boolean {
    return rStart <= v && v < rEnd
}