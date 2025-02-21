package com.sloimay.smath.vectors.swizzles

import com.sloimay.smath.vectors.IVec2
import com.sloimay.smath.vectors.IVec3

val IVec2.xx get() = IVec2.new(x, x)
val IVec2.xy get() = IVec2.new(x, y)
val IVec2.yx get() = IVec2.new(y, x)
val IVec2.yy get() = IVec2.new(y, y)
val IVec2.xxx get() = IVec3.new(x, x, x)
val IVec2.xxy get() = IVec3.new(x, x, y)
val IVec2.xyx get() = IVec3.new(x, y, x)
val IVec2.xyy get() = IVec3.new(x, y, y)
val IVec2.yxx get() = IVec3.new(y, x, x)
val IVec2.yxy get() = IVec3.new(y, x, y)
val IVec2.yyx get() = IVec3.new(y, y, x)
val IVec2.yyy get() = IVec3.new(y, y, y)