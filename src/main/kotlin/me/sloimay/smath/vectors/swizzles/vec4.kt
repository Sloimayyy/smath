package me.sloimay.smath.vectors.swizzles

import me.sloimay.smath.vectors.Vec2
import me.sloimay.smath.vectors.Vec3
import me.sloimay.smath.vectors.Vec4


val Vec4.xx get() = Vec2.new(x, x)
val Vec4.xy get() = Vec2.new(x, y)
val Vec4.xz get() = Vec2.new(x, z)
val Vec4.xw get() = Vec2.new(x, w)
val Vec4.yx get() = Vec2.new(y, x)
val Vec4.yy get() = Vec2.new(y, y)
val Vec4.yz get() = Vec2.new(y, z)
val Vec4.yw get() = Vec2.new(y, w)
val Vec4.zx get() = Vec2.new(z, x)
val Vec4.zy get() = Vec2.new(z, y)
val Vec4.zz get() = Vec2.new(z, z)
val Vec4.zw get() = Vec2.new(z, w)
val Vec4.wx get() = Vec2.new(w, x)
val Vec4.wy get() = Vec2.new(w, y)
val Vec4.wz get() = Vec2.new(w, z)
val Vec4.ww get() = Vec2.new(w, w)
val Vec4.xxx get() = Vec3.new(x, x, x)
val Vec4.xxy get() = Vec3.new(x, x, y)
val Vec4.xxz get() = Vec3.new(x, x, z)
val Vec4.xxw get() = Vec3.new(x, x, w)
val Vec4.xyx get() = Vec3.new(x, y, x)
val Vec4.xyy get() = Vec3.new(x, y, y)
val Vec4.xyz get() = Vec3.new(x, y, z)
val Vec4.xyw get() = Vec3.new(x, y, w)
val Vec4.xzx get() = Vec3.new(x, z, x)
val Vec4.xzy get() = Vec3.new(x, z, y)
val Vec4.xzz get() = Vec3.new(x, z, z)
val Vec4.xzw get() = Vec3.new(x, z, w)
val Vec4.xwx get() = Vec3.new(x, w, x)
val Vec4.xwy get() = Vec3.new(x, w, y)
val Vec4.xwz get() = Vec3.new(x, w, z)
val Vec4.xww get() = Vec3.new(x, w, w)
val Vec4.yxx get() = Vec3.new(y, x, x)
val Vec4.yxy get() = Vec3.new(y, x, y)
val Vec4.yxz get() = Vec3.new(y, x, z)
val Vec4.yxw get() = Vec3.new(y, x, w)
val Vec4.yyx get() = Vec3.new(y, y, x)
val Vec4.yyy get() = Vec3.new(y, y, y)
val Vec4.yyz get() = Vec3.new(y, y, z)
val Vec4.yyw get() = Vec3.new(y, y, w)
val Vec4.yzx get() = Vec3.new(y, z, x)
val Vec4.yzy get() = Vec3.new(y, z, y)
val Vec4.yzz get() = Vec3.new(y, z, z)
val Vec4.yzw get() = Vec3.new(y, z, w)
val Vec4.ywx get() = Vec3.new(y, w, x)
val Vec4.ywy get() = Vec3.new(y, w, y)
val Vec4.ywz get() = Vec3.new(y, w, z)
val Vec4.yww get() = Vec3.new(y, w, w)
val Vec4.zxx get() = Vec3.new(z, x, x)
val Vec4.zxy get() = Vec3.new(z, x, y)
val Vec4.zxz get() = Vec3.new(z, x, z)
val Vec4.zxw get() = Vec3.new(z, x, w)
val Vec4.zyx get() = Vec3.new(z, y, x)
val Vec4.zyy get() = Vec3.new(z, y, y)
val Vec4.zyz get() = Vec3.new(z, y, z)
val Vec4.zyw get() = Vec3.new(z, y, w)
val Vec4.zzx get() = Vec3.new(z, z, x)
val Vec4.zzy get() = Vec3.new(z, z, y)
val Vec4.zzz get() = Vec3.new(z, z, z)
val Vec4.zzw get() = Vec3.new(z, z, w)
val Vec4.zwx get() = Vec3.new(z, w, x)
val Vec4.zwy get() = Vec3.new(z, w, y)
val Vec4.zwz get() = Vec3.new(z, w, z)
val Vec4.zww get() = Vec3.new(z, w, w)
val Vec4.wxx get() = Vec3.new(w, x, x)
val Vec4.wxy get() = Vec3.new(w, x, y)
val Vec4.wxz get() = Vec3.new(w, x, z)
val Vec4.wxw get() = Vec3.new(w, x, w)
val Vec4.wyx get() = Vec3.new(w, y, x)
val Vec4.wyy get() = Vec3.new(w, y, y)
val Vec4.wyz get() = Vec3.new(w, y, z)
val Vec4.wyw get() = Vec3.new(w, y, w)
val Vec4.wzx get() = Vec3.new(w, z, x)
val Vec4.wzy get() = Vec3.new(w, z, y)
val Vec4.wzz get() = Vec3.new(w, z, z)
val Vec4.wzw get() = Vec3.new(w, z, w)
val Vec4.wwx get() = Vec3.new(w, w, x)
val Vec4.wwy get() = Vec3.new(w, w, y)
val Vec4.wwz get() = Vec3.new(w, w, z)
val Vec4.www get() = Vec3.new(w, w, w)
val Vec4.xxxx get() = Vec4.new(x, x, x, x)
val Vec4.xxxy get() = Vec4.new(x, x, x, y)
val Vec4.xxxz get() = Vec4.new(x, x, x, z)
val Vec4.xxxw get() = Vec4.new(x, x, x, w)
val Vec4.xxyx get() = Vec4.new(x, x, y, x)
val Vec4.xxyy get() = Vec4.new(x, x, y, y)
val Vec4.xxyz get() = Vec4.new(x, x, y, z)
val Vec4.xxyw get() = Vec4.new(x, x, y, w)
val Vec4.xxzx get() = Vec4.new(x, x, z, x)
val Vec4.xxzy get() = Vec4.new(x, x, z, y)
val Vec4.xxzz get() = Vec4.new(x, x, z, z)
val Vec4.xxzw get() = Vec4.new(x, x, z, w)
val Vec4.xxwx get() = Vec4.new(x, x, w, x)
val Vec4.xxwy get() = Vec4.new(x, x, w, y)
val Vec4.xxwz get() = Vec4.new(x, x, w, z)
val Vec4.xxww get() = Vec4.new(x, x, w, w)
val Vec4.xyxx get() = Vec4.new(x, y, x, x)
val Vec4.xyxy get() = Vec4.new(x, y, x, y)
val Vec4.xyxz get() = Vec4.new(x, y, x, z)
val Vec4.xyxw get() = Vec4.new(x, y, x, w)
val Vec4.xyyx get() = Vec4.new(x, y, y, x)
val Vec4.xyyy get() = Vec4.new(x, y, y, y)
val Vec4.xyyz get() = Vec4.new(x, y, y, z)
val Vec4.xyyw get() = Vec4.new(x, y, y, w)
val Vec4.xyzx get() = Vec4.new(x, y, z, x)
val Vec4.xyzy get() = Vec4.new(x, y, z, y)
val Vec4.xyzz get() = Vec4.new(x, y, z, z)
val Vec4.xyzw get() = Vec4.new(x, y, z, w)
val Vec4.xywx get() = Vec4.new(x, y, w, x)
val Vec4.xywy get() = Vec4.new(x, y, w, y)
val Vec4.xywz get() = Vec4.new(x, y, w, z)
val Vec4.xyww get() = Vec4.new(x, y, w, w)
val Vec4.xzxx get() = Vec4.new(x, z, x, x)
val Vec4.xzxy get() = Vec4.new(x, z, x, y)
val Vec4.xzxz get() = Vec4.new(x, z, x, z)
val Vec4.xzxw get() = Vec4.new(x, z, x, w)
val Vec4.xzyx get() = Vec4.new(x, z, y, x)
val Vec4.xzyy get() = Vec4.new(x, z, y, y)
val Vec4.xzyz get() = Vec4.new(x, z, y, z)
val Vec4.xzyw get() = Vec4.new(x, z, y, w)
val Vec4.xzzx get() = Vec4.new(x, z, z, x)
val Vec4.xzzy get() = Vec4.new(x, z, z, y)
val Vec4.xzzz get() = Vec4.new(x, z, z, z)
val Vec4.xzzw get() = Vec4.new(x, z, z, w)
val Vec4.xzwx get() = Vec4.new(x, z, w, x)
val Vec4.xzwy get() = Vec4.new(x, z, w, y)
val Vec4.xzwz get() = Vec4.new(x, z, w, z)
val Vec4.xzww get() = Vec4.new(x, z, w, w)
val Vec4.xwxx get() = Vec4.new(x, w, x, x)
val Vec4.xwxy get() = Vec4.new(x, w, x, y)
val Vec4.xwxz get() = Vec4.new(x, w, x, z)
val Vec4.xwxw get() = Vec4.new(x, w, x, w)
val Vec4.xwyx get() = Vec4.new(x, w, y, x)
val Vec4.xwyy get() = Vec4.new(x, w, y, y)
val Vec4.xwyz get() = Vec4.new(x, w, y, z)
val Vec4.xwyw get() = Vec4.new(x, w, y, w)
val Vec4.xwzx get() = Vec4.new(x, w, z, x)
val Vec4.xwzy get() = Vec4.new(x, w, z, y)
val Vec4.xwzz get() = Vec4.new(x, w, z, z)
val Vec4.xwzw get() = Vec4.new(x, w, z, w)
val Vec4.xwwx get() = Vec4.new(x, w, w, x)
val Vec4.xwwy get() = Vec4.new(x, w, w, y)
val Vec4.xwwz get() = Vec4.new(x, w, w, z)
val Vec4.xwww get() = Vec4.new(x, w, w, w)
val Vec4.yxxx get() = Vec4.new(y, x, x, x)
val Vec4.yxxy get() = Vec4.new(y, x, x, y)
val Vec4.yxxz get() = Vec4.new(y, x, x, z)
val Vec4.yxxw get() = Vec4.new(y, x, x, w)
val Vec4.yxyx get() = Vec4.new(y, x, y, x)
val Vec4.yxyy get() = Vec4.new(y, x, y, y)
val Vec4.yxyz get() = Vec4.new(y, x, y, z)
val Vec4.yxyw get() = Vec4.new(y, x, y, w)
val Vec4.yxzx get() = Vec4.new(y, x, z, x)
val Vec4.yxzy get() = Vec4.new(y, x, z, y)
val Vec4.yxzz get() = Vec4.new(y, x, z, z)
val Vec4.yxzw get() = Vec4.new(y, x, z, w)
val Vec4.yxwx get() = Vec4.new(y, x, w, x)
val Vec4.yxwy get() = Vec4.new(y, x, w, y)
val Vec4.yxwz get() = Vec4.new(y, x, w, z)
val Vec4.yxww get() = Vec4.new(y, x, w, w)
val Vec4.yyxx get() = Vec4.new(y, y, x, x)
val Vec4.yyxy get() = Vec4.new(y, y, x, y)
val Vec4.yyxz get() = Vec4.new(y, y, x, z)
val Vec4.yyxw get() = Vec4.new(y, y, x, w)
val Vec4.yyyx get() = Vec4.new(y, y, y, x)
val Vec4.yyyy get() = Vec4.new(y, y, y, y)
val Vec4.yyyz get() = Vec4.new(y, y, y, z)
val Vec4.yyyw get() = Vec4.new(y, y, y, w)
val Vec4.yyzx get() = Vec4.new(y, y, z, x)
val Vec4.yyzy get() = Vec4.new(y, y, z, y)
val Vec4.yyzz get() = Vec4.new(y, y, z, z)
val Vec4.yyzw get() = Vec4.new(y, y, z, w)
val Vec4.yywx get() = Vec4.new(y, y, w, x)
val Vec4.yywy get() = Vec4.new(y, y, w, y)
val Vec4.yywz get() = Vec4.new(y, y, w, z)
val Vec4.yyww get() = Vec4.new(y, y, w, w)
val Vec4.yzxx get() = Vec4.new(y, z, x, x)
val Vec4.yzxy get() = Vec4.new(y, z, x, y)
val Vec4.yzxz get() = Vec4.new(y, z, x, z)
val Vec4.yzxw get() = Vec4.new(y, z, x, w)
val Vec4.yzyx get() = Vec4.new(y, z, y, x)
val Vec4.yzyy get() = Vec4.new(y, z, y, y)
val Vec4.yzyz get() = Vec4.new(y, z, y, z)
val Vec4.yzyw get() = Vec4.new(y, z, y, w)
val Vec4.yzzx get() = Vec4.new(y, z, z, x)
val Vec4.yzzy get() = Vec4.new(y, z, z, y)
val Vec4.yzzz get() = Vec4.new(y, z, z, z)
val Vec4.yzzw get() = Vec4.new(y, z, z, w)
val Vec4.yzwx get() = Vec4.new(y, z, w, x)
val Vec4.yzwy get() = Vec4.new(y, z, w, y)
val Vec4.yzwz get() = Vec4.new(y, z, w, z)
val Vec4.yzww get() = Vec4.new(y, z, w, w)
val Vec4.ywxx get() = Vec4.new(y, w, x, x)
val Vec4.ywxy get() = Vec4.new(y, w, x, y)
val Vec4.ywxz get() = Vec4.new(y, w, x, z)
val Vec4.ywxw get() = Vec4.new(y, w, x, w)
val Vec4.ywyx get() = Vec4.new(y, w, y, x)
val Vec4.ywyy get() = Vec4.new(y, w, y, y)
val Vec4.ywyz get() = Vec4.new(y, w, y, z)
val Vec4.ywyw get() = Vec4.new(y, w, y, w)
val Vec4.ywzx get() = Vec4.new(y, w, z, x)
val Vec4.ywzy get() = Vec4.new(y, w, z, y)
val Vec4.ywzz get() = Vec4.new(y, w, z, z)
val Vec4.ywzw get() = Vec4.new(y, w, z, w)
val Vec4.ywwx get() = Vec4.new(y, w, w, x)
val Vec4.ywwy get() = Vec4.new(y, w, w, y)
val Vec4.ywwz get() = Vec4.new(y, w, w, z)
val Vec4.ywww get() = Vec4.new(y, w, w, w)
val Vec4.zxxx get() = Vec4.new(z, x, x, x)
val Vec4.zxxy get() = Vec4.new(z, x, x, y)
val Vec4.zxxz get() = Vec4.new(z, x, x, z)
val Vec4.zxxw get() = Vec4.new(z, x, x, w)
val Vec4.zxyx get() = Vec4.new(z, x, y, x)
val Vec4.zxyy get() = Vec4.new(z, x, y, y)
val Vec4.zxyz get() = Vec4.new(z, x, y, z)
val Vec4.zxyw get() = Vec4.new(z, x, y, w)
val Vec4.zxzx get() = Vec4.new(z, x, z, x)
val Vec4.zxzy get() = Vec4.new(z, x, z, y)
val Vec4.zxzz get() = Vec4.new(z, x, z, z)
val Vec4.zxzw get() = Vec4.new(z, x, z, w)
val Vec4.zxwx get() = Vec4.new(z, x, w, x)
val Vec4.zxwy get() = Vec4.new(z, x, w, y)
val Vec4.zxwz get() = Vec4.new(z, x, w, z)
val Vec4.zxww get() = Vec4.new(z, x, w, w)
val Vec4.zyxx get() = Vec4.new(z, y, x, x)
val Vec4.zyxy get() = Vec4.new(z, y, x, y)
val Vec4.zyxz get() = Vec4.new(z, y, x, z)
val Vec4.zyxw get() = Vec4.new(z, y, x, w)
val Vec4.zyyx get() = Vec4.new(z, y, y, x)
val Vec4.zyyy get() = Vec4.new(z, y, y, y)
val Vec4.zyyz get() = Vec4.new(z, y, y, z)
val Vec4.zyyw get() = Vec4.new(z, y, y, w)
val Vec4.zyzx get() = Vec4.new(z, y, z, x)
val Vec4.zyzy get() = Vec4.new(z, y, z, y)
val Vec4.zyzz get() = Vec4.new(z, y, z, z)
val Vec4.zyzw get() = Vec4.new(z, y, z, w)
val Vec4.zywx get() = Vec4.new(z, y, w, x)
val Vec4.zywy get() = Vec4.new(z, y, w, y)
val Vec4.zywz get() = Vec4.new(z, y, w, z)
val Vec4.zyww get() = Vec4.new(z, y, w, w)
val Vec4.zzxx get() = Vec4.new(z, z, x, x)
val Vec4.zzxy get() = Vec4.new(z, z, x, y)
val Vec4.zzxz get() = Vec4.new(z, z, x, z)
val Vec4.zzxw get() = Vec4.new(z, z, x, w)
val Vec4.zzyx get() = Vec4.new(z, z, y, x)
val Vec4.zzyy get() = Vec4.new(z, z, y, y)
val Vec4.zzyz get() = Vec4.new(z, z, y, z)
val Vec4.zzyw get() = Vec4.new(z, z, y, w)
val Vec4.zzzx get() = Vec4.new(z, z, z, x)
val Vec4.zzzy get() = Vec4.new(z, z, z, y)
val Vec4.zzzz get() = Vec4.new(z, z, z, z)
val Vec4.zzzw get() = Vec4.new(z, z, z, w)
val Vec4.zzwx get() = Vec4.new(z, z, w, x)
val Vec4.zzwy get() = Vec4.new(z, z, w, y)
val Vec4.zzwz get() = Vec4.new(z, z, w, z)
val Vec4.zzww get() = Vec4.new(z, z, w, w)
val Vec4.zwxx get() = Vec4.new(z, w, x, x)
val Vec4.zwxy get() = Vec4.new(z, w, x, y)
val Vec4.zwxz get() = Vec4.new(z, w, x, z)
val Vec4.zwxw get() = Vec4.new(z, w, x, w)
val Vec4.zwyx get() = Vec4.new(z, w, y, x)
val Vec4.zwyy get() = Vec4.new(z, w, y, y)
val Vec4.zwyz get() = Vec4.new(z, w, y, z)
val Vec4.zwyw get() = Vec4.new(z, w, y, w)
val Vec4.zwzx get() = Vec4.new(z, w, z, x)
val Vec4.zwzy get() = Vec4.new(z, w, z, y)
val Vec4.zwzz get() = Vec4.new(z, w, z, z)
val Vec4.zwzw get() = Vec4.new(z, w, z, w)
val Vec4.zwwx get() = Vec4.new(z, w, w, x)
val Vec4.zwwy get() = Vec4.new(z, w, w, y)
val Vec4.zwwz get() = Vec4.new(z, w, w, z)
val Vec4.zwww get() = Vec4.new(z, w, w, w)
val Vec4.wxxx get() = Vec4.new(w, x, x, x)
val Vec4.wxxy get() = Vec4.new(w, x, x, y)
val Vec4.wxxz get() = Vec4.new(w, x, x, z)
val Vec4.wxxw get() = Vec4.new(w, x, x, w)
val Vec4.wxyx get() = Vec4.new(w, x, y, x)
val Vec4.wxyy get() = Vec4.new(w, x, y, y)
val Vec4.wxyz get() = Vec4.new(w, x, y, z)
val Vec4.wxyw get() = Vec4.new(w, x, y, w)
val Vec4.wxzx get() = Vec4.new(w, x, z, x)
val Vec4.wxzy get() = Vec4.new(w, x, z, y)
val Vec4.wxzz get() = Vec4.new(w, x, z, z)
val Vec4.wxzw get() = Vec4.new(w, x, z, w)
val Vec4.wxwx get() = Vec4.new(w, x, w, x)
val Vec4.wxwy get() = Vec4.new(w, x, w, y)
val Vec4.wxwz get() = Vec4.new(w, x, w, z)
val Vec4.wxww get() = Vec4.new(w, x, w, w)
val Vec4.wyxx get() = Vec4.new(w, y, x, x)
val Vec4.wyxy get() = Vec4.new(w, y, x, y)
val Vec4.wyxz get() = Vec4.new(w, y, x, z)
val Vec4.wyxw get() = Vec4.new(w, y, x, w)
val Vec4.wyyx get() = Vec4.new(w, y, y, x)
val Vec4.wyyy get() = Vec4.new(w, y, y, y)
val Vec4.wyyz get() = Vec4.new(w, y, y, z)
val Vec4.wyyw get() = Vec4.new(w, y, y, w)
val Vec4.wyzx get() = Vec4.new(w, y, z, x)
val Vec4.wyzy get() = Vec4.new(w, y, z, y)
val Vec4.wyzz get() = Vec4.new(w, y, z, z)
val Vec4.wyzw get() = Vec4.new(w, y, z, w)
val Vec4.wywx get() = Vec4.new(w, y, w, x)
val Vec4.wywy get() = Vec4.new(w, y, w, y)
val Vec4.wywz get() = Vec4.new(w, y, w, z)
val Vec4.wyww get() = Vec4.new(w, y, w, w)
val Vec4.wzxx get() = Vec4.new(w, z, x, x)
val Vec4.wzxy get() = Vec4.new(w, z, x, y)
val Vec4.wzxz get() = Vec4.new(w, z, x, z)
val Vec4.wzxw get() = Vec4.new(w, z, x, w)
val Vec4.wzyx get() = Vec4.new(w, z, y, x)
val Vec4.wzyy get() = Vec4.new(w, z, y, y)
val Vec4.wzyz get() = Vec4.new(w, z, y, z)
val Vec4.wzyw get() = Vec4.new(w, z, y, w)
val Vec4.wzzx get() = Vec4.new(w, z, z, x)
val Vec4.wzzy get() = Vec4.new(w, z, z, y)
val Vec4.wzzz get() = Vec4.new(w, z, z, z)
val Vec4.wzzw get() = Vec4.new(w, z, z, w)
val Vec4.wzwx get() = Vec4.new(w, z, w, x)
val Vec4.wzwy get() = Vec4.new(w, z, w, y)
val Vec4.wzwz get() = Vec4.new(w, z, w, z)
val Vec4.wzww get() = Vec4.new(w, z, w, w)
val Vec4.wwxx get() = Vec4.new(w, w, x, x)
val Vec4.wwxy get() = Vec4.new(w, w, x, y)
val Vec4.wwxz get() = Vec4.new(w, w, x, z)
val Vec4.wwxw get() = Vec4.new(w, w, x, w)
val Vec4.wwyx get() = Vec4.new(w, w, y, x)
val Vec4.wwyy get() = Vec4.new(w, w, y, y)
val Vec4.wwyz get() = Vec4.new(w, w, y, z)
val Vec4.wwyw get() = Vec4.new(w, w, y, w)
val Vec4.wwzx get() = Vec4.new(w, w, z, x)
val Vec4.wwzy get() = Vec4.new(w, w, z, y)
val Vec4.wwzz get() = Vec4.new(w, w, z, z)
val Vec4.wwzw get() = Vec4.new(w, w, z, w)
val Vec4.wwwx get() = Vec4.new(w, w, w, x)
val Vec4.wwwy get() = Vec4.new(w, w, w, y)
val Vec4.wwwz get() = Vec4.new(w, w, w, z)
val Vec4.wwww get() = Vec4.new(w, w, w, w)