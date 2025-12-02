package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class IVec2(val x: Int, val y: Int) {

    companion object {
        fun splat(value: Byte) = IVec2(value, value)
        fun splat(value: Short) = IVec2(value, value)
        fun splat(value: Int) = IVec2(value, value)
        fun splat(value: Long) = IVec2(value, value)
        fun splat(value: Float) = IVec2(value, value)
        fun splat(value: Double) = IVec2(value, value)
        fun new(x: Byte, y: Byte) = IVec2(x, y)
        fun new(x: Short, y: Short) = IVec2(x, y)
        fun new(x: Int, y: Int) = IVec2(x, y)
        fun new(x: Long, y: Long) = IVec2(x, y)
        fun new(x: Float, y: Float) = IVec2(x, y)
        fun new(x: Double, y: Double) = IVec2(x, y)
        fun eye(index: Int, value: Int = 1): IVec2 {
            require(index in 0 until 2) { 
                "Index out of bounds of the range [0; 2). (Got ${index})."
            }
            return IVec2(if (index == 0) value else 0, if (index == 1) value else 0)
        }

        fun fromArray(array: IntArray): IVec2 {
            require(array.size == 2) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 2, got ${array.size}."
            }
            return IVec2(array[0], array[1])
        }

        val X: IVec2 = IVec2(1, 0)
        val Y: IVec2 = IVec2(0, 1)
        val ZERO: IVec2 = IVec2(0)
        val ONE: IVec2 = IVec2(1)
        val MIN: IVec2 = IVec2(Int.MIN_VALUE)
        val MAX: IVec2 = IVec2(Int.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toInt(), value.toInt())
    constructor(x: Byte, y: Byte) : this(x.toInt(), y.toInt())
    constructor(value: Short) : this(value.toInt(), value.toInt())
    constructor(x: Short, y: Short) : this(x.toInt(), y.toInt())
    constructor(value: Int) : this(value, value)
    constructor(value: Long) : this(value.toInt(), value.toInt())
    constructor(x: Long, y: Long) : this(x.toInt(), y.toInt())
    constructor(value: Float) : this(value.toInt(), value.toInt())
    constructor(x: Float, y: Float) : this(x.toInt(), y.toInt())
    constructor(value: Double) : this(value.toInt(), value.toInt())
    constructor(x: Double, y: Double) : this(x.toInt(), y.toInt())
    constructor() : this(0, 0)
    constructor(lamb: (Int) -> Int) : this(lamb(0), lamb(1))

    operator fun plus(other: IVec2) = IVec2(x + other.x, y + other.y)
    operator fun plus(other: Int) = IVec2(x + other, y + other)
    operator fun minus(other: IVec2) = IVec2(x - other.x, y - other.y)
    operator fun minus(other: Int) = IVec2(x - other, y - other)
    operator fun times(other: IVec2) = IVec2(x * other.x, y * other.y)
    operator fun times(other: Int) = IVec2(x * other, y * other)
    operator fun div(other: IVec2) = IVec2(x / other.x, y / other.y)
    operator fun div(other: Int) = IVec2(x / other, y / other)
    operator fun rem(other: IVec2) = IVec2(x % other.x, y % other.y)
    operator fun rem(other: Int) = IVec2(x % other, y % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = IVec2(-x, -y)
    operator fun get(idx: Byte): Int {
        require(idx in 0 until 2) { 
            "IVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Short): Int {
        require(idx in 0 until 2) { 
            "IVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Int): Int {
        require(idx in 0 until 2) { 
            "IVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; else -> y }
    }
    operator fun get(idx: Long): Int {
        require(idx in 0 until 2) { 
            "IVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }

    fun toBVec2() = BVec2(x.toByte(), y.toByte())
    fun toSVec2() = SVec2(x.toShort(), y.toShort())
    fun toLVec2() = LVec2(x.toLong(), y.toLong())
    fun toVec2() = Vec2(x.toFloat(), y.toFloat())
    fun toDVec2() = DVec2(x.toDouble(), y.toDouble())

    fun eq(other: IVec2) = x == other.x && y == other.y
    fun iter(): Iterator<Int> {
        return object : Iterator<Int> {
            private var idx = 0
            override fun hasNext() = idx < 2
            override fun next() = this@IVec2[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = IVec2(abs(x), abs(y))
    fun mod(value: Int) = IVec2(x.mod(value), y.mod(value))
    fun mod(other: IVec2) = IVec2(x.mod(other.x), y.mod(other.y))
    fun min(other: IVec2) = IVec2(min(x, other.x), min(y, other.y))
    fun min(other: Int) = IVec2(min(x, other), min(y, other))
    fun max(other: IVec2) = IVec2(max(x, other.x), max(y, other.y))
    fun max(other: Int) = IVec2(max(x, other), max(y, other))
    fun clamp(low: IVec2, high: IVec2) = IVec2(max(min(x, high.x), low.x), max(min(y, high.y), low.y))
    fun dot(other: IVec2) = x * other.x + y * other.y
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toDouble())
    fun dist(other: IVec2) = (this.toDVec2() - other.toDVec2()).len()
    fun distSq(other: IVec2) = (this.toDVec2() - other.toDVec2()).lenSq()
    fun normalize() = toDVec2() / len()
    fun dir() = toDVec2() / len()
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
    fun manhattan(other: IVec2) = (this - other).abs().eSum()
    fun cross(other: IVec2) = IVec3(0, 0, x * other.y - other.x * y)
    fun toArray() = intArrayOf(x, y)
    fun lerp(other: IVec2, t: Float) = this.toDVec2() * (1.0 - t.toDouble()) + other.toDVec2() * t.toDouble()
    fun floorDiv(other: IVec2) = IVec2(x.floorDiv(other.x), y.floorDiv(other.y))
    fun floorDiv(other: Int) = IVec2(x.floorDiv(other), y.floorDiv(other))

    infix fun and(other: IVec2) = IVec2(x and other.x, y and other.y)
    infix fun and(other: Int) = IVec2(x and other, y and other)
    infix fun or(other: IVec2) = IVec2(x or other.x, y or other.y)
    infix fun or(other: Int) = IVec2(x or other, y or other)
    infix fun xor(other: IVec2) = IVec2(x xor other.x, y xor other.y)
    infix fun xor(other: Int) = IVec2(x xor other, y xor other)
    infix fun shl(other: IVec2) = IVec2(x shl other.x, y shl other.y)
    infix fun shl(other: Int) = IVec2(x shl other, y shl other)
    infix fun shr(other: IVec2) = IVec2(x shr other.x, y shr other.y)
    infix fun shr(other: Int) = IVec2(x shr other, y shr other)
    infix fun ushr(other: IVec2) = IVec2(x ushr other.x, y ushr other.y)
    infix fun ushr(other: Int) = IVec2(x ushr other, y ushr other)
    fun inv() = IVec2(x.inv(), y.inv())

    fun extend(z: Byte) = IVec3(x, y, z.toInt())
    fun extend(z: Short) = IVec3(x, y, z.toInt())
    fun extend(z: Int) = IVec3(x, y, z)
    fun extend(z: Long) = IVec3(x, y, z.toInt())
    fun extend(z: Float) = IVec3(x, y, z.toInt())
    fun extend(z: Double) = IVec3(x, y, z.toInt())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun perm(other: BVec2) = IVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: SVec2) = IVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: IVec2) = IVec2(this[other.x], this[other.y])
    fun perm(other: LVec2) = IVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: BVec3) = IVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: SVec3) = IVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: IVec3) = IVec3(this[other.x], this[other.y], this[other.z])
    fun perm(other: LVec3) = IVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: BVec4) = IVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: SVec4) = IVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: IVec4) = IVec4(this[other.x], this[other.y], this[other.z], this[other.w])
    fun perm(other: LVec4) = IVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun withX(value: Byte) = IVec2(value.toInt(), y)
    fun withX(value: Short) = IVec2(value.toInt(), y)
    fun withX(value: Int) = IVec2(value, y)
    fun withX(value: Long) = IVec2(value.toInt(), y)
    fun withX(value: Float) = IVec2(value.toInt(), y)
    fun withX(value: Double) = IVec2(value.toInt(), y)
    fun withY(value: Byte) = IVec2(x, value.toInt())
    fun withY(value: Short) = IVec2(x, value.toInt())
    fun withY(value: Int) = IVec2(x, value)
    fun withY(value: Long) = IVec2(x, value.toInt())
    fun withY(value: Float) = IVec2(x, value.toInt())
    fun withY(value: Double) = IVec2(x, value.toInt())

    val xx get() = IVec2(x, x)
    val xy get() = IVec2(x, y)
    val yx get() = IVec2(y, x)
    val yy get() = IVec2(y, y)
    val xxx get() = IVec3(x, x, x)
    val xxy get() = IVec3(x, x, y)
    val xyx get() = IVec3(x, y, x)
    val xyy get() = IVec3(x, y, y)
    val yxx get() = IVec3(y, x, x)
    val yxy get() = IVec3(y, x, y)
    val yyx get() = IVec3(y, y, x)
    val yyy get() = IVec3(y, y, y)
    val xxxx get() = IVec4(x, x, x, x)
    val xxxy get() = IVec4(x, x, x, y)
    val xxyx get() = IVec4(x, x, y, x)
    val xxyy get() = IVec4(x, x, y, y)
    val xyxx get() = IVec4(x, y, x, x)
    val xyxy get() = IVec4(x, y, x, y)
    val xyyx get() = IVec4(x, y, y, x)
    val xyyy get() = IVec4(x, y, y, y)
    val yxxx get() = IVec4(y, x, x, x)
    val yxxy get() = IVec4(y, x, x, y)
    val yxyx get() = IVec4(y, x, y, x)
    val yxyy get() = IVec4(y, x, y, y)
    val yyxx get() = IVec4(y, y, x, x)
    val yyxy get() = IVec4(y, y, x, y)
    val yyyx get() = IVec4(y, y, y, x)
    val yyyy get() = IVec4(y, y, y, y)

    override fun toString(): String {
        return ("IVec2(" + 
                "x=$x, " + 
                "y=$y)")
    }

}

operator fun Int.plus(other: IVec2) = IVec2(this + other.x, this + other.y)
operator fun Int.minus(other: IVec2) = IVec2(this - other.x, this - other.y)
operator fun Int.times(other: IVec2) = IVec2(this * other.x, this * other.y)
operator fun Int.div(other: IVec2) = IVec2(this / other.x, this / other.y)
operator fun Int.rem(other: IVec2) = IVec2(this % other.x, this % other.y)

fun ivec2(value: Byte) = IVec2(value.toInt())
fun ivec2(x: Byte, y: Byte) = IVec2(x.toInt(), y.toInt())
fun ivec2(value: Short) = IVec2(value.toInt())
fun ivec2(x: Short, y: Short) = IVec2(x.toInt(), y.toInt())
fun ivec2(value: Int) = IVec2(value)
fun ivec2(x: Int, y: Int) = IVec2(x, y)
fun ivec2(value: Long) = IVec2(value.toInt())
fun ivec2(x: Long, y: Long) = IVec2(x.toInt(), y.toInt())
fun ivec2(value: Float) = IVec2(value.toInt())
fun ivec2(x: Float, y: Float) = IVec2(x.toInt(), y.toInt())
fun ivec2(value: Double) = IVec2(value.toInt())
fun ivec2(x: Double, y: Double) = IVec2(x.toInt(), y.toInt())
fun ivec2() = IVec2()
