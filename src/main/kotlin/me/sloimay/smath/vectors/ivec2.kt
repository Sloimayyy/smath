package me.sloimay.smath.vectors
import me.sloimay.smath.vectors.Vec2.Companion
import kotlin.math.*

data class IVec2(val x: Int, val y: Int) {

    companion object {
        val ZERO = new(0, 0)
        val ONE = new(1, 1)
        val X = new(1, 0)
        val Y = new(0, 1)
        val MIN = splat(Int.MIN_VALUE)
        val MAX = splat(Int.MAX_VALUE)

        fun new(x: Int, y: Int) = IVec2(x, y)
        fun splat(v: Int) = IVec2(v, v)

        private fun hashInt(i: Int): Int {
            var v = i
            v = v xor (v shl 13)
            v = v xor (v ushr 13)
            v = v xor (v shl 5)
            return v
        }
    }

    operator fun plus(other: IVec2) = new(this.x + other.x, this.y + other.y)
    operator fun plus(other: Int) = new(this.x + other, this.y + other)
    operator fun minus(other: IVec2) = new(this.x - other.x, this.y - other.y)
    operator fun minus(other: Int) = new(this.x - other, this.y - other)
    operator fun unaryMinus() = new(-this.x, -this.y)
    operator fun unaryPlus() = this
    operator fun times(other: IVec2) = new(this.x * other.x, this.y * other.y)
    operator fun times(other: Int) = new(this.x * other, this.y * other)
    operator fun div(other: IVec2) = new(this.x / other.x, this.y / other.y)
    operator fun div(other: Int) = new(this.x / other, this.y / other)
    operator fun rem(other: IVec2) = new(this.x % other.x, this.y % other.y)
    operator fun rem(other: Int) = new(this.x % other, this.y % other)
    operator fun get(idx: UByte) =
        when (idx) {
            0.toUByte() -> this.x
            else -> this.y
        }
    operator fun get(idx: UShort) = this[idx.toUByte()]
    operator fun get(idx: Short) = this[idx.toUByte()]
    operator fun get(idx: UInt) = this[idx.toUByte()]
    operator fun get(idx: Int) = this[idx.toUByte()]
    operator fun get(idx: Long) = this[idx.toUByte()]
    operator fun get(idx: ULong) = this[idx.toUByte()]

    infix fun shr(other: IVec2) = new(this.x shr other.x, this.y shr other.y)
    infix fun shr(other: Int) = new(this.x shr other, this.y shr other)
    infix fun shl(other: IVec2) = new(this.x shl other.x, this.y shl other.y)
    infix fun shl(other: Int) = new(this.x shl other, this.y shl other)
    infix fun and(other: IVec2) = new(this.x and other.x, this.y and other.y)
    infix fun and(other: Int) = new(this.x and other, this.y and other)

    fun asVec2() = Vec2.new(this.x.toFloat(), this.y.toFloat())

    fun equality(other: IVec2) = this.x == other.x && this.y == other.y
    fun abs() = new(abs(this.x), abs(this.y))
    fun withX(v: Int) = new(v, this.y)
    fun withY(v: Int) = new(this.x, v)
    fun max(other: IVec2) = new(max(this.x, other.x), max(this.y, other.y))
    fun min(other: IVec2) = new(min(this.x, other.x), min(this.y, other.y))
    fun clamp(min: IVec2, max: IVec2) = this.max(min).min(max)
    fun dot(other: IVec2) = this.x * other.x + this.y * other.y
    fun lengthSqrd() = this.dot(this)
    fun distSqrd(other: IVec2) = (this - other).lengthSqrd()
    fun floorDiv(other: IVec2) = new(this.x.floorDiv(other.x), this.y.floorDiv(other.y))
    fun elementProduct() = x * y
    fun maxElement() = max(x, y)
    fun copy() = new(x, y)

    fun cross(other: IVec2) = IVec3.new(
        0,
        0,
        this.x * other.y - other.x * this.y,
    )

    fun longestAxis(): Int {
        val maxEl = maxElement()
        return if (x == maxEl) {
            0
        } else {
            1
        }
    }

    fun rotate90(): IVec2 {
        val c = 0
        val s = 1
        return new(
            this.x * c - this.y * s,
            this.x * s + this.y * c,
        )
    }

    fun rotateM90(): IVec2 {
        val c = 0
        val s = -1
        return new(
            this.x * c - this.y * s,
            this.x * s + this.y * c,
        )
    }



    fun toArray(): IntArray {
        return intArrayOf(x, y)
    }


    override fun hashCode(): Int {
        var hash = 17
        hash = hash * 31 + hashInt(x)
        hash = hash * 31 + hashInt(y)
        return hash
    }

    override fun toString(): String {
        return "IVec2(" +
                "x=$x " +
                "y=$y " +
                ")"
    }
}


fun ivec2(x: Int, y: Int) = IVec2.new(x, y)