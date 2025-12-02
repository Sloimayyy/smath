package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class Vec3(val x: Float, val y: Float, val z: Float) {

    companion object {
        fun splat(value: Byte) = Vec3(value, value, value)
        fun splat(value: Short) = Vec3(value, value, value)
        fun splat(value: Int) = Vec3(value, value, value)
        fun splat(value: Long) = Vec3(value, value, value)
        fun splat(value: Float) = Vec3(value, value, value)
        fun splat(value: Double) = Vec3(value, value, value)
        fun new(x: Byte, y: Byte, z: Byte) = Vec3(x, y, z)
        fun new(x: Short, y: Short, z: Short) = Vec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = Vec3(x, y, z)
        fun new(x: Long, y: Long, z: Long) = Vec3(x, y, z)
        fun new(x: Float, y: Float, z: Float) = Vec3(x, y, z)
        fun new(x: Double, y: Double, z: Double) = Vec3(x, y, z)
        fun eye(index: Int, value: Float = 1.0f): Vec3 {
            require(index in 0 until 3) { 
                "Index out of bounds of the range [0; 3). (Got ${index})."
            }
            return Vec3(if (index == 0) value else 0.0f, if (index == 1) value else 0.0f, if (index == 2) value else 0.0f)
        }

        fun fromArray(array: FloatArray): Vec3 {
            require(array.size == 3) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 3, got ${array.size}."
            }
            return Vec3(array[0], array[1], array[2])
        }

        val X: Vec3 = Vec3(1.0f, 0.0f, 0.0f)
        val Y: Vec3 = Vec3(0.0f, 1.0f, 0.0f)
        val Z: Vec3 = Vec3(0.0f, 0.0f, 1.0f)
        val ZERO: Vec3 = Vec3(0.0f)
        val ONE: Vec3 = Vec3(1.0f)
        val INF: Vec3 = Vec3(Float.POSITIVE_INFINITY)
        val NEG_INF: Vec3 = Vec3(Float.NEGATIVE_INFINITY)
        val MIN: Vec3 = Vec3(Float.MIN_VALUE)
        val MAX: Vec3 = Vec3(Float.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toFloat(), value.toFloat(), value.toFloat())
    constructor(x: Byte, y: Byte, z: Byte) : this(x.toFloat(), y.toFloat(), z.toFloat())
    constructor(value: Short) : this(value.toFloat(), value.toFloat(), value.toFloat())
    constructor(x: Short, y: Short, z: Short) : this(x.toFloat(), y.toFloat(), z.toFloat())
    constructor(value: Int) : this(value.toFloat(), value.toFloat(), value.toFloat())
    constructor(x: Int, y: Int, z: Int) : this(x.toFloat(), y.toFloat(), z.toFloat())
    constructor(value: Long) : this(value.toFloat(), value.toFloat(), value.toFloat())
    constructor(x: Long, y: Long, z: Long) : this(x.toFloat(), y.toFloat(), z.toFloat())
    constructor(value: Float) : this(value, value, value)
    constructor(value: Double) : this(value.toFloat(), value.toFloat(), value.toFloat())
    constructor(x: Double, y: Double, z: Double) : this(x.toFloat(), y.toFloat(), z.toFloat())
    constructor() : this(0.0f, 0.0f, 0.0f)
    constructor(lamb: (Int) -> Float) : this(lamb(0), lamb(1), lamb(2))

    operator fun plus(other: Vec3) = Vec3(x + other.x, y + other.y, z + other.z)
    operator fun plus(other: Float) = Vec3(x + other, y + other, z + other)
    operator fun minus(other: Vec3) = Vec3(x - other.x, y - other.y, z - other.z)
    operator fun minus(other: Float) = Vec3(x - other, y - other, z - other)
    operator fun times(other: Vec3) = Vec3(x * other.x, y * other.y, z * other.z)
    operator fun times(other: Float) = Vec3(x * other, y * other, z * other)
    operator fun div(other: Vec3) = Vec3(x / other.x, y / other.y, z / other.z)
    operator fun div(other: Float) = Vec3(x / other, y / other, z / other)
    operator fun rem(other: Vec3) = Vec3(x % other.x, y % other.y, z % other.z)
    operator fun rem(other: Float) = Vec3(x % other, y % other, z % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = Vec3(-x, -y, -z)
    operator fun get(idx: Byte): Float {
        require(idx in 0 until 3) { 
            "Vec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Short): Float {
        require(idx in 0 until 3) { 
            "Vec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Int): Float {
        require(idx in 0 until 3) { 
            "Vec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; else -> z }
    }
    operator fun get(idx: Long): Float {
        require(idx in 0 until 3) { 
            "Vec3 indexing failed. Index should be in the range of 0 to 2 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; else -> z }
    }

    fun toBVec3() = BVec3(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte())
    fun toSVec3() = SVec3(x.toInt().toShort(), y.toInt().toShort(), z.toInt().toShort())
    fun toIVec3() = IVec3(x.toInt(), y.toInt(), z.toInt())
    fun toLVec3() = LVec3(x.toLong(), y.toLong(), z.toLong())
    fun toDVec3() = DVec3(x.toDouble(), y.toDouble(), z.toDouble())

    fun eq(other: Vec3) = x == other.x && y == other.y && z == other.z
    fun iter(): Iterator<Float> {
        return object : Iterator<Float> {
            private var idx = 0
            override fun hasNext() = idx < 3
            override fun next() = this@Vec3[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = Vec3(abs(x), abs(y), abs(z))
    fun mod(value: Float) = Vec3(x.mod(value), y.mod(value), z.mod(value))
    fun mod(other: Vec3) = Vec3(x.mod(other.x), y.mod(other.y), z.mod(other.z))
    fun min(other: Vec3) = Vec3(min(x, other.x), min(y, other.y), min(z, other.z))
    fun min(other: Float) = Vec3(min(x, other), min(y, other), min(z, other))
    fun max(other: Vec3) = Vec3(max(x, other.x), max(y, other.y), max(z, other.z))
    fun max(other: Float) = Vec3(max(x, other), max(y, other), max(z, other))
    fun clamp(low: Vec3, high: Vec3) = Vec3(max(min(x, high.x), low.x), max(min(y, high.y), low.y), max(min(z, high.z), low.z))
    fun dot(other: Vec3) = x * other.x + y * other.y + z * other.z
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq())
    fun dist(other: Vec3) = (this - other).len()
    fun distSq(other: Vec3) = (this - other).lenSq()
    fun normalize() = this / len()
    fun dir() = this / len()
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
    fun manhattan(other: Vec3) = (this - other).abs().eSum()
    fun cross(other: Vec3) = Vec3(y * other.z - other.y * z, z * other.x - other.z * x, x * other.y - other.x * y)
    fun toArray() = floatArrayOf(x, y, z)
    fun lerp(other: Vec3, t: Float) = this * (1.0f - t) + other * t
    fun floor() = Vec3(floor(x), floor(y), floor(z))
    fun ceil() = Vec3(ceil(x), ceil(y), ceil(z))
    fun round() = Vec3(round(x), round(y), round(z))
    fun fract() = mod(1.0f)
    fun rot(axis: Vec3, angle: Float): Vec3 {
        val c = cos(angle)
        val term1 = this * c
        val term2 = (axis.cross(this)) * sin(angle)
        val term3 = axis * ((axis.dot(this)) * (0.0f - c))
        return term1 + term2 + term3
    }
    fun quatMul(q: Quat): Vec3 {
        val u = Vec3(q.x, q.y, q.z)
        val scalar = q.w
        return (
                (2f * u.dot(this) * u) +
                (scalar * scalar - u.dot(u)) * this +
                (2f * scalar * u.cross(this))
        )
    }

    fun extend(w: Byte) = Vec4(x, y, z, w.toFloat())
    fun extend(w: Short) = Vec4(x, y, z, w.toFloat())
    fun extend(w: Int) = Vec4(x, y, z, w.toFloat())
    fun extend(w: Long) = Vec4(x, y, z, w.toFloat())
    fun extend(w: Float) = Vec4(x, y, z, w)
    fun extend(w: Double) = Vec4(x, y, z, w.toFloat())
    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); else -> withZ(value) }
    fun perm(other: BVec2) = Vec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: SVec2) = Vec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: IVec2) = Vec2(this[other.x], this[other.y])
    fun perm(other: LVec2) = Vec2(this[other.x.toInt()], this[other.y.toInt()])
    fun perm(other: BVec3) = Vec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: SVec3) = Vec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: IVec3) = Vec3(this[other.x], this[other.y], this[other.z])
    fun perm(other: LVec3) = Vec3(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()])
    fun perm(other: BVec4) = Vec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: SVec4) = Vec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun perm(other: IVec4) = Vec4(this[other.x], this[other.y], this[other.z], this[other.w])
    fun perm(other: LVec4) = Vec4(this[other.x.toInt()], this[other.y.toInt()], this[other.z.toInt()], this[other.w.toInt()])
    fun withX(value: Byte) = Vec3(value.toFloat(), y, z)
    fun withX(value: Short) = Vec3(value.toFloat(), y, z)
    fun withX(value: Int) = Vec3(value.toFloat(), y, z)
    fun withX(value: Long) = Vec3(value.toFloat(), y, z)
    fun withX(value: Float) = Vec3(value, y, z)
    fun withX(value: Double) = Vec3(value.toFloat(), y, z)
    fun withY(value: Byte) = Vec3(x, value.toFloat(), z)
    fun withY(value: Short) = Vec3(x, value.toFloat(), z)
    fun withY(value: Int) = Vec3(x, value.toFloat(), z)
    fun withY(value: Long) = Vec3(x, value.toFloat(), z)
    fun withY(value: Float) = Vec3(x, value, z)
    fun withY(value: Double) = Vec3(x, value.toFloat(), z)
    fun withZ(value: Byte) = Vec3(x, y, value.toFloat())
    fun withZ(value: Short) = Vec3(x, y, value.toFloat())
    fun withZ(value: Int) = Vec3(x, y, value.toFloat())
    fun withZ(value: Long) = Vec3(x, y, value.toFloat())
    fun withZ(value: Float) = Vec3(x, y, value)
    fun withZ(value: Double) = Vec3(x, y, value.toFloat())

    val xx get() = Vec2(x, x)
    val xy get() = Vec2(x, y)
    val xz get() = Vec2(x, z)
    val yx get() = Vec2(y, x)
    val yy get() = Vec2(y, y)
    val yz get() = Vec2(y, z)
    val zx get() = Vec2(z, x)
    val zy get() = Vec2(z, y)
    val zz get() = Vec2(z, z)
    val xxx get() = Vec3(x, x, x)
    val xxy get() = Vec3(x, x, y)
    val xxz get() = Vec3(x, x, z)
    val xyx get() = Vec3(x, y, x)
    val xyy get() = Vec3(x, y, y)
    val xyz get() = Vec3(x, y, z)
    val xzx get() = Vec3(x, z, x)
    val xzy get() = Vec3(x, z, y)
    val xzz get() = Vec3(x, z, z)
    val yxx get() = Vec3(y, x, x)
    val yxy get() = Vec3(y, x, y)
    val yxz get() = Vec3(y, x, z)
    val yyx get() = Vec3(y, y, x)
    val yyy get() = Vec3(y, y, y)
    val yyz get() = Vec3(y, y, z)
    val yzx get() = Vec3(y, z, x)
    val yzy get() = Vec3(y, z, y)
    val yzz get() = Vec3(y, z, z)
    val zxx get() = Vec3(z, x, x)
    val zxy get() = Vec3(z, x, y)
    val zxz get() = Vec3(z, x, z)
    val zyx get() = Vec3(z, y, x)
    val zyy get() = Vec3(z, y, y)
    val zyz get() = Vec3(z, y, z)
    val zzx get() = Vec3(z, z, x)
    val zzy get() = Vec3(z, z, y)
    val zzz get() = Vec3(z, z, z)
    val xxxx get() = Vec4(x, x, x, x)
    val xxxy get() = Vec4(x, x, x, y)
    val xxxz get() = Vec4(x, x, x, z)
    val xxyx get() = Vec4(x, x, y, x)
    val xxyy get() = Vec4(x, x, y, y)
    val xxyz get() = Vec4(x, x, y, z)
    val xxzx get() = Vec4(x, x, z, x)
    val xxzy get() = Vec4(x, x, z, y)
    val xxzz get() = Vec4(x, x, z, z)
    val xyxx get() = Vec4(x, y, x, x)
    val xyxy get() = Vec4(x, y, x, y)
    val xyxz get() = Vec4(x, y, x, z)
    val xyyx get() = Vec4(x, y, y, x)
    val xyyy get() = Vec4(x, y, y, y)
    val xyyz get() = Vec4(x, y, y, z)
    val xyzx get() = Vec4(x, y, z, x)
    val xyzy get() = Vec4(x, y, z, y)
    val xyzz get() = Vec4(x, y, z, z)
    val xzxx get() = Vec4(x, z, x, x)
    val xzxy get() = Vec4(x, z, x, y)
    val xzxz get() = Vec4(x, z, x, z)
    val xzyx get() = Vec4(x, z, y, x)
    val xzyy get() = Vec4(x, z, y, y)
    val xzyz get() = Vec4(x, z, y, z)
    val xzzx get() = Vec4(x, z, z, x)
    val xzzy get() = Vec4(x, z, z, y)
    val xzzz get() = Vec4(x, z, z, z)
    val yxxx get() = Vec4(y, x, x, x)
    val yxxy get() = Vec4(y, x, x, y)
    val yxxz get() = Vec4(y, x, x, z)
    val yxyx get() = Vec4(y, x, y, x)
    val yxyy get() = Vec4(y, x, y, y)
    val yxyz get() = Vec4(y, x, y, z)
    val yxzx get() = Vec4(y, x, z, x)
    val yxzy get() = Vec4(y, x, z, y)
    val yxzz get() = Vec4(y, x, z, z)
    val yyxx get() = Vec4(y, y, x, x)
    val yyxy get() = Vec4(y, y, x, y)
    val yyxz get() = Vec4(y, y, x, z)
    val yyyx get() = Vec4(y, y, y, x)
    val yyyy get() = Vec4(y, y, y, y)
    val yyyz get() = Vec4(y, y, y, z)
    val yyzx get() = Vec4(y, y, z, x)
    val yyzy get() = Vec4(y, y, z, y)
    val yyzz get() = Vec4(y, y, z, z)
    val yzxx get() = Vec4(y, z, x, x)
    val yzxy get() = Vec4(y, z, x, y)
    val yzxz get() = Vec4(y, z, x, z)
    val yzyx get() = Vec4(y, z, y, x)
    val yzyy get() = Vec4(y, z, y, y)
    val yzyz get() = Vec4(y, z, y, z)
    val yzzx get() = Vec4(y, z, z, x)
    val yzzy get() = Vec4(y, z, z, y)
    val yzzz get() = Vec4(y, z, z, z)
    val zxxx get() = Vec4(z, x, x, x)
    val zxxy get() = Vec4(z, x, x, y)
    val zxxz get() = Vec4(z, x, x, z)
    val zxyx get() = Vec4(z, x, y, x)
    val zxyy get() = Vec4(z, x, y, y)
    val zxyz get() = Vec4(z, x, y, z)
    val zxzx get() = Vec4(z, x, z, x)
    val zxzy get() = Vec4(z, x, z, y)
    val zxzz get() = Vec4(z, x, z, z)
    val zyxx get() = Vec4(z, y, x, x)
    val zyxy get() = Vec4(z, y, x, y)
    val zyxz get() = Vec4(z, y, x, z)
    val zyyx get() = Vec4(z, y, y, x)
    val zyyy get() = Vec4(z, y, y, y)
    val zyyz get() = Vec4(z, y, y, z)
    val zyzx get() = Vec4(z, y, z, x)
    val zyzy get() = Vec4(z, y, z, y)
    val zyzz get() = Vec4(z, y, z, z)
    val zzxx get() = Vec4(z, z, x, x)
    val zzxy get() = Vec4(z, z, x, y)
    val zzxz get() = Vec4(z, z, x, z)
    val zzyx get() = Vec4(z, z, y, x)
    val zzyy get() = Vec4(z, z, y, y)
    val zzyz get() = Vec4(z, z, y, z)
    val zzzx get() = Vec4(z, z, z, x)
    val zzzy get() = Vec4(z, z, z, y)
    val zzzz get() = Vec4(z, z, z, z)

    override fun toString(): String {
        return ("Vec3(" + 
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " + 
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " + 
                "z=${"%.5f".format(Locale.ENGLISH, z)})")
    }

}

operator fun Float.plus(other: Vec3) = Vec3(this + other.x, this + other.y, this + other.z)
operator fun Float.minus(other: Vec3) = Vec3(this - other.x, this - other.y, this - other.z)
operator fun Float.times(other: Vec3) = Vec3(this * other.x, this * other.y, this * other.z)
operator fun Float.div(other: Vec3) = Vec3(this / other.x, this / other.y, this / other.z)
operator fun Float.rem(other: Vec3) = Vec3(this % other.x, this % other.y, this % other.z)

fun vec3(value: Byte) = Vec3(value.toFloat())
fun vec3(x: Byte, y: Byte, z: Byte) = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
fun vec3(value: Short) = Vec3(value.toFloat())
fun vec3(x: Short, y: Short, z: Short) = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
fun vec3(value: Int) = Vec3(value.toFloat())
fun vec3(x: Int, y: Int, z: Int) = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
fun vec3(value: Long) = Vec3(value.toFloat())
fun vec3(x: Long, y: Long, z: Long) = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
fun vec3(value: Float) = Vec3(value)
fun vec3(x: Float, y: Float, z: Float) = Vec3(x, y, z)
fun vec3(value: Double) = Vec3(value.toFloat())
fun vec3(x: Double, y: Double, z: Double) = Vec3(x.toFloat(), y.toFloat(), z.toFloat())
fun vec3() = Vec3()
