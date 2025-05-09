package com.sloimay.smath

import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.max
import kotlin.math.min

fun remap(v: Float, vl: Float, vu: Float, ol: Float, ou: Float): Float {
    return (v - vl) * ((ou - ol) / (vu - vl)) + ol
}

fun remap(v: Double, vl: Double, vu: Double, ol: Double, ou: Double): Double {
    return (v - vl) * ((ou - ol) / (vu - vl)) + ol
}

fun clamp(v: Float, lo: Float, hi: Float) = max(min(v, hi), lo)
fun clamp(v: Double, lo: Double, hi: Double) = max(min(v, hi), lo)

fun safeAcos(v: Float): Float {
    // From JOML
    if (v < -1.0) {
        return PI.toFloat()
    } else if (v > 1.0) {
        return 0f
    } else {
        return acos(v)
    }
}



fun min(v1: Byte, v2: Byte) = min(v1.toInt(), v2.toInt()).toByte()
fun min(v1: UByte, v2: UByte) = min(v1.toUInt(), v2.toUInt()).toUByte()
fun min(v1: Short, v2: Short) = min(v1.toInt(), v2.toInt()).toShort()
fun min(v1: UShort, v2: UShort) = min(v1.toUInt(), v2.toUInt()).toUShort()
