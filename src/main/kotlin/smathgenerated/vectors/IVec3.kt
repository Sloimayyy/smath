package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class IVec3(val x: Int, val y: Int, val z: Int) {

    companion object {
        fun splat(value: Byte) = IVec3(value, value, value)
        fun splat(value: Short) = IVec3(value, value, value)
        fun splat(value: Int) = IVec3(value, value, value)
        fun splat(value: Long) = IVec3(value, value, value)
        fun splat(value: Float) = IVec3(value, value, value)
        fun splat(value: Double) = IVec3(value, value, value)
        fun new(x: Byte, y: Byte, z: Byte) = IVec3(x, y, z)
        fun new(x: Short, y: Short, z: Short) = IVec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = IVec3(x, y, z)
        fun new(x: Long, y: Long, z: Long) = IVec3(x, y, z)
        fun new(x: Float, y: Float, z: Float) = IVec3(x, y, z)
        fun new(x: Double, y: Double, z: Double) = IVec3(x, y, z)
        fun eye(index: Int, value: Int = 1): IVec3 {
            require(index in 0 until 3) { 
                "Index out of bounds of the range [0; 3). (Got ${index})."
            }
            return IVec3(if (index == 0) value else 0, if (index == 1) value else 0, if (index == 2) value else 0)
        }

        fun fromArray(array: IntArray): IVec3 {
            require(array.size == 3) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 3, got ${array.size}."
            }
            return IVec3(array[0], array[1], array[2])
        }

        val X: IVec3 = IVec3(1, 0, 0)
        val Y: IVec3 = IVec3(0, 1, 0)
        val Z: IVec3 = IVec3(0, 0, 1)
        val ZERO: IVec3 = IVec3(0)
        val ONE: IVec3 = IVec3(1)
        val MIN: IVec3 = IVec3(Int.MIN_VALUE)
        val MAX: IVec3 = IVec3(Int.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toInt(), value.toInt(), value.toInt())
    constructor(x: Byte, y: Byte, z: Byte) : this(x.toInt(), y.toInt(), z.toInt())
    constructor(value: Short) : this(value.toInt(), value.toInt(), value.toInt())
    constructor(x: Short, y: Short, z: Short) : this(x.toInt(), y.toInt(), z.toInt())
    constructor(value: Int) : this(value, value, value)
    constructor(value: Long) : this(value.toInt(), value.toInt(), value.toInt())
    constructor(x: Long, y: Long, z: Long) : this(x.toInt(), y.toInt(), z.toInt())
    constructor(value: Float) : this(value.toInt(), value.toInt(), value.toInt())
    constructor(x: Float, y: Float, z: Float) : this(x.toInt(), y.toInt(), z.toInt())
    constructor(value: Double) : this(value.toInt(), value.toInt(), value.toInt())
    constructor(x: Double, y: Double, z: Double) : this(x.toInt(), y.toInt(), z.toInt())
    constructor() : this(0, 0, 0)
    constructor(lamb: (Int) -> Int) : this(lamb(0), lamb(1), lamb(2))

    operator fun plus(other: IVec3) = IVec3(x + other.x, y + other.y, z + other.z)
    operator fun plus(other: Int) = IVec3(x + other, y + other, z + other)
    operator fun minus(other: IVec3) = IVec3(x - other.x, y - other.y, z - other.z)
    operator fun minus(other: Int) = IVec3(x - other, y - other, z - other)
    operator fun times(other: IVec3) = IVec3(x * other.x, y * other.y, z * other.z)
    operator fun times(other: Int) = IVec3(x * other, y * other, z * other)
    operator fun div(other: IVec3) = IVec3(x / other.x, y / other.y, z / other.z)
    operator fun div(other: Int) = IVec3(x / other, y / other, z / other)
    operator fun rem(other: IVec3) = IVec3(x % other.x, y % other.y, z % other.z)
    operator fun rem(other: Int) = IVec3(x % other, y % other, z % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = IVec3(-x, -y, -z)
    operator fun get(idx: Byte): Int {
        require(idx in 0 until 3) { 
            "IVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Short): Int {
        require(idx in 0 until 3) { 
            "IVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Int): Int {
        require(idx in 0 until 3) { 
            "IVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Long): Int {
        require(idx in 0 until 3) { 
            "IVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }

    fun toBVec3() = BVec3(x.toByte(), y.toByte(), z.toByte())
    fun toSVec3() = SVec3(x.toShort(), y.toShort(), z.toShort())
    fun toLVec3() = LVec3(x.toLong(), y.toLong(), z.toLong())
    fun toVec3() = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
    fun toDVec3() = DVec3(x.toDouble(), y.toDouble(), z.toDouble())

    fun eq(other: IVec3) = x == other.x && y == other.y && z == other.z
    fun iter(): Iterator<Int> {
        return object : Iterator<Int> {
            private var idx = 0
            override fun hasNext() = idx < 3
            override fun next() = this@IVec3[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = IVec3(abs(x), abs(y), abs(z))
    fun mod(value: Int) = IVec3(x.mod(value), y.mod(value), z.mod(value))
    fun mod(other: IVec3) = IVec3(x.mod(other.x), y.mod(other.y), z.mod(other.z))
    fun min(other: IVec3) = IVec3(min(x, other.x), min(y, other.y), min(z, other.z))
    fun min(other: Int) = IVec3(min(x, other), min(y, other), min(z, other))
    fun max(other: IVec3) = IVec3(max(x, other.x), max(y, other.y), max(z, other.z))
    fun max(other: Int) = IVec3(max(x, other), max(y, other), max(z, other))
    fun clamp(low: IVec3, high: IVec3) = IVec3(max(min(x, high.x), low.x), max(min(y, high.y), low.y), max(min(z, high.z), low.z))
    fun dot(other: IVec3) = x * other.x + y * other.y + z * other.z
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toDouble())
    fun dist(other: IVec3) = (this.toDVec3() - other.toDVec3()).len()
    fun distSq(other: IVec3) = (this.toDVec3() - other.toDVec3()).lenSq()
    fun normalize() = toDVec3() / len()
    fun dir() = toDVec3() / len()
    fun elementSum() = ((x + y) + z)
    fun eSum() = ((x + y) + z)
    fun elementProd() = ((x * y) * z)
    fun eProd() = ((x * y) * z)
    fun minElement() = min(min(x, y), z)
    fun eMin() = min(min(x, y), z)
    fun maxElement() = max(max(x, y), z)
    fun eMax() = max(max(x, y), z)
    fun indexOfMin() = when (eMin()) { x -> 0; y -> 1; else -> 2 }
    fun indexOfMax() = when (eMax()) { x -> 0; y -> 1; else -> 2 }
    fun manhattan(other: IVec3) = (this - other).abs().eSum()
    fun cross(other: IVec3) = IVec3(y * other.z - other.y * z, z * other.x - other.z * x, x * other.y - other.x * y)
    fun toArray() = intArrayOf(x, y, z)
    fun lerp(other: IVec3, t: Float) = this.toDVec3() * (1.0 - t.toDouble()) + other.toDVec3() * t.toDouble()
    fun floorDiv(other: IVec3) = IVec3(x.floorDiv(other.x), y.floorDiv(other.y), z.floorDiv(other.z))
    fun floorDiv(other: Int) = IVec3(x.floorDiv(other), y.floorDiv(other), z.floorDiv(other))

    infix fun and(other: IVec3) = IVec3(x and other.x, y and other.y, z and other.z)
    infix fun and(other: Int) = IVec3(x and other, y and other, z and other)
    infix fun or(other: IVec3) = IVec3(x or other.x, y or other.y, z or other.z)
    infix fun or(other: Int) = IVec3(x or other, y or other, z or other)
    infix fun xor(other: IVec3) = IVec3(x xor other.x, y xor other.y, z xor other.z)
    infix fun xor(other: Int) = IVec3(x xor other, y xor other, z xor other)
    infix fun shl(other: IVec3) = IVec3(x shl other.x, y shl other.y, z shl other.z)
    infix fun shl(other: Int) = IVec3(x shl other, y shl other, z shl other)
    infix fun shr(other: IVec3) = IVec3(x shr other.x, y shr other.y, z shr other.z)
    infix fun shr(other: Int) = IVec3(x shr other, y shr other, z shr other)
    infix fun ushr(other: IVec3) = IVec3(x ushr other.x, y ushr other.y, z ushr other.z)
    infix fun ushr(other: Int) = IVec3(x ushr other, y ushr other, z ushr other)
    fun inv() = IVec3(x.inv(), y.inv(), z.inv())

    fun extend(w: Byte) = IVec4(x, y, z, w.toInt())
    fun extend(w: Short) = IVec4(x, y, z, w.toInt())
    fun extend(w: Int) = IVec4(x, y, z, w)
    fun extend(w: Long) = IVec4(x, y, z, w.toInt())
    fun extend(w: Float) = IVec4(x, y, z, w.toInt())
    fun extend(w: Double) = IVec4(x, y, z, w.toInt())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
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
    fun withX(value: Byte) = IVec3(value.toInt(), y, z)
    fun withX(value: Short) = IVec3(value.toInt(), y, z)
    fun withX(value: Int) = IVec3(value, y, z)
    fun withX(value: Long) = IVec3(value.toInt(), y, z)
    fun withX(value: Float) = IVec3(value.toInt(), y, z)
    fun withX(value: Double) = IVec3(value.toInt(), y, z)
    fun withY(value: Byte) = IVec3(x, value.toInt(), z)
    fun withY(value: Short) = IVec3(x, value.toInt(), z)
    fun withY(value: Int) = IVec3(x, value, z)
    fun withY(value: Long) = IVec3(x, value.toInt(), z)
    fun withY(value: Float) = IVec3(x, value.toInt(), z)
    fun withY(value: Double) = IVec3(x, value.toInt(), z)
    fun withZ(value: Byte) = IVec3(x, y, value.toInt())
    fun withZ(value: Short) = IVec3(x, y, value.toInt())
    fun withZ(value: Int) = IVec3(x, y, value)
    fun withZ(value: Long) = IVec3(x, y, value.toInt())
    fun withZ(value: Float) = IVec3(x, y, value.toInt())
    fun withZ(value: Double) = IVec3(x, y, value.toInt())

    val xx get() = IVec2(x, x)
    val xy get() = IVec2(x, y)
    val xz get() = IVec2(x, z)
    val yx get() = IVec2(y, x)
    val yy get() = IVec2(y, y)
    val yz get() = IVec2(y, z)
    val zx get() = IVec2(z, x)
    val zy get() = IVec2(z, y)
    val zz get() = IVec2(z, z)
    val xxx get() = IVec3(x, x, x)
    val xxy get() = IVec3(x, x, y)
    val xxz get() = IVec3(x, x, z)
    val xyx get() = IVec3(x, y, x)
    val xyy get() = IVec3(x, y, y)
    val xyz get() = IVec3(x, y, z)
    val xzx get() = IVec3(x, z, x)
    val xzy get() = IVec3(x, z, y)
    val xzz get() = IVec3(x, z, z)
    val yxx get() = IVec3(y, x, x)
    val yxy get() = IVec3(y, x, y)
    val yxz get() = IVec3(y, x, z)
    val yyx get() = IVec3(y, y, x)
    val yyy get() = IVec3(y, y, y)
    val yyz get() = IVec3(y, y, z)
    val yzx get() = IVec3(y, z, x)
    val yzy get() = IVec3(y, z, y)
    val yzz get() = IVec3(y, z, z)
    val zxx get() = IVec3(z, x, x)
    val zxy get() = IVec3(z, x, y)
    val zxz get() = IVec3(z, x, z)
    val zyx get() = IVec3(z, y, x)
    val zyy get() = IVec3(z, y, y)
    val zyz get() = IVec3(z, y, z)
    val zzx get() = IVec3(z, z, x)
    val zzy get() = IVec3(z, z, y)
    val zzz get() = IVec3(z, z, z)
    val xxxx get() = IVec4(x, x, x, x)
    val xxxy get() = IVec4(x, x, x, y)
    val xxxz get() = IVec4(x, x, x, z)
    val xxyx get() = IVec4(x, x, y, x)
    val xxyy get() = IVec4(x, x, y, y)
    val xxyz get() = IVec4(x, x, y, z)
    val xxzx get() = IVec4(x, x, z, x)
    val xxzy get() = IVec4(x, x, z, y)
    val xxzz get() = IVec4(x, x, z, z)
    val xyxx get() = IVec4(x, y, x, x)
    val xyxy get() = IVec4(x, y, x, y)
    val xyxz get() = IVec4(x, y, x, z)
    val xyyx get() = IVec4(x, y, y, x)
    val xyyy get() = IVec4(x, y, y, y)
    val xyyz get() = IVec4(x, y, y, z)
    val xyzx get() = IVec4(x, y, z, x)
    val xyzy get() = IVec4(x, y, z, y)
    val xyzz get() = IVec4(x, y, z, z)
    val xzxx get() = IVec4(x, z, x, x)
    val xzxy get() = IVec4(x, z, x, y)
    val xzxz get() = IVec4(x, z, x, z)
    val xzyx get() = IVec4(x, z, y, x)
    val xzyy get() = IVec4(x, z, y, y)
    val xzyz get() = IVec4(x, z, y, z)
    val xzzx get() = IVec4(x, z, z, x)
    val xzzy get() = IVec4(x, z, z, y)
    val xzzz get() = IVec4(x, z, z, z)
    val yxxx get() = IVec4(y, x, x, x)
    val yxxy get() = IVec4(y, x, x, y)
    val yxxz get() = IVec4(y, x, x, z)
    val yxyx get() = IVec4(y, x, y, x)
    val yxyy get() = IVec4(y, x, y, y)
    val yxyz get() = IVec4(y, x, y, z)
    val yxzx get() = IVec4(y, x, z, x)
    val yxzy get() = IVec4(y, x, z, y)
    val yxzz get() = IVec4(y, x, z, z)
    val yyxx get() = IVec4(y, y, x, x)
    val yyxy get() = IVec4(y, y, x, y)
    val yyxz get() = IVec4(y, y, x, z)
    val yyyx get() = IVec4(y, y, y, x)
    val yyyy get() = IVec4(y, y, y, y)
    val yyyz get() = IVec4(y, y, y, z)
    val yyzx get() = IVec4(y, y, z, x)
    val yyzy get() = IVec4(y, y, z, y)
    val yyzz get() = IVec4(y, y, z, z)
    val yzxx get() = IVec4(y, z, x, x)
    val yzxy get() = IVec4(y, z, x, y)
    val yzxz get() = IVec4(y, z, x, z)
    val yzyx get() = IVec4(y, z, y, x)
    val yzyy get() = IVec4(y, z, y, y)
    val yzyz get() = IVec4(y, z, y, z)
    val yzzx get() = IVec4(y, z, z, x)
    val yzzy get() = IVec4(y, z, z, y)
    val yzzz get() = IVec4(y, z, z, z)
    val zxxx get() = IVec4(z, x, x, x)
    val zxxy get() = IVec4(z, x, x, y)
    val zxxz get() = IVec4(z, x, x, z)
    val zxyx get() = IVec4(z, x, y, x)
    val zxyy get() = IVec4(z, x, y, y)
    val zxyz get() = IVec4(z, x, y, z)
    val zxzx get() = IVec4(z, x, z, x)
    val zxzy get() = IVec4(z, x, z, y)
    val zxzz get() = IVec4(z, x, z, z)
    val zyxx get() = IVec4(z, y, x, x)
    val zyxy get() = IVec4(z, y, x, y)
    val zyxz get() = IVec4(z, y, x, z)
    val zyyx get() = IVec4(z, y, y, x)
    val zyyy get() = IVec4(z, y, y, y)
    val zyyz get() = IVec4(z, y, y, z)
    val zyzx get() = IVec4(z, y, z, x)
    val zyzy get() = IVec4(z, y, z, y)
    val zyzz get() = IVec4(z, y, z, z)
    val zzxx get() = IVec4(z, z, x, x)
    val zzxy get() = IVec4(z, z, x, y)
    val zzxz get() = IVec4(z, z, x, z)
    val zzyx get() = IVec4(z, z, y, x)
    val zzyy get() = IVec4(z, z, y, y)
    val zzyz get() = IVec4(z, z, y, z)
    val zzzx get() = IVec4(z, z, z, x)
    val zzzy get() = IVec4(z, z, z, y)
    val zzzz get() = IVec4(z, z, z, z)

    override fun toString(): String {
        return ("IVec3(" + 
                "x=$x, " + 
                "y=$y, " + 
                "z=$z)")
    }

}

operator fun Int.plus(other: IVec3) = IVec3(this + other.x, this + other.y, this + other.z)
operator fun Int.minus(other: IVec3) = IVec3(this - other.x, this - other.y, this - other.z)
operator fun Int.times(other: IVec3) = IVec3(this * other.x, this * other.y, this * other.z)
operator fun Int.div(other: IVec3) = IVec3(this / other.x, this / other.y, this / other.z)
operator fun Int.rem(other: IVec3) = IVec3(this % other.x, this % other.y, this % other.z)

fun ivec3(value: Byte) = IVec3(value.toInt())
fun ivec3(x: Byte, y: Byte, z: Byte) = IVec3(x.toInt(), y.toInt(), z.toInt())
fun ivec3(value: Short) = IVec3(value.toInt())
fun ivec3(x: Short, y: Short, z: Short) = IVec3(x.toInt(), y.toInt(), z.toInt())
fun ivec3(value: Int) = IVec3(value)
fun ivec3(x: Int, y: Int, z: Int) = IVec3(x, y, z)
fun ivec3(value: Long) = IVec3(value.toInt())
fun ivec3(x: Long, y: Long, z: Long) = IVec3(x.toInt(), y.toInt(), z.toInt())
fun ivec3(value: Float) = IVec3(value.toInt())
fun ivec3(x: Float, y: Float, z: Float) = IVec3(x.toInt(), y.toInt(), z.toInt())
fun ivec3(value: Double) = IVec3(value.toInt())
fun ivec3(x: Double, y: Double, z: Double) = IVec3(x.toInt(), y.toInt(), z.toInt())
fun ivec3() = IVec3()
