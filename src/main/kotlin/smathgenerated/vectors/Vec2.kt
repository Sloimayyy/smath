package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class Vec2(val x: Float, val y: Float) {

    companion object {
        fun splat(value: Byte) = Vec2(value, value)
        fun splat(value: Short) = Vec2(value, value)
        fun splat(value: Int) = Vec2(value, value)
        fun splat(value: Long) = Vec2(value, value)
        fun splat(value: Float) = Vec2(value, value)
        fun splat(value: Double) = Vec2(value, value)
        fun new(x: Byte, y: Byte) = Vec2(x, y)
        fun new(x: Short, y: Short) = Vec2(x, y)
        fun new(x: Int, y: Int) = Vec2(x, y)
        fun new(x: Long, y: Long) = Vec2(x, y)
        fun new(x: Float, y: Float) = Vec2(x, y)
        fun new(x: Double, y: Double) = Vec2(x, y)
        fun eye(index: Int, value: Float = 1.0f): Vec2 {
            require(index in 0 until 2) { 
                "Index out of bounds of the range [0; 2). (Got ${index})."
            }
            return Vec2(if (index == 0) value else 0.0f, if (index == 1) value else 0.0f)
        }

        fun fromArray(array: FloatArray): Vec2 {
            require(array.size == 2) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 2, got ${array.size}."
            }
            return Vec2(array[0], array[1])
        }

        val X: Vec2 = Vec2(1.0f, 0.0f)
        val Y: Vec2 = Vec2(0.0f, 1.0f)
        val ZERO: Vec2 = Vec2(0.0f)
        val ONE: Vec2 = Vec2(1.0f)
        val INF: Vec2 = Vec2(Float.POSITIVE_INFINITY)
        val NEG_INF: Vec2 = Vec2(Float.NEGATIVE_INFINITY)
        val MIN: Vec2 = Vec2(Float.MIN_VALUE)
        val MAX: Vec2 = Vec2(Float.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toFloat(), value.toFloat())
    constructor(x: Byte, y: Byte) : this(x.toFloat(), y.toFloat())
    constructor(value: Short) : this(value.toFloat(), value.toFloat())
    constructor(x: Short, y: Short) : this(x.toFloat(), y.toFloat())
    constructor(value: Int) : this(value.toFloat(), value.toFloat())
    constructor(x: Int, y: Int) : this(x.toFloat(), y.toFloat())
    constructor(value: Long) : this(value.toFloat(), value.toFloat())
    constructor(x: Long, y: Long) : this(x.toFloat(), y.toFloat())
    constructor(value: Float) : this(value, value)
    constructor(value: Double) : this(value.toFloat(), value.toFloat())
    constructor(x: Double, y: Double) : this(x.toFloat(), y.toFloat())
    constructor() : this(0.0f, 0.0f)
    constructor(lamb: (Int) -> Float) : this(lamb(0), lamb(1))

    operator fun plus(other: Vec2) = Vec2(x + other.x, y + other.y)
    operator fun plus(other: Float) = Vec2(x + other, y + other)
    operator fun minus(other: Vec2) = Vec2(x - other.x, y - other.y)
    operator fun minus(other: Float) = Vec2(x - other, y - other)
    operator fun times(other: Vec2) = Vec2(x * other.x, y * other.y)
    operator fun times(other: Float) = Vec2(x * other, y * other)
    operator fun div(other: Vec2) = Vec2(x / other.x, y / other.y)
    operator fun div(other: Float) = Vec2(x / other, y / other)
    operator fun rem(other: Vec2) = Vec2(x % other.x, y % other.y)
    operator fun rem(other: Float) = Vec2(x % other, y % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = Vec2(-x, -y)
    operator fun get(idx: Byte): Float {
        require(idx in 0 until 2) { 
            "Vec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Short): Float {
        require(idx in 0 until 2) { 
            "Vec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Int): Float {
        require(idx in 0 until 2) { 
            "Vec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; else -> y }
    }
    operator fun get(idx: Long): Float {
        require(idx in 0 until 2) { 
            "Vec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }

    fun toBVec2() = BVec2(x.toInt().toByte(), y.toInt().toByte())
    fun toSVec2() = SVec2(x.toInt().toShort(), y.toInt().toShort())
    fun toIVec2() = IVec2(x.toInt(), y.toInt())
    fun toLVec2() = LVec2(x.toLong(), y.toLong())
    fun toDVec2() = DVec2(x.toDouble(), y.toDouble())

    fun eq(other: Vec2) = x == other.x && y == other.y
    fun iter(): Iterator<Float> {
        return object : Iterator<Float> {
            private var idx = 0
            override fun hasNext() = idx < 2
            override fun next() = this@Vec2[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = Vec2(abs(x), abs(y))
    fun mod(value: Float) = Vec2(x.mod(value), y.mod(value))
    fun mod(other: Vec2) = Vec2(x.mod(other.x), y.mod(other.y))
    fun min(other: Vec2) = Vec2(min(x, other.x), min(y, other.y))
    fun min(other: Float) = Vec2(min(x, other), min(y, other))
    fun max(other: Vec2) = Vec2(max(x, other.x), max(y, other.y))
    fun max(other: Float) = Vec2(max(x, other), max(y, other))
    fun clamp(low: Vec2, high: Vec2) = Vec2(max(min(x, high.x), low.x), max(min(y, high.y), low.y))
    fun dot(other: Vec2) = x * other.x + y * other.y
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq())
    fun dist(other: Vec2) = (this - other).len()
    fun distSq(other: Vec2) = (this - other).lenSq()
    fun normalize() = this / len()
    fun dir() = this / len()
    fun elementSum() = (x + y)
    fun eSum() = (x + y)
    fun elementProd() = (x * y)
    fun eProd() = (x * y)
    fun minElement() = min(x, y)
    fun eMin() = min(x, y)
    fun maxElement() = max(x, y)
    fun eMax() = max(x, y)
    fun indexOfMin() = when (eMin()) { x -> 0; else -> 1 }
    fun indexOfMax() = when (eMax()) { x -> 0; else -> 1 }
    fun manhattan(other: Vec2) = (this - other).abs().eSum()
    fun cross(other: Vec2) = Vec3(0.0f, 0.0f, x * other.y - other.x * y)
    fun toArray() = floatArrayOf(x, y)
    fun lerp(other: Vec2, t: Float) = this * (1.0f - t) + other * t
    fun floor() = Vec2(floor(x), floor(y))
    fun ceil() = Vec2(ceil(x), ceil(y))
    fun round() = Vec2(round(x), round(y))
    fun fract() = mod(1.0f)
    fun rot(angle: Float): Vec2 {
        val c = cos(angle)
        val s = sin(angle)
        return Vec2(x*c - y*s, x*s + y*c)
    }

    fun extend(z: Byte) = Vec3(x, y, z.toFloat())
    fun extend(z: Short) = Vec3(x, y, z.toFloat())
    fun extend(z: Int) = Vec3(x, y, z.toFloat())
    fun extend(z: Long) = Vec3(x, y, z.toFloat())
    fun extend(z: Float) = Vec3(x, y, z)
    fun extend(z: Double) = Vec3(x, y, z.toFloat())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun perm(other: BVec2) = Vec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: SVec2) = Vec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: IVec2) = Vec2(this[other.x], this[other.y])
    fun perm(other: LVec2) = Vec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: BVec3) = Vec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: SVec3) = Vec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: IVec3) = Vec3(this[other.x], this[other.y], this[other.z])
    fun perm(other: LVec3) = Vec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: BVec4) = Vec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: SVec4) = Vec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: IVec4) = Vec4(this[other.x], this[other.y], this[other.z], this[other.w])
    fun perm(other: LVec4) = Vec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun withX(value: Byte) = Vec2(value.toFloat(), y)
    fun withX(value: Short) = Vec2(value.toFloat(), y)
    fun withX(value: Int) = Vec2(value.toFloat(), y)
    fun withX(value: Long) = Vec2(value.toFloat(), y)
    fun withX(value: Float) = Vec2(value, y)
    fun withX(value: Double) = Vec2(value.toFloat(), y)
    fun withY(value: Byte) = Vec2(x, value.toFloat())
    fun withY(value: Short) = Vec2(x, value.toFloat())
    fun withY(value: Int) = Vec2(x, value.toFloat())
    fun withY(value: Long) = Vec2(x, value.toFloat())
    fun withY(value: Float) = Vec2(x, value)
    fun withY(value: Double) = Vec2(x, value.toFloat())

    val xx get() = Vec2(x, x)
    val xy get() = Vec2(x, y)
    val yx get() = Vec2(y, x)
    val yy get() = Vec2(y, y)
    val xxx get() = Vec3(x, x, x)
    val xxy get() = Vec3(x, x, y)
    val xyx get() = Vec3(x, y, x)
    val xyy get() = Vec3(x, y, y)
    val yxx get() = Vec3(y, x, x)
    val yxy get() = Vec3(y, x, y)
    val yyx get() = Vec3(y, y, x)
    val yyy get() = Vec3(y, y, y)
    val xxxx get() = Vec4(x, x, x, x)
    val xxxy get() = Vec4(x, x, x, y)
    val xxyx get() = Vec4(x, x, y, x)
    val xxyy get() = Vec4(x, x, y, y)
    val xyxx get() = Vec4(x, y, x, x)
    val xyxy get() = Vec4(x, y, x, y)
    val xyyx get() = Vec4(x, y, y, x)
    val xyyy get() = Vec4(x, y, y, y)
    val yxxx get() = Vec4(y, x, x, x)
    val yxxy get() = Vec4(y, x, x, y)
    val yxyx get() = Vec4(y, x, y, x)
    val yxyy get() = Vec4(y, x, y, y)
    val yyxx get() = Vec4(y, y, x, x)
    val yyxy get() = Vec4(y, y, x, y)
    val yyyx get() = Vec4(y, y, y, x)
    val yyyy get() = Vec4(y, y, y, y)

    override fun toString(): String {
        return ("Vec2(" + 
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " + 
                "y=${"%.5f".format(Locale.ENGLISH, y)})")
    }

}

operator fun Float.plus(other: Vec2) = Vec2(this + other.x, this + other.y)
operator fun Float.minus(other: Vec2) = Vec2(this - other.x, this - other.y)
operator fun Float.times(other: Vec2) = Vec2(this * other.x, this * other.y)
operator fun Float.div(other: Vec2) = Vec2(this / other.x, this / other.y)
operator fun Float.rem(other: Vec2) = Vec2(this % other.x, this % other.y)

fun vec2(value: Byte) = Vec2(value.toFloat())
fun vec2(x: Byte, y: Byte) = Vec2(x.toFloat(), y.toFloat())
fun vec2(value: Short) = Vec2(value.toFloat())
fun vec2(x: Short, y: Short) = Vec2(x.toFloat(), y.toFloat())
fun vec2(value: Int) = Vec2(value.toFloat())
fun vec2(x: Int, y: Int) = Vec2(x.toFloat(), y.toFloat())
fun vec2(value: Long) = Vec2(value.toFloat())
fun vec2(x: Long, y: Long) = Vec2(x.toFloat(), y.toFloat())
fun vec2(value: Float) = Vec2(value)
fun vec2(x: Float, y: Float) = Vec2(x, y)
fun vec2(value: Double) = Vec2(value.toFloat())
fun vec2(x: Double, y: Double) = Vec2(x.toFloat(), y.toFloat())
fun vec2() = Vec2()
