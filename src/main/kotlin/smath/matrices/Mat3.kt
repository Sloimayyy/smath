package com.sloimay.smath.matrices

import com.sloimay.smath.vectors.Vec3

class Mat3(values: FloatArray) {

    val values: FloatArray

    init {
        if (values.size != 3*3) {
            throw Exception()
        }
        this.values = values.copyOf()
    }

    constructor(xAxis: Vec3, yAxis: Vec3, zAxis: Vec3) : this(
        floatArrayOf(
            xAxis.x, xAxis.y, xAxis.z,
            yAxis.x, yAxis.y, yAxis.z,
            zAxis.x, zAxis.y, zAxis.z,
        )
    )

    companion object {
        val IDENTITY = Mat3(Vec3.X, Vec3.Y, Vec3.Z)

        fun fromLambda(lamb: (Int, Int) -> Float): Mat3 {
            return Mat3(
                floatArrayOf(
                    lamb(0, 0), lamb(0, 1), lamb(0, 2),
                    lamb(1, 0), lamb(1, 1), lamb(1, 2),
                    lamb(2, 0), lamb(2, 1), lamb(2, 2),
                )
            )
        }
    }

    operator fun get(axis: Int, coord: Int): Float = values[axis * 3 + coord]


    fun transpose(): Mat3 {
        return fromLambda { axis, coord -> this[coord, axis] }
    }

    fun mul(other: Mat3): Mat3 {
        return fromLambda { axis, coord ->
            var res = 0f
            for (i in 0 until 3) {
                res += this[i, coord] * other[axis, i]
            }
            res
        }
    }

    fun subMatrix(excludedCol: Int, excludedRow: Int): Mat2 {
        val cols = (0 until 3).map { it }.filter { it != excludedCol }
        val rows = (0 until 3).map { it }.filter { it != excludedRow }
        return Mat2.fromLambda { axis, coord -> this[cols[axis], rows[coord]] }
    }

    fun minor(axis: Int, coord: Int): Float {
        return subMatrix(axis, coord).det()
    }

    fun cofactor(axis: Int, coord: Int): Float {
        return cofactorSignOfCoord(axis, coord) * minor(axis, coord)
    }

    fun det(): Float {
        return (0 until 3).map { this[0, it] * cofactor(0, it) }.sum()
    }




    private fun cofactorSignOfCoord(axis: Int, coord: Int): Int {
        return -1 * (((axis + coord) % 2) * 2 - 1)
    }

}