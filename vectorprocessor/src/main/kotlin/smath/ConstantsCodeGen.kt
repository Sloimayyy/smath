package com.sloimay.smath

import kotlin.math.roundToLong



fun genConstants(genContext: GenContext) {
    val (
        code,
        packageName,
        className,
        compType,
        dims,
        compNames
    ) = genContext

    fun numStrToTyped(numStr: String, type: String): String {
        val v = numStr.toDouble()
        val s = when (type) {
            "Float" -> "${v}f"
            "Double" -> "${v}"
            "Int" -> "${v.roundToLong()}"
            else -> "${v.roundToLong()}"
        }
        return s
    }


    code.append("// Constants\n")

    // Units
    for ((compIdx, compName) in compNames.withIndex()) {
        val constName = compName.uppercase()
        //code.append("val $className.Companion.$constName: $className\n")
        code.append("val $className.Companion.$constName: Vec4\n")
        //code.append("    get() = $className(${
        code.append("    get() = Vec4(${
            compNames.slice(0 until 4).mapIndexed { index, _ -> numStrToTyped(if (index != compIdx) "0" else "1", compType) }
                .joinToString(", ")
        })\n")
    }


}