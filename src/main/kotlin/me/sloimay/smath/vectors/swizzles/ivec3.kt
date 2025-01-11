package me.sloimay.smath.vectors.swizzles

import me.sloimay.smath.vectors.IVec2
import me.sloimay.smath.vectors.IVec3


val IVec3.xz get() = IVec2.new(x, z)