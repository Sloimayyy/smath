package me.sloimay.smath.vectors.swizzles

import me.sloimay.smath.vectors.Vec2
import me.sloimay.smath.vectors.Vec3
import me.sloimay.smath.vectors.Vec4



val Vec3.xy get() = Vec2.new(x, y)