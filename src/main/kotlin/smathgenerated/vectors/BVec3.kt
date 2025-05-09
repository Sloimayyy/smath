package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class BVec3(val x: Byte, val y: Byte, val z: Byte) {

    companion object {
        fun splat(value: Byte) = BVec3(value, value, value)
        fun splat(value: Short) = BVec3(value, value, value)
        fun splat(value: Int) = BVec3(value, value, value)
        fun splat(value: Long) = BVec3(value, value, value)
        fun splat(value: Float) = BVec3(value, value, value)
        fun splat(value: Double) = BVec3(value, value, value)
        fun new(x: Byte, y: Byte, z: Byte) = BVec3(x, y, z)
        fun new(x: Short, y: Short, z: Short) = BVec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = BVec3(x, y, z)
        fun new(x: Long, y: Long, z: Long) = BVec3(x, y, z)
        fun new(x: Float, y: Float, z: Float) = BVec3(x, y, z)
        fun new(x: Double, y: Double, z: Double) = BVec3(x, y, z)
        fun eye(index: Int, value: Byte = 1.toByte()): BVec3 {
            require(index in 0 until 3) { 
                "Index out of bounds of the range [0; 3). (Got ${index})."
            }
            return BVec3(if (index == 0) value else 0.toByte(), if (index == 1) value else 0.toByte(), if (index == 2) value else 0.toByte())
        }

        fun fromArray(array: ByteArray): BVec3 {
            require(array.size == 3) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 3, got ${array.size}."
            }
            return BVec3(array[0], array[1], array[2])
        }

        val X: BVec3 = BVec3(1.toByte(), 0.toByte(), 0.toByte())
        val Y: BVec3 = BVec3(0.toByte(), 1.toByte(), 0.toByte())
        val Z: BVec3 = BVec3(0.toByte(), 0.toByte(), 1.toByte())
        val ZERO: BVec3 = BVec3(0.toByte())
        val ONE: BVec3 = BVec3(1.toByte())
        val MIN: BVec3 = BVec3(Byte.MIN_VALUE)
        val MAX: BVec3 = BVec3(Byte.MAX_VALUE)
    }

    constructor(value: Byte) : this(value, value, value)
    constructor(value: Short) : this(value.toByte(), value.toByte(), value.toByte())
    constructor(x: Short, y: Short, z: Short) : this(x.toByte(), y.toByte(), z.toByte())
    constructor(value: Int) : this(value.toByte(), value.toByte(), value.toByte())
    constructor(x: Int, y: Int, z: Int) : this(x.toByte(), y.toByte(), z.toByte())
    constructor(value: Long) : this(value.toByte(), value.toByte(), value.toByte())
    constructor(x: Long, y: Long, z: Long) : this(x.toByte(), y.toByte(), z.toByte())
    constructor(value: Float) : this(value.toInt().toByte(), value.toInt().toByte(), value.toInt().toByte())
    constructor(x: Float, y: Float, z: Float) : this(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte())
    constructor(value: Double) : this(value.toInt().toByte(), value.toInt().toByte(), value.toInt().toByte())
    constructor(x: Double, y: Double, z: Double) : this(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte())
    constructor() : this(0.toByte(), 0.toByte(), 0.toByte())
    constructor(lamb: (Int) -> Byte) : this(lamb(0), lamb(1), lamb(2))

    operator fun plus(other: BVec3) = BVec3((x + other.x).toByte(), (y + other.y).toByte(), (z + other.z).toByte())
    operator fun plus(other: Byte) = BVec3((x + other).toByte(), (y + other).toByte(), (z + other).toByte())
    operator fun minus(other: BVec3) = BVec3((x - other.x).toByte(), (y - other.y).toByte(), (z - other.z).toByte())
    operator fun minus(other: Byte) = BVec3((x - other).toByte(), (y - other).toByte(), (z - other).toByte())
    operator fun times(other: BVec3) = BVec3((x * other.x).toByte(), (y * other.y).toByte(), (z * other.z).toByte())
    operator fun times(other: Byte) = BVec3((x * other).toByte(), (y * other).toByte(), (z * other).toByte())
    operator fun div(other: BVec3) = BVec3((x / other.x).toByte(), (y / other.y).toByte(), (z / other.z).toByte())
    operator fun div(other: Byte) = BVec3((x / other).toByte(), (y / other).toByte(), (z / other).toByte())
    operator fun rem(other: BVec3) = BVec3((x % other.x).toByte(), (y % other.y).toByte(), (z % other.z).toByte())
    operator fun rem(other: Byte) = BVec3((x % other).toByte(), (y % other).toByte(), (z % other).toByte())
    operator fun unaryPlus() = this
    operator fun unaryMinus() = BVec3(-x, -y, -z)
    operator fun get(idx: Byte): Byte {
        require(idx in 0 until 3) { 
            "BVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Short): Byte {
        require(idx in 0 until 3) { 
            "BVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Int): Byte {
        require(idx in 0 until 3) { 
            "BVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Long): Byte {
        require(idx in 0 until 3) { 
            "BVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }

    fun toSVec3() = SVec3(x.toShort(), y.toShort(), z.toShort())
    fun toIVec3() = IVec3(x.toInt(), y.toInt(), z.toInt())
    fun toLVec3() = LVec3(x.toLong(), y.toLong(), z.toLong())
    fun toVec3() = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
    fun toDVec3() = DVec3(x.toDouble(), y.toDouble(), z.toDouble())

    fun eq(other: BVec3) = x == other.x && y == other.y && z == other.z
    fun iter(): Iterator<Byte> {
        return object : Iterator<Byte> {
            private var idx = 0
            override fun hasNext() = idx < 3
            override fun next() = this@BVec3[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = BVec3(abs(x.toInt()).toByte(), abs(y.toInt()).toByte(), abs(z.toInt()).toByte())
    fun mod(value: Byte) = BVec3(x.mod(value), y.mod(value), z.mod(value))
    fun mod(other: BVec3) = BVec3(x.mod(other.x), y.mod(other.y), z.mod(other.z))
    fun min(other: BVec3) = BVec3(min(x.toInt(), other.x.toInt()).toByte(), min(y.toInt(), other.y.toInt()).toByte(), min(z.toInt(), other.z.toInt()).toByte())
    fun max(other: BVec3) = BVec3(max(x.toInt(), other.x.toInt()).toByte(), max(y.toInt(), other.y.toInt()).toByte(), max(z.toInt(), other.z.toInt()).toByte())
    fun clamp(low: BVec3, high: BVec3) = BVec3(max(min(x.toInt(), high.x.toInt()), low.x.toInt()).toByte(), max(min(y.toInt(), high.y.toInt()), low.y.toInt()).toByte(), max(min(z.toInt(), high.z.toInt()), low.z.toInt()).toByte())
    fun dot(other: BVec3) = x * other.x + y * other.y + z * other.z
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toFloat())
    fun dist(other: BVec3) = (this.toVec3() - other.toVec3()).len()
    fun distSq(other: BVec3) = (this.toVec3() - other.toVec3()).lenSq()
    fun normalize() = toVec3() / len()
    fun dir() = toVec3() / len()
    fun elementSum() = ((x.toInt() + y.toInt()) + z.toInt()).toByte()
    fun eSum() = ((x.toInt() + y.toInt()) + z.toInt()).toByte()
    fun elementProd() = ((x.toInt() * y.toInt()) * z.toInt()).toByte()
    fun eProd() = ((x.toInt() * y.toInt()) * z.toInt()).toByte()
    fun minElement() = min(min(x.toInt(), y.toInt()), z.toInt()).toByte()
    fun eMin() = min(min(x.toInt(), y.toInt()), z.toInt()).toByte()
    fun maxElement() = max(max(x.toInt(), y.toInt()), z.toInt()).toByte()
    fun eMax() = max(max(x.toInt(), y.toInt()), z.toInt()).toByte()
    fun indexOfMin() = when (eMin()) { x -> 0; y -> 1; else -> 2 }
    fun indexOfMax() = when (eMax()) { x -> 0; y -> 1; else -> 2 }
    fun manhattan(other: BVec3) = (this - other).abs().eSum()
    fun cross(other: BVec3) = BVec3(y * other.z - other.y * z, z * other.x - other.z * x, x * other.y - other.x * y)
    fun toArray() = byteArrayOf(x, y, z)
    fun lerp(other: BVec3, t: Float) = this.toVec3() * (1.0f - t) + other.toVec3() * t
    fun floorDiv(other: BVec3) = BVec3(x.floorDiv(other.x), y.floorDiv(other.y), z.floorDiv(other.z))
    fun floorDiv(other: Byte) = BVec3(x.floorDiv(other), y.floorDiv(other), z.floorDiv(other))

    infix fun and(other: BVec3) = BVec3(x.toInt() and other.x.toInt(), y.toInt() and other.y.toInt(), z.toInt() and other.z.toInt())
    infix fun and(other: Byte) = BVec3(x.toInt() and other.toInt(), y.toInt() and other.toInt(), z.toInt() and other.toInt())
    infix fun or(other: BVec3) = BVec3(x.toInt() or other.x.toInt(), y.toInt() or other.y.toInt(), z.toInt() or other.z.toInt())
    infix fun or(other: Byte) = BVec3(x.toInt() or other.toInt(), y.toInt() or other.toInt(), z.toInt() or other.toInt())
    infix fun xor(other: BVec3) = BVec3(x.toInt() xor other.x.toInt(), y.toInt() xor other.y.toInt(), z.toInt() xor other.z.toInt())
    infix fun xor(other: Byte) = BVec3(x.toInt() xor other.toInt(), y.toInt() xor other.toInt(), z.toInt() xor other.toInt())
    infix fun shl(other: BVec3) = BVec3(x.toInt() shl other.x.toInt(), y.toInt() shl other.y.toInt(), z.toInt() shl other.z.toInt())
    infix fun shl(other: Byte) = BVec3(x.toInt() shl other.toInt(), y.toInt() shl other.toInt(), z.toInt() shl other.toInt())
    infix fun shr(other: BVec3) = BVec3(x.toInt() shr other.x.toInt(), y.toInt() shr other.y.toInt(), z.toInt() shr other.z.toInt())
    infix fun shr(other: Byte) = BVec3(x.toInt() shr other.toInt(), y.toInt() shr other.toInt(), z.toInt() shr other.toInt())
    infix fun ushr(other: BVec3) = BVec3(x.toInt() ushr other.x.toInt(), y.toInt() ushr other.y.toInt(), z.toInt() ushr other.z.toInt())
    infix fun ushr(other: Byte) = BVec3(x.toInt() ushr other.toInt(), y.toInt() ushr other.toInt(), z.toInt() ushr other.toInt())
    fun inv() = BVec3(x.toInt().inv(), y.toInt().inv(), z.toInt().inv())

    fun extend(w: Byte) = BVec4(x, y, z, w)
    fun extend(w: Short) = BVec4(x, y, z, w.toByte())
    fun extend(w: Int) = BVec4(x, y, z, w.toByte())
    fun extend(w: Long) = BVec4(x, y, z, w.toByte())
    fun extend(w: Float) = BVec4(x, y, z, w.toInt().toByte())
    fun extend(w: Double) = BVec4(x, y, z, w.toInt().toByte())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
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
    fun withX(value: Byte) = BVec3(value, y, z)
    fun withX(value: Short) = BVec3(value.toByte(), y, z)
    fun withX(value: Int) = BVec3(value.toByte(), y, z)
    fun withX(value: Long) = BVec3(value.toByte(), y, z)
    fun withX(value: Float) = BVec3(value.toInt().toByte(), y, z)
    fun withX(value: Double) = BVec3(value.toInt().toByte(), y, z)
    fun withY(value: Byte) = BVec3(x, value, z)
    fun withY(value: Short) = BVec3(x, value.toByte(), z)
    fun withY(value: Int) = BVec3(x, value.toByte(), z)
    fun withY(value: Long) = BVec3(x, value.toByte(), z)
    fun withY(value: Float) = BVec3(x, value.toInt().toByte(), z)
    fun withY(value: Double) = BVec3(x, value.toInt().toByte(), z)
    fun withZ(value: Byte) = BVec3(x, y, value)
    fun withZ(value: Short) = BVec3(x, y, value.toByte())
    fun withZ(value: Int) = BVec3(x, y, value.toByte())
    fun withZ(value: Long) = BVec3(x, y, value.toByte())
    fun withZ(value: Float) = BVec3(x, y, value.toInt().toByte())
    fun withZ(value: Double) = BVec3(x, y, value.toInt().toByte())

    val xx get() = BVec2(x, x)
    val xy get() = BVec2(x, y)
    val xz get() = BVec2(x, z)
    val yx get() = BVec2(y, x)
    val yy get() = BVec2(y, y)
    val yz get() = BVec2(y, z)
    val zx get() = BVec2(z, x)
    val zy get() = BVec2(z, y)
    val zz get() = BVec2(z, z)
    val xxx get() = BVec3(x, x, x)
    val xxy get() = BVec3(x, x, y)
    val xxz get() = BVec3(x, x, z)
    val xyx get() = BVec3(x, y, x)
    val xyy get() = BVec3(x, y, y)
    val xyz get() = BVec3(x, y, z)
    val xzx get() = BVec3(x, z, x)
    val xzy get() = BVec3(x, z, y)
    val xzz get() = BVec3(x, z, z)
    val yxx get() = BVec3(y, x, x)
    val yxy get() = BVec3(y, x, y)
    val yxz get() = BVec3(y, x, z)
    val yyx get() = BVec3(y, y, x)
    val yyy get() = BVec3(y, y, y)
    val yyz get() = BVec3(y, y, z)
    val yzx get() = BVec3(y, z, x)
    val yzy get() = BVec3(y, z, y)
    val yzz get() = BVec3(y, z, z)
    val zxx get() = BVec3(z, x, x)
    val zxy get() = BVec3(z, x, y)
    val zxz get() = BVec3(z, x, z)
    val zyx get() = BVec3(z, y, x)
    val zyy get() = BVec3(z, y, y)
    val zyz get() = BVec3(z, y, z)
    val zzx get() = BVec3(z, z, x)
    val zzy get() = BVec3(z, z, y)
    val zzz get() = BVec3(z, z, z)
    val xxxx get() = BVec4(x, x, x, x)
    val xxxy get() = BVec4(x, x, x, y)
    val xxxz get() = BVec4(x, x, x, z)
    val xxyx get() = BVec4(x, x, y, x)
    val xxyy get() = BVec4(x, x, y, y)
    val xxyz get() = BVec4(x, x, y, z)
    val xxzx get() = BVec4(x, x, z, x)
    val xxzy get() = BVec4(x, x, z, y)
    val xxzz get() = BVec4(x, x, z, z)
    val xyxx get() = BVec4(x, y, x, x)
    val xyxy get() = BVec4(x, y, x, y)
    val xyxz get() = BVec4(x, y, x, z)
    val xyyx get() = BVec4(x, y, y, x)
    val xyyy get() = BVec4(x, y, y, y)
    val xyyz get() = BVec4(x, y, y, z)
    val xyzx get() = BVec4(x, y, z, x)
    val xyzy get() = BVec4(x, y, z, y)
    val xyzz get() = BVec4(x, y, z, z)
    val xzxx get() = BVec4(x, z, x, x)
    val xzxy get() = BVec4(x, z, x, y)
    val xzxz get() = BVec4(x, z, x, z)
    val xzyx get() = BVec4(x, z, y, x)
    val xzyy get() = BVec4(x, z, y, y)
    val xzyz get() = BVec4(x, z, y, z)
    val xzzx get() = BVec4(x, z, z, x)
    val xzzy get() = BVec4(x, z, z, y)
    val xzzz get() = BVec4(x, z, z, z)
    val yxxx get() = BVec4(y, x, x, x)
    val yxxy get() = BVec4(y, x, x, y)
    val yxxz get() = BVec4(y, x, x, z)
    val yxyx get() = BVec4(y, x, y, x)
    val yxyy get() = BVec4(y, x, y, y)
    val yxyz get() = BVec4(y, x, y, z)
    val yxzx get() = BVec4(y, x, z, x)
    val yxzy get() = BVec4(y, x, z, y)
    val yxzz get() = BVec4(y, x, z, z)
    val yyxx get() = BVec4(y, y, x, x)
    val yyxy get() = BVec4(y, y, x, y)
    val yyxz get() = BVec4(y, y, x, z)
    val yyyx get() = BVec4(y, y, y, x)
    val yyyy get() = BVec4(y, y, y, y)
    val yyyz get() = BVec4(y, y, y, z)
    val yyzx get() = BVec4(y, y, z, x)
    val yyzy get() = BVec4(y, y, z, y)
    val yyzz get() = BVec4(y, y, z, z)
    val yzxx get() = BVec4(y, z, x, x)
    val yzxy get() = BVec4(y, z, x, y)
    val yzxz get() = BVec4(y, z, x, z)
    val yzyx get() = BVec4(y, z, y, x)
    val yzyy get() = BVec4(y, z, y, y)
    val yzyz get() = BVec4(y, z, y, z)
    val yzzx get() = BVec4(y, z, z, x)
    val yzzy get() = BVec4(y, z, z, y)
    val yzzz get() = BVec4(y, z, z, z)
    val zxxx get() = BVec4(z, x, x, x)
    val zxxy get() = BVec4(z, x, x, y)
    val zxxz get() = BVec4(z, x, x, z)
    val zxyx get() = BVec4(z, x, y, x)
    val zxyy get() = BVec4(z, x, y, y)
    val zxyz get() = BVec4(z, x, y, z)
    val zxzx get() = BVec4(z, x, z, x)
    val zxzy get() = BVec4(z, x, z, y)
    val zxzz get() = BVec4(z, x, z, z)
    val zyxx get() = BVec4(z, y, x, x)
    val zyxy get() = BVec4(z, y, x, y)
    val zyxz get() = BVec4(z, y, x, z)
    val zyyx get() = BVec4(z, y, y, x)
    val zyyy get() = BVec4(z, y, y, y)
    val zyyz get() = BVec4(z, y, y, z)
    val zyzx get() = BVec4(z, y, z, x)
    val zyzy get() = BVec4(z, y, z, y)
    val zyzz get() = BVec4(z, y, z, z)
    val zzxx get() = BVec4(z, z, x, x)
    val zzxy get() = BVec4(z, z, x, y)
    val zzxz get() = BVec4(z, z, x, z)
    val zzyx get() = BVec4(z, z, y, x)
    val zzyy get() = BVec4(z, z, y, y)
    val zzyz get() = BVec4(z, z, y, z)
    val zzzx get() = BVec4(z, z, z, x)
    val zzzy get() = BVec4(z, z, z, y)
    val zzzz get() = BVec4(z, z, z, z)

    override fun toString(): String {
        return ("BVec3(" + 
                "x=$x, " + 
                "y=$y, " + 
                "z=$z)")
    }

}

operator fun Byte.plus(other: BVec3) = BVec3((this + other.x).toByte(), (this + other.y).toByte(), (this + other.z).toByte())
operator fun Byte.minus(other: BVec3) = BVec3((this - other.x).toByte(), (this - other.y).toByte(), (this - other.z).toByte())
operator fun Byte.times(other: BVec3) = BVec3((this * other.x).toByte(), (this * other.y).toByte(), (this * other.z).toByte())
operator fun Byte.div(other: BVec3) = BVec3((this / other.x).toByte(), (this / other.y).toByte(), (this / other.z).toByte())
operator fun Byte.rem(other: BVec3) = BVec3((this % other.x).toByte(), (this % other.y).toByte(), (this % other.z).toByte())

fun bvec3(value: Byte) = BVec3(value)
fun bvec3(x: Byte, y: Byte, z: Byte) = BVec3(x, y, z)
fun bvec3(value: Short) = BVec3(value.toByte())
fun bvec3(x: Short, y: Short, z: Short) = BVec3(x.toByte(), y.toByte(), z.toByte())
fun bvec3(value: Int) = BVec3(value.toByte())
fun bvec3(x: Int, y: Int, z: Int) = BVec3(x.toByte(), y.toByte(), z.toByte())
fun bvec3(value: Long) = BVec3(value.toByte())
fun bvec3(x: Long, y: Long, z: Long) = BVec3(x.toByte(), y.toByte(), z.toByte())
fun bvec3(value: Float) = BVec3(value.toInt().toByte())
fun bvec3(x: Float, y: Float, z: Float) = BVec3(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte())
fun bvec3(value: Double) = BVec3(value.toInt().toByte())
fun bvec3(x: Double, y: Double, z: Double) = BVec3(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte())
fun bvec3() = BVec3()
