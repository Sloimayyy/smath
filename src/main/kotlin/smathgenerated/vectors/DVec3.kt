package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class DVec3(val x: Double, val y: Double, val z: Double) {

    companion object {
        fun splat(value: Byte) = DVec3(value, value, value)
        fun splat(value: Short) = DVec3(value, value, value)
        fun splat(value: Int) = DVec3(value, value, value)
        fun splat(value: Long) = DVec3(value, value, value)
        fun splat(value: Float) = DVec3(value, value, value)
        fun splat(value: Double) = DVec3(value, value, value)
        fun new(x: Byte, y: Byte, z: Byte) = DVec3(x, y, z)
        fun new(x: Short, y: Short, z: Short) = DVec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = DVec3(x, y, z)
        fun new(x: Long, y: Long, z: Long) = DVec3(x, y, z)
        fun new(x: Float, y: Float, z: Float) = DVec3(x, y, z)
        fun new(x: Double, y: Double, z: Double) = DVec3(x, y, z)
        fun eye(index: Int, value: Double = 1.0): DVec3 {
            require(index in 0 until 3) { 
                "Index out of bounds of the range [0; 3). (Got ${index})."
            }
            return DVec3(if (index == 0) value else 0.0, if (index == 1) value else 0.0, if (index == 2) value else 0.0)
        }

        fun fromArray(array: DoubleArray): DVec3 {
            require(array.size == 3) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 3, got ${array.size}."
            }
            return DVec3(array[0], array[1], array[2])
        }

        val X: DVec3 = DVec3(1.0, 0.0, 0.0)
        val Y: DVec3 = DVec3(0.0, 1.0, 0.0)
        val Z: DVec3 = DVec3(0.0, 0.0, 1.0)
        val ZERO: DVec3 = DVec3(0.0)
        val ONE: DVec3 = DVec3(1.0)
        val INF: DVec3 = DVec3(Double.POSITIVE_INFINITY)
        val NEG_INF: DVec3 = DVec3(Double.NEGATIVE_INFINITY)
        val MIN: DVec3 = DVec3(Double.MIN_VALUE)
        val MAX: DVec3 = DVec3(Double.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toDouble(), value.toDouble(), value.toDouble())
    constructor(x: Byte, y: Byte, z: Byte) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(value: Short) : this(value.toDouble(), value.toDouble(), value.toDouble())
    constructor(x: Short, y: Short, z: Short) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(value: Int) : this(value.toDouble(), value.toDouble(), value.toDouble())
    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(value: Long) : this(value.toDouble(), value.toDouble(), value.toDouble())
    constructor(x: Long, y: Long, z: Long) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(value: Float) : this(value.toDouble(), value.toDouble(), value.toDouble())
    constructor(x: Float, y: Float, z: Float) : this(x.toDouble(), y.toDouble(), z.toDouble())
    constructor(value: Double) : this(value, value, value)
    constructor() : this(0.0, 0.0, 0.0)
    constructor(lamb: (Int) -> Double) : this(lamb(0), lamb(1), lamb(2))

    operator fun plus(other: DVec3) = DVec3(x + other.x, y + other.y, z + other.z)
    operator fun plus(other: Double) = DVec3(x + other, y + other, z + other)
    operator fun minus(other: DVec3) = DVec3(x - other.x, y - other.y, z - other.z)
    operator fun minus(other: Double) = DVec3(x - other, y - other, z - other)
    operator fun times(other: DVec3) = DVec3(x * other.x, y * other.y, z * other.z)
    operator fun times(other: Double) = DVec3(x * other, y * other, z * other)
    operator fun div(other: DVec3) = DVec3(x / other.x, y / other.y, z / other.z)
    operator fun div(other: Double) = DVec3(x / other, y / other, z / other)
    operator fun rem(other: DVec3) = DVec3(x % other.x, y % other.y, z % other.z)
    operator fun rem(other: Double) = DVec3(x % other, y % other, z % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = DVec3(-x, -y, -z)
    operator fun get(idx: Byte): Double {
        require(idx in 0 until 3) { 
            "DVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Short): Double {
        require(idx in 0 until 3) { 
            "DVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Int): Double {
        require(idx in 0 until 3) { 
            "DVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Long): Double {
        require(idx in 0 until 3) { 
            "DVec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }

    fun toBVec3() = BVec3(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte())
    fun toSVec3() = SVec3(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort())
    fun toIVec3() = IVec3(x.toInt(), y.toInt(), z.toInt())
    fun toLVec3() = LVec3(x.toLong(), y.toLong(), z.toLong())
    fun toVec3() = Vec3(x.toFloat(), y.toFloat(), z.toFloat())

    fun eq(other: DVec3) = x == other.x && y == other.y && z == other.z
    fun iter(): Iterator<Double> {
        return object : Iterator<Double> {
            private var idx = 0
            override fun hasNext() = idx < 3
            override fun next() = this@DVec3[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = DVec3(abs(x), abs(y), abs(z))
    fun mod(value: Double) = DVec3(x.mod(value), y.mod(value), z.mod(value))
    fun mod(other: DVec3) = DVec3(x.mod(other.x), y.mod(other.y), z.mod(other.z))
    fun min(other: DVec3) = DVec3(min(x, other.x), min(y, other.y), min(z, other.z))
    fun max(other: DVec3) = DVec3(max(x, other.x), max(y, other.y), max(z, other.z))
    fun clamp(low: DVec3, high: DVec3) = DVec3(max(min(x, high.x), low.x), max(min(y, high.y), low.y), max(min(z, high.z), low.z))
    fun dot(other: DVec3) = x * other.x + y * other.y + z * other.z
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq())
    fun dist(other: DVec3) = (this - other).len()
    fun distSq(other: DVec3) = (this - other).lenSq()
    fun normalize() = this / len()
    fun dir() = this / len()
    fun elementSum() = x + y + z
    fun eSum() = x + y + z
    fun elementProd() = x * y * z
    fun eProd() = x * y * z
    fun minElement() = min(min(x, y), z)
    fun eMin() = min(min(x, y), z)
    fun maxElement() = max(max(x, y), z)
    fun eMax() = max(max(x, y), z)
    fun indexOfMin() = when (eMin()) { x -> 0; y -> 1; else -> 2 }
    fun indexOfMax() = when (eMax()) { x -> 0; y -> 1; else -> 2 }
    fun manhattan(other: DVec3) = (this - other).abs().eSum()
    fun cross(other: DVec3) = DVec3(y * other.z - other.y * z, z * other.x - other.z * x, x * other.y - other.x * y)
    fun toArray() = doubleArrayOf(x, y, z)
    fun lerp(other: DVec3, t: Double) = this * (1.0 - t) + other * t
    fun floor() = DVec3(floor(x), floor(y), floor(z))
    fun ceil() = DVec3(ceil(x), ceil(y), ceil(z))
    fun round() = DVec3(round(x), round(y), round(z))
    fun fract() = mod(1.0)
    fun quatMul(q: Quat): DVec3 {
        val u = DVec3(q.x, q.y, q.z)
        val scalar = q.w.toDouble()
        return (
                (2f * u.dot(this) * u) +
                (scalar * scalar - u.dot(u)) * this +
                (2f * scalar * u.cross(this))
        )
    }

    fun extend(w: Byte) = DVec4(x, y, z, w.toDouble())
    fun extend(w: Short) = DVec4(x, y, z, w.toDouble())
    fun extend(w: Int) = DVec4(x, y, z, w.toDouble())
    fun extend(w: Long) = DVec4(x, y, z, w.toDouble())
    fun extend(w: Float) = DVec4(x, y, z, w.toDouble())
    fun extend(w: Double) = DVec4(x, y, z, w)
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
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
    fun withX(value: Byte) = DVec3(value.toDouble(), y, z)
    fun withX(value: Short) = DVec3(value.toDouble(), y, z)
    fun withX(value: Int) = DVec3(value.toDouble(), y, z)
    fun withX(value: Long) = DVec3(value.toDouble(), y, z)
    fun withX(value: Float) = DVec3(value.toDouble(), y, z)
    fun withX(value: Double) = DVec3(value, y, z)
    fun withY(value: Byte) = DVec3(x, value.toDouble(), z)
    fun withY(value: Short) = DVec3(x, value.toDouble(), z)
    fun withY(value: Int) = DVec3(x, value.toDouble(), z)
    fun withY(value: Long) = DVec3(x, value.toDouble(), z)
    fun withY(value: Float) = DVec3(x, value.toDouble(), z)
    fun withY(value: Double) = DVec3(x, value, z)
    fun withZ(value: Byte) = DVec3(x, y, value.toDouble())
    fun withZ(value: Short) = DVec3(x, y, value.toDouble())
    fun withZ(value: Int) = DVec3(x, y, value.toDouble())
    fun withZ(value: Long) = DVec3(x, y, value.toDouble())
    fun withZ(value: Float) = DVec3(x, y, value.toDouble())
    fun withZ(value: Double) = DVec3(x, y, value)

    val xx get() = DVec2(x, x)
    val xy get() = DVec2(x, y)
    val xz get() = DVec2(x, z)
    val yx get() = DVec2(y, x)
    val yy get() = DVec2(y, y)
    val yz get() = DVec2(y, z)
    val zx get() = DVec2(z, x)
    val zy get() = DVec2(z, y)
    val zz get() = DVec2(z, z)
    val xxx get() = DVec3(x, x, x)
    val xxy get() = DVec3(x, x, y)
    val xxz get() = DVec3(x, x, z)
    val xyx get() = DVec3(x, y, x)
    val xyy get() = DVec3(x, y, y)
    val xyz get() = DVec3(x, y, z)
    val xzx get() = DVec3(x, z, x)
    val xzy get() = DVec3(x, z, y)
    val xzz get() = DVec3(x, z, z)
    val yxx get() = DVec3(y, x, x)
    val yxy get() = DVec3(y, x, y)
    val yxz get() = DVec3(y, x, z)
    val yyx get() = DVec3(y, y, x)
    val yyy get() = DVec3(y, y, y)
    val yyz get() = DVec3(y, y, z)
    val yzx get() = DVec3(y, z, x)
    val yzy get() = DVec3(y, z, y)
    val yzz get() = DVec3(y, z, z)
    val zxx get() = DVec3(z, x, x)
    val zxy get() = DVec3(z, x, y)
    val zxz get() = DVec3(z, x, z)
    val zyx get() = DVec3(z, y, x)
    val zyy get() = DVec3(z, y, y)
    val zyz get() = DVec3(z, y, z)
    val zzx get() = DVec3(z, z, x)
    val zzy get() = DVec3(z, z, y)
    val zzz get() = DVec3(z, z, z)
    val xxxx get() = DVec4(x, x, x, x)
    val xxxy get() = DVec4(x, x, x, y)
    val xxxz get() = DVec4(x, x, x, z)
    val xxyx get() = DVec4(x, x, y, x)
    val xxyy get() = DVec4(x, x, y, y)
    val xxyz get() = DVec4(x, x, y, z)
    val xxzx get() = DVec4(x, x, z, x)
    val xxzy get() = DVec4(x, x, z, y)
    val xxzz get() = DVec4(x, x, z, z)
    val xyxx get() = DVec4(x, y, x, x)
    val xyxy get() = DVec4(x, y, x, y)
    val xyxz get() = DVec4(x, y, x, z)
    val xyyx get() = DVec4(x, y, y, x)
    val xyyy get() = DVec4(x, y, y, y)
    val xyyz get() = DVec4(x, y, y, z)
    val xyzx get() = DVec4(x, y, z, x)
    val xyzy get() = DVec4(x, y, z, y)
    val xyzz get() = DVec4(x, y, z, z)
    val xzxx get() = DVec4(x, z, x, x)
    val xzxy get() = DVec4(x, z, x, y)
    val xzxz get() = DVec4(x, z, x, z)
    val xzyx get() = DVec4(x, z, y, x)
    val xzyy get() = DVec4(x, z, y, y)
    val xzyz get() = DVec4(x, z, y, z)
    val xzzx get() = DVec4(x, z, z, x)
    val xzzy get() = DVec4(x, z, z, y)
    val xzzz get() = DVec4(x, z, z, z)
    val yxxx get() = DVec4(y, x, x, x)
    val yxxy get() = DVec4(y, x, x, y)
    val yxxz get() = DVec4(y, x, x, z)
    val yxyx get() = DVec4(y, x, y, x)
    val yxyy get() = DVec4(y, x, y, y)
    val yxyz get() = DVec4(y, x, y, z)
    val yxzx get() = DVec4(y, x, z, x)
    val yxzy get() = DVec4(y, x, z, y)
    val yxzz get() = DVec4(y, x, z, z)
    val yyxx get() = DVec4(y, y, x, x)
    val yyxy get() = DVec4(y, y, x, y)
    val yyxz get() = DVec4(y, y, x, z)
    val yyyx get() = DVec4(y, y, y, x)
    val yyyy get() = DVec4(y, y, y, y)
    val yyyz get() = DVec4(y, y, y, z)
    val yyzx get() = DVec4(y, y, z, x)
    val yyzy get() = DVec4(y, y, z, y)
    val yyzz get() = DVec4(y, y, z, z)
    val yzxx get() = DVec4(y, z, x, x)
    val yzxy get() = DVec4(y, z, x, y)
    val yzxz get() = DVec4(y, z, x, z)
    val yzyx get() = DVec4(y, z, y, x)
    val yzyy get() = DVec4(y, z, y, y)
    val yzyz get() = DVec4(y, z, y, z)
    val yzzx get() = DVec4(y, z, z, x)
    val yzzy get() = DVec4(y, z, z, y)
    val yzzz get() = DVec4(y, z, z, z)
    val zxxx get() = DVec4(z, x, x, x)
    val zxxy get() = DVec4(z, x, x, y)
    val zxxz get() = DVec4(z, x, x, z)
    val zxyx get() = DVec4(z, x, y, x)
    val zxyy get() = DVec4(z, x, y, y)
    val zxyz get() = DVec4(z, x, y, z)
    val zxzx get() = DVec4(z, x, z, x)
    val zxzy get() = DVec4(z, x, z, y)
    val zxzz get() = DVec4(z, x, z, z)
    val zyxx get() = DVec4(z, y, x, x)
    val zyxy get() = DVec4(z, y, x, y)
    val zyxz get() = DVec4(z, y, x, z)
    val zyyx get() = DVec4(z, y, y, x)
    val zyyy get() = DVec4(z, y, y, y)
    val zyyz get() = DVec4(z, y, y, z)
    val zyzx get() = DVec4(z, y, z, x)
    val zyzy get() = DVec4(z, y, z, y)
    val zyzz get() = DVec4(z, y, z, z)
    val zzxx get() = DVec4(z, z, x, x)
    val zzxy get() = DVec4(z, z, x, y)
    val zzxz get() = DVec4(z, z, x, z)
    val zzyx get() = DVec4(z, z, y, x)
    val zzyy get() = DVec4(z, z, y, y)
    val zzyz get() = DVec4(z, z, y, z)
    val zzzx get() = DVec4(z, z, z, x)
    val zzzy get() = DVec4(z, z, z, y)
    val zzzz get() = DVec4(z, z, z, z)

    override fun toString(): String {
        return ("DVec3(" + 
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " + 
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " + 
                "z=${"%.5f".format(Locale.ENGLISH, z)})")
    }

}

operator fun Double.plus(other: DVec3) = DVec3(this + other.x, this + other.y, this + other.z)
operator fun Double.minus(other: DVec3) = DVec3(this - other.x, this - other.y, this - other.z)
operator fun Double.times(other: DVec3) = DVec3(this * other.x, this * other.y, this * other.z)
operator fun Double.div(other: DVec3) = DVec3(this / other.x, this / other.y, this / other.z)
operator fun Double.rem(other: DVec3) = DVec3(this % other.x, this % other.y, this % other.z)

fun dvec3(value: Byte) = DVec3(value.toDouble())
fun dvec3(x: Byte, y: Byte, z: Byte) = DVec3(x.toDouble(), y.toDouble(), z.toDouble())
fun dvec3(value: Short) = DVec3(value.toDouble())
fun dvec3(x: Short, y: Short, z: Short) = DVec3(x.toDouble(), y.toDouble(), z.toDouble())
fun dvec3(value: Int) = DVec3(value.toDouble())
fun dvec3(x: Int, y: Int, z: Int) = DVec3(x.toDouble(), y.toDouble(), z.toDouble())
fun dvec3(value: Long) = DVec3(value.toDouble())
fun dvec3(x: Long, y: Long, z: Long) = DVec3(x.toDouble(), y.toDouble(), z.toDouble())
fun dvec3(value: Float) = DVec3(value.toDouble())
fun dvec3(x: Float, y: Float, z: Float) = DVec3(x.toDouble(), y.toDouble(), z.toDouble())
fun dvec3(value: Double) = DVec3(value)
fun dvec3(x: Double, y: Double, z: Double) = DVec3(x, y, z)
fun dvec3() = DVec3()
