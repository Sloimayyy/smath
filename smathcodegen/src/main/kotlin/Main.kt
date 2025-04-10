package com.sloimay.smathcodegen

import com.sloimay.smathcodegen.codefile.CodeFile
import com.sloimay.smathcodegen.vecfile.VecFileGen

fun main() {
    emptySmathGenFolder()

    //val s = "package com.sloimay.smath.vectors\n\ndata class Vec5(val x: Float, val y: Float, val z: Float, val w: Float, val v: Float)"


    for (vecToGen in VECS_TO_GEN) {
        VecFileGen(vecToGen).make()
    }




    /*val f = CodeFile(
        "Vec3.kt", "com.sloimay.smath.vectors", "vectors"
    )*/



    //f.writeToDisk()


    //f.writeText(s)

    // Give enough time for the file to be written so that smath can realise it exists
    Thread.sleep(1_000)
}