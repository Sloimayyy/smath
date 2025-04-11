package com.sloimay.smath.vectors

/*data class Vec2(val x: Float, val y: Float) {

    companion object {
        val ZERO = new(0f, 0f)
        val ONE = new(1f, 1f)
        val X = new(1f, 0f)
        val Y = new(0f, 1f)
        val INF = splat(Float.POSITIVE_INFINITY)
        val NEG_INF = splat(Float.NEGATIVE_INFINITY)
        val MIN = splat(Float.MIN_VALUE)
        val MAX = splat(Float.MAX_VALUE)

        fun new(x: Float, y: Float) = Vec2(x, y)
        fun splat(v: Float) = Vec2(v, v)

        fun lerp(a: Vec2, b: Vec2, t: Float) = a.lerp(b, t)
        fun fromAngle(angle: Float) = new(cos(angle), sin(angle))
    }

    operator fun plus(other: Vec2)  = new(this.x + other.x, this.y + other.y)
    operator fun plus(other: Float) = new(this.x + other, this.y + other)
    operator fun minus(other: Vec2) =  new(this.x - other.x, this.y - other.y)
    operator fun minus(other: Float) = new(this.x - other, this.y - other)
    operator fun unaryMinus() = new(-this.x, -this.y)
    operator fun unaryPlus() = this
    operator fun times(other: Vec2)  = new(this.x * other.x, this.y * other.y)
    operator fun times(other: Float) = new(this.x * other, this.y * other)
    operator fun div(other: Vec2)  = new(this.x / other.x, this.y / other.y)
    operator fun div(other: Float) = new(this.x / other, this.y / other)
    operator fun rem(other: Vec2)  = new(this.x % other.x, this.y % other.y)
    operator fun rem(other: Float) = new(this.x % other, this.y % other)
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

    fun equality(other: Vec2) = this.x == other.x && this.y == other.y
    fun floor() = new(kotlin.math.floor(this.x), kotlin.math.floor(this.y))
    fun ceil() = new(kotlin.math.ceil(this.x), kotlin.math.ceil(this.y))
    fun round() = new(kotlin.math.round(this.x), kotlin.math.round(this.y))
    fun abs() = new(kotlin.math.abs(this.x), kotlin.math.abs(this.y))
    fun withX(v: Float) = new(v, this.y)
    fun withY(v: Float) = new(this.x, v)
    fun max(other: Vec2) = new(
        kotlin.math.max(this.x, other.x),
        kotlin.math.max(this.y, other.y),
    )
    fun min(other: Vec2) = new(
        kotlin.math.min(this.x, other.x),
        kotlin.math.min(this.y, other.y),
    )
    fun clamp(min: Vec2, max: Vec2) = this.max(min).min(max)
    fun fract() = this - this.floor()
    fun dot(other: Vec2) = this.x * other.x + this.y * other.y
    fun lengthSqrd() = this.dot(this)
    fun length() = sqrt(this.dot(this))
    fun dist(other: Vec2) = (this - other).length()
    fun distSqrd(other: Vec2) = (this - other).lengthSqrd()
    fun normalize() = this * (1f / this.length())
    fun lerp(other: Vec2, t: Float) = this * (1f - t) + other * t
    fun extend(z: Float) = Vec3Ext.new(x, y, z)
    fun elementSum() = x + y

    fun asIVec2() = IVec2.new(this.x.toInt(), this.y.toInt())

    fun cross(other: Vec2) = Vec3Ext.new(
        0f,
        0f,
        this.x * other.y - other.x * this.y,
    )
    fun rotate(angle: Float): Vec2 {
        val c = cos(angle)
        val s = sin(angle)
        return new(
            this.x * c - this.y * s,
            this.x * s + this.y * c,
        )
    }


    override fun toString(): String {
        return "Vec2(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}" +
                ")"
    }
}

fun vec2(x: Float, y: Float) = Vec2.new(x, y)*/