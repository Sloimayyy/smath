package me.sloimay.smath

import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min


fun Float.floor() = floor(this)
fun Double.floor() = floor(this)

fun Float.clamp(low: Float, high: Float) = max(min(this, high), low)
fun Int.clamp(low: Int, high: Int) = max(min(this, high), low)