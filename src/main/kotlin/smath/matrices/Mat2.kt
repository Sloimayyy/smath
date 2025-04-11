package com.sloimay.smath.matrices

import com.sloimay.smath.vectors.Vec2

class Mat2(values: FloatArray) {

    val values: FloatArray

    init {
        if (values.size != 2*2) {
            throw Exception()
        }
        this.values = values.copyOf()
    }

    constructor(xAxis: Vec2, yAxis: Vec2) : this(
        floatArrayOf(
            xAxis.x, xAxis.y,
            yAxis.x, yAxis.y,
        )
    )

    companion object {
        val IDENTITY = Mat2(Vec2.X, Vec2.Y)

        fun fromLambda(lamb: (Int, Int) -> Float): Mat2 {
            return Mat2(
                floatArrayOf(
                    lamb(0, 0), lamb(0, 1),
                    lamb(1, 0), lamb(1, 1),
                )
            )
        }
    }

    operator fun get(axis: Int, coord: Int): Float = values[axis * 2 + coord]



    fun transpose(): Mat2 {
        return fromLambda { axis, coord -> this[coord, axis] }
    }

    fun mul(other: Mat2): Mat2 {
        return fromLambda { axis, coord ->
            var res = 0f
            for (i in 0 until 2) {
                res += this[i, coord] * other[axis, i]
            }
            res
        }
    }

    fun det(): Float {
        return this[0, 0] * this[1, 1] - this[1, 0] * this[0, 1]
    }

}