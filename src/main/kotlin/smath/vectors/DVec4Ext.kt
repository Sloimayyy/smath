package com.sloimay.smath.vectors
import java.util.*
import kotlin.math.*

/*
data class DVec4(val x: Double, val y: Double, val z: Double, val w: Double) {

    companion object {
        val ZERO = new(0.0, 0.0, 0.0, 0.0)
        val ONE = new(1.0, 1.0, 1.0, 1.0)
        val X = new(1.0, 0.0, 0.0, 0.0)
        val Y = new(0.0, 1.0, 0.0, 0.0)
        val Z = new(0.0, 0.0, 1.0, 0.0)
        val W = new(0.0, 0.0, 0.0, 1.0)
        val INF = splat(Double.POSITIVE_INFINITY)
        val NEG_INF = splat(Double.NEGATIVE_INFINITY)
        val MIN = splat(Double.MIN_VALUE)
        val MAX = splat(Double.MAX_VALUE)

        fun new(x: Double, y: Double, z: Double, w: Double) = DVec4(x, y, z, w)
        fun splat(v: Double) = DVec4(v, v, v, v)

        fun lerp(a: DVec4, b: DVec4, t: Double) = a.lerp(b, t)

        fun fromLambda(lamb: (axisIdx: Int) -> Double) = DVec4(
            lamb(0),
            lamb(1),
            lamb(2),
            lamb(3),
        )
    }

    operator fun plus(other: DVec4)  = new(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w)
    operator fun plus(other: Double) = new(this.x + other, this.y + other, this.z + other, this.w + other)
    operator fun minus(other: DVec4) =  new(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w)
    operator fun minus(other: Double) = new(this.x - other, this.y - other, this.z - other, this.w - other)
    operator fun unaryMinus() = new(-this.x, -this.y, -this.z, -this.w)
    operator fun unaryPlus() = this
    operator fun times(other: DVec4)  = new(this.x * other.x, this.y * other.y, this.z * other.z, this.w * other.w)
    operator fun times(other: Double) = new(this.x * other, this.y * other, this.z * other, this.w * other)
    operator fun div(other: DVec4)  = new(this.x / other.x, this.y / other.y, this.z / other.z, this.w / other.w)
    operator fun div(other: Double) = new(this.x / other, this.y / other, this.z / other, this.w / other)
    operator fun rem(other: DVec4)  = new(this.x % other.x, this.y % other.y, this.z % other.z, this.w % other.w)
    operator fun rem(other: Double) = new(this.x % other, this.y % other, this.z % other, this.w % other)
    operator fun get(idx: UByte) =
        when (idx) {
            0.toUByte() -> this.x
            1.toUByte() -> this.y
            2.toUByte() -> this.z
            else -> this.w
        }
    operator fun get(idx: UShort) = this[idx.toUByte()]
    operator fun get(idx: Short) = this[idx.toUByte()]
    operator fun get(idx: UInt) = this[idx.toUByte()]
    operator fun get(idx: Int) = this[idx.toUByte()]
    operator fun get(idx: Long) = this[idx.toUByte()]
    operator fun get(idx: ULong) = this[idx.toUByte()]

    fun equality(other: DVec4) = this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w
    fun floor() = new(floor(this.x), floor(this.y), floor(this.z), floor(this.w))
    fun ceil() = new(ceil(this.x), ceil(this.y), ceil(this.z), ceil(this.w))
    fun round() = new(round(this.x), round(this.y), round(this.z), round(this.w))
    fun abs() = new(abs(this.x), abs(this.y), abs(this.z), abs(this.w))
    fun withX(v: Double) = new(v, this.y, this.z, this.w)
    fun withY(v: Double) = new(this.x, v, this.z, this.w)
    fun withZ(v: Double) = new(this.x, this.y, v, this.w)
    fun withW(v: Double) = new(this.x, this.y, this.z, v)
    fun max(other: DVec4) = new(max(this.x, other.x), max(this.y, other.y), max(this.z, other.z), max(this.w, other.w))
    fun min(other: DVec4) = new(min(this.x, other.x), min(this.y, other.y), min(this.z, other.z), min(this.w, other.w))
    fun clamp(min: DVec4, max: DVec4) = this.max(min).min(max)
    fun fract() = this - this.floor()
    fun dot(other: DVec4) = this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w
    fun lengthSqrd() = this.dot(this)
    fun length() = sqrt(this.dot(this))
    fun dist(other: DVec4) = (this - other).length()
    fun distSqrd(other: DVec4) = (this - other).lengthSqrd()
    fun normalize() = this * (1f / this.length())
    fun lerp(other: DVec4, t: Double) = this * (1f - t) + other * t


    override fun toString(): String {
        return "DVec4(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " +
                "z=${"%.5f".format(Locale.ENGLISH, z)}, " +
                "w=${"%.5f".format(Locale.ENGLISH, w)}" +
                ")"
    }
}

operator fun Double.plus(vec: DVec4) = vec + this
operator fun Double.minus(vec: DVec4) = DVec4.new(this - vec.x, this - vec.y, this - vec.z, this - vec.w)
operator fun Double.times(vec: DVec4) = vec * this
operator fun Double.div(vec: DVec4) = DVec4.new(this / vec.x, this / vec.y, this / vec.z, this / vec.w)

fun dvec4(x: Double, y: Double, z: Double, w: Double) = DVec4.new(x, y, z, w)


 */