package com.sloimay.smath.vectors

data class DVec3(val x: Double, val y: Double, val z: Double) {
    companion object
}

/*data class DVec3(val x: Double, val y: Double, val z: Double) {

    companion object {
        val ZERO = new(0, 0, 0)
        val ONE = new(1, 1, 1)
        val X = new(1, 0, 0)
        val Y = new(0, 1, 0)
        val Z = new(0, 0, 1)
        val INF = splat(Double.POSITIVE_INFINITY)
        val NEG_INF = splat(Double.NEGATIVE_INFINITY)
        val MIN = splat(Double.MIN_VALUE)
        val MAX = splat(Double.MAX_VALUE)

        fun new(x: Double, y: Double, z: Double) = DVec3(x, y, z)
        fun new(x: Int, y: Int, z: Int) = DVec3(x.toDouble(), y.toDouble(), z.toDouble())
        fun splat(v: Double) = DVec3(v, v, v)

        fun fromArray(arr: DoubleArray) = new(arr[0], arr[1], arr[2])

        fun lerp(a: DVec3, b: DVec3, t: Double) = a.lerp(b, t)
    }

    operator fun plus(other: DVec3)  = new(this.x + other.x, this.y + other.y, this.z + other.z)
    operator fun plus(other: Double) = new(this.x + other, this.y + other, this.z + other)
    operator fun minus(other: DVec3) =  new(this.x - other.x, this.y - other.y, this.z - other.z)
    operator fun minus(other: Double) = new(this.x - other, this.y - other, this.z - other)
    operator fun unaryMinus() = new(-this.x, -this.y, -this.z)
    operator fun unaryPlus() = this
    operator fun times(other: DVec3)  = new(this.x * other.x, this.y * other.y, this.z * other.z)
    operator fun times(other: Double) = new(this.x * other, this.y * other, this.z * other)
    operator fun div(other: DVec3)  = new(this.x / other.x, this.y / other.y, this.z / other.z)
    operator fun div(other: Double) = new(this.x / other, this.y / other, this.z / other)
    operator fun rem(other: DVec3)  = new(this.x % other.x, this.y % other.y, this.z % other.z)
    operator fun rem(other: Double) = new(this.x % other, this.y % other, this.z % other)
    operator fun get(idx: UByte) =
        when (idx) {
            0.toUByte() -> this.x
            1.toUByte() -> this.y
            else -> this.z
        }
    operator fun get(idx: UShort) = this[idx.toUByte()]
    operator fun get(idx: Short) = this[idx.toUByte()]
    operator fun get(idx: UInt) = this[idx.toUByte()]
    operator fun get(idx: Int) = this[idx.toUByte()]
    operator fun get(idx: Long) = this[idx.toUByte()]
    operator fun get(idx: ULong) = this[idx.toUByte()]

    fun equality(other: DVec3) = this.x == other.x && this.y == other.y && this.z == other.z
    fun floor() = new(floor(this.x), floor(this.y), floor(this.z))
    fun ceil() = new(ceil(this.x), ceil(this.y), ceil(this.z))
    fun round() = new(round(this.x), round(this.y), round(this.z))
    fun abs() = new(abs(this.x), abs(this.y), abs(this.z))
    fun withX(v: Double) = new(v, this.y, this.z)
    fun withY(v: Double) = new(this.x, v, this.z)
    fun withZ(v: Double) = new(this.x, this.y, v)
    fun max(other: DVec3) = new(max(this.x, other.x), max(this.y, other.y), max(this.z, other.z))
    fun min(other: DVec3) = new(min(this.x, other.x), min(this.y, other.y), min(this.z, other.z))
    fun clamp(min: DVec3, max: DVec3) = this.max(min).min(max)
    fun fract() = this - this.floor()
    fun dot(other: DVec3) = this.x * other.x + this.y * other.y + this.z * other.z
    fun lengthSqrd() = this.dot(this)
    fun length() = sqrt(this.dot(this))
    fun dist(other: DVec3) = (this - other).length()
    fun distSqrd(other: DVec3) = (this - other).lengthSqrd()
    fun normalize() = this * (1f / this.length())
    fun lerp(other: DVec3, t: Double) = this * (1f - t) + other * t
    fun extend(w: Double) = DVec4.new(x, y, z, w)
    fun maxElement() = max(x, max(y, z))
    fun elementSum() = x + y + z
    fun setLength(length: Double) = this.normalize() * length

    fun asIVec3() = IVec3.new(this.x.toInt(), this.y.toInt(), this.z.toInt())
    fun asVec3() = Vec3.new(this.x.toFloat(), this.y.toFloat(), this.z.toFloat())

    fun cross(other: DVec3) = new(
        this.y * other.z - other.y * this.z,
        this.z * other.x - other.z * this.x,
        this.x * other.y - other.x * this.y,
    )
    fun anyOrthonormalPair(): Pair<DVec3, DVec3> {
        // From https://graphics.pixar.com/library/OrthonormalB/paper.pdf
        var sign = z.sign;
        if (z == 0.0) { sign = 1.0 }
        else if (z == -0.0) { sign = -1.0 }
        val a = -1.0f / (sign + z)
        val b = x * y * a
        return Pair(
            new(1.0f + sign * x * x * a, sign * b, -sign * x),
            new(b, sign + y * y * a, -y)
        )
    }
    fun limit(length: Double): DVec3 {
        val len = this.length()
        if (len > length) {
            return this.setLength(length)
        } else {
            return this
        }
    }

    fun withAxis(axis: Int, v: Double): DVec3 {
        return when (axis) {
            0 -> this.withX(v)
            1 -> this.withY(v)
            else -> this.withZ(v)
        }
    }

    override fun toString(): String {
        return "DVec3(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " +
                "z=${"%.5f".format(Locale.ENGLISH, z)}" +
                ")"
    }
}

operator fun Double.plus(vec: DVec3) = vec + this
operator fun Double.minus(vec: DVec3) = DVec3(this - vec.x, this - vec.y, this - vec.z)
operator fun Double.times(vec: DVec3) = vec * this
operator fun Double.div(vec: DVec3) = DVec3(this / vec.x, this / vec.y, this / vec.z)

fun dvec3(x: Double, y: Double, z: Double) = DVec3.new(x, y, z)
fun dvec3(x: Int, y: Int, z: Int) = DVec3.new(x, y, z)*/
