package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class SVec3(val x: Short, val y: Short, val z: Short) {

    companion object {
        fun splat(value: Byte) = SVec3(value, value, value)
        fun splat(value: Short) = SVec3(value, value, value)
        fun splat(value: Int) = SVec3(value, value, value)
        fun splat(value: Long) = SVec3(value, value, value)
        fun splat(value: Float) = SVec3(value, value, value)
        fun splat(value: Double) = SVec3(value, value, value)
        fun new(x: Byte, y: Byte, z: Byte) = SVec3(x, y, z)
        fun new(x: Short, y: Short, z: Short) = SVec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = SVec3(x, y, z)
        fun new(x: Long, y: Long, z: Long) = SVec3(x, y, z)
        fun new(x: Float, y: Float, z: Float) = SVec3(x, y, z)
        fun new(x: Double, y: Double, z: Double) = SVec3(x, y, z)
        fun eye(index: Int, value: Short = 1.toShort()): SVec3 {
            require(index in 0 until 3) { 
                "Index out of bounds of the range [0; 3). (Got ${index})."
            }
            return SVec3(if (index == 0) value else 0.toShort(), if (index == 1) value else 0.toShort(), if (index == 2) value else 0.toShort())
        }

        fun fromArray(array: ShortArray): SVec3 {
            require(array.size == 3) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 3, got ${array.size}."
            }
            return SVec3(array[0], array[1], array[2])
        }

        val X: SVec3 = SVec3(1.toShort(), 0.toShort(), 0.toShort())
        val Y: SVec3 = SVec3(0.toShort(), 1.toShort(), 0.toShort())
        val Z: SVec3 = SVec3(0.toShort(), 0.toShort(), 1.toShort())
        val ZERO: SVec3 = SVec3(0.toShort())
        val ONE: SVec3 = SVec3(1.toShort())
        val MIN: SVec3 = SVec3(Short.MIN_VALUE)
        val MAX: SVec3 = SVec3(Short.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toShort(), value.toShort(), value.toShort())
    constructor(x: Byte, y: Byte, z: Byte) : this(x.toShort(), y.toShort(), z.toShort())
    constructor(value: Short) : this(value, value, value)
    constructor(value: Int) : this(value.toShort(), value.toShort(), value.toShort())
    constructor(x: Int, y: Int, z: Int) : this(x.toShort(), y.toShort(), z.toShort())
    constructor(value: Long) : this(value.toShort(), value.toShort(), value.toShort())
    constructor(x: Long, y: Long, z: Long) : this(x.toShort(), y.toShort(), z.toShort())
    constructor(value: Float) : this(value.toInt().toShort(), value.toInt().toShort(), value.toInt().toShort())
    constructor(x: Float, y: Float, z: Float) : this(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort())
    constructor(value: Double) : this(value.toInt().toShort(), value.toInt().toShort(), value.toInt().toShort())
    constructor(x: Double, y: Double, z: Double) : this(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort())
    constructor() : this(0.toShort(), 0.toShort(), 0.toShort())
    constructor(lamb: (Int) -> Short) : this(lamb(0), lamb(1), lamb(2))

    operator fun plus(other: SVec3) = SVec3((x + other.x).toShort(), (y + other.y).toShort(), (z + other.z).toShort())
    operator fun plus(other: Short) = SVec3((x + other).toShort(), (y + other).toShort(), (z + other).toShort())
    operator fun minus(other: SVec3) = SVec3((x - other.x).toShort(), (y - other.y).toShort(), (z - other.z).toShort())
    operator fun minus(other: Short) = SVec3((x - other).toShort(), (y - other).toShort(), (z - other).toShort())
    operator fun times(other: SVec3) = SVec3((x * other.x).toShort(), (y * other.y).toShort(), (z * other.z).toShort())
    operator fun times(other: Short) = SVec3((x * other).toShort(), (y * other).toShort(), (z * other).toShort())
    operator fun div(other: SVec3) = SVec3((x / other.x).toShort(), (y / other.y).toShort(), (z / other.z).toShort())
    operator fun div(other: Short) = SVec3((x / other).toShort(), (y / other).toShort(), (z / other).toShort())
    operator fun rem(other: SVec3) = SVec3((x % other.x).toShort(), (y % other.y).toShort(), (z % other.z).toShort())
    operator fun rem(other: Short) = SVec3((x % other).toShort(), (y % other).toShort(), (z % other).toShort())
    operator fun unaryPlus() = this
    operator fun unaryMinus() = SVec3(-x, -y, -z)
    operator fun get(idx: Byte): Short {
        require(idx in 0 until 3) { 
            "SVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Short): Short {
        require(idx in 0 until 3) { 
            "SVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Int): Short {
        require(idx in 0 until 3) { 
            "SVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Long): Short {
        require(idx in 0 until 3) { 
            "SVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }

    fun toBVec3() = BVec3(x.toByte(), y.toByte(), z.toByte())
    fun toIVec3() = IVec3(x.toInt(), y.toInt(), z.toInt())
    fun toLVec3() = LVec3(x.toLong(), y.toLong(), z.toLong())
    fun toVec3() = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
    fun toDVec3() = DVec3(x.toDouble(), y.toDouble(), z.toDouble())

    fun eq(other: SVec3) = x == other.x && y == other.y && z == other.z
    fun iter(): Iterator<Short> {
        return object : Iterator<Short> {
            private var idx = 0
            override fun hasNext() = idx < 3
            override fun next() = this@SVec3[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = SVec3(abs(x.toInt()).toShort(), abs(y.toInt()).toShort(), abs(z.toInt()).toShort())
    fun mod(value: Short) = SVec3(x.mod(value), y.mod(value), z.mod(value))
    fun mod(other: SVec3) = SVec3(x.mod(other.x), y.mod(other.y), z.mod(other.z))
    fun min(other: SVec3) = SVec3(min(x.toInt(), other.x.toInt()).toShort(), min(y.toInt(), other.y.toInt()).toShort(), min(z.toInt(), other.z.toInt()).toShort())
    fun max(other: SVec3) = SVec3(max(x.toInt(), other.x.toInt()).toShort(), max(y.toInt(), other.y.toInt()).toShort(), max(z.toInt(), other.z.toInt()).toShort())
    fun clamp(low: SVec3, high: SVec3) = SVec3(max(min(x.toInt(), high.x.toInt()), low.x.toInt()).toShort(), max(min(y.toInt(), high.y.toInt()), low.y.toInt()).toShort(), max(min(z.toInt(), high.z.toInt()), low.z.toInt()).toShort())
    fun dot(other: SVec3) = x * other.x + y * other.y + z * other.z
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toFloat())
    fun dist(other: SVec3) = (this.toVec3() - other.toVec3()).len()
    fun distSq(other: SVec3) = (this.toVec3() - other.toVec3()).lenSq()
    fun normalize() = toVec3() / len()
    fun dir() = toVec3() / len()
    fun elementSum() = ((x.toInt() + y.toInt()) + z.toInt()).toShort()
    fun eSum() = ((x.toInt() + y.toInt()) + z.toInt()).toShort()
    fun elementProd() = ((x.toInt() * y.toInt()) * z.toInt()).toShort()
    fun eProd() = ((x.toInt() * y.toInt()) * z.toInt()).toShort()
    fun minElement() = min(min(x.toInt(), y.toInt()), z.toInt()).toShort()
    fun eMin() = min(min(x.toInt(), y.toInt()), z.toInt()).toShort()
    fun maxElement() = max(max(x.toInt(), y.toInt()), z.toInt()).toShort()
    fun eMax() = max(max(x.toInt(), y.toInt()), z.toInt()).toShort()
    fun indexOfMin() = when (eMin()) { x -> 0; y -> 1; else -> 2 }
    fun indexOfMax() = when (eMax()) { x -> 0; y -> 1; else -> 2 }
    fun manhattan(other: SVec3) = (this - other).abs().eSum()
    fun cross(other: SVec3) = SVec3(y * other.z - other.y * z, z * other.x - other.z * x, x * other.y - other.x * y)
    fun toArray() = shortArrayOf(x, y, z)
    fun lerp(other: SVec3, t: Float) = this.toVec3() * (1.0f - t) + other.toVec3() * t
    fun floorDiv(other: SVec3) = SVec3(x.floorDiv(other.x), y.floorDiv(other.y), z.floorDiv(other.z))
    fun floorDiv(other: Short) = SVec3(x.floorDiv(other), y.floorDiv(other), z.floorDiv(other))

    infix fun and(other: SVec3) = SVec3(x.toInt() and other.x.toInt(), y.toInt() and other.y.toInt(), z.toInt() and other.z.toInt())
    infix fun and(other: Short) = SVec3(x.toInt() and other.toInt(), y.toInt() and other.toInt(), z.toInt() and other.toInt())
    infix fun or(other: SVec3) = SVec3(x.toInt() or other.x.toInt(), y.toInt() or other.y.toInt(), z.toInt() or other.z.toInt())
    infix fun or(other: Short) = SVec3(x.toInt() or other.toInt(), y.toInt() or other.toInt(), z.toInt() or other.toInt())
    infix fun xor(other: SVec3) = SVec3(x.toInt() xor other.x.toInt(), y.toInt() xor other.y.toInt(), z.toInt() xor other.z.toInt())
    infix fun xor(other: Short) = SVec3(x.toInt() xor other.toInt(), y.toInt() xor other.toInt(), z.toInt() xor other.toInt())
    infix fun shl(other: SVec3) = SVec3(x.toInt() shl other.x.toInt(), y.toInt() shl other.y.toInt(), z.toInt() shl other.z.toInt())
    infix fun shl(other: Short) = SVec3(x.toInt() shl other.toInt(), y.toInt() shl other.toInt(), z.toInt() shl other.toInt())
    infix fun shr(other: SVec3) = SVec3(x.toInt() shr other.x.toInt(), y.toInt() shr other.y.toInt(), z.toInt() shr other.z.toInt())
    infix fun shr(other: Short) = SVec3(x.toInt() shr other.toInt(), y.toInt() shr other.toInt(), z.toInt() shr other.toInt())
    infix fun ushr(other: SVec3) = SVec3(x.toInt() ushr other.x.toInt(), y.toInt() ushr other.y.toInt(), z.toInt() ushr other.z.toInt())
    infix fun ushr(other: Short) = SVec3(x.toInt() ushr other.toInt(), y.toInt() ushr other.toInt(), z.toInt() ushr other.toInt())
    fun inv() = SVec3(x.toInt().inv(), y.toInt().inv(), z.toInt().inv())

    fun extend(w: Byte) = SVec4(x, y, z, w.toShort())
    fun extend(w: Short) = SVec4(x, y, z, w)
    fun extend(w: Int) = SVec4(x, y, z, w.toShort())
    fun extend(w: Long) = SVec4(x, y, z, w.toShort())
    fun extend(w: Float) = SVec4(x, y, z, w.toInt().toShort())
    fun extend(w: Double) = SVec4(x, y, z, w.toInt().toShort())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
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
    fun withX(value: Byte) = SVec3(value.toShort(), y, z)
    fun withX(value: Short) = SVec3(value, y, z)
    fun withX(value: Int) = SVec3(value.toShort(), y, z)
    fun withX(value: Long) = SVec3(value.toShort(), y, z)
    fun withX(value: Float) = SVec3(value.toInt().toShort(), y, z)
    fun withX(value: Double) = SVec3(value.toInt().toShort(), y, z)
    fun withY(value: Byte) = SVec3(x, value.toShort(), z)
    fun withY(value: Short) = SVec3(x, value, z)
    fun withY(value: Int) = SVec3(x, value.toShort(), z)
    fun withY(value: Long) = SVec3(x, value.toShort(), z)
    fun withY(value: Float) = SVec3(x, value.toInt().toShort(), z)
    fun withY(value: Double) = SVec3(x, value.toInt().toShort(), z)
    fun withZ(value: Byte) = SVec3(x, y, value.toShort())
    fun withZ(value: Short) = SVec3(x, y, value)
    fun withZ(value: Int) = SVec3(x, y, value.toShort())
    fun withZ(value: Long) = SVec3(x, y, value.toShort())
    fun withZ(value: Float) = SVec3(x, y, value.toInt().toShort())
    fun withZ(value: Double) = SVec3(x, y, value.toInt().toShort())

    val xx get() = SVec2(x, x)
    val xy get() = SVec2(x, y)
    val xz get() = SVec2(x, z)
    val yx get() = SVec2(y, x)
    val yy get() = SVec2(y, y)
    val yz get() = SVec2(y, z)
    val zx get() = SVec2(z, x)
    val zy get() = SVec2(z, y)
    val zz get() = SVec2(z, z)
    val xxx get() = SVec3(x, x, x)
    val xxy get() = SVec3(x, x, y)
    val xxz get() = SVec3(x, x, z)
    val xyx get() = SVec3(x, y, x)
    val xyy get() = SVec3(x, y, y)
    val xyz get() = SVec3(x, y, z)
    val xzx get() = SVec3(x, z, x)
    val xzy get() = SVec3(x, z, y)
    val xzz get() = SVec3(x, z, z)
    val yxx get() = SVec3(y, x, x)
    val yxy get() = SVec3(y, x, y)
    val yxz get() = SVec3(y, x, z)
    val yyx get() = SVec3(y, y, x)
    val yyy get() = SVec3(y, y, y)
    val yyz get() = SVec3(y, y, z)
    val yzx get() = SVec3(y, z, x)
    val yzy get() = SVec3(y, z, y)
    val yzz get() = SVec3(y, z, z)
    val zxx get() = SVec3(z, x, x)
    val zxy get() = SVec3(z, x, y)
    val zxz get() = SVec3(z, x, z)
    val zyx get() = SVec3(z, y, x)
    val zyy get() = SVec3(z, y, y)
    val zyz get() = SVec3(z, y, z)
    val zzx get() = SVec3(z, z, x)
    val zzy get() = SVec3(z, z, y)
    val zzz get() = SVec3(z, z, z)
    val xxxx get() = SVec4(x, x, x, x)
    val xxxy get() = SVec4(x, x, x, y)
    val xxxz get() = SVec4(x, x, x, z)
    val xxyx get() = SVec4(x, x, y, x)
    val xxyy get() = SVec4(x, x, y, y)
    val xxyz get() = SVec4(x, x, y, z)
    val xxzx get() = SVec4(x, x, z, x)
    val xxzy get() = SVec4(x, x, z, y)
    val xxzz get() = SVec4(x, x, z, z)
    val xyxx get() = SVec4(x, y, x, x)
    val xyxy get() = SVec4(x, y, x, y)
    val xyxz get() = SVec4(x, y, x, z)
    val xyyx get() = SVec4(x, y, y, x)
    val xyyy get() = SVec4(x, y, y, y)
    val xyyz get() = SVec4(x, y, y, z)
    val xyzx get() = SVec4(x, y, z, x)
    val xyzy get() = SVec4(x, y, z, y)
    val xyzz get() = SVec4(x, y, z, z)
    val xzxx get() = SVec4(x, z, x, x)
    val xzxy get() = SVec4(x, z, x, y)
    val xzxz get() = SVec4(x, z, x, z)
    val xzyx get() = SVec4(x, z, y, x)
    val xzyy get() = SVec4(x, z, y, y)
    val xzyz get() = SVec4(x, z, y, z)
    val xzzx get() = SVec4(x, z, z, x)
    val xzzy get() = SVec4(x, z, z, y)
    val xzzz get() = SVec4(x, z, z, z)
    val yxxx get() = SVec4(y, x, x, x)
    val yxxy get() = SVec4(y, x, x, y)
    val yxxz get() = SVec4(y, x, x, z)
    val yxyx get() = SVec4(y, x, y, x)
    val yxyy get() = SVec4(y, x, y, y)
    val yxyz get() = SVec4(y, x, y, z)
    val yxzx get() = SVec4(y, x, z, x)
    val yxzy get() = SVec4(y, x, z, y)
    val yxzz get() = SVec4(y, x, z, z)
    val yyxx get() = SVec4(y, y, x, x)
    val yyxy get() = SVec4(y, y, x, y)
    val yyxz get() = SVec4(y, y, x, z)
    val yyyx get() = SVec4(y, y, y, x)
    val yyyy get() = SVec4(y, y, y, y)
    val yyyz get() = SVec4(y, y, y, z)
    val yyzx get() = SVec4(y, y, z, x)
    val yyzy get() = SVec4(y, y, z, y)
    val yyzz get() = SVec4(y, y, z, z)
    val yzxx get() = SVec4(y, z, x, x)
    val yzxy get() = SVec4(y, z, x, y)
    val yzxz get() = SVec4(y, z, x, z)
    val yzyx get() = SVec4(y, z, y, x)
    val yzyy get() = SVec4(y, z, y, y)
    val yzyz get() = SVec4(y, z, y, z)
    val yzzx get() = SVec4(y, z, z, x)
    val yzzy get() = SVec4(y, z, z, y)
    val yzzz get() = SVec4(y, z, z, z)
    val zxxx get() = SVec4(z, x, x, x)
    val zxxy get() = SVec4(z, x, x, y)
    val zxxz get() = SVec4(z, x, x, z)
    val zxyx get() = SVec4(z, x, y, x)
    val zxyy get() = SVec4(z, x, y, y)
    val zxyz get() = SVec4(z, x, y, z)
    val zxzx get() = SVec4(z, x, z, x)
    val zxzy get() = SVec4(z, x, z, y)
    val zxzz get() = SVec4(z, x, z, z)
    val zyxx get() = SVec4(z, y, x, x)
    val zyxy get() = SVec4(z, y, x, y)
    val zyxz get() = SVec4(z, y, x, z)
    val zyyx get() = SVec4(z, y, y, x)
    val zyyy get() = SVec4(z, y, y, y)
    val zyyz get() = SVec4(z, y, y, z)
    val zyzx get() = SVec4(z, y, z, x)
    val zyzy get() = SVec4(z, y, z, y)
    val zyzz get() = SVec4(z, y, z, z)
    val zzxx get() = SVec4(z, z, x, x)
    val zzxy get() = SVec4(z, z, x, y)
    val zzxz get() = SVec4(z, z, x, z)
    val zzyx get() = SVec4(z, z, y, x)
    val zzyy get() = SVec4(z, z, y, y)
    val zzyz get() = SVec4(z, z, y, z)
    val zzzx get() = SVec4(z, z, z, x)
    val zzzy get() = SVec4(z, z, z, y)
    val zzzz get() = SVec4(z, z, z, z)

    override fun toString(): String {
        return ("SVec3(" + 
                "x=$x, " + 
                "y=$y, " + 
                "z=$z)")
    }

}

operator fun Short.plus(other: SVec3) = SVec3((this + other.x).toShort(), (this + other.y).toShort(), (this + other.z).toShort())
operator fun Short.minus(other: SVec3) = SVec3((this - other.x).toShort(), (this - other.y).toShort(), (this - other.z).toShort())
operator fun Short.times(other: SVec3) = SVec3((this * other.x).toShort(), (this * other.y).toShort(), (this * other.z).toShort())
operator fun Short.div(other: SVec3) = SVec3((this / other.x).toShort(), (this / other.y).toShort(), (this / other.z).toShort())
operator fun Short.rem(other: SVec3) = SVec3((this % other.x).toShort(), (this % other.y).toShort(), (this % other.z).toShort())

fun svec3(value: Byte) = SVec3(value.toShort())
fun svec3(x: Byte, y: Byte, z: Byte) = SVec3(x.toShort(), y.toShort(), z.toShort())
fun svec3(value: Short) = SVec3(value)
fun svec3(x: Short, y: Short, z: Short) = SVec3(x, y, z)
fun svec3(value: Int) = SVec3(value.toShort())
fun svec3(x: Int, y: Int, z: Int) = SVec3(x.toShort(), y.toShort(), z.toShort())
fun svec3(value: Long) = SVec3(value.toShort())
fun svec3(x: Long, y: Long, z: Long) = SVec3(x.toShort(), y.toShort(), z.toShort())
fun svec3(value: Float) = SVec3(value.toInt().toShort())
fun svec3(x: Float, y: Float, z: Float) = SVec3(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort())
fun svec3(value: Double) = SVec3(value.toInt().toShort())
fun svec3(x: Double, y: Double, z: Double) = SVec3(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort())
fun svec3() = SVec3()
