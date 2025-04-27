package com.sloimay.smath.vectors

import java.util.*


const val X_MASK_L: Long = 0x00_00_00_00_00_1F_FF_FF
const val Y_MASK_L: Long = X_MASK_L shl 21
const val Z_MASK_L: Long = X_MASK_L shl 42

const val TOP_21B_MASK: Int = (X_MASK_L shl 11).toInt()


/**
 * A class that was designed to be "quick" (QVec3 = Quick Vec3) by packing the 3 floats into
 * a single long, that's in a kotlin value class (so treated in every way as a long by the JVM).
 * You cannot pack 3 32bit numbers into a single 64bit number so the floats are much less precise,
 * which may not be a problem for certain computations. (The Q could also stand for "Quantized").
 * Unfortunately this was a failed attempt, revealing to be ~50% slower than the good ol' Vec3 class.
 * However, it may still be faster in some scenarios, so I'll leave it in (even if terribly incomplete)
 */
@JvmInline
value class QVec3 private constructor (val packed: Long) {

    val x get() = Float.fromBits( (packed and X_MASK_L).toInt() shl 11 )
    val y get() = Float.fromBits( ((packed and Y_MASK_L) ushr 21).toInt() shl 11 )
    val z get() = Float.fromBits( ((packed and Z_MASK_L) ushr 42).toInt() shl 11 )

    constructor(x: Float, y: Float, z: Float) : this(packIntoQVec3(x, y, z))

    operator fun plus(other: QVec3) = QVec3(x + other.x, y + other.y, z + other.z)
    operator fun times(other: QVec3) = QVec3(x * other.x, y * other.y, z * other.z)

    override fun toString(): String {
        return ("QVec3(" +
                "x=${"%.5f".format(Locale.ENGLISH, x)}, " +
                "y=${"%.5f".format(Locale.ENGLISH, y)}, " +
                "z=${"%.5f".format(Locale.ENGLISH, z)})")
    }

}


private fun packIntoQVec3(x: Float, y: Float, z: Float): Long {
    return (
            ((x.toRawBits() and TOP_21B_MASK) ushr 11).toLong() or
            ((y.toRawBits() and TOP_21B_MASK).toLong() shl 10) or
            ((z.toRawBits() and TOP_21B_MASK).toLong() shl 31)
    )
}