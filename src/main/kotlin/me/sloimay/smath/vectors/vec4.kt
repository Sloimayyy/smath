package me.sloimay.smath.vectors
import java.util.*
import kotlin.math.*

data class Vec4(var x: Float, val y: Float, val z: Float, val w: Float) {

    companion object {
        val ZERO = new(0f, 0f, 0f, 0f)
        val ONE = new(1f, 1f, 1f, 1f)
        val X = new(1f, 0f, 0f, 0f)
        val Y = new(0f, 1f, 0f, 0f)
        val Z = new(0f, 0f, 1f, 0f)
        val W = new(0f, 0f, 0f, 1f)
        val INF = splat(Float.POSITIVE_INFINITY)
        val NEG_INF = splat(Float.NEGATIVE_INFINITY)
        val MIN = splat(Float.MIN_VALUE)
        val MAX = splat(Float.MAX_VALUE)

        fun new(x: Float, y: Float, z: Float, w: Float) = Vec4(x, y, z, w)
        fun splat(v: Float) = Vec4(v, v, v, v)

        fun lerp(a: Vec4, b: Vec4, t: Float) = a.lerp(b, t)
    }

    operator fun plus(other: Vec4)  = new(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w)
    operator fun plus(other: Float) = new(this.x + other, this.y + other, this.z + other, this.w + other)
    operator fun minus(other: Vec4) =  new(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w)
    operator fun minus(other: Float) = new(this.x - other, this.y - other, this.z - other, this.w - other)
    operator fun unaryMinus() = new(-this.x, -this.y, -this.z, -this.w)
    operator fun unaryPlus() = this
    operator fun times(other: Vec4)  = new(this.x * other.x, this.y * other.y, this.z * other.z, this.w * other.w)
    operator fun times(other: Float) = new(this.x * other, this.y * other, this.z * other, this.w * other)
    operator fun div(other: Vec4)  = new(this.x / other.x, this.y / other.y, this.z / other.z, this.w / other.w)
    operator fun div(other: Float) = new(this.x / other, this.y / other, this.z / other, this.w / other)
    operator fun rem(other: Vec4)  = new(this.x % other.x, this.y % other.y, this.z % other.z, this.w % other.w)
    operator fun rem(other: Float) = new(this.x % other, this.y % other, this.z % other, this.w % other)
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

    fun equality(other: Vec4) = this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w
    fun floor() = new(floor(this.x), floor(this.y), floor(this.z), floor(this.w))
    fun ceil() = new(ceil(this.x), ceil(this.y), ceil(this.z), ceil(this.w))
    fun round() = new(round(this.x), round(this.y), round(this.z), round(this.w))
    fun abs() = new(abs(this.x), abs(this.y), abs(this.z), abs(this.w))
    fun withX(v: Float) = new(v, this.y, this.z, this.w)
    fun withY(v: Float) = new(this.x, v, this.z, this.w)
    fun withZ(v: Float) = new(this.x, this.y, v, this.w)
    fun withW(v: Float) = new(this.x, this.y, this.z, v)
    fun max(other: Vec4) = new(max(this.x, other.x), max(this.y, other.y), max(this.z, other.z), max(this.w, other.w))
    fun min(other: Vec4) = new(min(this.x, other.x), min(this.y, other.y), min(this.z, other.z), min(this.w, other.w))
    fun clamp(min: Vec4, max: Vec4) = this.max(min).min(max)
    fun fract() = this - this.floor()
    fun dot(other: Vec4) = this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w
    fun lengthSqrd() = this.dot(this)
    fun length() = sqrt(this.dot(this))
    fun dist(other: Vec4) = (this - other).length()
    fun distSqrd(other: Vec4) = (this - other).lengthSqrd()
    fun normalize() = this * (1f / this.length())
    fun lerp(other: Vec4, t: Float) = this * (1f - t) + other * t


    override fun toString(): String {
        return "Vec4(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, z)}, " +
                "z=${"%.5f".format(Locale.ENGLISH, w)}" +
                ")"
    }
}

operator fun Float.plus(vec: Vec4) = vec + this
operator fun Float.minus(vec: Vec4) = Vec4.new(this - vec.x, this - vec.y, this - vec.z, this - vec.w)
operator fun Float.times(vec: Vec4) = vec * this
operator fun Float.div(vec: Vec4) = Vec4.new(this / vec.x, this / vec.y, this / vec.z, this / vec.w)

fun vec4(x: Float, y: Float, z: Float, w: Float) = Vec4.new(x, y, z, w)
