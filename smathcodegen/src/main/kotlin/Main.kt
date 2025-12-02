package com.sloimay.smathcodegen

import com.sloimay.smathcodegen.vec.VecClassFileGen
import java.io.File

fun main() {
    emptySmathGenFolder()

    //val s = "package com.sloimay.smath.vectors\n\ndata class Vec5(val x: Float, val y: Float, val z: Float, val w: Float, val v: Float)"

    for (vecToGen in VECS_TO_GEN) {
        VecClassFileGen(vecToGen).make()
    }



    generateNumExtFile()




    /*val f = CodeFile(
        "Vec3.kt", "com.sloimay.smath.vectors", "vectors"
    )*/



    //f.writeToDisk()


    //f.writeText(s)

    // Give enough time for the file to be written so that smath can realise it exists
    Thread.sleep(1_000)
}

fun generateNumExtFile() {
    val numExtFolder = smathGenFolderPath().resolve("""numexts""").toFile()
    numExtFolder.mkdirs()
    val numExtFile = numExtFolder.resolve("NumExts.kt")

    var lines = mutableListOf(
        "package com.sloimay.smath.numexts",
        "",
        "import kotlin.math.floor",
        "import kotlin.math.round",
        "import kotlin.math.ceil",
        "import kotlin.math.min",
        "import kotlin.math.max",
        "import kotlin.math.abs",
        "",
        "import kotlin.math.sin",
        "import kotlin.math.cos",
        "import kotlin.math.tan",
        "import kotlin.math.asin",
        "import kotlin.math.acos",
        "import kotlin.math.atan",
        "import kotlin.math.sinh",
        "import kotlin.math.cosh",
        "import kotlin.math.tanh",
        "import kotlin.math.asinh",
        "import kotlin.math.acosh",
        "import kotlin.math.atanh",
        "",
        "import kotlin.math.sqrt",
        "import kotlin.math.cbrt",
        "import kotlin.math.ln",
        "import kotlin.math.ln1p",
        "import kotlin.math.log10",
        "import kotlin.math.log2",
        "import kotlin.math.exp",
        "import kotlin.math.expm1",
        "",
    )



    for (t in NUM_TYPES) {
        if (t.isFloat()) {
            lines += "fun ${t.name}.min(other: ${t.name}) = min(this, other)"
            lines += "fun ${t.name}.max(other: ${t.name}) = max(this, other)"
            lines += "fun ${t.name}.floor() = floor(this)"
            lines += "fun ${t.name}.ceil() = ceil(this)"
            lines += "fun ${t.name}.round() = round(this)"
            lines += "fun ${t.name}.clamp(low: ${t.name}, high: ${t.name}) = max(min(this, high), low)"
            lines += "fun ${t.name}.lerp(other: ${t.name}, t: ${t.name}) = this*(1f-t) + other*t"
        }

        if (!t.isUnsigned()) {
            if (t.bits < 32) {
                lines += "fun ${t.name}.abs() = abs(this.toInt()).to${t.name}()"
            } else {
                lines += "fun ${t.name}.abs() = abs(this)"
            }
        }

        run {
            val toType = if (t.bits < 32) {
                NUM_TYPES.find { it.name == "Float" }!!
            } else {
                NUM_TYPES.find { it.name == "Double" }!!
            }
            val conv = getNumConvData(t.name, toType.name)
            val convStr = conv.convStr
            val funcs = listOf(
                "sin", "cos", "tan", "asin", "acos", "atan",
                "sinh", "cosh", "tanh", "asinh", "acosh", "atanh",
                "sqrt", "cbrt",
                "ln", "ln1p", "log2", "log10", "exp", "expm1"
            )
            for (f in funcs) {
                lines += "fun ${t.name}.${f}() = ${f}(this${convStr})"
            }
        }


        lines += ""
    }

    val text = lines.joinToString("\n")

    numExtFile.writeText(text)
}