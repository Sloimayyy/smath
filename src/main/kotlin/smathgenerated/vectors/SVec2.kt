package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class SVec2(val x: Short, val y: Short) {

    companion object {
        fun splat(value: Byte) = SVec2(value, value)
        fun splat(value: Short) = SVec2(value, value)
        fun splat(value: Int) = SVec2(value, value)
        fun splat(value: Long) = SVec2(value, value)
        fun splat(value: Float) = SVec2(value, value)
        fun splat(value: Double) = SVec2(value, value)
        fun new(x: Byte, y: Byte) = SVec2(x, y)
        fun new(x: Short, y: Short) = SVec2(x, y)
        fun new(x: Int, y: Int) = SVec2(x, y)
        fun new(x: Long, y: Long) = SVec2(x, y)
        fun new(x: Float, y: Float) = SVec2(x, y)
        fun new(x: Double, y: Double) = SVec2(x, y)
        fun eye(index: Int, value: Short = 1.toShort()): SVec2 {
            require(index in 0 until 2) { 
                "Index out of bounds of the range [0; 2). (Got ${index})."
            }
            return SVec2(if (index == 0) value else 0.toShort(), if (index == 1) value else 0.toShort())
        }

        fun fromArray(array: ShortArray): SVec2 {
            require(array.size == 2) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 2, got ${array.size}."
            }
            return SVec2(array[0], array[1])
        }

        val X: SVec2 = SVec2(1.toShort(), 0.toShort())
        val Y: SVec2 = SVec2(0.toShort(), 1.toShort())
        val ZERO: SVec2 = SVec2(0.toShort())
        val ONE: SVec2 = SVec2(1.toShort())
        val MIN: SVec2 = SVec2(Short.MIN_VALUE)
        val MAX: SVec2 = SVec2(Short.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toShort(), value.toShort())
    constructor(x: Byte, y: Byte) : this(x.toShort(), y.toShort())
    constructor(value: Short) : this(value, value)
    constructor(value: Int) : this(value.toShort(), value.toShort())
    constructor(x: Int, y: Int) : this(x.toShort(), y.toShort())
    constructor(value: Long) : this(value.toShort(), value.toShort())
    constructor(x: Long, y: Long) : this(x.toShort(), y.toShort())
    constructor(value: Float) : this(value.toInt().toShort(), value.toInt().toShort())
    constructor(x: Float, y: Float) : this(x.toInt().toShort(), y.toInt().toShort())
    constructor(value: Double) : this(value.toInt().toShort(), value.toInt().toShort())
    constructor(x: Double, y: Double) : this(x.toInt().toShort(), y.toInt().toShort())
    constructor() : this(0.toShort(), 0.toShort())
    constructor(lamb: (Int) -> Short) : this(lamb(0), lamb(1))

    operator fun plus(other: SVec2) = SVec2((x + other.x).toShort(), (y + other.y).toShort())
    operator fun plus(other: Short) = SVec2((x + other).toShort(), (y + other).toShort())
    operator fun minus(other: SVec2) = SVec2((x - other.x).toShort(), (y - other.y).toShort())
    operator fun minus(other: Short) = SVec2((x - other).toShort(), (y - other).toShort())
    operator fun times(other: SVec2) = SVec2((x * other.x).toShort(), (y * other.y).toShort())
    operator fun times(other: Short) = SVec2((x * other).toShort(), (y * other).toShort())
    operator fun div(other: SVec2) = SVec2((x / other.x).toShort(), (y / other.y).toShort())
    operator fun div(other: Short) = SVec2((x / other).toShort(), (y / other).toShort())
    operator fun rem(other: SVec2) = SVec2((x % other.x).toShort(), (y % other.y).toShort())
    operator fun rem(other: Short) = SVec2((x % other).toShort(), (y % other).toShort())
    operator fun unaryPlus() = this
    operator fun unaryMinus() = SVec2(-x, -y)
    operator fun get(idx: Byte): Short {
        require(idx in 0 until 2) { 
            "SVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Short): Short {
        require(idx in 0 until 2) { 
            "SVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Int): Short {
        require(idx in 0 until 2) { 
            "SVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; else -> y }
    }
    operator fun get(idx: Long): Short {
        require(idx in 0 until 2) { 
            "SVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }

    fun toBVec2() = BVec2(x.toByte(), y.toByte())
    fun toIVec2() = IVec2(x.toInt(), y.toInt())
    fun toLVec2() = LVec2(x.toLong(), y.toLong())
    fun toVec2() = Vec2(x.toFloat(), y.toFloat())
    fun toDVec2() = DVec2(x.toDouble(), y.toDouble())

    fun eq(other: SVec2) = x == other.x && y == other.y
    fun iter(): Iterator<Short> {
        return object : Iterator<Short> {
            private var idx = 0
            override fun hasNext() = idx < 2
            override fun next() = this@SVec2[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = SVec2(abs(x.toInt()).toShort(), abs(y.toInt()).toShort())
    fun mod(value: Short) = SVec2(x.mod(value), y.mod(value))
    fun mod(other: SVec2) = SVec2(x.mod(other.x), y.mod(other.y))
    fun min(other: SVec2) = SVec2(min(x.toInt(), other.x.toInt()).toShort(), min(y.toInt(), other.y.toInt()).toShort())
    fun max(other: SVec2) = SVec2(max(x.toInt(), other.x.toInt()).toShort(), max(y.toInt(), other.y.toInt()).toShort())
    fun clamp(low: SVec2, high: SVec2) = SVec2(max(min(x.toInt(), high.x.toInt()), low.x.toInt()).toShort(), max(min(y.toInt(), high.y.toInt()), low.y.toInt()).toShort())
    fun dot(other: SVec2) = x * other.x + y * other.y
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toFloat())
    fun dist(other: SVec2) = (this.toVec2() - other.toVec2()).len()
    fun distSq(other: SVec2) = (this.toVec2() - other.toVec2()).lenSq()
    fun normalize() = toVec2() / len()
    fun dir() = toVec2() / len()
    fun elementSum() = (x.toInt() + y.toInt()).toShort()
    fun eSum() = (x.toInt() + y.toInt()).toShort()
    fun elementProd() = (x.toInt() * y.toInt()).toShort()
    fun eProd() = (x.toInt() * y.toInt()).toShort()
    fun minElement() = min(x.toInt(), y.toInt()).toShort()
    fun eMin() = min(x.toInt(), y.toInt()).toShort()
    fun maxElement() = max(x.toInt(), y.toInt()).toShort()
    fun eMax() = max(x.toInt(), y.toInt()).toShort()
    fun indexOfMin() = when (eMin()) { x -> 0; else -> 1 }
    fun indexOfMax() = when (eMax()) { x -> 0; else -> 1 }
    fun manhattan(other: SVec2) = (this - other).abs().eSum()
    fun cross(other: SVec2) = SVec3(0, 0, x * other.y - other.x * y)
    fun toArray() = shortArrayOf(x, y)
    fun lerp(other: SVec2, t: Float) = this.toVec2() * (1.0f - t) + other.toVec2() * t
    fun floorDiv(other: SVec2) = SVec2(x.floorDiv(other.x), y.floorDiv(other.y))
    fun floorDiv(other: Short) = SVec2(x.floorDiv(other), y.floorDiv(other))

    infix fun and(other: SVec2) = SVec2(x.toInt() and other.x.toInt(), y.toInt() and other.y.toInt())
    infix fun and(other: Short) = SVec2(x.toInt() and other.toInt(), y.toInt() and other.toInt())
    infix fun or(other: SVec2) = SVec2(x.toInt() or other.x.toInt(), y.toInt() or other.y.toInt())
    infix fun or(other: Short) = SVec2(x.toInt() or other.toInt(), y.toInt() or other.toInt())
    infix fun xor(other: SVec2) = SVec2(x.toInt() xor other.x.toInt(), y.toInt() xor other.y.toInt())
    infix fun xor(other: Short) = SVec2(x.toInt() xor other.toInt(), y.toInt() xor other.toInt())
    infix fun shl(other: SVec2) = SVec2(x.toInt() shl other.x.toInt(), y.toInt() shl other.y.toInt())
    infix fun shl(other: Short) = SVec2(x.toInt() shl other.toInt(), y.toInt() shl other.toInt())
    infix fun shr(other: SVec2) = SVec2(x.toInt() shr other.x.toInt(), y.toInt() shr other.y.toInt())
    infix fun shr(other: Short) = SVec2(x.toInt() shr other.toInt(), y.toInt() shr other.toInt())
    infix fun ushr(other: SVec2) = SVec2(x.toInt() ushr other.x.toInt(), y.toInt() ushr other.y.toInt())
    infix fun ushr(other: Short) = SVec2(x.toInt() ushr other.toInt(), y.toInt() ushr other.toInt())
    fun inv() = SVec2(x.toInt().inv(), y.toInt().inv())

    fun extend(z: Byte) = SVec3(x, y, z.toShort())
    fun extend(z: Short) = SVec3(x, y, z)
    fun extend(z: Int) = SVec3(x, y, z.toShort())
    fun extend(z: Long) = SVec3(x, y, z.toShort())
    fun extend(z: Float) = SVec3(x, y, z.toInt().toShort())
    fun extend(z: Double) = SVec3(x, y, z.toInt().toShort())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun perm(other: BVec2) = SVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: SVec2) = SVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: IVec2) = SVec2(this[other.x], this[other.y])
    fun perm(other: LVec2) = SVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: BVec3) = SVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: SVec3) = SVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: IVec3) = SVec3(this[other.x], this[other.y], this[other.z])
    fun perm(other: LVec3) = SVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: BVec4) = SVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: SVec4) = SVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: IVec4) = SVec4(this[other.x], this[other.y], this[other.z], this[other.w])
    fun perm(other: LVec4) = SVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun withX(value: Byte) = SVec2(value.toShort(), y)
    fun withX(value: Short) = SVec2(value, y)
    fun withX(value: Int) = SVec2(value.toShort(), y)
    fun withX(value: Long) = SVec2(value.toShort(), y)
    fun withX(value: Float) = SVec2(value.toInt().toShort(), y)
    fun withX(value: Double) = SVec2(value.toInt().toShort(), y)
    fun withY(value: Byte) = SVec2(x, value.toShort())
    fun withY(value: Short) = SVec2(x, value)
    fun withY(value: Int) = SVec2(x, value.toShort())
    fun withY(value: Long) = SVec2(x, value.toShort())
    fun withY(value: Float) = SVec2(x, value.toInt().toShort())
    fun withY(value: Double) = SVec2(x, value.toInt().toShort())

    val xx get() = SVec2(x, x)
    val xy get() = SVec2(x, y)
    val yx get() = SVec2(y, x)
    val yy get() = SVec2(y, y)
    val xxx get() = SVec3(x, x, x)
    val xxy get() = SVec3(x, x, y)
    val xyx get() = SVec3(x, y, x)
    val xyy get() = SVec3(x, y, y)
    val yxx get() = SVec3(y, x, x)
    val yxy get() = SVec3(y, x, y)
    val yyx get() = SVec3(y, y, x)
    val yyy get() = SVec3(y, y, y)
    val xxxx get() = SVec4(x, x, x, x)
    val xxxy get() = SVec4(x, x, x, y)
    val xxyx get() = SVec4(x, x, y, x)
    val xxyy get() = SVec4(x, x, y, y)
    val xyxx get() = SVec4(x, y, x, x)
    val xyxy get() = SVec4(x, y, x, y)
    val xyyx get() = SVec4(x, y, y, x)
    val xyyy get() = SVec4(x, y, y, y)
    val yxxx get() = SVec4(y, x, x, x)
    val yxxy get() = SVec4(y, x, x, y)
    val yxyx get() = SVec4(y, x, y, x)
    val yxyy get() = SVec4(y, x, y, y)
    val yyxx get() = SVec4(y, y, x, x)
    val yyxy get() = SVec4(y, y, x, y)
    val yyyx get() = SVec4(y, y, y, x)
    val yyyy get() = SVec4(y, y, y, y)

    override fun toString(): String {
        return ("SVec2(" + 
                "x=$x, " + 
                "y=$y)")
    }

}

operator fun Short.plus(other: SVec2) = SVec2((this + other.x).toShort(), (this + other.y).toShort())
operator fun Short.minus(other: SVec2) = SVec2((this - other.x).toShort(), (this - other.y).toShort())
operator fun Short.times(other: SVec2) = SVec2((this * other.x).toShort(), (this * other.y).toShort())
operator fun Short.div(other: SVec2) = SVec2((this / other.x).toShort(), (this / other.y).toShort())
operator fun Short.rem(other: SVec2) = SVec2((this % other.x).toShort(), (this % other.y).toShort())

fun svec2(value: Byte) = SVec2(value.toShort())
fun svec2(x: Byte, y: Byte) = SVec2(x.toShort(), y.toShort())
fun svec2(value: Short) = SVec2(value)
fun svec2(x: Short, y: Short) = SVec2(x, y)
fun svec2(value: Int) = SVec2(value.toShort())
fun svec2(x: Int, y: Int) = SVec2(x.toShort(), y.toShort())
fun svec2(value: Long) = SVec2(value.toShort())
fun svec2(x: Long, y: Long) = SVec2(x.toShort(), y.toShort())
fun svec2(value: Float) = SVec2(value.toInt().toShort())
fun svec2(x: Float, y: Float) = SVec2(x.toInt().toShort(), y.toInt().toShort())
fun svec2(value: Double) = SVec2(value.toInt().toShort())
fun svec2(x: Double, y: Double) = SVec2(x.toInt().toShort(), y.toInt().toShort())
fun svec2() = SVec2()
