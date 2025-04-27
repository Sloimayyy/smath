package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class LVec2(val x: Long, val y: Long) {

    companion object {
        fun splat(value: Byte) = LVec2(value, value)
        fun splat(value: Short) = LVec2(value, value)
        fun splat(value: Int) = LVec2(value, value)
        fun splat(value: Long) = LVec2(value, value)
        fun splat(value: Float) = LVec2(value, value)
        fun splat(value: Double) = LVec2(value, value)
        fun new(x: Byte, y: Byte) = LVec2(x, y)
        fun new(x: Short, y: Short) = LVec2(x, y)
        fun new(x: Int, y: Int) = LVec2(x, y)
        fun new(x: Long, y: Long) = LVec2(x, y)
        fun new(x: Float, y: Float) = LVec2(x, y)
        fun new(x: Double, y: Double) = LVec2(x, y)
        fun eye(index: Int, value: Long = 1L): LVec2 {
            require(index in 0 until 2) { 
                "Index out of bounds of the range [0; 2). (Got ${index})."
            }
            return LVec2(if (index == 0) value else 0L, if (index == 1) value else 0L)
        }

        fun fromArray(array: LongArray): LVec2 {
            require(array.size == 2) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 2, got ${array.size}."
            }
            return LVec2(array[0], array[1])
        }

        val X: LVec2 = LVec2(1L, 0L)
        val Y: LVec2 = LVec2(0L, 1L)
        val ZERO: LVec2 = LVec2(0L)
        val ONE: LVec2 = LVec2(1L)
        val MIN: LVec2 = LVec2(Long.MIN_VALUE)
        val MAX: LVec2 = LVec2(Long.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toLong(), value.toLong())
    constructor(x: Byte, y: Byte) : this(x.toLong(), y.toLong())
    constructor(value: Short) : this(value.toLong(), value.toLong())
    constructor(x: Short, y: Short) : this(x.toLong(), y.toLong())
    constructor(value: Int) : this(value.toLong(), value.toLong())
    constructor(x: Int, y: Int) : this(x.toLong(), y.toLong())
    constructor(value: Long) : this(value, value)
    constructor(value: Float) : this(value.toLong(), value.toLong())
    constructor(x: Float, y: Float) : this(x.toLong(), y.toLong())
    constructor(value: Double) : this(value.toLong(), value.toLong())
    constructor(x: Double, y: Double) : this(x.toLong(), y.toLong())
    constructor() : this(0L, 0L)
    constructor(lamb: (Int) -> Long) : this(lamb(0), lamb(1))

    operator fun plus(other: LVec2) = LVec2(x + other.x, y + other.y)
    operator fun plus(other: Long) = LVec2(x + other, y + other)
    operator fun minus(other: LVec2) = LVec2(x - other.x, y - other.y)
    operator fun minus(other: Long) = LVec2(x - other, y - other)
    operator fun times(other: LVec2) = LVec2(x * other.x, y * other.y)
    operator fun times(other: Long) = LVec2(x * other, y * other)
    operator fun div(other: LVec2) = LVec2(x / other.x, y / other.y)
    operator fun div(other: Long) = LVec2(x / other, y / other)
    operator fun rem(other: LVec2) = LVec2(x % other.x, y % other.y)
    operator fun rem(other: Long) = LVec2(x % other, y % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = LVec2(-x, -y)
    operator fun get(idx: Byte): Long {
        require(idx in 0 until 2) { 
            "LVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Short): Long {
        require(idx in 0 until 2) { 
            "LVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Int): Long {
        require(idx in 0 until 2) { 
            "LVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; else -> y }
    }
    operator fun get(idx: Long): Long {
        require(idx in 0 until 2) { 
            "LVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }

    fun toBVec2() = BVec2(x.toByte(), y.toByte())
    fun toSVec2() = SVec2(x.toShort(), y.toShort())
    fun toIVec2() = IVec2(x.toInt(), y.toInt())
    fun toVec2() = Vec2(x.toFloat(), y.toFloat())
    fun toDVec2() = DVec2(x.toDouble(), y.toDouble())

    fun eq(other: LVec2) = x == other.x && y == other.y
    fun iter(): Iterator<Long> {
        return object : Iterator<Long> {
            private var idx = 0
            override fun hasNext() = idx < 2
            override fun next() = this@LVec2[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = LVec2(abs(x), abs(y))
    fun mod(value: Long) = LVec2(x.mod(value), y.mod(value))
    fun mod(other: LVec2) = LVec2(x.mod(other.x), y.mod(other.y))
    fun min(other: LVec2) = LVec2(min(x, other.x), min(y, other.y))
    fun max(other: LVec2) = LVec2(max(x, other.x), max(y, other.y))
    fun clamp(low: LVec2, high: LVec2) = LVec2(max(min(x, high.x), low.x), max(min(y, high.y), low.y))
    fun dot(other: LVec2) = x * other.x + y * other.y
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toDouble())
    fun dist(other: LVec2) = (this.toDVec2() - other.toDVec2()).len()
    fun distSq(other: LVec2) = (this.toDVec2() - other.toDVec2()).lenSq()
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
    fun manhattan(other: LVec2) = (this - other).abs().eSum()
    fun cross(other: LVec2) = LVec3(0L, 0L, x * other.y - other.x * y)
    fun toArray() = longArrayOf(x, y)
    fun lerp(other: LVec2, t: Double) = this.toDVec2() * (1.0 - t) + other.toDVec2() * t
    fun floorDiv(other: LVec2) = LVec2(x.floorDiv(other.x), y.floorDiv(other.y))
    fun floorDiv(other: Long) = LVec2(x.floorDiv(other), y.floorDiv(other))

    infix fun and(other: LVec2) = LVec2(x and other.x, y and other.y)
    infix fun and(other: Long) = LVec2(x and other, y and other)
    infix fun or(other: LVec2) = LVec2(x or other.x, y or other.y)
    infix fun or(other: Long) = LVec2(x or other, y or other)
    infix fun xor(other: LVec2) = LVec2(x xor other.x, y xor other.y)
    infix fun xor(other: Long) = LVec2(x xor other, y xor other)
    infix fun shl(other: LVec2) = LVec2(x shl other.x.toInt(), y shl other.y.toInt())
    infix fun shl(other: Long) = LVec2(x shl other.toInt(), y shl other.toInt())
    infix fun shr(other: LVec2) = LVec2(x shr other.x.toInt(), y shr other.y.toInt())
    infix fun shr(other: Long) = LVec2(x shr other.toInt(), y shr other.toInt())
    infix fun ushr(other: LVec2) = LVec2(x ushr other.x.toInt(), y ushr other.y.toInt())
    infix fun ushr(other: Long) = LVec2(x ushr other.toInt(), y ushr other.toInt())
    fun inv() = LVec2(x.inv(), y.inv())

    fun extend(z: Byte) = LVec3(x, y, z.toLong())
    fun extend(z: Short) = LVec3(x, y, z.toLong())
    fun extend(z: Int) = LVec3(x, y, z.toLong())
    fun extend(z: Long) = LVec3(x, y, z)
    fun extend(z: Float) = LVec3(x, y, z.toLong())
    fun extend(z: Double) = LVec3(x, y, z.toLong())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun perm(other: BVec2) = LVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: SVec2) = LVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: IVec2) = LVec2(this[other.x], this[other.y])
    fun perm(other: LVec2) = LVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: BVec3) = LVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: SVec3) = LVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: IVec3) = LVec3(this[other.x], this[other.y], this[other.z])
    fun perm(other: LVec3) = LVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: BVec4) = LVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: SVec4) = LVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: IVec4) = LVec4(this[other.x], this[other.y], this[other.z], this[other.w])
    fun perm(other: LVec4) = LVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun withX(value: Byte) = LVec2(value.toLong(), y)
    fun withX(value: Short) = LVec2(value.toLong(), y)
    fun withX(value: Int) = LVec2(value.toLong(), y)
    fun withX(value: Long) = LVec2(value, y)
    fun withX(value: Float) = LVec2(value.toLong(), y)
    fun withX(value: Double) = LVec2(value.toLong(), y)
    fun withY(value: Byte) = LVec2(x, value.toLong())
    fun withY(value: Short) = LVec2(x, value.toLong())
    fun withY(value: Int) = LVec2(x, value.toLong())
    fun withY(value: Long) = LVec2(x, value)
    fun withY(value: Float) = LVec2(x, value.toLong())
    fun withY(value: Double) = LVec2(x, value.toLong())

    val xx get() = LVec2(x, x)
    val xy get() = LVec2(x, y)
    val yx get() = LVec2(y, x)
    val yy get() = LVec2(y, y)
    val xxx get() = LVec3(x, x, x)
    val xxy get() = LVec3(x, x, y)
    val xyx get() = LVec3(x, y, x)
    val xyy get() = LVec3(x, y, y)
    val yxx get() = LVec3(y, x, x)
    val yxy get() = LVec3(y, x, y)
    val yyx get() = LVec3(y, y, x)
    val yyy get() = LVec3(y, y, y)
    val xxxx get() = LVec4(x, x, x, x)
    val xxxy get() = LVec4(x, x, x, y)
    val xxyx get() = LVec4(x, x, y, x)
    val xxyy get() = LVec4(x, x, y, y)
    val xyxx get() = LVec4(x, y, x, x)
    val xyxy get() = LVec4(x, y, x, y)
    val xyyx get() = LVec4(x, y, y, x)
    val xyyy get() = LVec4(x, y, y, y)
    val yxxx get() = LVec4(y, x, x, x)
    val yxxy get() = LVec4(y, x, x, y)
    val yxyx get() = LVec4(y, x, y, x)
    val yxyy get() = LVec4(y, x, y, y)
    val yyxx get() = LVec4(y, y, x, x)
    val yyxy get() = LVec4(y, y, x, y)
    val yyyx get() = LVec4(y, y, y, x)
    val yyyy get() = LVec4(y, y, y, y)

    override fun toString(): String {
        return ("LVec2(" + 
                "x=$x, " + 
                "y=$y)")
    }

}

operator fun Long.plus(other: LVec2) = LVec2(this + other.x, this + other.y)
operator fun Long.minus(other: LVec2) = LVec2(this - other.x, this - other.y)
operator fun Long.times(other: LVec2) = LVec2(this * other.x, this * other.y)
operator fun Long.div(other: LVec2) = LVec2(this / other.x, this / other.y)
operator fun Long.rem(other: LVec2) = LVec2(this % other.x, this % other.y)

fun lvec2(value: Byte) = LVec2(value.toLong())
fun lvec2(x: Byte, y: Byte) = LVec2(x.toLong(), y.toLong())
fun lvec2(value: Short) = LVec2(value.toLong())
fun lvec2(x: Short, y: Short) = LVec2(x.toLong(), y.toLong())
fun lvec2(value: Int) = LVec2(value.toLong())
fun lvec2(x: Int, y: Int) = LVec2(x.toLong(), y.toLong())
fun lvec2(value: Long) = LVec2(value)
fun lvec2(x: Long, y: Long) = LVec2(x, y)
fun lvec2(value: Float) = LVec2(value.toLong())
fun lvec2(x: Float, y: Float) = LVec2(x.toLong(), y.toLong())
fun lvec2(value: Double) = LVec2(value.toLong())
fun lvec2(x: Double, y: Double) = LVec2(x.toLong(), y.toLong())
fun lvec2() = LVec2()
