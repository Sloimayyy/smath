package com.sloimay.smath.vectors.swizzles

import com.sloimay.smath.vectors.Vec2
import com.sloimay.smath.vectors.Vec3Ext
import com.sloimay.smath.vectors.Vec4


val Vec2.xx get() = Vec2.new(x, x)
val Vec2.xy get() = Vec2.new(x, y)
val Vec2.yx get() = Vec2.new(y, x)
val Vec2.yy get() = Vec2.new(y, y)
val Vec2.xxx get() = Vec3Ext.new(x, x, x)
val Vec2.xxy get() = Vec3Ext.new(x, x, y)
val Vec2.xyx get() = Vec3Ext.new(x, y, x)
val Vec2.xyy get() = Vec3Ext.new(x, y, y)
val Vec2.yxx get() = Vec3Ext.new(y, x, x)
val Vec2.yxy get() = Vec3Ext.new(y, x, y)
val Vec2.yyx get() = Vec3Ext.new(y, y, x)
val Vec2.yyy get() = Vec3Ext.new(y, y, y)
val Vec2.xxxx get() = Vec4.new(x, x, x, x)
val Vec2.xxxy get() = Vec4.new(x, x, x, y)
val Vec2.xxyx get() = Vec4.new(x, x, y, x)
val Vec2.xxyy get() = Vec4.new(x, x, y, y)
val Vec2.xyxx get() = Vec4.new(x, y, x, x)
val Vec2.xyxy get() = Vec4.new(x, y, x, y)
val Vec2.xyyx get() = Vec4.new(x, y, y, x)
val Vec2.xyyy get() = Vec4.new(x, y, y, y)
val Vec2.yxxx get() = Vec4.new(y, x, x, x)
val Vec2.yxxy get() = Vec4.new(y, x, x, y)
val Vec2.yxyx get() = Vec4.new(y, x, y, x)
val Vec2.yxyy get() = Vec4.new(y, x, y, y)
val Vec2.yyxx get() = Vec4.new(y, y, x, x)
val Vec2.yyxy get() = Vec4.new(y, y, x, y)
val Vec2.yyyx get() = Vec4.new(y, y, y, x)
val Vec2.yyyy get() = Vec4.new(y, y, y, y)