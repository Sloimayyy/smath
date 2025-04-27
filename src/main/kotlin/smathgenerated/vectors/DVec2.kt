package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class DVec2(val x: Double, val y: Double) {

    companion object {
        fun splat(value: Byte) = DVec2(value, value)
        fun splat(value: Short) = DVec2(value, value)
        fun splat(value: Int) = DVec2(value, value)
        fun splat(value: Long) = DVec2(value, value)
        fun splat(value: Float) = DVec2(value, value)
        fun splat(value: Double) = DVec2(value, value)
        fun new(x: Byte, y: Byte) = DVec2(x, y)
        fun new(x: Short, y: Short) = DVec2(x, y)
        fun new(x: Int, y: Int) = DVec2(x, y)
        fun new(x: Long, y: Long) = DVec2(x, y)
        fun new(x: Float, y: Float) = DVec2(x, y)
        fun new(x: Double, y: Double) = DVec2(x, y)
        fun eye(index: Int, value: Double = 1.0): DVec2 {
            require(index in 0 until 2) { 
                "Index out of bounds of the range [0; 2). (Got ${index})."
            }
            return DVec2(if (index == 0) value else 0.0, if (index == 1) value else 0.0)
        }

        fun fromArray(array: DoubleArray): DVec2 {
            require(array.size == 2) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 2, got ${array.size}."
            }
            return DVec2(array[0], array[1])
        }

        val X: DVec2 = DVec2(1.0, 0.0)
        val Y: DVec2 = DVec2(0.0, 1.0)
        val ZERO: DVec2 = DVec2(0.0)
        val ONE: DVec2 = DVec2(1.0)
        val INF: DVec2 = DVec2(Double.POSITIVE_INFINITY)
        val NEG_INF: DVec2 = DVec2(Double.NEGATIVE_INFINITY)
        val MIN: DVec2 = DVec2(Double.MIN_VALUE)
        val MAX: DVec2 = DVec2(Double.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toDouble(), value.toDouble())
    constructor(x: Byte, y: Byte) : this(x.toDouble(), y.toDouble())
    constructor(value: Short) : this(value.toDouble(), value.toDouble())
    constructor(x: Short, y: Short) : this(x.toDouble(), y.toDouble())
    constructor(value: Int) : this(value.toDouble(), value.toDouble())
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())
    constructor(value: Long) : this(value.toDouble(), value.toDouble())
    constructor(x: Long, y: Long) : this(x.toDouble(), y.toDouble())
    constructor(value: Float) : this(value.toDouble(), value.toDouble())
    constructor(x: Float, y: Float) : this(x.toDouble(), y.toDouble())
    constructor(value: Double) : this(value, value)
    constructor() : this(0.0, 0.0)
    constructor(lamb: (Int) -> Double) : this(lamb(0), lamb(1))

    operator fun plus(other: DVec2) = DVec2(x + other.x, y + other.y)
    operator fun plus(other: Double) = DVec2(x + other, y + other)
    operator fun minus(other: DVec2) = DVec2(x - other.x, y - other.y)
    operator fun minus(other: Double) = DVec2(x - other, y - other)
    operator fun times(other: DVec2) = DVec2(x * other.x, y * other.y)
    operator fun times(other: Double) = DVec2(x * other, y * other)
    operator fun div(other: DVec2) = DVec2(x / other.x, y / other.y)
    operator fun div(other: Double) = DVec2(x / other, y / other)
    operator fun rem(other: DVec2) = DVec2(x % other.x, y % other.y)
    operator fun rem(other: Double) = DVec2(x % other, y % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = DVec2(-x, -y)
    operator fun get(idx: Byte): Double {
        require(idx in 0 until 2) { 
            "DVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Short): Double {
        require(idx in 0 until 2) { 
            "DVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Int): Double {
        require(idx in 0 until 2) { 
            "DVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; else -> y }
    }
    operator fun get(idx: Long): Double {
        require(idx in 0 until 2) { 
            "DVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }

    fun toBVec2() = BVec2(x.toInt().toByte(), y.toInt().toByte())
    fun toSVec2() = SVec2(x.toInt().toShort(), y.toInt().toShort())
    fun toIVec2() = IVec2(x.toInt(), y.toInt())
    fun toLVec2() = LVec2(x.toLong(), y.toLong())
    fun toVec2() = Vec2(x.toFloat(), y.toFloat())

    fun eq(other: DVec2) = x == other.x && y == other.y
    fun iter(): Iterator<Double> {
        return object : Iterator<Double> {
            private var idx = 0
            override fun hasNext() = idx < 2
            override fun next() = this@DVec2[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = DVec2(abs(x), abs(y))
    fun mod(value: Double) = DVec2(x.mod(value), y.mod(value))
    fun mod(other: DVec2) = DVec2(x.mod(other.x), y.mod(other.y))
    fun min(other: DVec2) = DVec2(min(x, other.x), min(y, other.y))
    fun max(other: DVec2) = DVec2(max(x, other.x), max(y, other.y))
    fun clamp(low: DVec2, high: DVec2) = DVec2(max(min(x, high.x), low.x), max(min(y, high.y), low.y))
    fun dot(other: DVec2) = x * other.x + y * other.y
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq())
    fun dist(other: DVec2) = (this - other).len()
    fun distSq(other: DVec2) = (this - other).lenSq()
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
    fun manhattan(other: DVec2) = (this - other).abs().eSum()
    fun cross(other: DVec2) = DVec3(0.0, 0.0, x * other.y - other.x * y)
    fun toArray() = doubleArrayOf(x, y)
    fun lerp(other: DVec2, t: Double) = this * (1.0 - t) + other * t
    fun floor() = DVec2(floor(x), floor(y))
    fun ceil() = DVec2(ceil(x), ceil(y))
    fun round() = DVec2(round(x), round(y))
    fun fract() = mod(1.0)

    fun extend(z: Byte) = DVec3(x, y, z.toDouble())
    fun extend(z: Short) = DVec3(x, y, z.toDouble())
    fun extend(z: Int) = DVec3(x, y, z.toDouble())
    fun extend(z: Long) = DVec3(x, y, z.toDouble())
    fun extend(z: Float) = DVec3(x, y, z.toDouble())
    fun extend(z: Double) = DVec3(x, y, z)
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun perm(other: BVec2) = DVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: SVec2) = DVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: IVec2) = DVec2(this[other.x], this[other.y])
    fun perm(other: LVec2) = DVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: BVec3) = DVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: SVec3) = DVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: IVec3) = DVec3(this[other.x], this[other.y], this[other.z])
    fun perm(other: LVec3) = DVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: BVec4) = DVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: SVec4) = DVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: IVec4) = DVec4(this[other.x], this[other.y], this[other.z], this[other.w])
    fun perm(other: LVec4) = DVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun withX(value: Byte) = DVec2(value.toDouble(), y)
    fun withX(value: Short) = DVec2(value.toDouble(), y)
    fun withX(value: Int) = DVec2(value.toDouble(), y)
    fun withX(value: Long) = DVec2(value.toDouble(), y)
    fun withX(value: Float) = DVec2(value.toDouble(), y)
    fun withX(value: Double) = DVec2(value, y)
    fun withY(value: Byte) = DVec2(x, value.toDouble())
    fun withY(value: Short) = DVec2(x, value.toDouble())
    fun withY(value: Int) = DVec2(x, value.toDouble())
    fun withY(value: Long) = DVec2(x, value.toDouble())
    fun withY(value: Float) = DVec2(x, value.toDouble())
    fun withY(value: Double) = DVec2(x, value)

    val xx get() = DVec2(x, x)
    val xy get() = DVec2(x, y)
    val yx get() = DVec2(y, x)
    val yy get() = DVec2(y, y)
    val xxx get() = DVec3(x, x, x)
    val xxy get() = DVec3(x, x, y)
    val xyx get() = DVec3(x, y, x)
    val xyy get() = DVec3(x, y, y)
    val yxx get() = DVec3(y, x, x)
    val yxy get() = DVec3(y, x, y)
    val yyx get() = DVec3(y, y, x)
    val yyy get() = DVec3(y, y, y)
    val xxxx get() = DVec4(x, x, x, x)
    val xxxy get() = DVec4(x, x, x, y)
    val xxyx get() = DVec4(x, x, y, x)
    val xxyy get() = DVec4(x, x, y, y)
    val xyxx get() = DVec4(x, y, x, x)
    val xyxy get() = DVec4(x, y, x, y)
    val xyyx get() = DVec4(x, y, y, x)
    val xyyy get() = DVec4(x, y, y, y)
    val yxxx get() = DVec4(y, x, x, x)
    val yxxy get() = DVec4(y, x, x, y)
    val yxyx get() = DVec4(y, x, y, x)
    val yxyy get() = DVec4(y, x, y, y)
    val yyxx get() = DVec4(y, y, x, x)
    val yyxy get() = DVec4(y, y, x, y)
    val yyyx get() = DVec4(y, y, y, x)
    val yyyy get() = DVec4(y, y, y, y)

    override fun toString(): String {
        return ("DVec2(" + 
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " + 
                "y=${"%.5f".format(Locale.ENGLISH, y)})")
    }

}

operator fun Double.plus(other: DVec2) = DVec2(this + other.x, this + other.y)
operator fun Double.minus(other: DVec2) = DVec2(this - other.x, this - other.y)
operator fun Double.times(other: DVec2) = DVec2(this * other.x, this * other.y)
operator fun Double.div(other: DVec2) = DVec2(this / other.x, this / other.y)
operator fun Double.rem(other: DVec2) = DVec2(this % other.x, this % other.y)

fun dvec2(value: Byte) = DVec2(value.toDouble())
fun dvec2(x: Byte, y: Byte) = DVec2(x.toDouble(), y.toDouble())
fun dvec2(value: Short) = DVec2(value.toDouble())
fun dvec2(x: Short, y: Short) = DVec2(x.toDouble(), y.toDouble())
fun dvec2(value: Int) = DVec2(value.toDouble())
fun dvec2(x: Int, y: Int) = DVec2(x.toDouble(), y.toDouble())
fun dvec2(value: Long) = DVec2(value.toDouble())
fun dvec2(x: Long, y: Long) = DVec2(x.toDouble(), y.toDouble())
fun dvec2(value: Float) = DVec2(value.toDouble())
fun dvec2(x: Float, y: Float) = DVec2(x.toDouble(), y.toDouble())
fun dvec2(value: Double) = DVec2(value)
fun dvec2(x: Double, y: Double) = DVec2(x, y)
fun dvec2() = DVec2()
