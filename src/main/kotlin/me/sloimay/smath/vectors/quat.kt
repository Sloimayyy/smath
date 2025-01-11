package me.sloimay.smath.vectors

import java.util.*
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


data class Quat(var x: Float, var y: Float, var z: Float, var w: Float) {

    companion object {
        val IDENTITY = new(0f, 0f, 0f, 1f)

        fun new(x: Float, y: Float, z: Float, w: Float) = Quat(x, y, z, w)
    }

    operator fun times(v: Float) = new(x * v, y * v, z * v, w * v)
    operator fun get(idx: UByte) =
        when (idx) {
            0.toUByte() -> this.x
            1.toUByte() -> this.y
            2.toUByte() -> this.z
            else -> this.w
        }
    operator fun get(idx: UShort) = this[idx.toUByte()]
    operator fun get(idx: Short) = this[idx.toUByte()]
    operator fun get(idx: UInt) = this[idx.toUByte()]
    operator fun get(idx: Int) = this[idx.toUByte()]
    operator fun get(idx: Long) = this[idx.toUByte()]
    operator fun get(idx: ULong) = this[idx.toUByte()]
    operator fun set(idx: UByte, v: Float) =
        when (idx) {
            0.toUByte() -> this.x = v
            1.toUByte() -> this.y = v
            2.toUByte() -> this.z = v
            else -> this.w = v
        }
    operator fun set(idx: UShort, v: Float) { this[idx.toUByte()] = v }
    operator fun set(idx: Short, v: Float) { this[idx.toUByte()] = v }
    operator fun set(idx: UInt, v: Float) { this[idx.toUByte()] = v }
    operator fun set(idx: Int, v: Float) { this[idx.toUByte()] = v }
    operator fun set(idx: Long, v: Float) { this[idx.toUByte()] = v }
    operator fun set(idx: ULong, v: Float) { this[idx.toUByte()] = v }

    fun equality(other: Quat) = this.x == other.x && this.y == other.y && this.z == other.z && this.w == other.w
    fun withX(v: Float) = new(v, this.y, this.z, this.w)
    fun withY(v: Float) = new(this.x, v, this.z, this.w)
    fun withZ(v: Float) = new(this.x, this.y, v, this.w)
    fun withW(v: Float) = new(this.x, this.y, this.z, v)
    fun lengthSqrd() = this.x*this.x + this.y*this.y + this.z*this.z + this.w*this.w
    fun length() = sqrt(lengthSqrd())
    fun normalize() = this * (1f / this.length())
    fun conjugate() = new(-x, -y, -z, w)

    fun mult(other: Quat): Quat {
        // Hamiltonian multiplication
        val ax = this.x; val ay = this.y; val az = this.z; val aw = this.w;
        val bx = other.x; val by = other.y; val bz = other.z; val bw = other.w;
        return Quat(
            ax * bw + aw * bx + ay * bz - az * by,
            ay * bw + aw * by + az * bx - ax * bz,
            az * bw + aw * bz + ax * by - ay * bx,
            aw * bw - ax * bx - ay * by - az * bz,
        )
    }

    fun fromAxisAngle(axis: Vec3, angle: Float): Quat {
        val axisNorm = axis.normalize()
        val sinA = sin(angle * 0.5f)
        return Quat(
            sinA * axisNorm.x,
            sinA * axisNorm.y,
            sinA * axisNorm.z,
            cos(angle * 0.5f)
        )
    }


    override fun toString(): String {
        return "Quat(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, z)}, " +
                "z=${"%.5f".format(Locale.ENGLISH, w)}" +
                ")"
    }
}