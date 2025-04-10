package com.sloimay.smath

import java.io.OutputStream


fun genOperators(genContext: GenContext) {

    val code = genContext.code
    val className = genContext.className
    val compNames = genContext.compNames
    val compType = genContext.compType


    code.append("// Operators\n")

    genBinaryOp(genContext, "plus", "+")
    genBinaryOp(genContext, "minus", "-")
    genBinaryOp(genContext, "times", "*")
    genBinaryOp(genContext, "div", "/")
    genBinaryOp(genContext, "rem", "%")

}






private fun genBinaryOp(genContext: GenContext, opName: String, opSymbol: String) {
    val code = genContext.code
    val className = genContext.className
    val compNames = genContext.compNames
    val compType = genContext.compType

    code
        .append("operator fun $className.$opName(other: $className): $className = $className(")
        .append(compNames.joinToString(", ") { "$it $opSymbol other.$it" })
        .append(")")

    code.append("\n")

    code
        .append("operator fun $className.$opName(other: $compType): $className = $className(")
        .append(compNames.joinToString(", ") { "$it $opSymbol other" })
        .append(")")

    code.append("\n")
}