package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class BVec2(val x: Byte, val y: Byte) {

    companion object {
        fun splat(value: Byte) = BVec2(value, value)
        fun splat(value: Short) = BVec2(value, value)
        fun splat(value: Int) = BVec2(value, value)
        fun splat(value: Long) = BVec2(value, value)
        fun splat(value: Float) = BVec2(value, value)
        fun splat(value: Double) = BVec2(value, value)
        fun new(x: Byte, y: Byte) = BVec2(x, y)
        fun new(x: Short, y: Short) = BVec2(x, y)
        fun new(x: Int, y: Int) = BVec2(x, y)
        fun new(x: Long, y: Long) = BVec2(x, y)
        fun new(x: Float, y: Float) = BVec2(x, y)
        fun new(x: Double, y: Double) = BVec2(x, y)

        fun fromArray(array: ByteArray): BVec2 {
            require(array.size == 2) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 2, got ${array.size}."
            }
            return BVec2(array[0], array[1])
        }

        val X: BVec2 = BVec2(1.toByte(), 0.toByte())
        val Y: BVec2 = BVec2(0.toByte(), 1.toByte())
        val ZERO: BVec2 = BVec2(0.toByte())
        val ONE: BVec2 = BVec2(1.toByte())
        val MIN: BVec2 = BVec2(Byte.MIN_VALUE)
        val MAX: BVec2 = BVec2(Byte.MAX_VALUE)
    }

    constructor(value: Byte) : this(value, value)
    constructor(value: Short) : this(value.toByte(), value.toByte())
    constructor(x: Short, y: Short) : this(x.toByte(), y.toByte())
    constructor(value: Int) : this(value.toByte(), value.toByte())
    constructor(x: Int, y: Int) : this(x.toByte(), y.toByte())
    constructor(value: Long) : this(value.toByte(), value.toByte())
    constructor(x: Long, y: Long) : this(x.toByte(), y.toByte())
    constructor(value: Float) : this(value.toInt().toByte(), value.toInt().toByte())
    constructor(x: Float, y: Float) : this(x.toInt().toByte(), y.toInt().toByte())
    constructor(value: Double) : this(value.toInt().toByte(), value.toInt().toByte())
    constructor(x: Double, y: Double) : this(x.toInt().toByte(), y.toInt().toByte())
    constructor() : this(0.toByte(), 0.toByte())
    constructor(lamb: (Int) -> Byte) : this(lamb(0), lamb(1))

    operator fun plus(other: BVec2) = BVec2((x + other.x).toByte(), (y + other.y).toByte())
    operator fun plus(other: Byte) = BVec2((x + other).toByte(), (y + other).toByte())
    operator fun minus(other: BVec2) = BVec2((x - other.x).toByte(), (y - other.y).toByte())
    operator fun minus(other: Byte) = BVec2((x - other).toByte(), (y - other).toByte())
    operator fun times(other: BVec2) = BVec2((x * other.x).toByte(), (y * other.y).toByte())
    operator fun times(other: Byte) = BVec2((x * other).toByte(), (y * other).toByte())
    operator fun div(other: BVec2) = BVec2((x / other.x).toByte(), (y / other.y).toByte())
    operator fun div(other: Byte) = BVec2((x / other).toByte(), (y / other).toByte())
    operator fun rem(other: BVec2) = BVec2((x % other.x).toByte(), (y % other.y).toByte())
    operator fun rem(other: Byte) = BVec2((x % other).toByte(), (y % other).toByte())
    operator fun unaryPlus() = this
    operator fun unaryMinus() = BVec2(-x, -y)
    operator fun get(idx: Byte): Byte {
        require(idx in 0 until 2) { 
            "BVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Short): Byte {
        require(idx in 0 until 2) { 
            "BVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }
    operator fun get(idx: Int): Byte {
        require(idx in 0 until 2) { 
            "BVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; else -> y }
    }
    operator fun get(idx: Long): Byte {
        require(idx in 0 until 2) { 
            "BVec2 indexing failed. Index should be in the range of 0 to 1 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; else -> y }
    }

    fun toSVec2() = SVec2(x.toShort(), y.toShort())
    fun toIVec2() = IVec2(x.toInt(), y.toInt())
    fun toLVec2() = LVec2(x.toLong(), y.toLong())
    fun toVec2() = Vec2(x.toFloat(), y.toFloat())
    fun toDVec2() = DVec2(x.toDouble(), y.toDouble())

    fun eq(other: BVec2) = x == other.x && y == other.y
    fun abs() = BVec2(abs(x.toInt()).toByte(), abs(y.toInt()).toByte())
    fun mod(value: Byte) = BVec2(x.mod(value), y.mod(value))
    fun mod(other: BVec2) = BVec2(x.mod(other.x), y.mod(other.y))
    fun min(other: BVec2) = BVec2(min(x.toInt(), other.x.toInt()).toByte(), min(y.toInt(), other.y.toInt()).toByte())
    fun max(other: BVec2) = BVec2(max(x.toInt(), other.x.toInt()).toByte(), max(y.toInt(), other.y.toInt()).toByte())
    fun clamp(low: BVec2, high: BVec2) = BVec2(max(min(x.toInt(), high.x.toInt()), low.x.toInt()).toByte(), max(min(y.toInt(), high.y.toInt()), low.y.toInt()).toByte())
    fun dot(other: BVec2) = x * other.x + y * other.y
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toFloat())
    fun dist(other: BVec2) = (this.toVec2() - other.toVec2()).len()
    fun distSq(other: BVec2) = (this.toVec2() - other.toVec2()).lenSq()
    fun normalize() = toVec2() / len()
    fun dir() = toVec2() / len()
    fun elementSum() = x + y
    fun eSum() = x + y
    fun elementProd() = x * y
    fun eProd() = x * y
    fun minElement() = min(x.toInt(), y.toInt()).toByte()
    fun eMin() = min(x.toInt(), y.toInt()).toByte()
    fun maxElement() = max(x.toInt(), y.toInt()).toByte()
    fun eMax() = max(x.toInt(), y.toInt()).toByte()
    fun indexOfMin() = when (eMin()) { x -> 0; else -> 1 }
    fun indexOfMax() = when (eMax()) { x -> 0; else -> 1 }
    fun manhattan(other: BVec2) = (this - other).abs().eSum()
    fun cross(other: BVec2) = BVec3(0, 0, x * other.y - other.x * y)
    fun toArray() = byteArrayOf(x, y)
    fun lerp(other: BVec2, t: Float) = this.toVec2() * (1.0f - t) + other.toVec2() * t
    fun floorDiv(other: BVec2) = BVec2(x.floorDiv(other.x), y.floorDiv(other.y))
    fun floorDiv(other: Byte) = BVec2(x.floorDiv(other), y.floorDiv(other))

    infix fun and(other: BVec2) = BVec2(x.toInt() and other.x.toInt(), y.toInt() and other.y.toInt())
    infix fun and(other: Byte) = BVec2(x.toInt() and other.toInt(), y.toInt() and other.toInt())
    infix fun or(other: BVec2) = BVec2(x.toInt() or other.x.toInt(), y.toInt() or other.y.toInt())
    infix fun or(other: Byte) = BVec2(x.toInt() or other.toInt(), y.toInt() or other.toInt())
    infix fun xor(other: BVec2) = BVec2(x.toInt() xor other.x.toInt(), y.toInt() xor other.y.toInt())
    infix fun xor(other: Byte) = BVec2(x.toInt() xor other.toInt(), y.toInt() xor other.toInt())
    infix fun shl(other: BVec2) = BVec2(x.toInt() shl other.x.toInt(), y.toInt() shl other.y.toInt())
    infix fun shl(other: Byte) = BVec2(x.toInt() shl other.toInt(), y.toInt() shl other.toInt())
    infix fun shr(other: BVec2) = BVec2(x.toInt() shr other.x.toInt(), y.toInt() shr other.y.toInt())
    infix fun shr(other: Byte) = BVec2(x.toInt() shr other.toInt(), y.toInt() shr other.toInt())
    infix fun ushr(other: BVec2) = BVec2(x.toInt() ushr other.x.toInt(), y.toInt() ushr other.y.toInt())
    infix fun ushr(other: Byte) = BVec2(x.toInt() ushr other.toInt(), y.toInt() ushr other.toInt())
    fun inv() = BVec2(x.toInt().inv(), y.toInt().inv())

    fun extend(z: Byte) = BVec3(x, y, z)
    fun extend(z: Short) = BVec3(x, y, z.toByte())
    fun extend(z: Int) = BVec3(x, y, z.toByte())
    fun extend(z: Long) = BVec3(x, y, z.toByte())
    fun extend(z: Float) = BVec3(x, y, z.toInt().toByte())
    fun extend(z: Double) = BVec3(x, y, z.toInt().toByte())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); else -> withY(value) }
    fun perm(other: BVec2) = BVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: SVec2) = BVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: IVec2) = BVec2(this[other.x], this[other.y])
    fun perm(other: LVec2) = BVec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: BVec3) = BVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: SVec3) = BVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: IVec3) = BVec3(this[other.x], this[other.y], this[other.z])
    fun perm(other: LVec3) = BVec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: BVec4) = BVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: SVec4) = BVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: IVec4) = BVec4(this[other.x], this[other.y], this[other.z], this[other.w])
    fun perm(other: LVec4) = BVec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun withX(value: Byte) = BVec2(value, y)
    fun withX(value: Short) = BVec2(value.toByte(), y)
    fun withX(value: Int) = BVec2(value.toByte(), y)
    fun withX(value: Long) = BVec2(value.toByte(), y)
    fun withX(value: Float) = BVec2(value.toInt().toByte(), y)
    fun withX(value: Double) = BVec2(value.toInt().toByte(), y)
    fun withY(value: Byte) = BVec2(x, value)
    fun withY(value: Short) = BVec2(x, value.toByte())
    fun withY(value: Int) = BVec2(x, value.toByte())
    fun withY(value: Long) = BVec2(x, value.toByte())
    fun withY(value: Float) = BVec2(x, value.toInt().toByte())
    fun withY(value: Double) = BVec2(x, value.toInt().toByte())

    val xx get() = BVec2(x, x)
    val xy get() = BVec2(x, y)
    val yx get() = BVec2(y, x)
    val yy get() = BVec2(y, y)
    val xxx get() = BVec3(x, x, x)
    val xxy get() = BVec3(x, x, y)
    val xyx get() = BVec3(x, y, x)
    val xyy get() = BVec3(x, y, y)
    val yxx get() = BVec3(y, x, x)
    val yxy get() = BVec3(y, x, y)
    val yyx get() = BVec3(y, y, x)
    val yyy get() = BVec3(y, y, y)
    val xxxx get() = BVec4(x, x, x, x)
    val xxxy get() = BVec4(x, x, x, y)
    val xxyx get() = BVec4(x, x, y, x)
    val xxyy get() = BVec4(x, x, y, y)
    val xyxx get() = BVec4(x, y, x, x)
    val xyxy get() = BVec4(x, y, x, y)
    val xyyx get() = BVec4(x, y, y, x)
    val xyyy get() = BVec4(x, y, y, y)
    val yxxx get() = BVec4(y, x, x, x)
    val yxxy get() = BVec4(y, x, x, y)
    val yxyx get() = BVec4(y, x, y, x)
    val yxyy get() = BVec4(y, x, y, y)
    val yyxx get() = BVec4(y, y, x, x)
    val yyxy get() = BVec4(y, y, x, y)
    val yyyx get() = BVec4(y, y, y, x)
    val yyyy get() = BVec4(y, y, y, y)

    override fun toString(): String {
        return ("BVec2(" + 
                "x=$x, " + 
                "y=$y)")
    }

}

operator fun Byte.plus(other: BVec2) = BVec2((this + other.x).toByte(), (this + other.y).toByte())
operator fun Byte.minus(other: BVec2) = BVec2((this - other.x).toByte(), (this - other.y).toByte())
operator fun Byte.times(other: BVec2) = BVec2((this * other.x).toByte(), (this * other.y).toByte())
operator fun Byte.div(other: BVec2) = BVec2((this / other.x).toByte(), (this / other.y).toByte())
operator fun Byte.rem(other: BVec2) = BVec2((this % other.x).toByte(), (this % other.y).toByte())

fun bvec2(value: Byte) = BVec2(value)
fun bvec2(x: Byte, y: Byte) = BVec2(x, y)
fun bvec2(value: Short) = BVec2(value.toByte())
fun bvec2(x: Short, y: Short) = BVec2(x.toByte(), y.toByte())
fun bvec2(value: Int) = BVec2(value.toByte())
fun bvec2(x: Int, y: Int) = BVec2(x.toByte(), y.toByte())
fun bvec2(value: Long) = BVec2(value.toByte())
fun bvec2(x: Long, y: Long) = BVec2(x.toByte(), y.toByte())
fun bvec2(value: Float) = BVec2(value.toInt().toByte())
fun bvec2(x: Float, y: Float) = BVec2(x.toInt().toByte(), y.toInt().toByte())
fun bvec2(value: Double) = BVec2(value.toInt().toByte())
fun bvec2(x: Double, y: Double) = BVec2(x.toInt().toByte(), y.toInt().toByte())
fun bvec2() = BVec2()
