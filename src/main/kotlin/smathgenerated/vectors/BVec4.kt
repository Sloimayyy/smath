package com.sloimay.smath.vectors

import java.util.*
import kotlin.math.*

data class BVec4(val x: Byte, val y: Byte, val z: Byte, val w: Byte) {

    companion object {
        fun splat(value: Byte) = BVec4(value, value, value, value)
        fun splat(value: Short) = BVec4(value, value, value, value)
        fun splat(value: Int) = BVec4(value, value, value, value)
        fun splat(value: Long) = BVec4(value, value, value, value)
        fun splat(value: Float) = BVec4(value, value, value, value)
        fun splat(value: Double) = BVec4(value, value, value, value)
        fun new(x: Byte, y: Byte, z: Byte, w: Byte) = BVec4(x, y, z, w)
        fun new(x: Short, y: Short, z: Short, w: Short) = BVec4(x, y, z, w)
        fun new(x: Int, y: Int, z: Int, w: Int) = BVec4(x, y, z, w)
        fun new(x: Long, y: Long, z: Long, w: Long) = BVec4(x, y, z, w)
        fun new(x: Float, y: Float, z: Float, w: Float) = BVec4(x, y, z, w)
        fun new(x: Double, y: Double, z: Double, w: Double) = BVec4(x, y, z, w)
        fun eye(index: Int, value: Byte = 1.toByte()): BVec4 {
            require(index in 0 until 4) { 
                "Index out of bounds of the range [0; 4). (Got ${index})."
            }
            return BVec4(if (index == 0) value else 0.toByte(), if (index == 1) value else 0.toByte(), if (index == 2) value else 0.toByte(), if (index == 3) value else 0.toByte())
        }

        fun fromArray(array: ByteArray): BVec4 {
            require(array.size == 4) { 
                "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected 4, got ${array.size}."
            }
            return BVec4(array[0], array[1], array[2], array[3])
        }

        val X: BVec4 = BVec4(1.toByte(), 0.toByte(), 0.toByte(), 0.toByte())
        val Y: BVec4 = BVec4(0.toByte(), 1.toByte(), 0.toByte(), 0.toByte())
        val Z: BVec4 = BVec4(0.toByte(), 0.toByte(), 1.toByte(), 0.toByte())
        val W: BVec4 = BVec4(0.toByte(), 0.toByte(), 0.toByte(), 1.toByte())
        val ZERO: BVec4 = BVec4(0.toByte())
        val ONE: BVec4 = BVec4(1.toByte())
        val MIN: BVec4 = BVec4(Byte.MIN_VALUE)
        val MAX: BVec4 = BVec4(Byte.MAX_VALUE)
    }

    constructor(value: Byte) : this(value, value, value, value)
    constructor(value: Short) : this(value.toByte(), value.toByte(), value.toByte(), value.toByte())
    constructor(x: Short, y: Short, z: Short, w: Short) : this(x.toByte(), y.toByte(), z.toByte(), w.toByte())
    constructor(value: Int) : this(value.toByte(), value.toByte(), value.toByte(), value.toByte())
    constructor(x: Int, y: Int, z: Int, w: Int) : this(x.toByte(), y.toByte(), z.toByte(), w.toByte())
    constructor(value: Long) : this(value.toByte(), value.toByte(), value.toByte(), value.toByte())
    constructor(x: Long, y: Long, z: Long, w: Long) : this(x.toByte(), y.toByte(), z.toByte(), w.toByte())
    constructor(value: Float) : this(value.toInt().toByte(), value.toInt().toByte(), value.toInt().toByte(), value.toInt().toByte())
    constructor(x: Float, y: Float, z: Float, w: Float) : this(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte(), w.toInt().toByte())
    constructor(value: Double) : this(value.toInt().toByte(), value.toInt().toByte(), value.toInt().toByte(), value.toInt().toByte())
    constructor(x: Double, y: Double, z: Double, w: Double) : this(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte(), w.toInt().toByte())
    constructor() : this(0.toByte(), 0.toByte(), 0.toByte(), 0.toByte())
    constructor(lamb: (Int) -> Byte) : this(lamb(0), lamb(1), lamb(2), lamb(3))

    operator fun plus(other: BVec4) = BVec4((x + other.x).toByte(), (y + other.y).toByte(), (z + other.z).toByte(), (w + other.w).toByte())
    operator fun plus(other: Byte) = BVec4((x + other).toByte(), (y + other).toByte(), (z + other).toByte(), (w + other).toByte())
    operator fun minus(other: BVec4) = BVec4((x - other.x).toByte(), (y - other.y).toByte(), (z - other.z).toByte(), (w - other.w).toByte())
    operator fun minus(other: Byte) = BVec4((x - other).toByte(), (y - other).toByte(), (z - other).toByte(), (w - other).toByte())
    operator fun times(other: BVec4) = BVec4((x * other.x).toByte(), (y * other.y).toByte(), (z * other.z).toByte(), (w * other.w).toByte())
    operator fun times(other: Byte) = BVec4((x * other).toByte(), (y * other).toByte(), (z * other).toByte(), (w * other).toByte())
    operator fun div(other: BVec4) = BVec4((x / other.x).toByte(), (y / other.y).toByte(), (z / other.z).toByte(), (w / other.w).toByte())
    operator fun div(other: Byte) = BVec4((x / other).toByte(), (y / other).toByte(), (z / other).toByte(), (w / other).toByte())
    operator fun rem(other: BVec4) = BVec4((x % other.x).toByte(), (y % other.y).toByte(), (z % other.z).toByte(), (w % other.w).toByte())
    operator fun rem(other: Byte) = BVec4((x % other).toByte(), (y % other).toByte(), (z % other).toByte(), (w % other).toByte())
    operator fun unaryPlus() = this
    operator fun unaryMinus() = BVec4(-x, -y, -z, -w)
    operator fun get(idx: Byte): Byte {
        require(idx in 0 until 4) { 
            "BVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }
    operator fun get(idx: Short): Byte {
        require(idx in 0 until 4) { 
            "BVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }
    operator fun get(idx: Int): Byte {
        require(idx in 0 until 4) { 
            "BVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }
    operator fun get(idx: Long): Byte {
        require(idx in 0 until 4) { 
            "BVec4 indexing failed. Index should be in the range of 0 to 3 (inclusive) but got ${idx}."
        }
        return when (idx.toInt()) { 0 -> x; 1 -> y; 2 -> z; else -> w }
    }

    fun toSVec4() = SVec4(x.toShort(), y.toShort(), z.toShort(), w.toShort())
    fun toIVec4() = IVec4(x.toInt(), y.toInt(), z.toInt(), w.toInt())
    fun toLVec4() = LVec4(x.toLong(), y.toLong(), z.toLong(), w.toLong())
    fun toVec4() = Vec4(x.toFloat(), y.toFloat(), z.toFloat(), w.toFloat())
    fun toDVec4() = DVec4(x.toDouble(), y.toDouble(), z.toDouble(), w.toDouble())

    fun eq(other: BVec4) = x == other.x && y == other.y && z == other.z && w == other.w
    fun iter(): Iterator<Byte> {
        return object : Iterator<Byte> {
            private var idx = 0
            override fun hasNext() = idx < 4
            override fun next() = this@BVec4[idx++]
        }
    }
    fun seq() = iter().asSequence()
    fun abs() = BVec4(abs(x.toInt()).toByte(), abs(y.toInt()).toByte(), abs(z.toInt()).toByte(), abs(w.toInt()).toByte())
    fun mod(value: Byte) = BVec4(x.mod(value), y.mod(value), z.mod(value), w.mod(value))
    fun mod(other: BVec4) = BVec4(x.mod(other.x), y.mod(other.y), z.mod(other.z), w.mod(other.w))
    fun min(other: BVec4) = BVec4(min(x.toInt(), other.x.toInt()).toByte(), min(y.toInt(), other.y.toInt()).toByte(), min(z.toInt(), other.z.toInt()).toByte(), min(w.toInt(), other.w.toInt()).toByte())
    fun max(other: BVec4) = BVec4(max(x.toInt(), other.x.toInt()).toByte(), max(y.toInt(), other.y.toInt()).toByte(), max(z.toInt(), other.z.toInt()).toByte(), max(w.toInt(), other.w.toInt()).toByte())
    fun clamp(low: BVec4, high: BVec4) = BVec4(max(min(x.toInt(), high.x.toInt()), low.x.toInt()).toByte(), max(min(y.toInt(), high.y.toInt()), low.y.toInt()).toByte(), max(min(z.toInt(), high.z.toInt()), low.z.toInt()).toByte(), max(min(w.toInt(), high.w.toInt()), low.w.toInt()).toByte())
    fun dot(other: BVec4) = x * other.x + y * other.y + z * other.z + w * other.w
    fun lenSq() = dot(this)
    fun len() = sqrt(lenSq().toFloat())
    fun dist(other: BVec4) = (this.toVec4() - other.toVec4()).len()
    fun distSq(other: BVec4) = (this.toVec4() - other.toVec4()).lenSq()
    fun normalize() = toVec4() / len()
    fun dir() = toVec4() / len()
    fun elementSum() = ((x.toInt() + y.toInt()) + (z.toInt() + w.toInt())).toByte()
    fun eSum() = ((x.toInt() + y.toInt()) + (z.toInt() + w.toInt())).toByte()
    fun elementProd() = ((x.toInt() * y.toInt()) * (z.toInt() * w.toInt())).toByte()
    fun eProd() = ((x.toInt() * y.toInt()) * (z.toInt() * w.toInt())).toByte()
    fun minElement() = min(min(x.toInt(), y.toInt()), min(z.toInt(), w.toInt())).toByte()
    fun eMin() = min(min(x.toInt(), y.toInt()), min(z.toInt(), w.toInt())).toByte()
    fun maxElement() = max(max(x.toInt(), y.toInt()), max(z.toInt(), w.toInt())).toByte()
    fun eMax() = max(max(x.toInt(), y.toInt()), max(z.toInt(), w.toInt())).toByte()
    fun indexOfMin() = when (eMin()) { x -> 0; y -> 1; z -> 2; else -> 3 }
    fun indexOfMax() = when (eMax()) { x -> 0; y -> 1; z -> 2; else -> 3 }
    fun manhattan(other: BVec4) = (this - other).abs().eSum()
    fun toArray() = byteArrayOf(x, y, z, w)
    fun lerp(other: BVec4, t: Float) = this.toVec4() * (1.0f - t) + other.toVec4() * t
    fun floorDiv(other: BVec4) = BVec4(x.floorDiv(other.x), y.floorDiv(other.y), z.floorDiv(other.z), w.floorDiv(other.w))
    fun floorDiv(other: Byte) = BVec4(x.floorDiv(other), y.floorDiv(other), z.floorDiv(other), w.floorDiv(other))

    infix fun and(other: BVec4) = BVec4(x.toInt() and other.x.toInt(), y.toInt() and other.y.toInt(), z.toInt() and other.z.toInt(), w.toInt() and other.w.toInt())
    infix fun and(other: Byte) = BVec4(x.toInt() and other.toInt(), y.toInt() and other.toInt(), z.toInt() and other.toInt(), w.toInt() and other.toInt())
    infix fun or(other: BVec4) = BVec4(x.toInt() or other.x.toInt(), y.toInt() or other.y.toInt(), z.toInt() or other.z.toInt(), w.toInt() or other.w.toInt())
    infix fun or(other: Byte) = BVec4(x.toInt() or other.toInt(), y.toInt() or other.toInt(), z.toInt() or other.toInt(), w.toInt() or other.toInt())
    infix fun xor(other: BVec4) = BVec4(x.toInt() xor other.x.toInt(), y.toInt() xor other.y.toInt(), z.toInt() xor other.z.toInt(), w.toInt() xor other.w.toInt())
    infix fun xor(other: Byte) = BVec4(x.toInt() xor other.toInt(), y.toInt() xor other.toInt(), z.toInt() xor other.toInt(), w.toInt() xor other.toInt())
    infix fun shl(other: BVec4) = BVec4(x.toInt() shl other.x.toInt(), y.toInt() shl other.y.toInt(), z.toInt() shl other.z.toInt(), w.toInt() shl other.w.toInt())
    infix fun shl(other: Byte) = BVec4(x.toInt() shl other.toInt(), y.toInt() shl other.toInt(), z.toInt() shl other.toInt(), w.toInt() shl other.toInt())
    infix fun shr(other: BVec4) = BVec4(x.toInt() shr other.x.toInt(), y.toInt() shr other.y.toInt(), z.toInt() shr other.z.toInt(), w.toInt() shr other.w.toInt())
    infix fun shr(other: Byte) = BVec4(x.toInt() shr other.toInt(), y.toInt() shr other.toInt(), z.toInt() shr other.toInt(), w.toInt() shr other.toInt())
    infix fun ushr(other: BVec4) = BVec4(x.toInt() ushr other.x.toInt(), y.toInt() ushr other.y.toInt(), z.toInt() ushr other.z.toInt(), w.toInt() ushr other.w.toInt())
    infix fun ushr(other: Byte) = BVec4(x.toInt() ushr other.toInt(), y.toInt() ushr other.toInt(), z.toInt() ushr other.toInt(), w.toInt() ushr other.toInt())
    fun inv() = BVec4(x.toInt().inv(), y.toInt().inv(), z.toInt().inv(), w.toInt().inv())

    fun withElement(elementIdx: Int, value: Byte) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Short) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Int) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Long) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Float) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
    fun withElement(elementIdx: Int, value: Double) = when (elementIdx) { 0 -> withX(value); 1 -> withY(value); 2 -> withZ(value); else -> withW(value) }
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
    fun withX(value: Byte) = BVec4(value, y, z, w)
    fun withX(value: Short) = BVec4(value.toByte(), y, z, w)
    fun withX(value: Int) = BVec4(value.toByte(), y, z, w)
    fun withX(value: Long) = BVec4(value.toByte(), y, z, w)
    fun withX(value: Float) = BVec4(value.toInt().toByte(), y, z, w)
    fun withX(value: Double) = BVec4(value.toInt().toByte(), y, z, w)
    fun withY(value: Byte) = BVec4(x, value, z, w)
    fun withY(value: Short) = BVec4(x, value.toByte(), z, w)
    fun withY(value: Int) = BVec4(x, value.toByte(), z, w)
    fun withY(value: Long) = BVec4(x, value.toByte(), z, w)
    fun withY(value: Float) = BVec4(x, value.toInt().toByte(), z, w)
    fun withY(value: Double) = BVec4(x, value.toInt().toByte(), z, w)
    fun withZ(value: Byte) = BVec4(x, y, value, w)
    fun withZ(value: Short) = BVec4(x, y, value.toByte(), w)
    fun withZ(value: Int) = BVec4(x, y, value.toByte(), w)
    fun withZ(value: Long) = BVec4(x, y, value.toByte(), w)
    fun withZ(value: Float) = BVec4(x, y, value.toInt().toByte(), w)
    fun withZ(value: Double) = BVec4(x, y, value.toInt().toByte(), w)
    fun withW(value: Byte) = BVec4(x, y, z, value)
    fun withW(value: Short) = BVec4(x, y, z, value.toByte())
    fun withW(value: Int) = BVec4(x, y, z, value.toByte())
    fun withW(value: Long) = BVec4(x, y, z, value.toByte())
    fun withW(value: Float) = BVec4(x, y, z, value.toInt().toByte())
    fun withW(value: Double) = BVec4(x, y, z, value.toInt().toByte())

    val xx get() = BVec2(x, x)
    val xy get() = BVec2(x, y)
    val xz get() = BVec2(x, z)
    val xw get() = BVec2(x, w)
    val yx get() = BVec2(y, x)
    val yy get() = BVec2(y, y)
    val yz get() = BVec2(y, z)
    val yw get() = BVec2(y, w)
    val zx get() = BVec2(z, x)
    val zy get() = BVec2(z, y)
    val zz get() = BVec2(z, z)
    val zw get() = BVec2(z, w)
    val wx get() = BVec2(w, x)
    val wy get() = BVec2(w, y)
    val wz get() = BVec2(w, z)
    val ww get() = BVec2(w, w)
    val xxx get() = BVec3(x, x, x)
    val xxy get() = BVec3(x, x, y)
    val xxz get() = BVec3(x, x, z)
    val xxw get() = BVec3(x, x, w)
    val xyx get() = BVec3(x, y, x)
    val xyy get() = BVec3(x, y, y)
    val xyz get() = BVec3(x, y, z)
    val xyw get() = BVec3(x, y, w)
    val xzx get() = BVec3(x, z, x)
    val xzy get() = BVec3(x, z, y)
    val xzz get() = BVec3(x, z, z)
    val xzw get() = BVec3(x, z, w)
    val xwx get() = BVec3(x, w, x)
    val xwy get() = BVec3(x, w, y)
    val xwz get() = BVec3(x, w, z)
    val xww get() = BVec3(x, w, w)
    val yxx get() = BVec3(y, x, x)
    val yxy get() = BVec3(y, x, y)
    val yxz get() = BVec3(y, x, z)
    val yxw get() = BVec3(y, x, w)
    val yyx get() = BVec3(y, y, x)
    val yyy get() = BVec3(y, y, y)
    val yyz get() = BVec3(y, y, z)
    val yyw get() = BVec3(y, y, w)
    val yzx get() = BVec3(y, z, x)
    val yzy get() = BVec3(y, z, y)
    val yzz get() = BVec3(y, z, z)
    val yzw get() = BVec3(y, z, w)
    val ywx get() = BVec3(y, w, x)
    val ywy get() = BVec3(y, w, y)
    val ywz get() = BVec3(y, w, z)
    val yww get() = BVec3(y, w, w)
    val zxx get() = BVec3(z, x, x)
    val zxy get() = BVec3(z, x, y)
    val zxz get() = BVec3(z, x, z)
    val zxw get() = BVec3(z, x, w)
    val zyx get() = BVec3(z, y, x)
    val zyy get() = BVec3(z, y, y)
    val zyz get() = BVec3(z, y, z)
    val zyw get() = BVec3(z, y, w)
    val zzx get() = BVec3(z, z, x)
    val zzy get() = BVec3(z, z, y)
    val zzz get() = BVec3(z, z, z)
    val zzw get() = BVec3(z, z, w)
    val zwx get() = BVec3(z, w, x)
    val zwy get() = BVec3(z, w, y)
    val zwz get() = BVec3(z, w, z)
    val zww get() = BVec3(z, w, w)
    val wxx get() = BVec3(w, x, x)
    val wxy get() = BVec3(w, x, y)
    val wxz get() = BVec3(w, x, z)
    val wxw get() = BVec3(w, x, w)
    val wyx get() = BVec3(w, y, x)
    val wyy get() = BVec3(w, y, y)
    val wyz get() = BVec3(w, y, z)
    val wyw get() = BVec3(w, y, w)
    val wzx get() = BVec3(w, z, x)
    val wzy get() = BVec3(w, z, y)
    val wzz get() = BVec3(w, z, z)
    val wzw get() = BVec3(w, z, w)
    val wwx get() = BVec3(w, w, x)
    val wwy get() = BVec3(w, w, y)
    val wwz get() = BVec3(w, w, z)
    val www get() = BVec3(w, w, w)
    val xxxx get() = BVec4(x, x, x, x)
    val xxxy get() = BVec4(x, x, x, y)
    val xxxz get() = BVec4(x, x, x, z)
    val xxxw get() = BVec4(x, x, x, w)
    val xxyx get() = BVec4(x, x, y, x)
    val xxyy get() = BVec4(x, x, y, y)
    val xxyz get() = BVec4(x, x, y, z)
    val xxyw get() = BVec4(x, x, y, w)
    val xxzx get() = BVec4(x, x, z, x)
    val xxzy get() = BVec4(x, x, z, y)
    val xxzz get() = BVec4(x, x, z, z)
    val xxzw get() = BVec4(x, x, z, w)
    val xxwx get() = BVec4(x, x, w, x)
    val xxwy get() = BVec4(x, x, w, y)
    val xxwz get() = BVec4(x, x, w, z)
    val xxww get() = BVec4(x, x, w, w)
    val xyxx get() = BVec4(x, y, x, x)
    val xyxy get() = BVec4(x, y, x, y)
    val xyxz get() = BVec4(x, y, x, z)
    val xyxw get() = BVec4(x, y, x, w)
    val xyyx get() = BVec4(x, y, y, x)
    val xyyy get() = BVec4(x, y, y, y)
    val xyyz get() = BVec4(x, y, y, z)
    val xyyw get() = BVec4(x, y, y, w)
    val xyzx get() = BVec4(x, y, z, x)
    val xyzy get() = BVec4(x, y, z, y)
    val xyzz get() = BVec4(x, y, z, z)
    val xyzw get() = BVec4(x, y, z, w)
    val xywx get() = BVec4(x, y, w, x)
    val xywy get() = BVec4(x, y, w, y)
    val xywz get() = BVec4(x, y, w, z)
    val xyww get() = BVec4(x, y, w, w)
    val xzxx get() = BVec4(x, z, x, x)
    val xzxy get() = BVec4(x, z, x, y)
    val xzxz get() = BVec4(x, z, x, z)
    val xzxw get() = BVec4(x, z, x, w)
    val xzyx get() = BVec4(x, z, y, x)
    val xzyy get() = BVec4(x, z, y, y)
    val xzyz get() = BVec4(x, z, y, z)
    val xzyw get() = BVec4(x, z, y, w)
    val xzzx get() = BVec4(x, z, z, x)
    val xzzy get() = BVec4(x, z, z, y)
    val xzzz get() = BVec4(x, z, z, z)
    val xzzw get() = BVec4(x, z, z, w)
    val xzwx get() = BVec4(x, z, w, x)
    val xzwy get() = BVec4(x, z, w, y)
    val xzwz get() = BVec4(x, z, w, z)
    val xzww get() = BVec4(x, z, w, w)
    val xwxx get() = BVec4(x, w, x, x)
    val xwxy get() = BVec4(x, w, x, y)
    val xwxz get() = BVec4(x, w, x, z)
    val xwxw get() = BVec4(x, w, x, w)
    val xwyx get() = BVec4(x, w, y, x)
    val xwyy get() = BVec4(x, w, y, y)
    val xwyz get() = BVec4(x, w, y, z)
    val xwyw get() = BVec4(x, w, y, w)
    val xwzx get() = BVec4(x, w, z, x)
    val xwzy get() = BVec4(x, w, z, y)
    val xwzz get() = BVec4(x, w, z, z)
    val xwzw get() = BVec4(x, w, z, w)
    val xwwx get() = BVec4(x, w, w, x)
    val xwwy get() = BVec4(x, w, w, y)
    val xwwz get() = BVec4(x, w, w, z)
    val xwww get() = BVec4(x, w, w, w)
    val yxxx get() = BVec4(y, x, x, x)
    val yxxy get() = BVec4(y, x, x, y)
    val yxxz get() = BVec4(y, x, x, z)
    val yxxw get() = BVec4(y, x, x, w)
    val yxyx get() = BVec4(y, x, y, x)
    val yxyy get() = BVec4(y, x, y, y)
    val yxyz get() = BVec4(y, x, y, z)
    val yxyw get() = BVec4(y, x, y, w)
    val yxzx get() = BVec4(y, x, z, x)
    val yxzy get() = BVec4(y, x, z, y)
    val yxzz get() = BVec4(y, x, z, z)
    val yxzw get() = BVec4(y, x, z, w)
    val yxwx get() = BVec4(y, x, w, x)
    val yxwy get() = BVec4(y, x, w, y)
    val yxwz get() = BVec4(y, x, w, z)
    val yxww get() = BVec4(y, x, w, w)
    val yyxx get() = BVec4(y, y, x, x)
    val yyxy get() = BVec4(y, y, x, y)
    val yyxz get() = BVec4(y, y, x, z)
    val yyxw get() = BVec4(y, y, x, w)
    val yyyx get() = BVec4(y, y, y, x)
    val yyyy get() = BVec4(y, y, y, y)
    val yyyz get() = BVec4(y, y, y, z)
    val yyyw get() = BVec4(y, y, y, w)
    val yyzx get() = BVec4(y, y, z, x)
    val yyzy get() = BVec4(y, y, z, y)
    val yyzz get() = BVec4(y, y, z, z)
    val yyzw get() = BVec4(y, y, z, w)
    val yywx get() = BVec4(y, y, w, x)
    val yywy get() = BVec4(y, y, w, y)
    val yywz get() = BVec4(y, y, w, z)
    val yyww get() = BVec4(y, y, w, w)
    val yzxx get() = BVec4(y, z, x, x)
    val yzxy get() = BVec4(y, z, x, y)
    val yzxz get() = BVec4(y, z, x, z)
    val yzxw get() = BVec4(y, z, x, w)
    val yzyx get() = BVec4(y, z, y, x)
    val yzyy get() = BVec4(y, z, y, y)
    val yzyz get() = BVec4(y, z, y, z)
    val yzyw get() = BVec4(y, z, y, w)
    val yzzx get() = BVec4(y, z, z, x)
    val yzzy get() = BVec4(y, z, z, y)
    val yzzz get() = BVec4(y, z, z, z)
    val yzzw get() = BVec4(y, z, z, w)
    val yzwx get() = BVec4(y, z, w, x)
    val yzwy get() = BVec4(y, z, w, y)
    val yzwz get() = BVec4(y, z, w, z)
    val yzww get() = BVec4(y, z, w, w)
    val ywxx get() = BVec4(y, w, x, x)
    val ywxy get() = BVec4(y, w, x, y)
    val ywxz get() = BVec4(y, w, x, z)
    val ywxw get() = BVec4(y, w, x, w)
    val ywyx get() = BVec4(y, w, y, x)
    val ywyy get() = BVec4(y, w, y, y)
    val ywyz get() = BVec4(y, w, y, z)
    val ywyw get() = BVec4(y, w, y, w)
    val ywzx get() = BVec4(y, w, z, x)
    val ywzy get() = BVec4(y, w, z, y)
    val ywzz get() = BVec4(y, w, z, z)
    val ywzw get() = BVec4(y, w, z, w)
    val ywwx get() = BVec4(y, w, w, x)
    val ywwy get() = BVec4(y, w, w, y)
    val ywwz get() = BVec4(y, w, w, z)
    val ywww get() = BVec4(y, w, w, w)
    val zxxx get() = BVec4(z, x, x, x)
    val zxxy get() = BVec4(z, x, x, y)
    val zxxz get() = BVec4(z, x, x, z)
    val zxxw get() = BVec4(z, x, x, w)
    val zxyx get() = BVec4(z, x, y, x)
    val zxyy get() = BVec4(z, x, y, y)
    val zxyz get() = BVec4(z, x, y, z)
    val zxyw get() = BVec4(z, x, y, w)
    val zxzx get() = BVec4(z, x, z, x)
    val zxzy get() = BVec4(z, x, z, y)
    val zxzz get() = BVec4(z, x, z, z)
    val zxzw get() = BVec4(z, x, z, w)
    val zxwx get() = BVec4(z, x, w, x)
    val zxwy get() = BVec4(z, x, w, y)
    val zxwz get() = BVec4(z, x, w, z)
    val zxww get() = BVec4(z, x, w, w)
    val zyxx get() = BVec4(z, y, x, x)
    val zyxy get() = BVec4(z, y, x, y)
    val zyxz get() = BVec4(z, y, x, z)
    val zyxw get() = BVec4(z, y, x, w)
    val zyyx get() = BVec4(z, y, y, x)
    val zyyy get() = BVec4(z, y, y, y)
    val zyyz get() = BVec4(z, y, y, z)
    val zyyw get() = BVec4(z, y, y, w)
    val zyzx get() = BVec4(z, y, z, x)
    val zyzy get() = BVec4(z, y, z, y)
    val zyzz get() = BVec4(z, y, z, z)
    val zyzw get() = BVec4(z, y, z, w)
    val zywx get() = BVec4(z, y, w, x)
    val zywy get() = BVec4(z, y, w, y)
    val zywz get() = BVec4(z, y, w, z)
    val zyww get() = BVec4(z, y, w, w)
    val zzxx get() = BVec4(z, z, x, x)
    val zzxy get() = BVec4(z, z, x, y)
    val zzxz get() = BVec4(z, z, x, z)
    val zzxw get() = BVec4(z, z, x, w)
    val zzyx get() = BVec4(z, z, y, x)
    val zzyy get() = BVec4(z, z, y, y)
    val zzyz get() = BVec4(z, z, y, z)
    val zzyw get() = BVec4(z, z, y, w)
    val zzzx get() = BVec4(z, z, z, x)
    val zzzy get() = BVec4(z, z, z, y)
    val zzzz get() = BVec4(z, z, z, z)
    val zzzw get() = BVec4(z, z, z, w)
    val zzwx get() = BVec4(z, z, w, x)
    val zzwy get() = BVec4(z, z, w, y)
    val zzwz get() = BVec4(z, z, w, z)
    val zzww get() = BVec4(z, z, w, w)
    val zwxx get() = BVec4(z, w, x, x)
    val zwxy get() = BVec4(z, w, x, y)
    val zwxz get() = BVec4(z, w, x, z)
    val zwxw get() = BVec4(z, w, x, w)
    val zwyx get() = BVec4(z, w, y, x)
    val zwyy get() = BVec4(z, w, y, y)
    val zwyz get() = BVec4(z, w, y, z)
    val zwyw get() = BVec4(z, w, y, w)
    val zwzx get() = BVec4(z, w, z, x)
    val zwzy get() = BVec4(z, w, z, y)
    val zwzz get() = BVec4(z, w, z, z)
    val zwzw get() = BVec4(z, w, z, w)
    val zwwx get() = BVec4(z, w, w, x)
    val zwwy get() = BVec4(z, w, w, y)
    val zwwz get() = BVec4(z, w, w, z)
    val zwww get() = BVec4(z, w, w, w)
    val wxxx get() = BVec4(w, x, x, x)
    val wxxy get() = BVec4(w, x, x, y)
    val wxxz get() = BVec4(w, x, x, z)
    val wxxw get() = BVec4(w, x, x, w)
    val wxyx get() = BVec4(w, x, y, x)
    val wxyy get() = BVec4(w, x, y, y)
    val wxyz get() = BVec4(w, x, y, z)
    val wxyw get() = BVec4(w, x, y, w)
    val wxzx get() = BVec4(w, x, z, x)
    val wxzy get() = BVec4(w, x, z, y)
    val wxzz get() = BVec4(w, x, z, z)
    val wxzw get() = BVec4(w, x, z, w)
    val wxwx get() = BVec4(w, x, w, x)
    val wxwy get() = BVec4(w, x, w, y)
    val wxwz get() = BVec4(w, x, w, z)
    val wxww get() = BVec4(w, x, w, w)
    val wyxx get() = BVec4(w, y, x, x)
    val wyxy get() = BVec4(w, y, x, y)
    val wyxz get() = BVec4(w, y, x, z)
    val wyxw get() = BVec4(w, y, x, w)
    val wyyx get() = BVec4(w, y, y, x)
    val wyyy get() = BVec4(w, y, y, y)
    val wyyz get() = BVec4(w, y, y, z)
    val wyyw get() = BVec4(w, y, y, w)
    val wyzx get() = BVec4(w, y, z, x)
    val wyzy get() = BVec4(w, y, z, y)
    val wyzz get() = BVec4(w, y, z, z)
    val wyzw get() = BVec4(w, y, z, w)
    val wywx get() = BVec4(w, y, w, x)
    val wywy get() = BVec4(w, y, w, y)
    val wywz get() = BVec4(w, y, w, z)
    val wyww get() = BVec4(w, y, w, w)
    val wzxx get() = BVec4(w, z, x, x)
    val wzxy get() = BVec4(w, z, x, y)
    val wzxz get() = BVec4(w, z, x, z)
    val wzxw get() = BVec4(w, z, x, w)
    val wzyx get() = BVec4(w, z, y, x)
    val wzyy get() = BVec4(w, z, y, y)
    val wzyz get() = BVec4(w, z, y, z)
    val wzyw get() = BVec4(w, z, y, w)
    val wzzx get() = BVec4(w, z, z, x)
    val wzzy get() = BVec4(w, z, z, y)
    val wzzz get() = BVec4(w, z, z, z)
    val wzzw get() = BVec4(w, z, z, w)
    val wzwx get() = BVec4(w, z, w, x)
    val wzwy get() = BVec4(w, z, w, y)
    val wzwz get() = BVec4(w, z, w, z)
    val wzww get() = BVec4(w, z, w, w)
    val wwxx get() = BVec4(w, w, x, x)
    val wwxy get() = BVec4(w, w, x, y)
    val wwxz get() = BVec4(w, w, x, z)
    val wwxw get() = BVec4(w, w, x, w)
    val wwyx get() = BVec4(w, w, y, x)
    val wwyy get() = BVec4(w, w, y, y)
    val wwyz get() = BVec4(w, w, y, z)
    val wwyw get() = BVec4(w, w, y, w)
    val wwzx get() = BVec4(w, w, z, x)
    val wwzy get() = BVec4(w, w, z, y)
    val wwzz get() = BVec4(w, w, z, z)
    val wwzw get() = BVec4(w, w, z, w)
    val wwwx get() = BVec4(w, w, w, x)
    val wwwy get() = BVec4(w, w, w, y)
    val wwwz get() = BVec4(w, w, w, z)
    val wwww get() = BVec4(w, w, w, w)

    override fun toString(): String {
        return ("BVec4(" + 
                "x=$x, " + 
                "y=$y, " + 
                "z=$z, " + 
                "w=$w)")
    }

}

operator fun Byte.plus(other: BVec4) = BVec4((this + other.x).toByte(), (this + other.y).toByte(), (this + other.z).toByte(), (this + other.w).toByte())
operator fun Byte.minus(other: BVec4) = BVec4((this - other.x).toByte(), (this - other.y).toByte(), (this - other.z).toByte(), (this - other.w).toByte())
operator fun Byte.times(other: BVec4) = BVec4((this * other.x).toByte(), (this * other.y).toByte(), (this * other.z).toByte(), (this * other.w).toByte())
operator fun Byte.div(other: BVec4) = BVec4((this / other.x).toByte(), (this / other.y).toByte(), (this / other.z).toByte(), (this / other.w).toByte())
operator fun Byte.rem(other: BVec4) = BVec4((this % other.x).toByte(), (this % other.y).toByte(), (this % other.z).toByte(), (this % other.w).toByte())

fun bvec4(value: Byte) = BVec4(value)
fun bvec4(x: Byte, y: Byte, z: Byte, w: Byte) = BVec4(x, y, z, w)
fun bvec4(value: Short) = BVec4(value.toByte())
fun bvec4(x: Short, y: Short, z: Short, w: Short) = BVec4(x.toByte(), y.toByte(), z.toByte(), w.toByte())
fun bvec4(value: Int) = BVec4(value.toByte())
fun bvec4(x: Int, y: Int, z: Int, w: Int) = BVec4(x.toByte(), y.toByte(), z.toByte(), w.toByte())
fun bvec4(value: Long) = BVec4(value.toByte())
fun bvec4(x: Long, y: Long, z: Long, w: Long) = BVec4(x.toByte(), y.toByte(), z.toByte(), w.toByte())
fun bvec4(value: Float) = BVec4(value.toInt().toByte())
fun bvec4(x: Float, y: Float, z: Float, w: Float) = BVec4(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte(), w.toInt().toByte())
fun bvec4(value: Double) = BVec4(value.toInt().toByte())
fun bvec4(x: Double, y: Double, z: Double, w: Double) = BVec4(x.toInt().toByte(), y.toInt().toByte(), z.toInt().toByte(), w.toInt().toByte())
fun bvec4() = BVec4()
