package com.sloimay

import com.sloimay.smath.matrices.Mat3
import com.sloimay.smath.matrices.Mat4
import com.sloimay.smath.vectors.Quat
import com.sloimay.smath.vectors.Vec3
import com.sloimay.smath.vectors.Vec4
import java.math.BigDecimal
import kotlin.math.PI
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource


@OptIn(ExperimentalTime::class)
fun main() {

    println(Vec4(0f, 1f, 2f, 3f))



    // -134176
    println(
        BigDecimal(
        Mat4(
            floatArrayOf(
                2f, 9f, 3f, 33f,
                3f, 35f, 34f, 7f,
                1f, 1f, 96f, 17f,
                6f, 32f, 73f, 54f,
            )
        ).det().toDouble())
    )


    val ts = TimeSource.Monotonic
    val start = ts.markNow()

    var sum = 0f

    for (i in 0 until 1) {
        val mat = Mat4(
            floatArrayOf(
                2f, 9f, 3f, 33f,
                3f, i.toFloat() * 0.01f, 34f, 7f,
                1f, 1f, i.toFloat() * 0.01f, 17f,
                6f, 32f, 73f, 54f,
            )
        )
        sum += mat.det()
    }

    println("${start.elapsedNow()} ${sum}")




    val mat = Mat4(
        floatArrayOf(
            2f, 9f, 3f, 33f,
            3f, 35f, 34f, 7f,
            1f, 1f, 96f, 17f,
            6f, 32.98753785398f, 73f, 54f,
        )
    ).scale(20000000f)

    val inverseMat = mat.inverse()

    println(mat.mul(inverseMat).values.toList())




    val myPoint = Vec3(1f, 1f, 1f)

    val trans = Mat4.fromTranslation(Vec3(2.97853f, 2f, 2f))
    val rot = Mat4.fromQuat(Quat.fromAxisAngle(Vec3.Y, (PI/3).toFloat()))
    val myTransform = trans.mul(rot)

    println(myTransform.inverse().transformPoint(myTransform.transformPoint(myPoint)))

}