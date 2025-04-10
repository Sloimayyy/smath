package com.sloimay.smath.matrices

import com.sloimay.smath.vectors.Quat
import com.sloimay.smath.vectors.Vec3
import com.sloimay.smath.vectors.Vec4
import com.sloimay.smath.vectors.Vec3Impl.Z
import com.sloimay.smath.vectors.swizzles.xyz

/**
 *
 * Row major
 *
 */
class Mat4(values: FloatArray) {

    val values: FloatArray

    init {
        if (values.size != 4*4) {
            throw Exception()
        }
        this.values = values.copyOf()
    }

    constructor(xAxis: Vec4, yAxis: Vec4, zAxis: Vec4, wAxis: Vec4) : this(
        floatArrayOf(
            xAxis.x, xAxis.y, xAxis.z, xAxis.w,
            yAxis.x, yAxis.y, yAxis.z, yAxis.w,
            zAxis.x, zAxis.y, zAxis.z, zAxis.w,
            wAxis.x, wAxis.y, wAxis.z, wAxis.w,
        )
    )

    companion object {
        val IDENTITY = Mat4(Vec4.X, Vec4.Y, Vec4.Z, Vec4.W)

        fun fromLambda(lamb: (Int, Int) -> Float): Mat4 {
            return Mat4(
                floatArrayOf(
                    lamb(0, 0), lamb(0, 1), lamb(0, 2), lamb(0, 3),
                    lamb(1, 0), lamb(1, 1), lamb(1, 2), lamb(1, 3),
                    lamb(2, 0), lamb(2, 1), lamb(2, 2), lamb(2, 3),
                    lamb(3, 0), lamb(3, 1), lamb(3, 2), lamb(3, 3),
                )
            )
        }

        /**
         * Impl from glam
         */
        private fun quatToAxes(rot: Quat): Triple<Vec4, Vec4, Vec4> {
            val (x, y, z, w) = rot

            val x2 = x + x
            val y2 = y + y
            val z2 = z + z
            val xx = x * x2
            val xy = x * y2
            val xz = x * z2
            val yy = y * y2
            val yz = y * z2
            val zz = z * z2
            val wx = w * x2
            val wy = w * y2
            val wz = w * z2
            val xAxis = Vec4(1.0f - (yy + zz), xy + wz, xz - wy, 0.0f)
            val yAxis = Vec4(xy - wz, 1.0f - (xx + zz), yz + wx, 0.0f)
            val zAxis = Vec4(xz + wy, yz - wx, 1.0f - (xx + yy), 0.0f)

            return Triple(xAxis, yAxis, zAxis)
        }

        fun fromQuat(quat: Quat): Mat4 {
            val (xAxis, yAxis, zAxis) = quatToAxes(quat)
            return Mat4(xAxis, yAxis, zAxis, Vec4.W)
        }

        fun fromScale(scale: Vec3): Mat4 {
            return Mat4(
                Vec4.X * scale.x,
                Vec4.Y * scale.y,
                Vec4.Z * scale.z,
                Vec4.W,
            )
        }

        fun fromTranslation(translation: Vec3): Mat4 {
            return Mat4(
                Vec4.X,
                Vec4.Y,
                Vec4.Z,
                Vec4(translation.x, translation.y, translation.z, 1f),
            )
        }

        fun fromAxisAngle(axis: Vec3, angle: Float): Mat4 {
            return fromQuat(Quat.fromAxisAngle(axis, angle))
        }

    }

    operator fun get(axis: Int, coord: Int): Float = values[axis * 4 + coord]


    fun timesVec4(vec4: Vec4): Vec4 {
        return Vec4.fromLambda {
            return@fromLambda (
                this[0, it] * vec4.x +
                this[1, it] * vec4.y +
                this[2, it] * vec4.z +
                this[3, it] * vec4.w
            )
        }
    }

    fun transformPoint(p: Vec3): Vec3 {
        return this.timesVec4(p.extend(1f)).xyz
    }

    fun transpose(): Mat4 {
        return fromLambda { axis, coord -> this[coord, axis] }
    }

    fun mul(other: Mat4): Mat4 {
        return fromLambda { axis, coord ->
            var res = 0f
            for (i in 0 until 4) {
                res += this[i, coord] * other[axis, i]
            }
            res
        }
    }

    fun scale(s: Float): Mat4 {
        return fromLambda { axis, coord -> this[axis, coord] * s }
    }

    fun subMatrix(excludedCol: Int, excludedRow: Int): Mat3 {
        val cols = (0 until 4).map { it }.filter { it != excludedCol }
        val rows = (0 until 4).map { it }.filter { it != excludedRow }
        return Mat3.fromLambda { axis, coord -> this[cols[axis], rows[coord]] }
    }

    fun minor(axis: Int, coord: Int): Float {
        return subMatrix(axis, coord).det()
    }

    fun cofactor(axis: Int, coord: Int): Float {
        return cofactorSignOfCoord(axis, coord) * minor(axis, coord)
    }

    fun det(): Float {
        return (0 until 4).map { this[0, it] * cofactor(0, it) }.sum()
    }

    fun cofactorMatrix(): Mat4 {
        return fromLambda { axis, coord -> cofactor(axis, coord) }
    }

    fun inverse(): Mat4 {
        return cofactorMatrix().transpose().scale(1f / this.det())
    }



    private fun cofactorSignOfCoord(axis: Int, coord: Int): Int {
        return -1 * (((axis + coord) % 2) * 2 - 1)
    }
}