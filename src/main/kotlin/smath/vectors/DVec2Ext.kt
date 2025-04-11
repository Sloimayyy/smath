package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/*
data class DVec2(val x: Double, val y: Double) {

    companion object {
        val ZERO = new(0.0, 0.0)
        val ONE = new(1.0, 1.0)
        val X = new(1.0, 0.0)
        val Y = new(0.0, 1.0)
        val INF = splat(Double.POSITIVE_INFINITY)
        val NEG_INF = splat(Double.NEGATIVE_INFINITY)
        val MIN = splat(Double.MIN_VALUE)
        val MAX = splat(Double.MAX_VALUE)

        fun new(x: Double, y: Double) = DVec2(x, y)
        fun splat(v: Double) = DVec2(v, v)

        fun lerp(a: DVec2, b: DVec2, t: Double) = a.lerp(b, t)
        fun fromAngle(angle: Double) = new(cos(angle), sin(angle))
    }

    operator fun plus(other: DVec2)  = new(this.x + other.x, this.y + other.y)
    operator fun plus(other: Double) = new(this.x + other, this.y + other)
    operator fun minus(other: DVec2) =  new(this.x - other.x, this.y - other.y)
    operator fun minus(other: Double) = new(this.x - other, this.y - other)
    operator fun unaryMinus() = new(-this.x, -this.y)
    operator fun unaryPlus() = this
    operator fun times(other: DVec2)  = new(this.x * other.x, this.y * other.y)
    operator fun times(other: Double) = new(this.x * other, this.y * other)
    operator fun div(other: DVec2)  = new(this.x / other.x, this.y / other.y)
    operator fun div(other: Double) = new(this.x / other, this.y / other)
    operator fun rem(other: DVec2)  = new(this.x % other.x, this.y % other.y)
    operator fun rem(other: Double) = new(this.x % other, this.y % other)
    operator fun get(idx: UByte) =
        when (idx) {
            0.toUByte() -> this.x
            else -> this.y
        }
    operator fun get(idx: UShort) = this[idx.toUByte()]
    operator fun get(idx: Short) = this[idx.toUByte()]
    operator fun get(idx: UInt) = this[idx.toUByte()]
    operator fun get(idx: Int) = this[idx.toUByte()]
    operator fun get(idx: Long) = this[idx.toUByte()]
    operator fun get(idx: ULong) = this[idx.toUByte()]

    fun equality(other: DVec2) = this.x == other.x && this.y == other.y
    fun floor() = new(kotlin.math.floor(this.x), kotlin.math.floor(this.y))
    fun ceil() = new(kotlin.math.ceil(this.x), kotlin.math.ceil(this.y))
    fun round() = new(kotlin.math.round(this.x), kotlin.math.round(this.y))
    fun abs() = new(kotlin.math.abs(this.x), kotlin.math.abs(this.y))
    fun withX(v: Double) = new(v, this.y)
    fun withY(v: Double) = new(this.x, v)
    fun max(other: DVec2) = new(
        kotlin.math.max(this.x, other.x),
        kotlin.math.max(this.y, other.y),
    )
    fun min(other: DVec2) = new(
        kotlin.math.min(this.x, other.x),
        kotlin.math.min(this.y, other.y),
    )
    fun clamp(min: DVec2, max: DVec2) = this.max(min).min(max)
    fun fract() = this - this.floor()
    fun dot(other: DVec2) = this.x * other.x + this.y * other.y
    fun lengthSqrd() = this.dot(this)
    fun length() = sqrt(this.dot(this))
    fun dist(other: DVec2) = (this - other).length()
    fun distSqrd(other: DVec2) = (this - other).lengthSqrd()
    fun normalize() = this * (1f / this.length())
    fun lerp(other: DVec2, t: Double) = this * (1f - t) + other * t
    fun extend(z: Double) = DVec3Ext.new(x, y, z)
    fun elementSum() = x + y

    fun asIVec2() = IVec2.new(this.x.toInt(), this.y.toInt())

    fun cross(other:DVec2) = DVec3Ext.new(
        0.0,
        0.0,
        this.x * other.y - other.x * this.y,
    )
    fun rotate(angle: Double): DVec2 {
        val c = cos(angle)
        val s = sin(angle)
        return new(
            this.x * c - this.y * s,
            this.x * s + this.y * c,
        )
    }


    override fun toString(): String {
        return "DVec2(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}" +
                ")"
    }
}

fun dvec2(x: Double, y: Double) = DVec2.new(x, y)
 */