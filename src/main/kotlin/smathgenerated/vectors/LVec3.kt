package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class LVec3(val x: Long, val y: Long, val z: Long) {

    companion object {
        fun splat(value: Byte) = LVec3(value, value, value)
        fun splat(value: Short) = LVec3(value, value, value)
        fun splat(value: Int) = LVec3(value, value, value)
        fun splat(value: Long) = LVec3(value, value, value)
        fun splat(value: Float) = LVec3(value, value, value)
        fun splat(value: Double) = LVec3(value, value, value)
        fun new(x: Byte, y: Byte, z: Byte) = LVec3(x, y, z)
        fun new(x: Short, y: Short, z: Short) = LVec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = LVec3(x, y, z)
        fun new(x: Long, y: Long, z: Long) = LVec3(x, y, z)
        fun new(x: Float, y: Float, z: Float) = LVec3(x, y, z)
        fun new(x: Double, y: Double, z: Double) = LVec3(x, y, z)
        fun eye(index: Int, value: Long = 1L): LVec3 {
            require(index in 0 until 3) { 
                "Index out of bounds of the range [0; 3). (Got ${index})."
            }
            return LVec3(if (index == 0) value else 0L, if (index == 1) value else 0L, if (index == 2) value else 0L)
        }

        fun fromArray(array: LongArray): LVec3 {
            require(array.size == 3) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 3, got ${array.size}."
            }
            return LVec3(array[0], array[1], array[2])
        }

        val X: LVec3 = LVec3(1L, 0L, 0L)
        val Y: LVec3 = LVec3(0L, 1L, 0L)
        val Z: LVec3 = LVec3(0L, 0L, 1L)
        val ZERO: LVec3 = LVec3(0L)
        val ONE: LVec3 = LVec3(1L)
        val MIN: LVec3 = LVec3(Long.MIN_VALUE)
        val MAX: LVec3 = LVec3(Long.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toLong(), value.toLong(), value.toLong())
    constructor(x: Byte, y: Byte, z: Byte) : this(x.toLong(), y.toLong(), z.toLong())
    constructor(value: Short) : this(value.toLong(), value.toLong(), value.toLong())
    constructor(x: Short, y: Short, z: Short) : this(x.toLong(), y.toLong(), z.toLong())
    constructor(value: Int) : this(value.toLong(), value.toLong(), value.toLong())
    constructor(x: Int, y: Int, z: Int) : this(x.toLong(), y.toLong(), z.toLong())
    constructor(value: Long) : this(value, value, value)
    constructor(value: Float) : this(value.toLong(), value.toLong(), value.toLong())
    constructor(x: Float, y: Float, z: Float) : this(x.toLong(), y.toLong(), z.toLong())
    constructor(value: Double) : this(value.toLong(), value.toLong(), value.toLong())
    constructor(x: Double, y: Double, z: Double) : this(x.toLong(), y.toLong(), z.toLong())
    constructor() : this(0L, 0L, 0L)
    constructor(lamb: (Int) -> Long) : this(lamb(0), lamb(1), lamb(2))

    operator fun plus(other: LVec3) = LVec3(x + other.x, y + other.y, z + other.z)
    operator fun plus(other: Long) = LVec3(x + other, y + other, z + other)
    operator fun minus(other: LVec3) = LVec3(x - other.x, y - other.y, z - other.z)
    operator fun minus(other: Long) = LVec3(x - other, y - other, z - other)
    operator fun times(other: LVec3) = LVec3(x * other.x, y * other.y, z * other.z)
    operator fun times(other: Long) = LVec3(x * other, y * other, z * other)
    operator fun div(other: LVec3) = LVec3(x / other.x, y / other.y, z / other.z)
    operator fun div(other: Long) = LVec3(x / other, y / other, z / other)
    operator fun rem(other: LVec3) = LVec3(x % other.x, y % other.y, z % other.z)
    operator fun rem(other: Long) = LVec3(x % other, y % other, z % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = LVec3(-x, -y, -z)
    operator fun get(idx: Byte): Long {
        require(idx in 0 until 3) { 
            "LVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Short): Long {
        require(idx in 0 until 3) { 
            "LVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Int): Long {
        require(idx in 0 until 3) { 
            "LVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Long): Long {
        require(idx in 0 until 3) { 
            "LVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }

    fun toBVec3() = BVec3(x.toByte(), y.toByte(), z.toByte())
    fun toSVec3() = SVec3(x.toShort(), y.toShort(), z.toShort())
    fun toIVec3() = IVec3(x.toInt(), y.toInt(), z.toInt())
    fun toVec3() = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
    fun toDVec3() = DVec3(x.toDouble(), y.toDouble(), z.toDouble())

    fun eq(other: LVec3) = x == other.x && y == other.y && z == other.z
    fun iter(): Iterator<Long> {
        return object : Iterator<Long> {
            private var idx = 0
            override fun hasNext() = idx < 3
            override fun next() = this@LVec3[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = LVec3(abs(x), abs(y), abs(z))
    fun mod(value: Long) = LVec3(x.mod(value), y.mod(value), z.mod(value))
    fun mod(other: LVec3) = LVec3(x.mod(other.x), y.mod(other.y), z.mod(other.z))
    fun min(other: LVec3) = LVec3(min(x, other.x), min(y, other.y), min(z, other.z))
    fun max(other: LVec3) = LVec3(max(x, other.x), max(y, other.y), max(z, other.z))
    fun clamp(low: LVec3, high: LVec3) = LVec3(max(min(x, high.x), low.x), max(min(y, high.y), low.y), max(min(z, high.z), low.z))
    fun dot(other: LVec3) = x * other.x + y * other.y + z * other.z
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toDouble())
    fun dist(other: LVec3) = (this.toDVec3() - other.toDVec3()).len()
    fun distSq(other: LVec3) = (this.toDVec3() - other.toDVec3()).lenSq()
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
    fun manhattan(other: LVec3) = (this - other).abs().eSum()
    fun cross(other: LVec3) = LVec3(y * other.z - other.y * z, z * other.x - other.z * x, x * other.y - other.x * y)
    fun toArray() = longArrayOf(x, y, z)
    fun lerp(other: LVec3, t: Double) = this.toDVec3() * (1.0 - t) + other.toDVec3() * t
    fun floorDiv(other: LVec3) = LVec3(x.floorDiv(other.x), y.floorDiv(other.y), z.floorDiv(other.z))
    fun floorDiv(other: Long) = LVec3(x.floorDiv(other), y.floorDiv(other), z.floorDiv(other))

    infix fun and(other: LVec3) = LVec3(x and other.x, y and other.y, z and other.z)
    infix fun and(other: Long) = LVec3(x and other, y and other, z and other)
    infix fun or(other: LVec3) = LVec3(x or other.x, y or other.y, z or other.z)
    infix fun or(other: Long) = LVec3(x or other, y or other, z or other)
    infix fun xor(other: LVec3) = LVec3(x xor other.x, y xor other.y, z xor other.z)
    infix fun xor(other: Long) = LVec3(x xor other, y xor other, z xor other)
    infix fun shl(other: LVec3) = LVec3(x shl other.x.toInt(), y shl other.y.toInt(), z shl other.z.toInt())
    infix fun shl(other: Long) = LVec3(x shl other.toInt(), y shl other.toInt(), z shl other.toInt())
    infix fun shr(other: LVec3) = LVec3(x shr other.x.toInt(), y shr other.y.toInt(), z shr other.z.toInt())
    infix fun shr(other: Long) = LVec3(x shr other.toInt(), y shr other.toInt(), z shr other.toInt())
    infix fun ushr(other: LVec3) = LVec3(x ushr other.x.toInt(), y ushr other.y.toInt(), z ushr other.z.toInt())
    infix fun ushr(other: Long) = LVec3(x ushr other.toInt(), y ushr other.toInt(), z ushr other.toInt())
    fun inv() = LVec3(x.inv(), y.inv(), z.inv())

    fun extend(w: Byte) = LVec4(x, y, z, w.toLong())
    fun extend(w: Short) = LVec4(x, y, z, w.toLong())
    fun extend(w: Int) = LVec4(x, y, z, w.toLong())
    fun extend(w: Long) = LVec4(x, y, z, w)
    fun extend(w: Float) = LVec4(x, y, z, w.toLong())
    fun extend(w: Double) = LVec4(x, y, z, w.toLong())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
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
    fun withX(value: Byte) = LVec3(value.toLong(), y, z)
    fun withX(value: Short) = LVec3(value.toLong(), y, z)
    fun withX(value: Int) = LVec3(value.toLong(), y, z)
    fun withX(value: Long) = LVec3(value, y, z)
    fun withX(value: Float) = LVec3(value.toLong(), y, z)
    fun withX(value: Double) = LVec3(value.toLong(), y, z)
    fun withY(value: Byte) = LVec3(x, value.toLong(), z)
    fun withY(value: Short) = LVec3(x, value.toLong(), z)
    fun withY(value: Int) = LVec3(x, value.toLong(), z)
    fun withY(value: Long) = LVec3(x, value, z)
    fun withY(value: Float) = LVec3(x, value.toLong(), z)
    fun withY(value: Double) = LVec3(x, value.toLong(), z)
    fun withZ(value: Byte) = LVec3(x, y, value.toLong())
    fun withZ(value: Short) = LVec3(x, y, value.toLong())
    fun withZ(value: Int) = LVec3(x, y, value.toLong())
    fun withZ(value: Long) = LVec3(x, y, value)
    fun withZ(value: Float) = LVec3(x, y, value.toLong())
    fun withZ(value: Double) = LVec3(x, y, value.toLong())

    val xx get() = LVec2(x, x)
    val xy get() = LVec2(x, y)
    val xz get() = LVec2(x, z)
    val yx get() = LVec2(y, x)
    val yy get() = LVec2(y, y)
    val yz get() = LVec2(y, z)
    val zx get() = LVec2(z, x)
    val zy get() = LVec2(z, y)
    val zz get() = LVec2(z, z)
    val xxx get() = LVec3(x, x, x)
    val xxy get() = LVec3(x, x, y)
    val xxz get() = LVec3(x, x, z)
    val xyx get() = LVec3(x, y, x)
    val xyy get() = LVec3(x, y, y)
    val xyz get() = LVec3(x, y, z)
    val xzx get() = LVec3(x, z, x)
    val xzy get() = LVec3(x, z, y)
    val xzz get() = LVec3(x, z, z)
    val yxx get() = LVec3(y, x, x)
    val yxy get() = LVec3(y, x, y)
    val yxz get() = LVec3(y, x, z)
    val yyx get() = LVec3(y, y, x)
    val yyy get() = LVec3(y, y, y)
    val yyz get() = LVec3(y, y, z)
    val yzx get() = LVec3(y, z, x)
    val yzy get() = LVec3(y, z, y)
    val yzz get() = LVec3(y, z, z)
    val zxx get() = LVec3(z, x, x)
    val zxy get() = LVec3(z, x, y)
    val zxz get() = LVec3(z, x, z)
    val zyx get() = LVec3(z, y, x)
    val zyy get() = LVec3(z, y, y)
    val zyz get() = LVec3(z, y, z)
    val zzx get() = LVec3(z, z, x)
    val zzy get() = LVec3(z, z, y)
    val zzz get() = LVec3(z, z, z)
    val xxxx get() = LVec4(x, x, x, x)
    val xxxy get() = LVec4(x, x, x, y)
    val xxxz get() = LVec4(x, x, x, z)
    val xxyx get() = LVec4(x, x, y, x)
    val xxyy get() = LVec4(x, x, y, y)
    val xxyz get() = LVec4(x, x, y, z)
    val xxzx get() = LVec4(x, x, z, x)
    val xxzy get() = LVec4(x, x, z, y)
    val xxzz get() = LVec4(x, x, z, z)
    val xyxx get() = LVec4(x, y, x, x)
    val xyxy get() = LVec4(x, y, x, y)
    val xyxz get() = LVec4(x, y, x, z)
    val xyyx get() = LVec4(x, y, y, x)
    val xyyy get() = LVec4(x, y, y, y)
    val xyyz get() = LVec4(x, y, y, z)
    val xyzx get() = LVec4(x, y, z, x)
    val xyzy get() = LVec4(x, y, z, y)
    val xyzz get() = LVec4(x, y, z, z)
    val xzxx get() = LVec4(x, z, x, x)
    val xzxy get() = LVec4(x, z, x, y)
    val xzxz get() = LVec4(x, z, x, z)
    val xzyx get() = LVec4(x, z, y, x)
    val xzyy get() = LVec4(x, z, y, y)
    val xzyz get() = LVec4(x, z, y, z)
    val xzzx get() = LVec4(x, z, z, x)
    val xzzy get() = LVec4(x, z, z, y)
    val xzzz get() = LVec4(x, z, z, z)
    val yxxx get() = LVec4(y, x, x, x)
    val yxxy get() = LVec4(y, x, x, y)
    val yxxz get() = LVec4(y, x, x, z)
    val yxyx get() = LVec4(y, x, y, x)
    val yxyy get() = LVec4(y, x, y, y)
    val yxyz get() = LVec4(y, x, y, z)
    val yxzx get() = LVec4(y, x, z, x)
    val yxzy get() = LVec4(y, x, z, y)
    val yxzz get() = LVec4(y, x, z, z)
    val yyxx get() = LVec4(y, y, x, x)
    val yyxy get() = LVec4(y, y, x, y)
    val yyxz get() = LVec4(y, y, x, z)
    val yyyx get() = LVec4(y, y, y, x)
    val yyyy get() = LVec4(y, y, y, y)
    val yyyz get() = LVec4(y, y, y, z)
    val yyzx get() = LVec4(y, y, z, x)
    val yyzy get() = LVec4(y, y, z, y)
    val yyzz get() = LVec4(y, y, z, z)
    val yzxx get() = LVec4(y, z, x, x)
    val yzxy get() = LVec4(y, z, x, y)
    val yzxz get() = LVec4(y, z, x, z)
    val yzyx get() = LVec4(y, z, y, x)
    val yzyy get() = LVec4(y, z, y, y)
    val yzyz get() = LVec4(y, z, y, z)
    val yzzx get() = LVec4(y, z, z, x)
    val yzzy get() = LVec4(y, z, z, y)
    val yzzz get() = LVec4(y, z, z, z)
    val zxxx get() = LVec4(z, x, x, x)
    val zxxy get() = LVec4(z, x, x, y)
    val zxxz get() = LVec4(z, x, x, z)
    val zxyx get() = LVec4(z, x, y, x)
    val zxyy get() = LVec4(z, x, y, y)
    val zxyz get() = LVec4(z, x, y, z)
    val zxzx get() = LVec4(z, x, z, x)
    val zxzy get() = LVec4(z, x, z, y)
    val zxzz get() = LVec4(z, x, z, z)
    val zyxx get() = LVec4(z, y, x, x)
    val zyxy get() = LVec4(z, y, x, y)
    val zyxz get() = LVec4(z, y, x, z)
    val zyyx get() = LVec4(z, y, y, x)
    val zyyy get() = LVec4(z, y, y, y)
    val zyyz get() = LVec4(z, y, y, z)
    val zyzx get() = LVec4(z, y, z, x)
    val zyzy get() = LVec4(z, y, z, y)
    val zyzz get() = LVec4(z, y, z, z)
    val zzxx get() = LVec4(z, z, x, x)
    val zzxy get() = LVec4(z, z, x, y)
    val zzxz get() = LVec4(z, z, x, z)
    val zzyx get() = LVec4(z, z, y, x)
    val zzyy get() = LVec4(z, z, y, y)
    val zzyz get() = LVec4(z, z, y, z)
    val zzzx get() = LVec4(z, z, z, x)
    val zzzy get() = LVec4(z, z, z, y)
    val zzzz get() = LVec4(z, z, z, z)

    override fun toString(): String {
        return ("LVec3(" + 
                "x=$x, " + 
                "y=$y, " + 
                "z=$z)")
    }

}

operator fun Long.plus(other: LVec3) = LVec3(this + other.x, this + other.y, this + other.z)
operator fun Long.minus(other: LVec3) = LVec3(this - other.x, this - other.y, this - other.z)
operator fun Long.times(other: LVec3) = LVec3(this * other.x, this * other.y, this * other.z)
operator fun Long.div(other: LVec3) = LVec3(this / other.x, this / other.y, this / other.z)
operator fun Long.rem(other: LVec3) = LVec3(this % other.x, this % other.y, this % other.z)

fun lvec3(value: Byte) = LVec3(value.toLong())
fun lvec3(x: Byte, y: Byte, z: Byte) = LVec3(x.toLong(), y.toLong(), z.toLong())
fun lvec3(value: Short) = LVec3(value.toLong())
fun lvec3(x: Short, y: Short, z: Short) = LVec3(x.toLong(), y.toLong(), z.toLong())
fun lvec3(value: Int) = LVec3(value.toLong())
fun lvec3(x: Int, y: Int, z: Int) = LVec3(x.toLong(), y.toLong(), z.toLong())
fun lvec3(value: Long) = LVec3(value)
fun lvec3(x: Long, y: Long, z: Long) = LVec3(x, y, z)
fun lvec3(value: Float) = LVec3(value.toLong())
fun lvec3(x: Float, y: Float, z: Float) = LVec3(x.toLong(), y.toLong(), z.toLong())
fun lvec3(value: Double) = LVec3(value.toLong())
fun lvec3(x: Double, y: Double, z: Double) = LVec3(x.toLong(), y.toLong(), z.toLong())
fun lvec3() = LVec3()
