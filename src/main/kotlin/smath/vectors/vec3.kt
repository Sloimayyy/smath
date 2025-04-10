package com.sloimay.smath.vectors


import java.util.*
import kotlin.math.sqrt
import kotlin.math.floor
import kotlin.math.ceil
import kotlin.math.round
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max
import kotlin.math.*

/*@GenVecOps("Float")
data class Vec3(val x: Float, val y: Float, val z: Float) {
    companion object
}*/



data class Vec3(val x: Float, val y: Float, val z: Float) {

    companion object {
        val ZERO = new(0f, 0f, 0f)
        val ONE = new(1f, 1f, 1f)
        val X = new(1f, 0f, 0f)
        val Y = new(0f, 1f, 0f)
        val Z = new(0f, 0f, 1f)
        val INF = splat(Float.POSITIVE_INFINITY)
        val NEG_INF = splat(Float.NEGATIVE_INFINITY)
        val MIN = splat(Float.MIN_VALUE)
        val MAX = splat(Float.MAX_VALUE)

        fun new(x: Float, y: Float, z: Float) = Vec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
        fun splat(v: Float) = Vec3(v, v, v)
        fun eye(index: Int) = Vec3(
            if (index == 0) 1f else 0f,
            if (index == 1) 1f else 0f,
            if (index == 2) 1f else 0f,
        )

        fun lerp(a: Vec3, b: Vec3, t: Float) = a.lerp(b, t)
    }

    operator fun plus(other: Vec3)  = new(this.x + other.x, this.y + other.y, this.z + other.z)
    operator fun plus(other: Float) = new(this.x + other, this.y + other, this.z + other)
    operator fun minus(other: Vec3) =  new(this.x - other.x, this.y - other.y, this.z - other.z)
    operator fun minus(other: Float) = new(this.x - other, this.y - other, this.z - other)
    operator fun unaryMinus() = new(-this.x, -this.y, -this.z)
    operator fun unaryPlus() = this
    operator fun times(other: Vec3)  = new(this.x * other.x, this.y * other.y, this.z * other.z)
    operator fun times(other: Float) = new(this.x * other, this.y * other, this.z * other)
    operator fun div(other: Vec3)  = new(this.x / other.x, this.y / other.y, this.z / other.z)
    operator fun div(other: Float) = new(this.x / other, this.y / other, this.z / other)
    operator fun rem(other: Vec3)  = new(this.x % other.x, this.y % other.y, this.z % other.z)
    operator fun rem(other: Float) = new(this.x % other, this.y % other, this.z % other)
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

    fun equality(other: Vec3) = this.x == other.x && this.y == other.y && this.z == other.z
    fun floor() = new(floor(this.x), floor(this.y), floor(this.z))
    fun ceil() = new(ceil(this.x), ceil(this.y), ceil(this.z))
    fun round() = new(round(this.x), round(this.y), round(this.z))
    fun abs() = new(abs(this.x), abs(this.y), abs(this.z))
    fun withX(v: Float) = new(v, this.y, this.z)
    fun withY(v: Float) = new(this.x, v, this.z)
    fun withZ(v: Float) = new(this.x, this.y, v)
    fun max(other: Vec3) = new(max(this.x, other.x), max(this.y, other.y), max(this.z, other.z))
    fun min(other: Vec3) = new(min(this.x, other.x), min(this.y, other.y), min(this.z, other.z))
    fun clamp(min: Vec3, max: Vec3) = this.max(min).min(max)
    fun fract() = this - this.floor()
    fun dot(other: Vec3) = this.x * other.x + this.y * other.y + this.z * other.z
    fun lengthSqrd() = this.dot(this)
    fun length() = sqrt(this.dot(this))
    fun dist(other: Vec3) = (this - other).length()
    fun distSqrd(other: Vec3) = (this - other).lengthSqrd()
    fun normalize() = this * (1f / this.length())
    fun lerp(other: Vec3, t: Float) = this * (1f - t) + other * t
    fun extend(w: Float) = Vec4.new(x, y, z, w)
    fun setLength(length: Float) = this.normalize() * length
    fun elementSum() = x + y + z
    fun manhattan(other: Vec3) = (this - other).abs().elementSum()

    fun asIVec3() = IVec3.new(this.x.toInt(), this.y.toInt(), this.z.toInt())

    fun quatMul(q: Quat): Vec3 {
        val u = new(q.x, q.y, q.z)
        val scalar = q.w
        return (
                (2f * u.dot(this) * u) +
                (scalar * scalar - u.dot(u)) * this +
                (2f * scalar * u.cross(this))
        )
    }

    fun cross(other: Vec3) = new(
        this.y * other.z - other.y * this.z,
        this.z * other.x - other.z * this.x,
        this.x * other.y - other.x * this.y,
    )
    fun anyOrthonormalPair(): Pair<Vec3, Vec3> {
        // From https://graphics.pixar.com/library/OrthonormalB/paper.pdf
        var sign = z.sign;
        if (z == 0.0f) { sign = 1.0f }
        else if (z == -0.0f) { sign = -1.0f }
        val a = -1.0f / (sign + z)
        val b = x * y * a
        return Pair(
            new(1.0f + sign * x * x * a, sign * b, -sign * x),
            new(b, sign + y * y * a, -y)
        )
    }
    fun limit(length: Float): Vec3 {
        val len = this.length()
        if (len > length) {
            return this.setLength(length)
        } else {
            return this
        }
    }

    override fun toString(): String {
        return "Vec3(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " +
                "z=${"%.5f".format(Locale.ENGLISH, z)}" +
                ")"
    }
}

operator fun Float.plus(vec: Vec3) = vec + this
operator fun Float.minus(vec: Vec3) = Vec3(this - vec.x, this - vec.y, this - vec.z)
operator fun Float.times(vec: Vec3) = vec * this
operator fun Float.div(vec: Vec3) = Vec3(this / vec.x, this / vec.y, this / vec.z)

fun vec3(x: Float, y: Float, z: Float) = Vec3.new(x, y, z)
fun vec3(x: Int, y: Int, z: Int) = Vec3.new(x, y, z)
