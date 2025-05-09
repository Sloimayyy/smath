package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class IVec4(val x: Int, val y: Int, val z: Int, val w: Int) {

    companion object {
        fun splat(value: Byte) = IVec4(value, value, value, value)
        fun splat(value: Short) = IVec4(value, value, value, value)
        fun splat(value: Int) = IVec4(value, value, value, value)
        fun splat(value: Long) = IVec4(value, value, value, value)
        fun splat(value: Float) = IVec4(value, value, value, value)
        fun splat(value: Double) = IVec4(value, value, value, value)
        fun new(x: Byte, y: Byte, z: Byte, w: Byte) = IVec4(x, y, z, w)
        fun new(x: Short, y: Short, z: Short, w: Short) = IVec4(x, y, z, w)
        fun new(x: Int, y: Int, z: Int, w: Int) = IVec4(x, y, z, w)
        fun new(x: Long, y: Long, z: Long, w: Long) = IVec4(x, y, z, w)
        fun new(x: Float, y: Float, z: Float, w: Float) = IVec4(x, y, z, w)
        fun new(x: Double, y: Double, z: Double, w: Double) = IVec4(x, y, z, w)
        fun eye(index: Int, value: Int = 1): IVec4 {
            require(index in 0 until 4) { 
                "Index out of bounds of the range [0; 4). (Got ${index})."
            }
            return IVec4(if (index == 0) value else 0, if (index == 1) value else 0, if (index == 2) value else 0, if (index == 3) value else 0)
        }

        fun fromArray(array: IntArray): IVec4 {
            require(array.size == 4) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 4, got ${array.size}."
            }
            return IVec4(array[0], array[1], array[2], array[3])
        }

        val X: IVec4 = IVec4(1, 0, 0, 0)
        val Y: IVec4 = IVec4(0, 1, 0, 0)
        val Z: IVec4 = IVec4(0, 0, 1, 0)
        val W: IVec4 = IVec4(0, 0, 0, 1)
        val ZERO: IVec4 = IVec4(0)
        val ONE: IVec4 = IVec4(1)
        val MIN: IVec4 = IVec4(Int.MIN_VALUE)
        val MAX: IVec4 = IVec4(Int.MAX_VALUE)
    }

    constructor(value: Byte) : this(value.toInt(), value.toInt(), value.toInt(), value.toInt())
    constructor(x: Byte, y: Byte, z: Byte, w: Byte) : this(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    constructor(value: Short) : this(value.toInt(), value.toInt(), value.toInt(), value.toInt())
    constructor(x: Short, y: Short, z: Short, w: Short) : this(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    constructor(value: Int) : this(value, value, value, value)
    constructor(value: Long) : this(value.toInt(), value.toInt(), value.toInt(), value.toInt())
    constructor(x: Long, y: Long, z: Long, w: Long) : this(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    constructor(value: Float) : this(value.toInt(), value.toInt(), value.toInt(), value.toInt())
    constructor(x: Float, y: Float, z: Float, w: Float) : this(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    constructor(value: Double) : this(value.toInt(), value.toInt(), value.toInt(), value.toInt())
    constructor(x: Double, y: Double, z: Double, w: Double) : this(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    constructor() : this(0, 0, 0, 0)
    constructor(lamb: (Int) -> Int) : this(lamb(0), lamb(1), lamb(2), lamb(3))

    operator fun plus(other: IVec4) = IVec4(x + other.x, y + other.y, z + other.z, w + other.w)
    operator fun plus(other: Int) = IVec4(x + other, y + other, z + other, w + other)
    operator fun minus(other: IVec4) = IVec4(x - other.x, y - other.y, z - other.z, w - other.w)
    operator fun minus(other: Int) = IVec4(x - other, y - other, z - other, w - other)
    operator fun times(other: IVec4) = IVec4(x * other.x, y * other.y, z * other.z, w * other.w)
    operator fun times(other: Int) = IVec4(x * other, y * other, z * other, w * other)
    operator fun div(other: IVec4) = IVec4(x / other.x, y / other.y, z / other.z, w / other.w)
    operator fun div(other: Int) = IVec4(x / other, y / other, z / other, w / other)
    operator fun rem(other: IVec4) = IVec4(x % other.x, y % other.y, z % other.z, w % other.w)
    operator fun rem(other: Int) = IVec4(x % other, y % other, z % other, w % other)
    operator fun unaryPlus() = this
    operator fun unaryMinus() = IVec4(-x, -y, -z, -w)
    operator fun get(idx: Byte): Int {
        require(idx in 0 until 4) { 
            "IVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }
    operator fun get(idx: Short): Int {
        require(idx in 0 until 4) { 
            "IVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }
    operator fun get(idx: Int): Int {
        require(idx in 0 until 4) { 
            "IVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }
    operator fun get(idx: Long): Int {
        require(idx in 0 until 4) { 
            "IVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }

    fun toBVec4() = BVec4(x.toByte(), y.toByte(), z.toByte(), w.toByte())
    fun toSVec4() = SVec4(x.toShort(), y.toShort(), z.toShort(), w.toShort())
    fun toLVec4() = LVec4(x.toLong(), y.toLong(), z.toLong(), w.toLong())
    fun toVec4() = Vec4(x.toFloat(), y.toFloat(), z.toFloat(), w.toFloat())
    fun toDVec4() = DVec4(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

    fun eq(other: IVec4) = x == other.x && y == other.y && z == other.z && w == other.w
    fun iter(): Iterator<Int> {
        return object : Iterator<Int> {
            private var idx = 0
            override fun hasNext() = idx < 4
            override fun next() = this@IVec4[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = IVec4(abs(x), abs(y), abs(z), abs(w))
    fun mod(value: Int) = IVec4(x.mod(value), y.mod(value), z.mod(value), w.mod(value))
    fun mod(other: IVec4) = IVec4(x.mod(other.x), y.mod(other.y), z.mod(other.z), w.mod(other.w))
    fun min(other: IVec4) = IVec4(min(x, other.x), min(y, other.y), min(z, other.z), min(w, other.w))
    fun max(other: IVec4) = IVec4(max(x, other.x), max(y, other.y), max(z, other.z), max(w, other.w))
    fun clamp(low: IVec4, high: IVec4) = IVec4(max(min(x, high.x), low.x), max(min(y, high.y), low.y), max(min(z, high.z), low.z), max(min(w, high.w), low.w))
    fun dot(other: IVec4) = x * other.x + y * other.y + z * other.z + w * other.w
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toDouble())
    fun dist(other: IVec4) = (this.toDVec4() - other.toDVec4()).len()
    fun distSq(other: IVec4) = (this.toDVec4() - other.toDVec4()).lenSq()
    fun normalize() = toDVec4() / len()
    fun dir() = toDVec4() / len()
    fun elementSum() = ((x + y) + (z + w))
    fun eSum() = ((x + y) + (z + w))
    fun elementProd() = ((x * y) * (z * w))
    fun eProd() = ((x * y) * (z * w))
    fun minElement() = min(min(x, y), min(z, w))
    fun eMin() = min(min(x, y), min(z, w))
    fun maxElement() = max(max(x, y), max(z, w))
    fun eMax() = max(max(x, y), max(z, w))
    fun indexOfMin() = when (eMin()) { x -> 0; y -> 1; z -> 2; else -> 3 }
    fun indexOfMax() = when (eMax()) { x -> 0; y -> 1; z -> 2; else -> 3 }
    fun manhattan(other: IVec4) = (this - other).abs().eSum()
    fun toArray() = intArrayOf(x, y, z, w)
    fun lerp(other: IVec4, t: Float) = this.toDVec4() * (1.0 - t.toDouble()) + other.toDVec4() * t.toDouble()
    fun floorDiv(other: IVec4) = IVec4(x.floorDiv(other.x), y.floorDiv(other.y), z.floorDiv(other.z), w.floorDiv(other.w))
    fun floorDiv(other: Int) = IVec4(x.floorDiv(other), y.floorDiv(other), z.floorDiv(other), w.floorDiv(other))

    infix fun and(other: IVec4) = IVec4(x and other.x, y and other.y, z and other.z, w and other.w)
    infix fun and(other: Int) = IVec4(x and other, y and other, z and other, w and other)
    infix fun or(other: IVec4) = IVec4(x or other.x, y or other.y, z or other.z, w or other.w)
    infix fun or(other: Int) = IVec4(x or other, y or other, z or other, w or other)
    infix fun xor(other: IVec4) = IVec4(x xor other.x, y xor other.y, z xor other.z, w xor other.w)
    infix fun xor(other: Int) = IVec4(x xor other, y xor other, z xor other, w xor other)
    infix fun shl(other: IVec4) = IVec4(x shl other.x, y shl other.y, z shl other.z, w shl other.w)
    infix fun shl(other: Int) = IVec4(x shl other, y shl other, z shl other, w shl other)
    infix fun shr(other: IVec4) = IVec4(x shr other.x, y shr other.y, z shr other.z, w shr other.w)
    infix fun shr(other: Int) = IVec4(x shr other, y shr other, z shr other, w shr other)
    infix fun ushr(other: IVec4) = IVec4(x ushr other.x, y ushr other.y, z ushr other.z, w ushr other.w)
    infix fun ushr(other: Int) = IVec4(x ushr other, y ushr other, z ushr other, w ushr other)
    fun inv() = IVec4(x.inv(), y.inv(), z.inv(), w.inv())

    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
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
    fun withX(value: Byte) = IVec4(value.toInt(), y, z, w)
    fun withX(value: Short) = IVec4(value.toInt(), y, z, w)
    fun withX(value: Int) = IVec4(value, y, z, w)
    fun withX(value: Long) = IVec4(value.toInt(), y, z, w)
    fun withX(value: Float) = IVec4(value.toInt(), y, z, w)
    fun withX(value: Double) = IVec4(value.toInt(), y, z, w)
    fun withY(value: Byte) = IVec4(x, value.toInt(), z, w)
    fun withY(value: Short) = IVec4(x, value.toInt(), z, w)
    fun withY(value: Int) = IVec4(x, value, z, w)
    fun withY(value: Long) = IVec4(x, value.toInt(), z, w)
    fun withY(value: Float) = IVec4(x, value.toInt(), z, w)
    fun withY(value: Double) = IVec4(x, value.toInt(), z, w)
    fun withZ(value: Byte) = IVec4(x, y, value.toInt(), w)
    fun withZ(value: Short) = IVec4(x, y, value.toInt(), w)
    fun withZ(value: Int) = IVec4(x, y, value, w)
    fun withZ(value: Long) = IVec4(x, y, value.toInt(), w)
    fun withZ(value: Float) = IVec4(x, y, value.toInt(), w)
    fun withZ(value: Double) = IVec4(x, y, value.toInt(), w)
    fun withW(value: Byte) = IVec4(x, y, z, value.toInt())
    fun withW(value: Short) = IVec4(x, y, z, value.toInt())
    fun withW(value: Int) = IVec4(x, y, z, value)
    fun withW(value: Long) = IVec4(x, y, z, value.toInt())
    fun withW(value: Float) = IVec4(x, y, z, value.toInt())
    fun withW(value: Double) = IVec4(x, y, z, value.toInt())

    val xx get() = IVec2(x, x)
    val xy get() = IVec2(x, y)
    val xz get() = IVec2(x, z)
    val xw get() = IVec2(x, w)
    val yx get() = IVec2(y, x)
    val yy get() = IVec2(y, y)
    val yz get() = IVec2(y, z)
    val yw get() = IVec2(y, w)
    val zx get() = IVec2(z, x)
    val zy get() = IVec2(z, y)
    val zz get() = IVec2(z, z)
    val zw get() = IVec2(z, w)
    val wx get() = IVec2(w, x)
    val wy get() = IVec2(w, y)
    val wz get() = IVec2(w, z)
    val ww get() = IVec2(w, w)
    val xxx get() = IVec3(x, x, x)
    val xxy get() = IVec3(x, x, y)
    val xxz get() = IVec3(x, x, z)
    val xxw get() = IVec3(x, x, w)
    val xyx get() = IVec3(x, y, x)
    val xyy get() = IVec3(x, y, y)
    val xyz get() = IVec3(x, y, z)
    val xyw get() = IVec3(x, y, w)
    val xzx get() = IVec3(x, z, x)
    val xzy get() = IVec3(x, z, y)
    val xzz get() = IVec3(x, z, z)
    val xzw get() = IVec3(x, z, w)
    val xwx get() = IVec3(x, w, x)
    val xwy get() = IVec3(x, w, y)
    val xwz get() = IVec3(x, w, z)
    val xww get() = IVec3(x, w, w)
    val yxx get() = IVec3(y, x, x)
    val yxy get() = IVec3(y, x, y)
    val yxz get() = IVec3(y, x, z)
    val yxw get() = IVec3(y, x, w)
    val yyx get() = IVec3(y, y, x)
    val yyy get() = IVec3(y, y, y)
    val yyz get() = IVec3(y, y, z)
    val yyw get() = IVec3(y, y, w)
    val yzx get() = IVec3(y, z, x)
    val yzy get() = IVec3(y, z, y)
    val yzz get() = IVec3(y, z, z)
    val yzw get() = IVec3(y, z, w)
    val ywx get() = IVec3(y, w, x)
    val ywy get() = IVec3(y, w, y)
    val ywz get() = IVec3(y, w, z)
    val yww get() = IVec3(y, w, w)
    val zxx get() = IVec3(z, x, x)
    val zxy get() = IVec3(z, x, y)
    val zxz get() = IVec3(z, x, z)
    val zxw get() = IVec3(z, x, w)
    val zyx get() = IVec3(z, y, x)
    val zyy get() = IVec3(z, y, y)
    val zyz get() = IVec3(z, y, z)
    val zyw get() = IVec3(z, y, w)
    val zzx get() = IVec3(z, z, x)
    val zzy get() = IVec3(z, z, y)
    val zzz get() = IVec3(z, z, z)
    val zzw get() = IVec3(z, z, w)
    val zwx get() = IVec3(z, w, x)
    val zwy get() = IVec3(z, w, y)
    val zwz get() = IVec3(z, w, z)
    val zww get() = IVec3(z, w, w)
    val wxx get() = IVec3(w, x, x)
    val wxy get() = IVec3(w, x, y)
    val wxz get() = IVec3(w, x, z)
    val wxw get() = IVec3(w, x, w)
    val wyx get() = IVec3(w, y, x)
    val wyy get() = IVec3(w, y, y)
    val wyz get() = IVec3(w, y, z)
    val wyw get() = IVec3(w, y, w)
    val wzx get() = IVec3(w, z, x)
    val wzy get() = IVec3(w, z, y)
    val wzz get() = IVec3(w, z, z)
    val wzw get() = IVec3(w, z, w)
    val wwx get() = IVec3(w, w, x)
    val wwy get() = IVec3(w, w, y)
    val wwz get() = IVec3(w, w, z)
    val www get() = IVec3(w, w, w)
    val xxxx get() = IVec4(x, x, x, x)
    val xxxy get() = IVec4(x, x, x, y)
    val xxxz get() = IVec4(x, x, x, z)
    val xxxw get() = IVec4(x, x, x, w)
    val xxyx get() = IVec4(x, x, y, x)
    val xxyy get() = IVec4(x, x, y, y)
    val xxyz get() = IVec4(x, x, y, z)
    val xxyw get() = IVec4(x, x, y, w)
    val xxzx get() = IVec4(x, x, z, x)
    val xxzy get() = IVec4(x, x, z, y)
    val xxzz get() = IVec4(x, x, z, z)
    val xxzw get() = IVec4(x, x, z, w)
    val xxwx get() = IVec4(x, x, w, x)
    val xxwy get() = IVec4(x, x, w, y)
    val xxwz get() = IVec4(x, x, w, z)
    val xxww get() = IVec4(x, x, w, w)
    val xyxx get() = IVec4(x, y, x, x)
    val xyxy get() = IVec4(x, y, x, y)
    val xyxz get() = IVec4(x, y, x, z)
    val xyxw get() = IVec4(x, y, x, w)
    val xyyx get() = IVec4(x, y, y, x)
    val xyyy get() = IVec4(x, y, y, y)
    val xyyz get() = IVec4(x, y, y, z)
    val xyyw get() = IVec4(x, y, y, w)
    val xyzx get() = IVec4(x, y, z, x)
    val xyzy get() = IVec4(x, y, z, y)
    val xyzz get() = IVec4(x, y, z, z)
    val xyzw get() = IVec4(x, y, z, w)
    val xywx get() = IVec4(x, y, w, x)
    val xywy get() = IVec4(x, y, w, y)
    val xywz get() = IVec4(x, y, w, z)
    val xyww get() = IVec4(x, y, w, w)
    val xzxx get() = IVec4(x, z, x, x)
    val xzxy get() = IVec4(x, z, x, y)
    val xzxz get() = IVec4(x, z, x, z)
    val xzxw get() = IVec4(x, z, x, w)
    val xzyx get() = IVec4(x, z, y, x)
    val xzyy get() = IVec4(x, z, y, y)
    val xzyz get() = IVec4(x, z, y, z)
    val xzyw get() = IVec4(x, z, y, w)
    val xzzx get() = IVec4(x, z, z, x)
    val xzzy get() = IVec4(x, z, z, y)
    val xzzz get() = IVec4(x, z, z, z)
    val xzzw get() = IVec4(x, z, z, w)
    val xzwx get() = IVec4(x, z, w, x)
    val xzwy get() = IVec4(x, z, w, y)
    val xzwz get() = IVec4(x, z, w, z)
    val xzww get() = IVec4(x, z, w, w)
    val xwxx get() = IVec4(x, w, x, x)
    val xwxy get() = IVec4(x, w, x, y)
    val xwxz get() = IVec4(x, w, x, z)
    val xwxw get() = IVec4(x, w, x, w)
    val xwyx get() = IVec4(x, w, y, x)
    val xwyy get() = IVec4(x, w, y, y)
    val xwyz get() = IVec4(x, w, y, z)
    val xwyw get() = IVec4(x, w, y, w)
    val xwzx get() = IVec4(x, w, z, x)
    val xwzy get() = IVec4(x, w, z, y)
    val xwzz get() = IVec4(x, w, z, z)
    val xwzw get() = IVec4(x, w, z, w)
    val xwwx get() = IVec4(x, w, w, x)
    val xwwy get() = IVec4(x, w, w, y)
    val xwwz get() = IVec4(x, w, w, z)
    val xwww get() = IVec4(x, w, w, w)
    val yxxx get() = IVec4(y, x, x, x)
    val yxxy get() = IVec4(y, x, x, y)
    val yxxz get() = IVec4(y, x, x, z)
    val yxxw get() = IVec4(y, x, x, w)
    val yxyx get() = IVec4(y, x, y, x)
    val yxyy get() = IVec4(y, x, y, y)
    val yxyz get() = IVec4(y, x, y, z)
    val yxyw get() = IVec4(y, x, y, w)
    val yxzx get() = IVec4(y, x, z, x)
    val yxzy get() = IVec4(y, x, z, y)
    val yxzz get() = IVec4(y, x, z, z)
    val yxzw get() = IVec4(y, x, z, w)
    val yxwx get() = IVec4(y, x, w, x)
    val yxwy get() = IVec4(y, x, w, y)
    val yxwz get() = IVec4(y, x, w, z)
    val yxww get() = IVec4(y, x, w, w)
    val yyxx get() = IVec4(y, y, x, x)
    val yyxy get() = IVec4(y, y, x, y)
    val yyxz get() = IVec4(y, y, x, z)
    val yyxw get() = IVec4(y, y, x, w)
    val yyyx get() = IVec4(y, y, y, x)
    val yyyy get() = IVec4(y, y, y, y)
    val yyyz get() = IVec4(y, y, y, z)
    val yyyw get() = IVec4(y, y, y, w)
    val yyzx get() = IVec4(y, y, z, x)
    val yyzy get() = IVec4(y, y, z, y)
    val yyzz get() = IVec4(y, y, z, z)
    val yyzw get() = IVec4(y, y, z, w)
    val yywx get() = IVec4(y, y, w, x)
    val yywy get() = IVec4(y, y, w, y)
    val yywz get() = IVec4(y, y, w, z)
    val yyww get() = IVec4(y, y, w, w)
    val yzxx get() = IVec4(y, z, x, x)
    val yzxy get() = IVec4(y, z, x, y)
    val yzxz get() = IVec4(y, z, x, z)
    val yzxw get() = IVec4(y, z, x, w)
    val yzyx get() = IVec4(y, z, y, x)
    val yzyy get() = IVec4(y, z, y, y)
    val yzyz get() = IVec4(y, z, y, z)
    val yzyw get() = IVec4(y, z, y, w)
    val yzzx get() = IVec4(y, z, z, x)
    val yzzy get() = IVec4(y, z, z, y)
    val yzzz get() = IVec4(y, z, z, z)
    val yzzw get() = IVec4(y, z, z, w)
    val yzwx get() = IVec4(y, z, w, x)
    val yzwy get() = IVec4(y, z, w, y)
    val yzwz get() = IVec4(y, z, w, z)
    val yzww get() = IVec4(y, z, w, w)
    val ywxx get() = IVec4(y, w, x, x)
    val ywxy get() = IVec4(y, w, x, y)
    val ywxz get() = IVec4(y, w, x, z)
    val ywxw get() = IVec4(y, w, x, w)
    val ywyx get() = IVec4(y, w, y, x)
    val ywyy get() = IVec4(y, w, y, y)
    val ywyz get() = IVec4(y, w, y, z)
    val ywyw get() = IVec4(y, w, y, w)
    val ywzx get() = IVec4(y, w, z, x)
    val ywzy get() = IVec4(y, w, z, y)
    val ywzz get() = IVec4(y, w, z, z)
    val ywzw get() = IVec4(y, w, z, w)
    val ywwx get() = IVec4(y, w, w, x)
    val ywwy get() = IVec4(y, w, w, y)
    val ywwz get() = IVec4(y, w, w, z)
    val ywww get() = IVec4(y, w, w, w)
    val zxxx get() = IVec4(z, x, x, x)
    val zxxy get() = IVec4(z, x, x, y)
    val zxxz get() = IVec4(z, x, x, z)
    val zxxw get() = IVec4(z, x, x, w)
    val zxyx get() = IVec4(z, x, y, x)
    val zxyy get() = IVec4(z, x, y, y)
    val zxyz get() = IVec4(z, x, y, z)
    val zxyw get() = IVec4(z, x, y, w)
    val zxzx get() = IVec4(z, x, z, x)
    val zxzy get() = IVec4(z, x, z, y)
    val zxzz get() = IVec4(z, x, z, z)
    val zxzw get() = IVec4(z, x, z, w)
    val zxwx get() = IVec4(z, x, w, x)
    val zxwy get() = IVec4(z, x, w, y)
    val zxwz get() = IVec4(z, x, w, z)
    val zxww get() = IVec4(z, x, w, w)
    val zyxx get() = IVec4(z, y, x, x)
    val zyxy get() = IVec4(z, y, x, y)
    val zyxz get() = IVec4(z, y, x, z)
    val zyxw get() = IVec4(z, y, x, w)
    val zyyx get() = IVec4(z, y, y, x)
    val zyyy get() = IVec4(z, y, y, y)
    val zyyz get() = IVec4(z, y, y, z)
    val zyyw get() = IVec4(z, y, y, w)
    val zyzx get() = IVec4(z, y, z, x)
    val zyzy get() = IVec4(z, y, z, y)
    val zyzz get() = IVec4(z, y, z, z)
    val zyzw get() = IVec4(z, y, z, w)
    val zywx get() = IVec4(z, y, w, x)
    val zywy get() = IVec4(z, y, w, y)
    val zywz get() = IVec4(z, y, w, z)
    val zyww get() = IVec4(z, y, w, w)
    val zzxx get() = IVec4(z, z, x, x)
    val zzxy get() = IVec4(z, z, x, y)
    val zzxz get() = IVec4(z, z, x, z)
    val zzxw get() = IVec4(z, z, x, w)
    val zzyx get() = IVec4(z, z, y, x)
    val zzyy get() = IVec4(z, z, y, y)
    val zzyz get() = IVec4(z, z, y, z)
    val zzyw get() = IVec4(z, z, y, w)
    val zzzx get() = IVec4(z, z, z, x)
    val zzzy get() = IVec4(z, z, z, y)
    val zzzz get() = IVec4(z, z, z, z)
    val zzzw get() = IVec4(z, z, z, w)
    val zzwx get() = IVec4(z, z, w, x)
    val zzwy get() = IVec4(z, z, w, y)
    val zzwz get() = IVec4(z, z, w, z)
    val zzww get() = IVec4(z, z, w, w)
    val zwxx get() = IVec4(z, w, x, x)
    val zwxy get() = IVec4(z, w, x, y)
    val zwxz get() = IVec4(z, w, x, z)
    val zwxw get() = IVec4(z, w, x, w)
    val zwyx get() = IVec4(z, w, y, x)
    val zwyy get() = IVec4(z, w, y, y)
    val zwyz get() = IVec4(z, w, y, z)
    val zwyw get() = IVec4(z, w, y, w)
    val zwzx get() = IVec4(z, w, z, x)
    val zwzy get() = IVec4(z, w, z, y)
    val zwzz get() = IVec4(z, w, z, z)
    val zwzw get() = IVec4(z, w, z, w)
    val zwwx get() = IVec4(z, w, w, x)
    val zwwy get() = IVec4(z, w, w, y)
    val zwwz get() = IVec4(z, w, w, z)
    val zwww get() = IVec4(z, w, w, w)
    val wxxx get() = IVec4(w, x, x, x)
    val wxxy get() = IVec4(w, x, x, y)
    val wxxz get() = IVec4(w, x, x, z)
    val wxxw get() = IVec4(w, x, x, w)
    val wxyx get() = IVec4(w, x, y, x)
    val wxyy get() = IVec4(w, x, y, y)
    val wxyz get() = IVec4(w, x, y, z)
    val wxyw get() = IVec4(w, x, y, w)
    val wxzx get() = IVec4(w, x, z, x)
    val wxzy get() = IVec4(w, x, z, y)
    val wxzz get() = IVec4(w, x, z, z)
    val wxzw get() = IVec4(w, x, z, w)
    val wxwx get() = IVec4(w, x, w, x)
    val wxwy get() = IVec4(w, x, w, y)
    val wxwz get() = IVec4(w, x, w, z)
    val wxww get() = IVec4(w, x, w, w)
    val wyxx get() = IVec4(w, y, x, x)
    val wyxy get() = IVec4(w, y, x, y)
    val wyxz get() = IVec4(w, y, x, z)
    val wyxw get() = IVec4(w, y, x, w)
    val wyyx get() = IVec4(w, y, y, x)
    val wyyy get() = IVec4(w, y, y, y)
    val wyyz get() = IVec4(w, y, y, z)
    val wyyw get() = IVec4(w, y, y, w)
    val wyzx get() = IVec4(w, y, z, x)
    val wyzy get() = IVec4(w, y, z, y)
    val wyzz get() = IVec4(w, y, z, z)
    val wyzw get() = IVec4(w, y, z, w)
    val wywx get() = IVec4(w, y, w, x)
    val wywy get() = IVec4(w, y, w, y)
    val wywz get() = IVec4(w, y, w, z)
    val wyww get() = IVec4(w, y, w, w)
    val wzxx get() = IVec4(w, z, x, x)
    val wzxy get() = IVec4(w, z, x, y)
    val wzxz get() = IVec4(w, z, x, z)
    val wzxw get() = IVec4(w, z, x, w)
    val wzyx get() = IVec4(w, z, y, x)
    val wzyy get() = IVec4(w, z, y, y)
    val wzyz get() = IVec4(w, z, y, z)
    val wzyw get() = IVec4(w, z, y, w)
    val wzzx get() = IVec4(w, z, z, x)
    val wzzy get() = IVec4(w, z, z, y)
    val wzzz get() = IVec4(w, z, z, z)
    val wzzw get() = IVec4(w, z, z, w)
    val wzwx get() = IVec4(w, z, w, x)
    val wzwy get() = IVec4(w, z, w, y)
    val wzwz get() = IVec4(w, z, w, z)
    val wzww get() = IVec4(w, z, w, w)
    val wwxx get() = IVec4(w, w, x, x)
    val wwxy get() = IVec4(w, w, x, y)
    val wwxz get() = IVec4(w, w, x, z)
    val wwxw get() = IVec4(w, w, x, w)
    val wwyx get() = IVec4(w, w, y, x)
    val wwyy get() = IVec4(w, w, y, y)
    val wwyz get() = IVec4(w, w, y, z)
    val wwyw get() = IVec4(w, w, y, w)
    val wwzx get() = IVec4(w, w, z, x)
    val wwzy get() = IVec4(w, w, z, y)
    val wwzz get() = IVec4(w, w, z, z)
    val wwzw get() = IVec4(w, w, z, w)
    val wwwx get() = IVec4(w, w, w, x)
    val wwwy get() = IVec4(w, w, w, y)
    val wwwz get() = IVec4(w, w, w, z)
    val wwww get() = IVec4(w, w, w, w)

    override fun toString(): String {
        return ("IVec4(" + 
                "x=$x, " + 
                "y=$y, " + 
                "z=$z, " + 
                "w=$w)")
    }

}

operator fun Int.plus(other: IVec4) = IVec4(this + other.x, this + other.y, this + other.z, this + other.w)
operator fun Int.minus(other: IVec4) = IVec4(this - other.x, this - other.y, this - other.z, this - other.w)
operator fun Int.times(other: IVec4) = IVec4(this * other.x, this * other.y, this * other.z, this * other.w)
operator fun Int.div(other: IVec4) = IVec4(this / other.x, this / other.y, this / other.z, this / other.w)
operator fun Int.rem(other: IVec4) = IVec4(this % other.x, this % other.y, this % other.z, this % other.w)

fun ivec4(value: Byte) = IVec4(value.toInt())
fun ivec4(x: Byte, y: Byte, z: Byte, w: Byte) = IVec4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
fun ivec4(value: Short) = IVec4(value.toInt())
fun ivec4(x: Short, y: Short, z: Short, w: Short) = IVec4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
fun ivec4(value: Int) = IVec4(value)
fun ivec4(x: Int, y: Int, z: Int, w: Int) = IVec4(x, y, z, w)
fun ivec4(value: Long) = IVec4(value.toInt())
fun ivec4(x: Long, y: Long, z: Long, w: Long) = IVec4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
fun ivec4(value: Float) = IVec4(value.toInt())
fun ivec4(x: Float, y: Float, z: Float, w: Float) = IVec4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
fun ivec4(value: Double) = IVec4(value.toInt())
fun ivec4(x: Double, y: Double, z: Double, w: Double) = IVec4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
fun ivec4() = IVec4()
