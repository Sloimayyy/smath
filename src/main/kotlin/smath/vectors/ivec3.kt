package com.sloimay.smath.vectors
import kotlin.math.*

data class IVec3(val x: Int, val y: Int, val z: Int) {

    companion object {
        val ZERO = new(0, 0, 0)
        val ONE = new(1, 1, 1)
        val X = new(1, 0, 0)
        val Y = new(0, 1, 0)
        val Z = new(0, 0, 1)
        val MIN = splat(Int.MIN_VALUE)
        val MAX = splat(Int.MAX_VALUE)

        fun new(x: Int, y: Int, z: Int) = IVec3(x, y, z)
        fun splat(v: Int) = IVec3(v, v, v)

        fun fromArray(arr: IntArray) = new(arr[0], arr[1], arr[2])

        private fun hashInt(i: Int): Int {
            var v = i
            v = v xor (v shl 13)
            v = v xor (v ushr 13)
            v = v xor (v shl 5)
            return v
        }
    }

    operator fun plus(other: IVec3) = new(this.x + other.x, this.y + other.y, this.z + other.z)
    operator fun plus(other: Int) = new(this.x + other, this.y + other, this.z + other)
    operator fun minus(other: IVec3) = new(this.x - other.x, this.y - other.y, this.z - other.z)
    operator fun minus(other: Int) = new(this.x - other, this.y - other, this.z - other)
    operator fun unaryMinus() = new(-this.x, -this.y, -this.z)
    operator fun unaryPlus() = this
    operator fun times(other: IVec3) = new(this.x * other.x, this.y * other.y, this.z * other.z)
    operator fun times(other: Int) = new(this.x * other, this.y * other, this.z * other)
    operator fun div(other: IVec3) = new(this.x / other.x, this.y / other.y, this.z / other.z)
    operator fun div(other: Int) = new(this.x / other, this.y / other, this.z / other)
    operator fun rem(other: IVec3) = new(this.x % other.x, this.y % other.y, this.z % other.z)
    operator fun rem(other: Int) = new(this.x % other, this.y % other, this.z % other)
    operator fun get(idx: UByte) =
        when (idx) {
            0.toUByte() -> this.x
            1.toUByte() -> this.y
            else -> this.z
        }
    operator fun get(idx: UShort) = this[idx.toUByte()]
    operator fun get(idx: Short) = this[idx.toUByte()]
    operator fun get(idx: UInt) = this[idx.toUByte()]
    operator fun get(idx: Int) = this[idx.toUByte()]
    operator fun get(idx: Long) = this[idx.toUByte()]
    operator fun get(idx: ULong) = this[idx.toUByte()]

    infix fun shr(other: IVec3) = new(this.x shr other.x, this.y shr other.y, this.z shr other.z)
    infix fun shr(other: Int) = new(this.x shr other, this.y shr other, this.z shr other)
    infix fun shl(other: IVec3) = new(this.x shl other.x, this.y shl other.y, this.z shl other.z)
    infix fun shl(other: Int) = new(this.x shl other, this.y shl other, this.z shl other)
    infix fun and(other: IVec3) = new(this.x and other.x, this.y and other.y, this.z and other.z)
    infix fun and(other: Int) = new(this.x and other, this.y and other, this.z and other)

    fun asVec3() = Vec3.new(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())
    fun asDVec3() = DVec3.new(this.x.toDouble(), this.y.toDouble(), this.z.toDouble())

    fun equality(other: IVec3) = this.x == other.x && this.y == other.y && this.z == other.z
    fun abs() = new(abs(this.x), abs(this.y), abs(this.z))
    fun withX(v: Int) = new(v, this.y, this.z)
    fun withY(v: Int) = new(this.x, v, this.z)
    fun withZ(v: Int) = new(this.x, this.y, v)
    fun max(other: IVec3) = new(max(this.x, other.x), max(this.y, other.y), max(this.z, other.z))
    fun min(other: IVec3) = new(min(this.x, other.x), min(this.y, other.y), min(this.z, other.z))
    fun clamp(min: IVec3, max: IVec3) = this.max(min).min(max)
    fun dot(other: IVec3) = this.x * other.x + this.y * other.y + this.z * other.z
    fun lengthSqrd() = this.dot(this)
    fun distSqrd(other: IVec3) = (this - other).lengthSqrd()
    fun floorDiv(other: IVec3) = new(this.x.floorDiv(other.x), this.y.floorDiv(other.y), this.z.floorDiv(other.z))
    fun elementProduct() = x * y * z
    fun maxElement() = max(x, max(y, z))
    fun copy() = new(x, y, z)

    fun cross(other: IVec3) = new(
        this.y * other.z - other.y * this.z,
        this.z * other.x - other.z * this.x,
        this.x * other.y - other.x * this.y,
    )

    fun longestAxis(): Int {
        val maxEl = maxElement()
        return if (x == maxEl) {
            0
        } else if (y == maxEl) {
            1
        } else {
            2
        }
    }

    fun withAxis(axis: Int, v: Int): IVec3 {
        return when (axis) {
            0 -> this.withX(v)
            1 -> this.withY(v)
            else -> this.withZ(v)
        }
    }


    fun toArray() = intArrayOf(x, y, z)


    /*override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + hashInt(x)
        hash = hash * 31 + hashInt(y)
        hash = hash * 31 + hashInt(z)
        return hash
    }*/

    override fun toString(): String {
        return "IVec3(" +
                "x=$x " +
                "y=$y " +
                "z=$z" +
                ")"
    }
}

operator fun Int.plus(vec: IVec3) = vec + this
operator fun Int.minus(vec: IVec3) = IVec3(this - vec.x, this - vec.y, this - vec.z)
operator fun Int.times(vec: IVec3) = vec * this
operator fun Int.div(vec: IVec3) = IVec3(this / vec.x, this / vec.y, this / vec.z)

fun ivec3(x: Int, y: Int, z: Int) = IVec3.new(x, y, z)